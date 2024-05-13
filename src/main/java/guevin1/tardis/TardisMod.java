package guevin1.tardis;

import guevin1.tardis.entity.ControlEntity;
import guevin1.tardis.registies.TardisCommands;
import guevin1.tardis.registies.TardisEntities;
import guevin1.tardis.registies.TardisSound;
import guevin1.tardis.worlds.dimension.OverworldData;
import guevin1.tardis.worlds.dimension.DimensionHelper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

import java.util.List;

public class TardisMod implements ModInitializer {
    public static final String MOD_ID = "tardis";
    public  static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        ServerLifecycleEvents.SERVER_STARTED.register((s) -> {
            List<String> worlds = OverworldData.getTardisDim(s);
            for (String world: worlds) {
                DimensionHelper.getOrCreateWorld(s,world);
            }
        });
        TardisCommands.registerCommands();
        GeckoLib.initialize();
        TardisSound.registerSounds();
        TardisEntities.registerEntities();
        LOGGER.info(MOD_ID + " init");




    }
    public static BlockPos addXYZ(BlockPos po,Direction direction){
        po.offset(direction,2);
        return po;
    }
}
