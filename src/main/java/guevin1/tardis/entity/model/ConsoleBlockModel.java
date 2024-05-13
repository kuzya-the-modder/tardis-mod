package guevin1.tardis.entity.model;

import guevin1.tardis.TardisMod;
import guevin1.tardis.entity.block.ConsoleBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class ConsoleBlockModel extends GeoModel<ConsoleBlockEntity> {


    @Override
    public Identifier getModelResource(ConsoleBlockEntity animatable) {
        return new Identifier(TardisMod.MOD_ID,"geo/model/console.geo.json");
    }

    @Override
    public Identifier getTextureResource(ConsoleBlockEntity animatable) {
        return new Identifier(TardisMod.MOD_ID,"geo/textures/texture.png");
    }

    @Override
    public Identifier getAnimationResource(ConsoleBlockEntity animatable) {
        return new Identifier(TardisMod.MOD_ID,"animation/console.animation.json");
    }
}
