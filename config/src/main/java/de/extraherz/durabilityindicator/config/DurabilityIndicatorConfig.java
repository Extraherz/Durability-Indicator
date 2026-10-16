
package de.extraherz.durabilityindicator.config;

import de.extraherz.durabilityindicator.DurabilityColorPalette;
import eu.midnightdust.lib.config.MidnightConfig;

import java.util.Objects;

public final class DurabilityIndicatorConfig extends MidnightConfig {

    public static final String MOD_ID = "durabilityindicator";

    // General

    @Entry(category = "general")
    public static boolean enabled = true;

    @Entry(category = "general")
    public static boolean showUndamagedInCreative = false;

    // Display

    @Entry(category = "display", isSlider = true,
            min = 0.4, max = 1.2, precision = 100)
    public static float textScale = 0.6f;

    @Entry(category = "display")
    public static boolean compactNumbers = true;

    @Entry(category = "display")
    public static boolean textShadow = true;

    @Entry(category = "display")
    public static boolean showVanillaDurabilityBar = true;

    // Position

    @Entry(category = "position", isSlider = true,
            min = -12, max = 12)
    public static int xOffset = 0;

    @Entry(category = "position", isSlider = true,
            min = -12, max = 12)
    public static int yOffsetWithBar = 0;

    @Entry(category = "position", isSlider = true,
            min = -12, max = 12)
    public static int yOffsetWithoutBar = 0;

    // Colors

    @Entry(category = "colors", isColor = true)
    public static String greenColor = "#55FF55";

    @Entry(category = "colors", isColor = true)
    public static String orangeColor = "#FFAA00";

    @Entry(category = "colors", isColor = true)
    public static String yellowColor = "#FFFF55";

    @Entry(category = "colors", isColor = true)
    public static String redColor = "#FF5555";

    @Entry(category = "colors", isColor = true)
    public static String darkRedColor = "#AA0000";

    // Thresholds

    @Entry(category = "thresholds", isSlider = true,
            min = 0, max = 100)
    public static int greenThreshold = 75;

    @Entry(category = "thresholds", isSlider = true,
            min = 0, max = 100)
    public static int orangeThreshold = 50;

    @Entry(category = "thresholds", isSlider = true,
            min = 0, max = 100)
    public static int yellowThreshold = 25;

    @Entry(category = "thresholds", isSlider = true,
            min = 0, max = 100)
    public static int redThreshold = 5;

    private static PaletteSettings cachedSettings;
    private static DurabilityColorPalette cachedPalette;

    public static void initialize() {
        MidnightConfig.register(MOD_ID, DurabilityIndicatorConfig.class);
    }

    public static DurabilityColorPalette getColorPalette() {
        if (cachedSettings == null
                || cachedSettings.greenThreshold != greenThreshold
                || cachedSettings.orangeThreshold != orangeThreshold
                || cachedSettings.yellowThreshold != yellowThreshold
                || cachedSettings.redThreshold != redThreshold
                || !Objects.equals(cachedSettings.green, greenColor)
                || !Objects.equals(cachedSettings.orange, orangeColor)
                || !Objects.equals(cachedSettings.yellow, yellowColor)
                || !Objects.equals(cachedSettings.red, redColor)
                || !Objects.equals(cachedSettings.darkRed, darkRedColor)) {

            cachedSettings = new PaletteSettings(
                    greenColor,
                    orangeColor,
                    yellowColor,
                    redColor,
                    darkRedColor,
                    greenThreshold,
                    orangeThreshold,
                    yellowThreshold,
                    redThreshold
            );

            cachedPalette = DurabilityColorPalette.fromHex(
                    greenColor,
                    orangeColor,
                    yellowColor,
                    redColor,
                    darkRedColor,
                    greenThreshold,
                    orangeThreshold,
                    yellowThreshold,
                    redThreshold
            );
        }

        return cachedPalette;
    }

    private record PaletteSettings(
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
    }
}
