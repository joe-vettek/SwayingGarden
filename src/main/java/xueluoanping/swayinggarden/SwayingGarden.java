package xueluoanping.swayinggarden;


import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xueluoanping.swayinggarden.config.General;
import xueluoanping.swayinggarden.data.start;

import java.util.Objects;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SwayingGarden.MOD_ID)
public class SwayingGarden {
    public static final String MOD_ID = "swaying_garden";
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public static final boolean useLogger = Objects.equals(System.getProperty("forgegradle.runs.dev"), "true");
    public static final String NETWORK_VERSION = "1.0";

    @SuppressWarnings("removal")
    public SwayingGarden() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::gatherData);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, General.CLIENT_CONFIG);
    }


    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        //        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().options);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        //        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        //        LOGGER.info("Got IMC {}", event.getIMCStream().
        //                map(m->m.getMessageSupplier().get()).
        //                collect(Collectors.toList()));

    }


    public void gatherData(final GatherDataEvent event) {
        start.dataGen(event);
    }

    public static void logger(Object... x) {
        if (useLogger) {
            LOGGER.info(getStr(x));
        }
    }

    public static String getStr(Object... x) {
        StringBuilder output = new StringBuilder();
        for (Object i : x) {
            output.append(i).append(" ");
        }
        return output.toString();
    }

    public static void error(Object... x) {
        LOGGER.error(getStr(x));
    }

    public static ResourceLocation rl(String name) {
        return new ResourceLocation(SwayingGarden.MOD_ID, name);
    }
}
