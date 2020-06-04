package me.jellysquid.mods.sodium.mixin.options;

import me.jellysquid.mods.sodium.client.SodiumClientMod;
import me.jellysquid.mods.sodium.client.gui.SodiumGameOptions;
import net.minecraft.client.options.CloudRenderMode;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.GraphicsMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GameOptions.class)
public class MixinGameOptions {
    @Shadow
    public int viewDistance;

    @Shadow
    public GraphicsMode graphicsMode;

    /**
     * @author JellySquid
     */
    @Overwrite
    public CloudRenderMode getCloudRenderMode() {
        SodiumGameOptions options = SodiumClientMod.options();

        if (this.viewDistance < 4 || !options.quality.enableClouds) {
            return CloudRenderMode.OFF;
        }

        return options.quality.cloudQuality.isFancy(graphicsMode.getId() >= GraphicsMode.FANCY.getId()) ? CloudRenderMode.FANCY : CloudRenderMode.FAST;
    }
}
