
package de.extraherz.durabilityindicator.fabric;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DurabilityIndicatorFabric implements ClientModInitializer {

    private static final Logger LOGGER =
            LoggerFactory.getLogger("durabilityindicator");

    @Override
    public void onInitializeClient() {
        LOGGER.info("Durability Indicator initialized successfully on Fabric.");
    }
}
