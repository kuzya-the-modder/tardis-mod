package guevin1.tardis.models.block;

import guevin1.tardis.entity.block.ConsoleBlockEntity;
import guevin1.tardis.entity.model.ConsoleBlockModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ConsoleModelRender extends GeoBlockRenderer<ConsoleBlockEntity> {



    public ConsoleModelRender(BlockEntityRendererFactory.Context context) {
        super(new ConsoleBlockModel());
    }
}
