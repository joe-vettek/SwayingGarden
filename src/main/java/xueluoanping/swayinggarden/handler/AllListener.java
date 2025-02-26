package xueluoanping.swayinggarden.handler;


import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.TagsUpdatedEvent;
import xueluoanping.swayinggarden.SwayingGarden;
import xueluoanping.swayinggarden.client.shader.IrisHook;

@EventBusSubscriber(modid = SwayingGarden.MOD_ID)
public class AllListener {

    @SubscribeEvent
    public static void onTagsUpdatedEvent(TagsUpdatedEvent tagsUpdatedEvent) {
        if (tagsUpdatedEvent.getUpdateCause() == TagsUpdatedEvent.UpdateCause.CLIENT_PACKET_RECEIVED) {
            IrisHook.reload();
        }
    }

}
