package xueluoanping.swayinggarden.mixin;


import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.coderbot.iris.Iris;
import net.coderbot.iris.block_rendering.BlockRenderingSettings;
import net.minecraft.block.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xueluoanping.swayinggarden.client.shader.IrisAttach;

@Mixin(BlockRenderingSettings.class)
public abstract class MixinWorldRenderingSettings {


    @Shadow(remap = false)
    private Object2IntMap<BlockState> blockStateIds;

    @Inject(at = {@At(value = "INVOKE", target = "Ljava/lang/Object;equals(Ljava/lang/Object;)Z")},
            method = {"setBlockStateIds"},
            remap = false)
    private void ecliptic$reload(Object2IntMap<BlockState> blockStateIds, CallbackInfo ci) {
        IrisAttach.reload(blockStateIds, Iris.getIrisConfig().getShaderPackName().orElse(""));
    }


}
