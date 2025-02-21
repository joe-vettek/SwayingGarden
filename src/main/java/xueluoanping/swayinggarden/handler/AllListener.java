package xueluoanping.swayinggarden.handler;


import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xueluoanping.swayinggarden.SwayingGarden;
import xueluoanping.swayinggarden.client.shader.IrisHook;

@Mod.EventBusSubscriber(modid = SwayingGarden.MOD_ID)
public class AllListener {

    private static long lastTime=0;
    @SubscribeEvent
    public static void onTagsUpdatedEvent(TagsUpdatedEvent tagsUpdatedEvent) {
        long l = System.currentTimeMillis();
        if (l-lastTime>1000) {
            IrisHook.reload();
            lastTime=l;
        }
    }

}
