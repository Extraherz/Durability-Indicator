
package de.extraherz.durabilityindicator;

public enum DurabilityTier {

    GREEN("#55FF55"),
    ORANGE("#FFAA00"),
    YELLOW("#FFFF55"),
    RED("#FF5555"),
    DARK_RED("#AA0000");

    private final String defaultHexColor;
    private final int defaultArgbColor;

    DurabilityTier(String defaultHexColor) {
        this.defaultHexColor = defaultHexColor;
        this.defaultArgbColor = 0xFF000000
                | Integer.parseInt(defaultHexColor.substring(1), 16);
    }

    public String getDefaultHexColor() {
        return defaultHexColor;
    }

    public int getDefaultArgbColor() {
        return defaultArgbColor;
    }

    public static DurabilityTier fromPercentage(int percentage) {
        int value = Math.clamp(percentage, 0, 100);

        if (value >= 75) {
            return GREEN;
        }

        if (value >= 50) {
            return ORANGE;
        }

        if (value >= 25) {
            return YELLOW;
        }

        if (value >= 5) {
            return RED;
        }

        return DARK_RED;
    }
}
