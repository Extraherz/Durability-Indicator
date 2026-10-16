
package de.extraherz.durabilityindicator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DurabilityCoreTest {

    @Test
    void calculatesRemainingDurability() {
        assertEquals(1561, DurabilityCalculator.remaining(1561, 0));
        assertEquals(1549, DurabilityCalculator.remaining(1561, 12));
        assertEquals(1, DurabilityCalculator.remaining(1561, 1560));
        assertEquals(0, DurabilityCalculator.remaining(1561, 1561));
        assertEquals(0, DurabilityCalculator.remaining(0, 0));
    }

    @Test
    void clampsInvalidDamageValues() {
        assertEquals(1561, DurabilityCalculator.remaining(1561, -5));
        assertEquals(0, DurabilityCalculator.remaining(1561, 2000));
    }

    @Test
    void calculatesDurabilityPercentage() {
        assertEquals(100, DurabilityCalculator.percentage(100, 0));
        assertEquals(75, DurabilityCalculator.percentage(100, 25));
        assertEquals(50, DurabilityCalculator.percentage(100, 50));
        assertEquals(25, DurabilityCalculator.percentage(100, 75));
        assertEquals(5, DurabilityCalculator.percentage(100, 95));
        assertEquals(0, DurabilityCalculator.percentage(100, 100));
    }

    @Test
    void formatsCompactDurability() {
        assertEquals("1,6k", DurabilityFormatter.formatCompact(1561));
        assertEquals("1,5k", DurabilityFormatter.formatCompact(1549));
        assertEquals("1,6k", DurabilityFormatter.formatCompact(1550));
        assertEquals("1k", DurabilityFormatter.formatCompact(1000));
        assertEquals("999", DurabilityFormatter.formatCompact(999));
        assertEquals("75", DurabilityFormatter.formatCompact(75));
        assertEquals("0", DurabilityFormatter.formatCompact(0));
        assertEquals("1m", DurabilityFormatter.formatCompact(999999));
    }

    @Test
    void formatsExactDurability() {
        assertEquals("1561", DurabilityFormatter.formatExact(1561));
        assertEquals("1549", DurabilityFormatter.formatExact(1549));
        assertEquals("0", DurabilityFormatter.formatExact(-1));
    }

    @Test
    void selectsCorrectDurabilityTier() {
        assertEquals(DurabilityTier.GREEN, DurabilityTier.fromPercentage(100));
        assertEquals(DurabilityTier.GREEN, DurabilityTier.fromPercentage(75));

        assertEquals(DurabilityTier.ORANGE, DurabilityTier.fromPercentage(74));
        assertEquals(DurabilityTier.ORANGE, DurabilityTier.fromPercentage(50));

        assertEquals(DurabilityTier.YELLOW, DurabilityTier.fromPercentage(49));
        assertEquals(DurabilityTier.YELLOW, DurabilityTier.fromPercentage(25));

        assertEquals(DurabilityTier.RED, DurabilityTier.fromPercentage(24));
        assertEquals(DurabilityTier.RED, DurabilityTier.fromPercentage(5));

        assertEquals(DurabilityTier.DARK_RED, DurabilityTier.fromPercentage(4));
        assertEquals(DurabilityTier.DARK_RED, DurabilityTier.fromPercentage(0));
    }

    @Test
    void checksCreativeModeVisibility() {
        // Survival: always show durability
        assertEquals(true, DurabilityVisibility.shouldDisplay(
                1561, 0, false, false
        ));

        assertEquals(true, DurabilityVisibility.shouldDisplay(
                1561, 10, false, false
        ));

        // Creative: hide undamaged items by default
        assertEquals(false, DurabilityVisibility.shouldDisplay(
                1561, 0, true, false
        ));

        // Creative: damaged items remain visible
        assertEquals(true, DurabilityVisibility.shouldDisplay(
                1561, 10, true, false
        ));

        // Creative: config override allows undamaged items
        assertEquals(true, DurabilityVisibility.shouldDisplay(
                1561, 0, true, true
        ));

        // Items without durability never show an overlay
        assertEquals(false, DurabilityVisibility.shouldDisplay(
                0, 0, false, true
        ));
    }

    @Test
    void calculatesDefaultOverlayPosition() {
        assertEquals(
                13.0f,
                DurabilityOverlayLayout.getDefaultYOffset(true)
        );

        assertEquals(
                15.0f,
                DurabilityOverlayLayout.getDefaultYOffset(false)
        );

        assertEquals(
                8.5f,
                DurabilityOverlayLayout.DEFAULT_X_OFFSET
        );
    }

    @Test
    void createsOverlayData() {
        DurabilityColorPalette palette = DurabilityColorPalette.DEFAULT;

        DurabilityOverlayData damaged = DurabilityOverlayData.create(
                1561, 12, false, true, false, true, palette
        );

        assertNotNull(damaged);
        assertEquals("1,5k", damaged.text());
        assertEquals(8.5f, damaged.xOffset());
        assertEquals(13.0f, damaged.yOffset());
        assertEquals(0xFF55FF55, damaged.color());

        DurabilityOverlayData undamaged = DurabilityOverlayData.create(
                1561, 0, false, false, false, true, palette
        );

        assertNotNull(undamaged);
        assertEquals("1,6k", undamaged.text());
        assertEquals(15.0f, undamaged.yOffset());

        DurabilityOverlayData exact = DurabilityOverlayData.create(
                1561, 0, false, false, false, false, palette
        );

        assertNotNull(exact);
        assertEquals("1561", exact.text());

        assertNull(DurabilityOverlayData.create(
                1561, 0, true, false, false, true, palette
        ));

        assertNotNull(DurabilityOverlayData.create(
                1561, 12, true, true, false, true, palette
        ));
    }
}
