package guevin1.tardis.client;

import guevin1.tardis.entity.block.ConsoleBlockEntity;
import guevin1.tardis.entity.model.ConsoleBlockModel;
import guevin1.tardis.models.block.ConsoleModelRender;
import guevin1.tardis.models.block.TardisModelRender;
import guevin1.tardis.registies.TardisBlockEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

import static guevin1.tardis.registies.TardisBlockEntities.CONSOLE_BLOCK_ENTITY;
import static guevin1.tardis.registies.TardisBlockEntities.TARDIS_BLOCK_ENTITY;

public class TardisClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(TARDIS_BLOCK_ENTITY, TardisModelRender::new);
        EntityModelLayerRegistry.registerModelLayer(TardisModelLayers.TARDIS,TardisModelRender::getTexturedModelData);
        BlockEntityRendererFactories.register(CONSOLE_BLOCK_ENTITY, ConsoleModelRender::new);
    }
}
