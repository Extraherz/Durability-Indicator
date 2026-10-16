
package de.extraherz.durabilityindicator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DurabilityColorPaletteTest {

    @Test
    void usesDefaultColorsAtBoundaries() {
        DurabilityColorPalette palette = DurabilityColorPalette.DEFAULT;

        assertEquals(0xFF55FF55, palette.colorForPercentage(100));
        assertEquals(0xFF55FF55, palette.colorForPercentage(75));

        assertEquals(0xFFFFAA00, palette.colorForPercentage(74));
        assertEquals(0xFFFFAA00, palette.colorForPercentage(50));

        assertEquals(0xFFFFFF55, palette.colorForPercentage(49));
        assertEquals(0xFFFFFF55, palette.colorForPercentage(25));

        assertEquals(0xFFFF5555, palette.colorForPercentage(24));
        assertEquals(0xFFFF5555, palette.colorForPercentage(5));

        assertEquals(0xFFAA0000, palette.colorForPercentage(4));
        assertEquals(0xFFAA0000, palette.colorForPercentage(0));
    }

    @Test
    void supportsCustomColorsAndThresholds() {
        DurabilityColorPalette palette = DurabilityColorPalette.fromHex(
                "#102030",
                "#203040",
                "#304050",
                "#405060",
                "#506070",
                80, 60, 30, 10
        );

        assertEquals(0xFF102030, palette.colorForPercentage(80));
        assertEquals(0xFF203040, palette.colorForPercentage(79));
        assertEquals(0xFF304050, palette.colorForPercentage(59));
        assertEquals(0xFF405060, palette.colorForPercentage(29));
        assertEquals(0xFF506070, palette.colorForPercentage(9));
    }

    @Test
    void fallsBackForInvalidColors() {
        DurabilityColorPalette palette = DurabilityColorPalette.fromHex(
                "invalid",
                "#FFAA00",
                "#FFFF55",
                "#FF5555",
                "#AA0000",
                75, 50, 25, 5
        );

        assertEquals(0xFF55FF55, palette.colorForPercentage(100));
    }

    @Test
    void handlesOverlappingThresholds() {
        DurabilityColorPalette palette = DurabilityColorPalette.fromHex(
                "#55FF55",
                "#FFAA00",
                "#FFFF55",
                "#FF5555",
                "#AA0000",
                10, 90, 20, 5
        );

        assertEquals(0xFF55FF55, palette.colorForPercentage(95));
        assertEquals(0xFFFFFF55, palette.colorForPercentage(50));
        assertEquals(0xFFAA0000, palette.colorForPercentage(4));
    }
}
