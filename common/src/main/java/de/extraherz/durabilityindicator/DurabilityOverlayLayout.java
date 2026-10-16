
package de.extraherz.durabilityindicator;

public final class DurabilityOverlayLayout {

    public static final float DEFAULT_X_OFFSET = 8.5f;

    public static final float DEFAULT_Y_WITH_BAR = 13.0f;
    public static final float DEFAULT_Y_WITHOUT_BAR = 15.0f;

    private DurabilityOverlayLayout() {
    }

    public static float getDefaultYOffset(boolean barVisible) {
        return barVisible
                ? DEFAULT_Y_WITH_BAR
                : DEFAULT_Y_WITHOUT_BAR;
    }
}
