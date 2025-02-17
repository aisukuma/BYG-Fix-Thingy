package potionstudios.byg.client.textures;

import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.state.BlockState;
import potionstudios.byg.common.block.BYGBlocks;
import potionstudios.byg.common.item.BYGItems;

public class ColorManager {

    public static synchronized void onBlockColorsInit(BlockColors blockColors) {
        //registers the colors for blocks that changes colors based on biome
        blockColors.register((unknown, lightReader, pos, unknown2) -> lightReader != null && pos != null ? BiomeColors.getAverageGrassColor(lightReader, pos) : GrassColor.get(0.5D, 1.0D), BYGBlocks.LUSH_GRASS_BLOCK, BYGBlocks.OVERGROWN_STONE, BYGBlocks.OVERGROWN_NETHERRACK, BYGBlocks.TINY_LILYPADS, BYGBlocks.OVERGROWN_DACITE, BYGBlocks.NETHER_BRISTLE);
        blockColors.register((unknown, lightReader, pos, unknown2) -> lightReader != null && pos != null ? BiomeColors.getAverageFoliageColor(lightReader, pos) : FoliageColor.get(0.5D, 1.0D), BYGBlocks.MAHOGANY_LEAVES, BYGBlocks.POISON_IVY, BYGBlocks.LEAF_PILE, BYGBlocks.ORCHARD_LEAVES, BYGBlocks.FLOWERING_ORCHARD_LEAVES, BYGBlocks.RIPE_ORCHARD_LEAVES, BYGBlocks.WILLOW_LEAVES, BYGBlocks.MAPLE_LEAVES, BYGBlocks.JOSHUA_LEAVES, BYGBlocks.RIPE_JOSHUA_LEAVES, BYGBlocks.CYPRESS_LEAVES);
        blockColors.register((unknown, lightReader, pos, unknown2) -> lightReader != null && pos != null ? BiomeColors.getAverageWaterColor(lightReader, pos) : -1, BYGBlocks.WATER_BARREL_CACTUS);
    }

    public static synchronized void onItemColorsInit(BlockColors blockColors, ItemColors itemColors) {
        // Use the Block's colour handler from BlockItem
        ItemColor itemBlockColourHandler = (stack, tintIndex) -> {
            BlockState state = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();
            return blockColors.getColor(state, null, null, tintIndex);
        };


        if (itemBlockColourHandler != null) {
            itemColors.register(itemBlockColourHandler,
                BYGItems.LUSH_GRASS_BLOCK, BYGItems.OVERGROWN_NETHERRACK, BYGItems.MAHOGANY_LEAVES,
                BYGItems.POISON_IVY, BYGItems.OVERGROWN_STONE,
                BYGItems.TINY_LILYPADS, BYGItems.NETHER_BRISTLE, BYGItems.OVERGROWN_DACITE, BYGItems.LEAF_PILE,
                BYGItems.ORCHARD_LEAVES, BYGItems.FLOWERING_ORCHARD_LEAVES, BYGItems.RIPE_ORCHARD_LEAVES,
                BYGItems.WILLOW_LEAVES, BYGItems.CYPRESS_LEAVES,
                BYGItems.MAPLE_LEAVES, BYGItems.JOSHUA_LEAVES, BYGItems.RIPE_JOSHUA_LEAVES);
        }
    }
}