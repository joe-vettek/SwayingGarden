package xueluoanping.swayinggarden.handler;


import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xueluoanping.swayinggarden.SwayingGarden;
import xueluoanping.swayinggarden.client.shader.OculusHook;

@Mod.EventBusSubscriber(modid = SwayingGarden.MOD_ID)
public class AllListener {

    @SubscribeEvent
    public static void onTagsUpdatedEvent(TagsUpdatedEvent tagsUpdatedEvent) {
        if (tagsUpdatedEvent.getUpdateCause() == TagsUpdatedEvent.UpdateCause.CLIENT_PACKET_RECEIVED) {
            OculusHook.reload();
        }
    }

}
