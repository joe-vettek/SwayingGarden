package xueluoanping.swayinggarden.client.shader;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.command.arguments.BlockStateParser;
import net.minecraft.state.Property;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITagCollection;
import net.minecraft.tags.TagCollectionManager;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocationException;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import xueluoanping.swayinggarden.SwayingGarden;
import xueluoanping.swayinggarden.config.General;
import xueluoanping.swayinggarden.util.TagUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class IrisAttach {

    public static void reload(@Nullable Object2IntMap<BlockState> blockStateIds, @Nullable String shaderpack) {
        if (shaderpack == null) shaderpack = "";

        if (blockStateIds != null) {
            copyCustom(blockStateIds, shaderpack);

            copy(blockStateIds, shaderpack, General.doubleBlockPlantsTops.get(), Blocks.TALL_GRASS.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER));
            copy(blockStateIds, shaderpack, General.doubleBlockPlantsBottoms.get(), Blocks.TALL_GRASS.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));

            copy(blockStateIds, shaderpack, General.wheatLike.get(), Blocks.WHEAT.defaultBlockState());
            copy(blockStateIds, shaderpack, General.grassLike.get(), Blocks.GRASS.defaultBlockState());
            copy(blockStateIds, shaderpack, General.leavesLike.get(), Blocks.OAK_LEAVES.defaultBlockState());
            copy(blockStateIds, shaderpack, General.saplingLike.get(), Blocks.OAK_SAPLING.defaultBlockState());
            copy(blockStateIds, shaderpack, General.vineLike.get(), Blocks.VINE.defaultBlockState());
            copy(blockStateIds, shaderpack, General.padLike.get(), Blocks.LILY_PAD.defaultBlockState());
        }
    }

    public static void copyCustom( Object2IntMap<BlockState> blockStateIds,  String shaderpack) {
        for (String string : General.customLike.get()) {
            String[] split = string.split("@");
            if (split.length == 2) {
                BlockState blockState = parseBlock(split[0]);
                if (blockState != null) {
                    copy(blockStateIds, shaderpack, General.of(split[1]), blockState);
                }
            }
        }
    }

    public static List<BlockState> getBlocks(List<? extends String> strings) {
        List<BlockState> blocks = new ArrayList<>();
        for (String string : strings) {
            try {
                List<BlockState> blocks2 = new ArrayList<>();
                boolean checkState = string.contains("%");
                String[] split = string.split("%");
                if (checkState) {
                    string = split[0];
                }
                if (string.startsWith("!")) {
                    String classStringSplit = string.split("!")[1];
                    try {
                        Class<?> aClass = Class.forName(classStringSplit);
                        for (Map.Entry<RegistryKey<Block>, Block> blockEntry : Registry.BLOCK.entrySet()) {
                            if (blockEntry.getValue().getClass().equals(aClass)) {
                                blocks2.addAll(blockEntry.getValue().getStateDefinition().getPossibleStates());
                            }
                        }
                    } catch (Exception e) {

                    }
                } else if (string.startsWith("~")) {
                    String classStringSplit = string.split("~")[1];
                    try {
                        Class<?> aClass = Class.forName(classStringSplit);
                        for (Map.Entry<RegistryKey<Block>, Block> blockEntry : Registry.BLOCK.entrySet()) {
                            if (aClass.isAssignableFrom(blockEntry.getValue().getClass())) {
                                blocks2.addAll(blockEntry.getValue().getStateDefinition().getPossibleStates());
                            }
                        }
                    } catch (Exception e) {

                    }
                } else if (string.startsWith("#")) {
                    string = string.split("#")[1];
                    ITag<Block> blockTagKey = TagUtil.create(string);
                    for (Block value : blockTagKey.getValues()) {
                        blocks2.addAll(value.getStateDefinition().getPossibleStates());
                    }
                } else {
                    Pattern pattern = Pattern.compile(string);
                    for (Map.Entry<RegistryKey<Block>, Block> blockEntry : Registry.BLOCK.entrySet()) {
                        if (pattern.matcher(blockEntry.getKey().location().toString()).matches()) {
                            blocks2.addAll(blockEntry.getValue().getStateDefinition().getPossibleStates());
                        }
                    }
                }
                if (checkState) {
                    String[] split1 = split[1].split(":");
                    List<Pair<Pattern, Pattern>> pairs = new ArrayList<>();
                    if (split1.length % 2 == 0) {
                        for (int i = 0; i < split1.length / 2; i++) {
                            try {
                                pairs.add(Pair.of(
                                        Pattern.compile(split1[i * 2]), Pattern.compile(split1[i * 2 + 1])
                                ));
                            } catch (PatternSyntaxException e) {

                            }
                        }
                    }
                    for (Pair<Pattern, Pattern> pair : pairs) {
                        for (int i = 0; i < blocks2.size(); i++) {
                            BlockState blockState = blocks2.get(i);
                            for (Property<?> property : blockState.getValues().keySet()) {
                                if (pair.getFirst().matcher(property.getName()).matches()) {
                                    if (pair.getSecond().matcher(blockState.getValue(property).toString()).matches()) {
                                        continue;
                                    } else {
                                        blocks2.remove(i);
                                        i--;
                                    }
                                }
                            }
                        }
                    }

                }
                blocks.addAll(blocks2);
            } catch (PatternSyntaxException | ResourceLocationException e) {
                continue;
            }
        }
        return blocks;
    }

    public static BlockState parseBlock(String s) {
        BlockStateParser blockstateparser$blockresult =new  BlockStateParser(new StringReader(s), false);
        return blockstateparser$blockresult.getState();
    }

    private static void copy(Object2IntMap<BlockState> blockStateIds, String shaderpack, List<? extends String> strings, BlockState blockState) {
        for (BlockState block1 : getBlocks(strings)) {
            copy(blockStateIds, blockState, block1);
        }
    }


    public static void copy(@Nonnull Object2IntMap<BlockState> blockStateIds, BlockState state, Block block) {
        int idsInt = blockStateIds.getInt(state);
        if (idsInt != blockStateIds.defaultReturnValue()) {
            block.getStateDefinition().getPossibleStates()
                    .forEach(b -> blockStateIds.putIfAbsent(b, idsInt));
        }
    }

    public static void copy(@Nonnull Object2IntMap<BlockState> blockStateIds, BlockState state, BlockState aim) {
        int idsInt = blockStateIds.getInt(state);
        if (idsInt != blockStateIds.defaultReturnValue()) {
            int i = blockStateIds.putIfAbsent(aim, idsInt);
        }
    }
}
