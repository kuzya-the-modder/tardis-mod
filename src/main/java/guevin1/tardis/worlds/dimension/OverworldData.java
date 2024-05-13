package guevin1.tardis.worlds.dimension;

import guevin1.tardis.TardisMod;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.WorldSavePath;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OverworldData {

    public static List<String> getTardisDim(MinecraftServer server){
        List<String> allLines = new ArrayList<>();
        String pathS = server.getSavePath(WorldSavePath.ROOT).toString();
        Path path = Paths.get(pathS+"/data/tardis-dim.txt");
        try {
            byte[] bytes = Files.readAllBytes(path);
            allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
            TardisMod.LOGGER.info(allLines.toString());
        } catch (IOException e) {
            TardisMod.LOGGER.info("No file for tardis-dim UwU");
        }
        return allLines;
    }
    public static void addTardisDim(MinecraftServer server, String name){
        List<String> worlds = getTardisDim(server);
        worlds.add(name);
        String pathS = server.getSavePath(WorldSavePath.ROOT).toString();
        Path path = Paths.get(pathS+"/data/tardis-dim.txt");
        try {
            PrintWriter out = new PrintWriter(path.toFile());
            TardisMod.LOGGER.info(String.join("\n", worlds));
            out.write(String.join("\n", worlds));
            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
