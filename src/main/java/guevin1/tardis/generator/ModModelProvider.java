package guevin1.tardis.generator;

import guevin1.tardis.TardisMod;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModModelProvider extends FabricModelProvider {
    private static List<Block> blockList = new ArrayList<>();
    private static List<Item> itemList = new ArrayList<>();

    public static void setBlockList(List<Block> blockLists) {
        blockList = blockLists;
    }

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        TardisMod.LOGGER.info(blockList.toString());
        for (Block bl: blockList) {
            blockStateModelGenerator.registerSimpleCubeAll(bl);
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        for (Item it: itemList) {
            itemModelGenerator.register(it, Models.GENERATED);

        }
    }
}
