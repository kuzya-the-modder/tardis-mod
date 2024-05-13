package guevin1.tardis.entity.block;

import guevin1.tardis.registies.TardisBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class TardisBlockEntity extends BlockEntity {

    private UUID uuid = UUID.randomUUID();

    public TardisBlockEntity(BlockPos pos, BlockState state) {
        super(TardisBlockEntities.TARDIS_BLOCK_ENTITY , pos, state);
    }



    public void setTardisUuid(UUID uudid) {
        this.uuid = uudid;
    }

    public UUID getTardisUuid() {
        return uuid;
    }



    @Override
    public void readNbt(NbtCompound nbt) {
        // UUID
        super.readNbt(nbt);
        uuid = UUID.fromString(nbt.getString("tardis"));
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        // UUID
        nbt.putString("tardis", String.valueOf(uuid));
        super.writeNbt(nbt);
        this.markDirty();
    }
    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}
