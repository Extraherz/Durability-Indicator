
package de.extraherz.durabilityindicator.fabric;

import de.extraherz.durabilityindicator.config.DurabilityIndicatorConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ExtractItemDecorationsCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DurabilityIndicatorFabric implements ClientModInitializer {

    private static final Logger LOGGER =
            LoggerFactory.getLogger("durabilityindicator");

    @Override
    public void onInitializeClient() {
        DurabilityIndicatorConfig.initialize();

        ExtractItemDecorationsCallback.EVENT.register(
                DurabilityOverlayRenderer::render
        );

        LOGGER.info("Durability Indicator initialized successfully on Fabric.");
        LOGGER.info("Durability Indicator configuration registered.");
        LOGGER.info("Durability overlay renderer registered.");
    }
}
