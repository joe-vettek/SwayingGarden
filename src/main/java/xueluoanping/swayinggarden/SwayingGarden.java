package xueluoanping.swayinggarden;


import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xueluoanping.swayinggarden.config.General;

import java.util.Objects;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SwayingGarden.MOD_ID)
public class SwayingGarden {
    public static final String MOD_ID = "swaying_garden";
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final boolean useLogger = Objects.equals(System.getProperty("forgegradle.runs.dev"), "true");

    @SuppressWarnings("removal")
    public SwayingGarden() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, General.CLIENT_CONFIG);
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
