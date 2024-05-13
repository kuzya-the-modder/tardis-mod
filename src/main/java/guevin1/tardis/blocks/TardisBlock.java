package guevin1.tardis.blocks;

import guevin1.tardis.TardisMod;
import guevin1.tardis.entity.block.MemoryBlockEntity;
import guevin1.tardis.entity.block.TardisBlockEntity;
import guevin1.tardis.registies.TardisBlocks;
import guevin1.tardis.worlds.dimension.DimensionHelper;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

import static net.minecraft.state.property.Properties.HORIZONTAL_FACING;

public class TardisBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final DirectionProperty FACING_TARDIS = HorizontalFacingBlock.FACING;
    public static final BooleanProperty OPEN = Properties.OPEN;
    public static final VoxelShape EAST_MODEL = Block.createCuboidShape(0,0,0,15,32,16);
    public static final VoxelShape WEST_MODEL = Block.createCuboidShape(1,0,0,16,32,16);

    public static final VoxelShape CLOSE = Block.createCuboidShape(0,0,0,16,32,16);
    public static final VoxelShape NORTH_MODEL = Block.createCuboidShape(0,0,1,16,32,16);
    public static final VoxelShape SOUTH_MODEL = Block.createCuboidShape(0,0,0,16,32,15);
    public TardisBlock(Settings settings) {
        super(settings);
        setDefaultState(this.getStateManager().getDefaultState().with(Properties.HORIZONTAL_FACING,Direction.EAST));
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient){
            TardisBlockEntity tardisBlock = (TardisBlockEntity) world.getBlockEntity(pos);
            UUID uuid = UUID.randomUUID();
            tardisBlock.setTardisUuid(uuid);

            ServerWorld world1 = DimensionHelper.getOrCreateWorld(world.getServer(),"tardis-"+uuid);
            world1.setBlockState(new BlockPos(0,128,0), TardisBlocks.CONSOLE_BLOCK.getDefaultState());
            MemoryBlockEntity console = (MemoryBlockEntity) world1.getBlockEntity(new BlockPos(0,128,0));
            TardisMod.LOGGER.info(console.toString());
            TardisMod.LOGGER.info(pos.toString());
            console.setTardisXYZ(pos);
            console.setTardisDIM((ServerWorld) world);
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(FACING_TARDIS);
        VoxelShape shape = CLOSE;
        Boolean openz = state.get(OPEN);
        if (openz) {
            switch (dir) {
                case NORTH:
                    shape = NORTH_MODEL;
                    break;
                case WEST:
                    shape = WEST_MODEL;
                    break;
                case SOUTH:
                    shape = SOUTH_MODEL;
                    break;
                case EAST:
                    shape = EAST_MODEL;
                    break;
            }
        }
        return shape;

    }

    @Override
    public VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
        Direction dir = state.get(FACING_TARDIS);
        VoxelShape shape = CLOSE;
        Boolean openz = state.get(OPEN);
        if (openz) {
            switch (dir) {
                case NORTH:
                    shape = NORTH_MODEL;
                    break;
                case WEST:
                    shape = WEST_MODEL;
                    break;
                case SOUTH:
                    shape = SOUTH_MODEL;
                    break;
                case EAST:
                    shape = EAST_MODEL;
                    break;
            }
        }
        return shape;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        Boolean openz = state.get(OPEN);

        if (!world.isClient()){
            if (openz) {
                TardisBlockEntity tardisBlock = (TardisBlockEntity) world.getBlockEntity(pos);
                UUID uuid = tardisBlock.getTardisUuid();

                ServerWorld world1 = DimensionHelper.getOrCreateWorld(world.getServer(),"tardis-"+uuid);
                world1.setBlockState(new BlockPos(0,128,0), TardisBlocks.MEMORY_BLOCK.getDefaultState());
                MemoryBlockEntity memory = (MemoryBlockEntity) world1.getBlockEntity(new BlockPos(0,128,0));
                Vec3d doorXYZ = memory.getDoorXYZ().toCenterPos();
                doorXYZ.add(0.5,0,0.5);
                memory.setTardisXYZ(tardisBlock.getPos());
                BlockPos doorXYZBlock = BlockPos.ofFloored(doorXYZ);
                Float yaw = entity.getYaw();
                if (!world1.getBlockState(doorXYZBlock).isAir()) {
                    Direction dir = world1.getBlockState(doorXYZBlock).get(HORIZONTAL_FACING);
                    doorXYZ = doorXYZ.offset(dir,entity.getBoundingBox().getLengthX()/2);
                    yaw = dir.asRotation();
                }
                entity.teleport(world1,doorXYZ.x,doorXYZ.y,doorXYZ.z, PositionFlag.VALUES,yaw,entity.getPitch());
            }
        }
        super.onEntityCollision(state, world, pos, entity);

    }



    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }



    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.OPEN);
        stateManager.add(HORIZONTAL_FACING);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TardisBlockEntity(pos,state);

    }
}
