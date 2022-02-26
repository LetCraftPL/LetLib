package pl.letcraft.letlib.bukkit.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import pl.letcraft.letlib.util.RandomUtil;

import java.util.List;

public class LocationUtil {

    public static boolean isSafe(@NotNull Location location) {
        List<Biome> biomes = List.of(
                Biome.OCEAN,
                Biome.DEEP_OCEAN,
                Biome.COLD_OCEAN,
                Biome.DEEP_COLD_OCEAN,
                Biome.DEEP_FROZEN_OCEAN,
                Biome.DEEP_LUKEWARM_OCEAN,
                Biome.ICE_SPIKES,
                Biome.LUSH_CAVES,
                Biome.DRIPSTONE_CAVES,
                Biome.FROZEN_OCEAN,
                Biome.LUKEWARM_OCEAN,
                Biome.WARM_OCEAN,
                Biome.RIVER,
                Biome.FROZEN_RIVER
        );

        return isSafe(location, biomes);
    }

    public static boolean isSafe(@NotNull Location location, @NotNull List<Biome> unsafeBiomes) {
        Block feet = location.getBlock();
        if (!feet.getType().isTransparent() &&
                !feet.getLocation().add(0.0, 1.0, 0.0).getBlock().getType().isTransparent()) {
            return false;
        }

        Block head = feet.getRelative(BlockFace.UP);
        if (!head.getType().isTransparent()) {
            return false;
        }

        Block ground = feet.getRelative(BlockFace.DOWN);

        if (!ground.getType().isSolid()) {
            return false;
        }

        Biome biome = location.getBlock().getBiome();

        return unsafeBiomes.contains(biome);
    }


    public static Location getRandomLocation(@NotNull World world, @NotNull TeleportationDetails details) {
        int border = (int) world.getWorldBorder().getSize() / 2 - 1;
        if (details.xMin() + 100 > border && details.zMin() + 100 > border) {
            return null;
        }

        int x = RandomUtil.randomInt(details.xMin(), details.xMax());
        int z = RandomUtil.randomInt(details.zMin(), details.zMax());
        int y = world.getHighestBlockYAt(x, z) + 1;
        Location location = new Location(world, x, y, z);
        if (!isSafe(location)) {
            return getRandomLocation(world, details);
        }

        return location;
    }


    public record TeleportationDetails(int xMin, int xMax, int zMin, int zMax) {
    }

}
