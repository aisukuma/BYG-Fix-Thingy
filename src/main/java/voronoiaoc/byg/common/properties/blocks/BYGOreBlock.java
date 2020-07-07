package voronoiaoc.byg.common.properties.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BYGOreBlock extends Block {
    public BYGOreBlock(Settings properties) {
        super(properties);
    }

    @Override
    public void onStacksDropped(BlockState state, World world, BlockPos pos, ItemStack stack) {
        super.onStacksDropped(this.getDefaultState(), world, pos, stack);
    }

    @Override
    protected void dropExperience(World world, BlockPos pos, int size) {
        super.dropExperience(world, pos, size);
    }
}
