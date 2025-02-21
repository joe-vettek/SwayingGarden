package xueluoanping.swayinggarden.client.shader;

import net.irisshaders.iris.Iris;
import net.irisshaders.iris.shaderpack.materialmap.WorldRenderingSettings;
import xueluoanping.swayinggarden.util.Platform;

public class OculusHook {

    public static void reload() {
        if (Platform.isModLoaded("oculus")) {
            OculusAttach.reload(WorldRenderingSettings.INSTANCE.getBlockStateIds(), Iris.getIrisConfig().getShaderPackName().orElse(""));
            ((IAttach) WorldRenderingSettings.INSTANCE).swaying_garden$set(true);
        }
    }
}
