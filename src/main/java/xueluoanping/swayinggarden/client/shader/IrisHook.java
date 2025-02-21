package xueluoanping.swayinggarden.client.shader;

import net.coderbot.iris.Iris;
import net.coderbot.iris.block_rendering.BlockRenderingSettings;
import xueluoanping.swayinggarden.mixin.SimpleMixinPlugin;

public class IrisHook {

    public static void reload() {
        if (SimpleMixinPlugin.isIrisLikeLoad()) {
            IrisAttach.reload(BlockRenderingSettings.INSTANCE.getBlockStateIds(), Iris.getIrisConfig().getShaderPackName().orElse(""));
            // ((MixinWorldRenderingSettingsAttach)BlockRenderingSettings.INSTANCE).setReloadRequired(true);
        }
    }
}
