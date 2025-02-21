package xueluoanping.swayinggarden.client;


import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    @SubscribeEvent
    public static void onClientEvent(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {

        });
    }


    @SubscribeEvent
    public static void onBuildCreativeModeTabContentsEvent(BuildCreativeModeTabContentsEvent event) {

    }
}
