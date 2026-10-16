
package de.extraherz.durabilityindicator;

public final class DurabilityFormatter {

    private static final String[] SUFFIXES = {
            "k", "m", "b"
    };

    private DurabilityFormatter() {
    }

    public static String formatCompact(int durability) {
        int value = Math.max(0, durability);

        if (value < 1_000) {
            return Integer.toString(value);
        }

        long divisor = 1_000L;

        for (int index = 0; index < SUFFIXES.length; index++) {
            long tenths = (value * 10L + divisor / 2L) / divisor;

            if (tenths < 10_000 || index == SUFFIXES.length - 1) {
                long whole = tenths / 10L;
                long fraction = tenths % 10L;

                if (fraction == 0L) {
                    return whole + SUFFIXES[index];
                }

                return whole + "," + fraction + SUFFIXES[index];
            }

            divisor *= 1_000L;
        }

        throw new IllegalStateException("Unsupported durability value");
    }

    public static String formatExact(int durability) {
        return Integer.toString(Math.max(0, durability));
    }
}
