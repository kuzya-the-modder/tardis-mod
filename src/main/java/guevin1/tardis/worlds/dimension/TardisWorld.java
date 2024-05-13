package guevin1.tardis.worlds.dimension;

import net.minecraft.recipe.RecipeManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import xyz.nucleoid.fantasy.RuntimeWorld;
import xyz.nucleoid.fantasy.RuntimeWorldConfig;

import java.util.function.BooleanSupplier;

public class TardisWorld extends RuntimeWorld {
    private long dynSeed;
    private static final RecipeManager RECIPE_MANAGER = new RecipeManager();

    public TardisWorld(MinecraftServer server, RegistryKey<World> registryKey, RuntimeWorldConfig config, Style style) {
        super(server, registryKey, config, style);
    }


    @Override
    public void tick(BooleanSupplier shouldKeepTicking) {
        this.dynSeed = this.random.nextLong();

        super.tick(shouldKeepTicking);
    }

    @Override
    public RecipeManager getRecipeManager() {
        return RECIPE_MANAGER;
    }

    @Override
    public long getSeed() {
        return this.dynSeed;
    }
}
