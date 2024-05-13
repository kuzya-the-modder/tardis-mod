package guevin1.tardis.client;

import guevin1.tardis.TardisMod;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class TardisModelLayers extends EntityModelLayers {
    public static final EntityModelLayer TARDIS = new EntityModelLayer(new Identifier(TardisMod.MOD_ID,"tardis"),"main");

}
