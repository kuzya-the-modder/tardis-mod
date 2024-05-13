package guevin1.tardis.entity.block;

import guevin1.tardis.TardisMod;
import guevin1.tardis.registies.TardisBlockEntities;
import guevin1.tardis.registies.TardisSound;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.sound.Sound;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class MemoryBlockEntity extends BlockEntity {

    public BlockPos TardisXYZ = new BlockPos(0,70,0);
    public BlockPos doorXYZ = new BlockPos(5,128,4);
    public RegistryKey<World> TardisDIM = ServerWorld.OVERWORLD;
    public Direction TardisDir = Direction.NORTH;
    public MemoryBlockEntity(BlockPos pos, BlockState state) {
        super(TardisBlockEntities.MEMORY_BLOCK_ENTITY, pos, state);
    }
    public static int Tick = 0;
    public static void tick(World world1, BlockPos pos, BlockState state1, MemoryBlockEntity blockEntity) {
        if (!world1.isClient) {
            Tick++;

            if(world1.getServer().getTicks()%200 == 0){
                world1.playSound(null, pos, TardisSound.TARDIS_AMBIENT, SoundCategory.AMBIENT, 0.3f, 0.3f);
            }
        }
    }

    public void setDoorXYZ(BlockPos door) {
        doorXYZ = door;
    }

    public BlockPos getTardisXYZ() {
        return TardisXYZ;
    }

    public RegistryKey<World> getTardisDIM() {
        return TardisDIM;
    }

    public void setTardisXYZ(BlockPos tardisXYZ) {
        TardisXYZ = tardisXYZ;
        TardisMod.LOGGER.info(TardisXYZ.toString());
    }

    public void setTardisDIM(ServerWorld tardisDIM) {
        RegistryKey<World> dime =  tardisDIM.getRegistryKey();
        TardisDIM = dime;
    }

    public void setTardisDir(Direction tardisDir) {
        TardisDir = tardisDir;
    }

    public Direction getTardisDir() {
        return TardisDir;
    }

    public BlockPos getDoorXYZ() {
        return doorXYZ;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.markDirty();
        // Door XYZ
        NbtCompound door = nbt.getCompound("door");
        int dx = door.getInt("x");
        int dy = door.getInt("y");
        int dz = door.getInt("z");
        doorXYZ = new BlockPos(dx,dy,dz);
        // Tardis World AND XYZ
        NbtCompound tardisOut = nbt.getCompound("tardis");
        int x = tardisOut.getInt("x");
        int y = tardisOut.getInt("y");
        int z = tardisOut.getInt("z");
        TardisDir = Direction.fromRotation(tardisOut.getFloat("direction"));
        TardisXYZ = new BlockPos(x,y,z);
        TardisDIM = RegistryKey.of(RegistryKeys.WORLD,Identifier.tryParse(tardisOut.getString("dim")));
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        // Tardis
        NbtCompound tardisOut = new NbtCompound();
        tardisOut.putInt("x", TardisXYZ.getX());
        tardisOut.putInt("y", TardisXYZ.getY());
        tardisOut.putInt("z", TardisXYZ.getZ());
        tardisOut.putString("dim", TardisDIM.getValue().toString());
        tardisOut.putFloat("direction", TardisDir.asRotation());
        // Door
        NbtCompound door = new NbtCompound();
        door.putInt("x", doorXYZ.getX());
        door.putInt("y", doorXYZ.getY());
        door.putInt("z", doorXYZ.getZ());
        nbt.put("tardis",tardisOut);
        nbt.put("door",door);

        super.writeNbt(nbt);
        markDirty();
    }
}
