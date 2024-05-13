package guevin1.tardis.registies;

import guevin1.tardis.command.TestStructure;
import guevin1.tardis.command.TestWorld;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class TardisCommands {
    public static void registerCommands() {

        CommandRegistrationCallback.EVENT.register(TestWorld::register);
        CommandRegistrationCallback.EVENT.register(TestStructure::register);
    }
}
