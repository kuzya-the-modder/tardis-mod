package guevin1.tardis.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import guevin1.tardis.TardisMod;
import guevin1.tardis.registies.TardisBlocks;
import guevin1.tardis.structure.TardisStructure;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class TestStructure {

    private static int testStructure(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        ServerWorld world = player.getServerWorld();
        TardisMod.LOGGER.info(String.valueOf(world));
        BlockPos pos = player.getBlockPos();
        world.setBlockState(pos, TardisBlocks.TARDIS_BLOCK.getDefaultState(),  Block.NOTIFY_LISTENERS);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        world.updateNeighbors(pos, TardisBlocks.TARDIS_BLOCK);
        TardisStructure.addStructure(new BlockPos(-19,102,-19),world,"tardis");
        return 0;
    }

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("testStructure").executes(TestStructure::testStructure));

    }
}
