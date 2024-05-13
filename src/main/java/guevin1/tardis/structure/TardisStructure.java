package guevin1.tardis.structure;

import guevin1.tardis.TardisMod;
import net.minecraft.block.Block;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class TardisStructure {
    public static void addStructure(BlockPos pos, ServerWorld world,String nameStructure){
        var placementData = new StructurePlacementData().setPosition(new BlockPos(0,0,0))
                .setPlaceFluids(true)
                .setIgnoreEntities(false)
                .setUpdateNeighbors(true)
                .setInitializeMobs(true);
        var structure = world.getStructureTemplateManager().getTemplate(Identifier.of(TardisMod.MOD_ID,nameStructure));
        if (structure.isEmpty()){
            TardisMod.LOGGER.info("structure not loaded");
            return;
        }

        var structureResult = structure.get();
        var wasPlaced = structureResult.place(world, pos,new BlockPos(0,0,0),placementData, Random.create(), Block.NOTIFY_NEIGHBORS);
    }
}
