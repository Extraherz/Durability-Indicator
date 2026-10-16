
package de.extraherz.durabilityindicator.mixin;

import de.extraherz.durabilityindicator.config.DurabilityIndicatorConfig;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiGraphicsExtractor.class)
public abstract class GuiGraphicsExtractorMixin {

    @Inject(
            method = "itemBar(Lnet/minecraft/world/item/ItemStack;II)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void durabilityindicator$hideVanillaBar(
            ItemStack stack,
            int x,
            int y,
            CallbackInfo ci
    ) {
        if (!DurabilityIndicatorConfig.showVanillaDurabilityBar) {
            ci.cancel();
        }
    }
}
