package xueluoanping.swayinggarden.client;


import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xueluoanping.swayinggarden.SwayingGarden;
import xueluoanping.swayinggarden.mixin.SimpleMixinPlugin;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    @SubscribeEvent
    public static void onClientEvent(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            if (!SimpleMixinPlugin.isIrisLikeLoad())
                SwayingGarden.LOGGER.warn("Iris like mod is not installed, the mod would not work without that mod.");
        });
    }
}
