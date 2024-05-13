package guevin1.tardis.blocks;

import guevin1.tardis.TardisMod;
import guevin1.tardis.entity.block.MemoryBlockEntity;
import guevin1.tardis.entity.block.TardisBlockEntity;
import guevin1.tardis.registies.TardisBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.UUID;

import static net.minecraft.state.property.Properties.HORIZONTAL_FACING;

public class DoorBlock extends Block {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty OPEN = Properties.OPEN;
    public static final int depth = 16;
    public static final int height = 32;
    public static final int width = 3;

//    OutlineShape
    public static final VoxelShape EAST_MODEL = Block.createCuboidShape(0,0,0,width,height,depth);
    public static final VoxelShape WEST_MODEL = Block.createCuboidShape(16-width,0,0,16,height,depth);
    public static final VoxelShape NORTH_MODEL = Block.createCuboidShape(0,0,16-width,depth,height,depth);
    public static final VoxelShape SOUTH_MODEL = Block.createCuboidShape(0,0,0,depth,height,width);


//    ColissionShape
    public static final VoxelShape EAST_MODEL_COLLISION = Block.createCuboidShape(0,0,0,width-1,height,depth);
    public static final VoxelShape WEST_MODEL_COLLISION = Block.createCuboidShape(16-width+1,0,0,16,height,depth);
    public static final VoxelShape NORTH_MODEL_COLLISION = Block.createCuboidShape(0,0,16-width+1,depth,height,depth);
    public static final VoxelShape SOUTH_MODEL_COLLISION = Block.createCuboidShape(0,0,0,depth,height,width-1);
    public DoorBlock(Settings settings) {
        super(settings);
        setDefaultState(this.getStateManager().getDefaultState().with(Properties.HORIZONTAL_FACING,Direction.EAST));

    }


    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        MemoryBlockEntity consoleBlockEntity = (MemoryBlockEntity) world.getBlockEntity(new BlockPos(0,128,0));
        if (world.getRegistryKey().getValue().toString().contains("tardis:tardis")){
            consoleBlockEntity.setDoorXYZ(pos);
        }

        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(FACING);
        VoxelShape shape = EAST_MODEL_COLLISION;
        switch (dir){
            case NORTH:
                shape = NORTH_MODEL_COLLISION;
                break;
            case WEST:
                shape = WEST_MODEL_COLLISION;
                break;
            case SOUTH:
                shape = SOUTH_MODEL_COLLISION;
                break;
            case EAST:
                shape = EAST_MODEL_COLLISION;
                break;
        }
        return shape;
    }

    @Override
    public VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
        Direction dir = state.get(FACING);
        VoxelShape shape = EAST_MODEL;
        switch (dir){
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
        return shape;

    }



    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(FACING);
        VoxelShape shape = EAST_MODEL_COLLISION;
        switch (dir){
            case NORTH:
                shape = NORTH_MODEL_COLLISION;
                break;
            case WEST:
                shape = WEST_MODEL_COLLISION;
                break;
            case SOUTH:
                shape = SOUTH_MODEL_COLLISION;
                break;
            case EAST:
                shape = EAST_MODEL_COLLISION;
                break;
        }
        return shape;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(FACING);
        VoxelShape shape = EAST_MODEL;
        switch (dir){
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
        return shape;
    }

    @Override
    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        Direction dir = state.get(FACING);
        VoxelShape shape = EAST_MODEL;
        switch (dir){
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
        return shape;
    }
    public boolean collidesWithBlockHitBox(BlockPos pos, BlockState state,Entity e) {
        Direction dir = state.get(FACING);
        VoxelShape shape = EAST_MODEL;
        switch (dir){
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
        VoxelShape voxelShape2 = shape.offset(pos.getX(), pos.getY(), pos.getZ());
        return VoxelShapes.matchesAnywhere(voxelShape2, VoxelShapes.cuboid(e.getBoundingBox()), BooleanBiFunction.AND);
    }
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        Boolean openz = state.get(OPEN);
        Boolean isDOWN = false;
        if (!isDOWN && collidesWithBlockHitBox(pos,state,entity) && !world.isClient()) {
            TardisMod.LOGGER.info("SEXXX");
            if (openz) {
                try {
                    MemoryBlockEntity memoryBlockEntity = (MemoryBlockEntity) world.getBlockEntity(new BlockPos(0, 128, 0));
                    if (memoryBlockEntity != null) {
                        if (world.getRegistryKey().getValue().toString().contains("tardis:tardis")) {
                            Vec3d posTardisVec = memoryBlockEntity.getTardisXYZ().toCenterPos();
                            posTardisVec.add(0.5,0,0.5);
                            RegistryKey<World> TardisDIMReg = memoryBlockEntity.getTardisDIM();
                            ServerWorld TardisDIM = world.getServer().getWorld(TardisDIMReg);
                            BlockPos TardisXYZBlock = BlockPos.ofFloored(posTardisVec);
                            BlockState TardisState = TardisDIM.getBlockState(TardisXYZBlock);

                            if (!TardisDIM.getBlockState(TardisXYZBlock).isAir()) {
                                posTardisVec = posTardisVec.offset(TardisState.get(HORIZONTAL_FACING),1);
                            }else{
                                TardisDIM.setBlockState(TardisXYZBlock, TardisBlocks.TARDIS_BLOCK.getDefaultState().with(HORIZONTAL_FACING,memoryBlockEntity.getTardisDir()));
                                TardisBlockEntity tardisBlockEntity = (TardisBlockEntity) TardisDIM.getBlockEntity(TardisXYZBlock);
                                UUID uuid = UUID.fromString(world.getRegistryKey().getValue().toString().replace("tardis:tardis-",""));
                                tardisBlockEntity.setTardisUuid(uuid);
                                posTardisVec = posTardisVec.offset(TardisDIM.getBlockState(TardisXYZBlock).get(HORIZONTAL_FACING),entity.getBoundingBox().getLengthX()/2);
                            }
//
                            Direction dir = TardisState.get(HORIZONTAL_FACING);
                            double[] Rotation = new double[]{Math.PI,Math.PI/2,0,Math.PI*1.5};
                            entity.teleport(TardisDIM, posTardisVec.getX(), posTardisVec.getY(), posTardisVec.getZ(), PositionFlag.VALUES, (float) (dir.asRotation()-entity.prevYaw+90), entity.prevPitch);
                        }
                    }else {
                        entity.teleport(entity.getServer().getWorld(World.OVERWORLD).toServerWorld(),0,70,0,PositionFlag.VALUES, entity.prevYaw, entity.prevPitch);
                    }

                } catch (ClassCastException e){
                    TardisMod.LOGGER.info("Я не ебу что за ошибка на: "+e.toString());
                    entity.teleport(entity.getServer().getWorld(World.OVERWORLD).toServerWorld(),0,70,0,PositionFlag.VALUES, entity.prevYaw, entity.prevPitch);
                }


            }
        }
        super.onEntityCollision(state, world, pos, entity);
    }



    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
        stateManager.add(Properties.OPEN);
    }


}
