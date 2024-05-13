package guevin1.tardis.blocks.decorative.light;

import guevin1.tardis.TardisMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.ServerTask;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

public class TardisLightBlock extends Block {
    public static final BooleanProperty POWERED = Properties.POWERED;
    public static final IntProperty LIGHT_LEVEL = Properties.POWER;
    public static final BooleanProperty Random = BooleanProperty.of("random");
    public TardisLightBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(POWERED,false).with(LIGHT_LEVEL, 0).with(Random,false));;
    }
    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World wol = ctx.getWorld();
        return (BlockState) this.getDefaultState().with(POWERED, wol.isReceivingRedstonePower(ctx.getBlockPos())).with(LIGHT_LEVEL, wol.getReceivedRedstonePower(ctx.getBlockPos()));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.POWER);
        stateManager.add(Properties.POWERED);
        stateManager.add(Random);
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return super.getStrongRedstonePower(state, world, pos, direction);
    }

    @Override
    public void neighborUpdate(BlockState stateB, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!world.isClient() && !stateB.get(Random)) {
            int power = world.getReceivedRedstonePower(pos);
            settings.luminance(state -> stateB.get(POWERED) ? power : 0);
            world.setBlockState(pos, stateB.with(POWERED, power > 0).with(LIGHT_LEVEL,power), Block.NOTIFY_LISTENERS);

        }
        super.neighborUpdate(stateB, world, pos, sourceBlock, sourcePos, notify);
    }


    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient){

            TardisMod.LOGGER.info(String.valueOf(state.get(Random)));
            if (state.get(LIGHT_LEVEL) < 4) {
                world.setBlockState(pos, state.with(LIGHT_LEVEL, 7).with(Random,true).with(POWERED, true), Block.NOTIFY_LISTENERS);
                TardisMod.LOGGER.info(String.valueOf(pos));
            }else if (state.get(Random)) {
                world.setBlockState(pos, state.with(LIGHT_LEVEL, 0).with(Random,false).with(POWERED, false), Block.NOTIFY_LISTENERS);

            }
        }
        super.randomTick(state, world, pos, random);
    }

}
