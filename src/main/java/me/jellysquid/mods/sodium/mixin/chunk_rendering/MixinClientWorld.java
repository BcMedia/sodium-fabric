package me.jellysquid.mods.sodium.mixin.chunk_rendering;

import me.jellysquid.mods.sodium.client.world.ClientWorldExtended;
import me.jellysquid.mods.sodium.client.world.SodiumChunkManager;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientChunkManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(ClientWorld.class)
public abstract class MixinClientWorld implements ClientWorldExtended {
    private long seed;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(ClientPlayNetworkHandler clientPlayNetworkHandler, ClientWorld.class_5271 arg, DimensionType dimensionType, int chunkLoadDistance, Supplier<Profiler> supplier, WorldRenderer worldRenderer, boolean bl, long l, CallbackInfo ci) {
        this.seed = l;
    }

    @Redirect(method = "<init>", at = @At(value = "NEW", target = "net/minecraft/client/world/ClientChunkManager"))
    private ClientChunkManager redirectClientChunkManagerCtor(ClientWorld world, int renderDistance) {
        return new SodiumChunkManager(world, renderDistance);
    }

    public long getSeed() {
        return this.seed;
    }
}
