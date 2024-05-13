package guevin1.tardis.registies;

import guevin1.tardis.TardisMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;


public class TardisSound {
    public static final SoundEvent TARDIS_AMBIENT = registerSound("tardis_ambient");
    public static SoundEvent registerSound(String name) {
        Identifier id = new Identifier(TardisMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        // TODO: register sounds here
        TardisMod.LOGGER.info("registering sounds");
    }

}
