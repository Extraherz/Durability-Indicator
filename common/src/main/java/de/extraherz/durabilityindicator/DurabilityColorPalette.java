
package de.extraherz.durabilityindicator;

public record DurabilityColorPalette(
        int greenColor,
        int orangeColor,
        int yellowColor,
        int redColor,
        int darkRedColor,
        int greenThreshold,
        int orangeThreshold,
        int yellowThreshold,
        int redThreshold
) {

    public static final DurabilityColorPalette DEFAULT = fromHex(
            "#55FF55",
            "#FFAA00",
            "#FFFF55",
            "#FF5555",
            "#AA0000",
            75, 50, 25, 5
    );

    public static DurabilityColorPalette fromHex(
            String green,
            String orange,
            String yellow,
            String red,
            String darkRed,
            int greenThreshold,
            int orangeThreshold,
            int yellowThreshold,
            int redThreshold
    ) {
        return new DurabilityColorPalette(
                parseColor(green, DEFAULT_GREEN),
                parseColor(orange, DEFAULT_ORANGE),
                parseColor(yellow, DEFAULT_YELLOW),
                parseColor(red, DEFAULT_RED),
                parseColor(darkRed, DEFAULT_DARK_RED),
                greenThreshold,
                orangeThreshold,
                yellowThreshold,
                redThreshold
        );
    }

    private static final int DEFAULT_GREEN = 0xFF55FF55;
    private static final int DEFAULT_ORANGE = 0xFFFFAA00;
    private static final int DEFAULT_YELLOW = 0xFFFFFF55;
    private static final int DEFAULT_RED = 0xFFFF5555;
    private static final int DEFAULT_DARK_RED = 0xFFAA0000;

    public int colorForPercentage(int percentage) {
        int value = Math.clamp(percentage, 0, 100);

        // Keep thresholds in a valid descending order.
        int redMin = Math.clamp(redThreshold, 0, 100);

        int yellowMin = Math.max(
                redMin,
                Math.clamp(yellowThreshold, 0, 100)
        );

        int orangeMin = Math.max(
                yellowMin,
                Math.clamp(orangeThreshold, 0, 100)
        );

        int greenMin = Math.max(
                orangeMin,
                Math.clamp(greenThreshold, 0, 100)
        );

        if (value >= greenMin) {
            return greenColor;
        }

        if (value >= orangeMin) {
            return orangeColor;
        }

        if (value >= yellowMin) {
            return yellowColor;
        }

        if (value >= redMin) {
            return redColor;
        }

        return darkRedColor;
    }

    private static int parseColor(String hex, int fallback) {
        if (hex == null || !hex.matches("#[0-9a-fA-F]{6}")) {
            return fallback;
        }

        return 0xFF000000 | Integer.parseInt(hex.substring(1), 16);
    }
}
