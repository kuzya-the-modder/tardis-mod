package guevin1.tardis.registies;

import guevin1.tardis.TardisMod;
import guevin1.tardis.entity.ControlEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.minecraft.entity.mob.MobEntity.createMobAttributes;

public class TardisEntities {
    public static final EntityType<ControlEntity> CONTROL = Registry.register(Registries.ENTITY_TYPE, new Identifier(TardisMod.MOD_ID, "control"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, ControlEntity::new).dimensions(EntityDimensions.fixed(0.25F, 0.25F)).build());;

    public static void registerEntities() {
        FabricDefaultAttributeRegistry.register(CONTROL, createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0D));
        TardisMod.LOGGER.info("registering entities");
    }


}
