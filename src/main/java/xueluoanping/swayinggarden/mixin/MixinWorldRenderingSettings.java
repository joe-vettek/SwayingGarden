package xueluoanping.swayinggarden.mixin;


import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.coderbot.iris.Iris;
import net.coderbot.iris.block_rendering.BlockRenderingSettings;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xueluoanping.swayinggarden.client.shader.IrisAttach;

@Mixin(BlockRenderingSettings.class)
public abstract class MixinWorldRenderingSettings {

    @Shadow(remap = false) private boolean reloadRequired;

    @Shadow(remap = false) private Object2IntMap<BlockState> blockStateIds;

    @Inject(at = {@At("RETURN")}, method = {"setBlockStateIds"},require = 0,remap = false)
    private void ecliptic$reload(CallbackInfo ci) {
        if(reloadRequired){
            IrisAttach.reload(blockStateIds, Iris.getIrisConfig().getShaderPackName().orElse(""));
        }
    }


}
