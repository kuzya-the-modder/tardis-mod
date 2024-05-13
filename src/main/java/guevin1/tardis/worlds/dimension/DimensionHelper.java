package guevin1.tardis.worlds.dimension;

import guevin1.tardis.TardisMod;
import guevin1.tardis.structure.TardisStructure;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import xyz.nucleoid.fantasy.Fantasy;
import xyz.nucleoid.fantasy.RuntimeWorldConfig;
import xyz.nucleoid.fantasy.RuntimeWorldHandle;
import xyz.nucleoid.fantasy.util.VoidChunkGenerator;

public class DimensionHelper {

    public static ServerWorld getOrCreateWorld(MinecraftServer server, String  name){
        Fantasy fantasy = Fantasy.get(server);
        RuntimeWorldConfig worldConfig = new RuntimeWorldConfig()
                .setDifficulty(Difficulty.HARD)
                .setGameRule(GameRules.DO_DAYLIGHT_CYCLE, false)
                .setGameRule(GameRules.KEEP_INVENTORY, true)
                .setGameRule(GameRules.DO_MOB_SPAWNING, false)
                .setGameRule(GameRules.DO_WARDEN_SPAWNING, false)
                .setTimeOfDay(0)
                .setGenerator(new VoidChunkGenerator(server.getRegistryManager().get(RegistryKeys.BIOME).getEntry(0).get())).setWorldConstructor(TardisWorld::new)

                .setSeed(name.hashCode());
        RuntimeWorldHandle RuntimeWorldHandle = fantasy.getOrOpenPersistentWorld(new Identifier(TardisMod.MOD_ID, name), worldConfig);
        ServerWorld world = RuntimeWorldHandle.asWorld();
        if (!OverworldData.getTardisDim(server).contains(name)){
            OverworldData.addTardisDim(server,name);
            TardisStructure.addStructure(new BlockPos(-19,102,-19),world,"tardis");
        }
        return world;


    }

}
