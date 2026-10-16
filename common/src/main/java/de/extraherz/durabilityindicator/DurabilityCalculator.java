
package de.extraherz.durabilityindicator;

public final class DurabilityCalculator {

    private DurabilityCalculator() {
    }

    public static int remaining(int maximum, int damage) {
        if (maximum <= 0) {
            return 0;
        }

        int safeDamage = Math.clamp(damage, 0, maximum);
        return maximum - safeDamage;
    }

    public static int percentage(int maximum, int damage) {
        if (maximum <= 0) {
            return 0;
        }

        int remaining = remaining(maximum, damage);

        return (int) ((remaining * 100L) / maximum);
    }
}
