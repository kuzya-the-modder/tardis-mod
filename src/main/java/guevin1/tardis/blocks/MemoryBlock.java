package guevin1.tardis.blocks;

import guevin1.tardis.entity.block.MemoryBlockEntity;
import guevin1.tardis.registies.TardisBlockEntities;
import guevin1.tardis.registies.TardisSound;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MemoryBlock extends BlockWithEntity implements BlockEntityProvider {

    public MemoryBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MemoryBlockEntity(pos,state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, TardisBlockEntities.MEMORY_BLOCK_ENTITY, (world1, pos, state1, blockEntity) -> MemoryBlockEntity.tick(world1, pos, state1, (MemoryBlockEntity) blockEntity));
    }
}
