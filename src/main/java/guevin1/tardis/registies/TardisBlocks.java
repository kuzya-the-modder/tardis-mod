package guevin1.tardis.registies;

import guevin1.tardis.TardisMod;
import guevin1.tardis.blocks.ConsoleBlock;
import guevin1.tardis.blocks.MemoryBlock;
import guevin1.tardis.blocks.DoorBlock;
import guevin1.tardis.blocks.TardisBlock;
import guevin1.tardis.blocks.decorative.light.TardisLightBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

import java.util.function.ToIntFunction;

public class TardisBlocks {
    public static final Block MEMORY_BLOCK;
    public static final Block DOOR_BLOCK;
    public static final Block TARDIS_BLOCK;
    public static final Block CONSOLE_BLOCK;


    public static final Block TARDIS_LIGHT_BLOCK;
    private static Block registerBlock(String name,Block block){
        registerBlockItem(name,block);
        return Registry.register(Registries.BLOCK, new Identifier(TardisMod.MOD_ID,name),block);
    }
    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(TardisMod.MOD_ID,name),
                new BlockItem(block, new FabricItemSettings()));
    }
    static{
        MEMORY_BLOCK = registerBlock("memory",new MemoryBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
        DOOR_BLOCK = registerBlock("door",new DoorBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
        TARDIS_BLOCK = registerBlock("tardis",new TardisBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK).lightLevel(15)));
        CONSOLE_BLOCK = registerBlock("console",new ConsoleBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK).lightLevel(10)));
        TARDIS_LIGHT_BLOCK = registerBlock("light", new TardisLightBlock(FabricBlockSettings.create().luminance(createLightLevel())));
    }
    public static ToIntFunction<BlockState> createLightLevel(int litLevel) {
        return state -> state.get(Properties.POWERED) != false ? litLevel : 0;
    }
    public static ToIntFunction<BlockState> createLightLevel() {
        return state -> state.get(Properties.POWERED) ? state.get(Properties.POWER) : 0;
    }
}
