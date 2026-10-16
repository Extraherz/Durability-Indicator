
package de.extraherz.durabilityindicator.fabric;

import de.extraherz.durabilityindicator.DurabilityOverlayData;
import de.extraherz.durabilityindicator.config.DurabilityIndicatorConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix3x2fStack;

public final class DurabilityOverlayRenderer {

    private DurabilityOverlayRenderer() {
    }

    public static void render(
            GuiGraphicsExtractor graphics,
            Font font,
            ItemStack stack,
            int x,
            int y
    ) {
        if (!DurabilityIndicatorConfig.enabled
                || stack.isEmpty()
                || !stack.isDamageableItem()) {
            return;
        }

        Minecraft minecraft = Minecraft.getInstance();

        boolean creativeMode = minecraft.player != null
                && minecraft.player.isCreative();

        boolean vanillaBarVisible = stack.isBarVisible()
                && DurabilityIndicatorConfig.showVanillaDurabilityBar;

        DurabilityOverlayData data = DurabilityOverlayData.create(
                stack.getMaxDamage(),
                stack.getDamageValue(),
                creativeMode,
                vanillaBarVisible,
                DurabilityIndicatorConfig.showUndamagedInCreative,
                DurabilityIndicatorConfig.compactNumbers,
                DurabilityIndicatorConfig.getColorPalette()
        );

        if (data == null) {
            return;
        }

        float xOffset = data.xOffset()
                + DurabilityIndicatorConfig.xOffset;

        float yOffset = data.yOffset()
                + (vanillaBarVisible
                ? DurabilityIndicatorConfig.yOffsetWithBar
                : DurabilityIndicatorConfig.yOffsetWithoutBar);

        float scale = DurabilityIndicatorConfig.textScale;

        Matrix3x2fStack pose = graphics.pose();
        pose.pushMatrix();

        try {
            pose.translate(
                    x + xOffset,
                    y + yOffset
            );

            pose.scale(scale, scale);

            graphics.text(
                    font,
                    data.text(),
                    -font.width(data.text()) / 2,
                    -font.lineHeight,
                    data.color(),
                    DurabilityIndicatorConfig.textShadow
            );
        } finally {
            pose.popMatrix();
        }
    }
}
