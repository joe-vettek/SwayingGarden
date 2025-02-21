package xueluoanping.swayinggarden.config;

import net.minecraft.util.ResourceLocationException;
import net.minecraftforge.common.ForgeConfigSpec;
import xueluoanping.swayinggarden.util.TagUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

public class General {
    public static ForgeConfigSpec CLIENT_CONFIG;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> wheatLike;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> grassLike;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> saplingLike;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> doubleBlockPlantsTops;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> doubleBlockPlantsBottoms;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> vineLike;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> padLike;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> leavesLike;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> customLike;

    public static boolean isValidRegex2(Object o) {
        if (!(o instanceof String )) {
            return false;
        }
        String regex= (String) o;
        if (regex.contains("@")) {
            String[] split = regex.split("@");
            if (split[1].split("@").length != 2) return false;
            return isValidRegex(split[1]);
        } else {
            return false;
        }
    }

    public static boolean isValidRegex(Object o) {
        if (!(o instanceof String )) {
            return false;
        }
        String regex= (String) o;
        try {
            if (regex.contains("%")) {
                String[] split = regex.split("%");
                String[] strings = split[1].split(":");
                if (strings.length < 2 && strings.length % 2 == 1) return false;
                regex = split[0];
            }
            if (regex.startsWith("!")) {
                return regex.split("!").length != 2;
            }
            if (regex.startsWith("~")) {
                return regex.split("~").length != 2;
            }
            if (regex.startsWith("#")) {
                TagUtil.create(regex);
                return true;
            }
            Pattern.compile(regex);
            return true;
        } catch (PatternSyntaxException | ResourceLocationException e) {
            return false;
        }
    }
    
    public static List<String> of(String... strings){
        return Arrays.stream(strings).collect(Collectors.toList());
    }

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        COMMON_BUILDER.comment("This mod is designed to make it easier for modded plant blocks to quickly support shaders effects, preventing them from awkwardly standing in swaying blossom.");
        COMMON_BUILDER.comment("Base settings")
                .comment("Tags or regular expressions are allowed. Tags must start with # and not use regex. Also ! to target class is available (use ~ if want to get more matches). Use % to add state check.")
                .push("Base");
        wheatLike = COMMON_BUILDER.comment("List of small crops.")
                .define("Wheat Like",()-> of("~net.minecraft.world.level.block.CropBlock"),General::isValidRegex);

        grassLike = COMMON_BUILDER.comment("List of small flowers and grasses.")
                .define("Grass Like", of("#minecraft:small_flowers",
                        "regions_unexplored:clover",
                        "biomesoplenty:clover"), General::isValidRegex);

        saplingLike = COMMON_BUILDER.comment("List of small saplings.")
                .define("Sapling Like", of("#minecraft:saplings", ".*berry_bush"), General::isValidRegex);

        doubleBlockPlantsTops = COMMON_BUILDER.comment("List of upper parts of double block plants.")
                .define("Double Block Plants Uppers", of("#minecraft:tall_flowers%half:upper"), General::isValidRegex);

        doubleBlockPlantsBottoms = COMMON_BUILDER.comment("List of lower parts of double block plants.")
                .define("Double Block Plants Lowers", of("#minecraft:tall_flowers%half:lower"), General::isValidRegex);

        vineLike = COMMON_BUILDER.comment("List of small vine-like plants.")
                .define("Vine Like", of("atmospheric:passion_vine"), General::isValidRegex);

        leavesLike = COMMON_BUILDER.comment("List of small leaf-like plants.")
                .define("Leaf Like", of("#minecraft:leaves",
                        "#dynamictrees:foliage",
                        "environmental:.*_hanging_wisteria_leaves",
                        "environmental:cattail_stalk",
                        "environmental:cattail"), General::isValidRegex);

        padLike = COMMON_BUILDER.comment("List of small lily pad-like plants.")
                .define("Pad Like", of("regions_unexplored:flowering_lily_pad",
                        "biomesoplenty:huge_lily_pad",
                        "environmental:giant_lily_pad",
                        "environmental:large_lily_pad"), General::isValidRegex);

        customLike = COMMON_BUILDER.comment("List of custom-like plants, use '@' to split template and targets, template string can get from F3+i at target blocks, such as minecraft:tall_grass[half=lower]@#minecraft:tall_flowers[half=lower].")
                .define("Custom Like", of("minecraft:vine@teastory:rice_plant"), General::isValidRegex2);


        COMMON_BUILDER.pop();


        CLIENT_CONFIG = COMMON_BUILDER.build();


    }


}
