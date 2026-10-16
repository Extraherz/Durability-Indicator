
package de.extraherz.durabilityindicator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
