package xueluoanping.swayinggarden.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import xueluoanping.swayinggarden.SwayingGarden;

import java.util.concurrent.CompletableFuture;


public class start {
    public final static String MODID = SwayingGarden.MOD_ID;

    public static void dataGen(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        if (event.includeServer()) {
            SwayingGarden.logger("Generate We Data!!!");
        }

        if (event.includeClient()) {

        }


    }
}
