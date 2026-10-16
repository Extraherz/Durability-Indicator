
package de.extraherz.durabilityindicator.neoforge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(value = "durabilityindicator", dist = Dist.CLIENT)
public final class DurabilityIndicatorNeoForge {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(DurabilityIndicatorNeoForge.class);

    public DurabilityIndicatorNeoForge() {
        LOGGER.info("Durability Indicator initialized successfully on NeoForge.");
    }
}
