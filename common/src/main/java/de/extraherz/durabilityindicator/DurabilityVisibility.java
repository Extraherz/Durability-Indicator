
package de.extraherz.durabilityindicator;

public final class DurabilityVisibility {

    public static final boolean DEFAULT_SHOW_UNDAMAGED_IN_CREATIVE = false;

    private DurabilityVisibility() {
    }

    public static boolean shouldDisplay(
            int maximum,
            int damage,
            boolean creativeMode,
            boolean showUndamagedInCreative
    ) {
        if (maximum <= 0) {
            return false;
        }

        if (creativeMode && damage <= 0 && !showUndamagedInCreative) {
            return false;
        }

        return true;
    }
}
