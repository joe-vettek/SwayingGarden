package xueluoanping.swayinggarden.util;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

public class TagUtil {
    public static ITag<Block> create(String pName) {
        return BlockTags.getAllTags().getTagOrEmpty(new ResourceLocation(pName));
    }
}
