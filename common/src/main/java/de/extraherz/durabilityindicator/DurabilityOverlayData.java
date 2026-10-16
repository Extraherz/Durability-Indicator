
package de.extraherz.durabilityindicator;

public record DurabilityOverlayData(
        String text,
        int color,
        float xOffset,
        float yOffset
) {

    public static DurabilityOverlayData create(
            int maximum,
            int damage,
            boolean creativeMode,
            boolean vanillaBarVisible,
            boolean showUndamagedInCreative,
            boolean compactNumbers,
            DurabilityColorPalette colorPalette
    ) {
        if (!DurabilityVisibility.shouldDisplay(
                maximum,
                damage,
                creativeMode,
                showUndamagedInCreative
        )) {
            return null;
        }

        int remaining = DurabilityCalculator.remaining(
                maximum,
                damage
        );

        int percentage = DurabilityCalculator.percentage(
                maximum,
                damage
        );

        String text = compactNumbers
                ? DurabilityFormatter.formatCompact(remaining)
                : DurabilityFormatter.formatExact(remaining);

        int color = colorPalette.colorForPercentage(percentage);

        float xOffset = DurabilityOverlayLayout.DEFAULT_X_OFFSET;

        float yOffset = DurabilityOverlayLayout.getDefaultYOffset(
                vanillaBarVisible
        );

        return new DurabilityOverlayData(
                text,
                color,
                xOffset,
                yOffset
        );
    }
}
