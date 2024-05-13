package guevin1.tardis.registies;

import guevin1.tardis.TardisMod;
import guevin1.tardis.entity.block.ConsoleBlockEntity;
import guevin1.tardis.entity.block.MemoryBlockEntity;
import guevin1.tardis.entity.block.TardisBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class TardisBlockEntities {
    public static final BlockEntityType<MemoryBlockEntity> MEMORY_BLOCK_ENTITY;
    public static final BlockEntityType<TardisBlockEntity> TARDIS_BLOCK_ENTITY;
    public static final BlockEntityType<ConsoleBlockEntity> CONSOLE_BLOCK_ENTITY;
    public static <T extends BlockEntity> BlockEntityType<T> register(String name, FabricBlockEntityTypeBuilder.Factory<T> newMethod, Block... block){
        return Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(TardisMod.MOD_ID,name),
                FabricBlockEntityTypeBuilder.create(newMethod,
                        block).build()
        );
    }
    static{
        // Notice!!! INSTANCE
        TARDIS_BLOCK_ENTITY = register("tardisblock",TardisBlockEntity::new, TardisBlocks.TARDIS_BLOCK);
        MEMORY_BLOCK_ENTITY = register("memoryblock", MemoryBlockEntity::new, TardisBlocks.MEMORY_BLOCK);
        CONSOLE_BLOCK_ENTITY = register("consoleblock", ConsoleBlockEntity::new, TardisBlocks.CONSOLE_BLOCK);
        TardisMod.LOGGER.info("Registered Block Entities");
    }
}