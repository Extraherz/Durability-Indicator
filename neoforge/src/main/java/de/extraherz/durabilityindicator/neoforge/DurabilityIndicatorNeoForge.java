
package de.extraherz.durabilityindicator.neoforge;

import de.extraherz.durabilityindicator.config.DurabilityIndicatorConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterItemDecorationsEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(value = "durabilityindicator", dist = Dist.CLIENT)
public final class DurabilityIndicatorNeoForge {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(DurabilityIndicatorNeoForge.class);

    public DurabilityIndicatorNeoForge(IEventBus modEventBus) {
        DurabilityIndicatorConfig.initialize();

        modEventBus.addListener(this::registerItemDecorations);

        LOGGER.info("Durability Indicator initialized successfully on NeoForge.");
        LOGGER.info("Durability Indicator configuration registered.");
    }

    private void registerItemDecorations(
            RegisterItemDecorationsEvent event
    ) {
        for (Item item : BuiltInRegistries.ITEM) {
            event.register(
                    item,
                    DurabilityOverlayDecorator.INSTANCE
            );
        }

        LOGGER.info("Durability overlay decorators registered.");
    }
}
