package pl.letcraft.letlib.util;

import java.util.Random;

public final class RandomUtil {

    private static final Random random = new Random();

    private RandomUtil() {}

    public static int randomInt(int min, int max) {
        return (int)(Math.random() * (max - min + 1) + min);
    }

    public static double randomDouble(double min, double max) {
        return random.nextDouble() * (max - min) + min;
    }

    public static boolean tryChance(double chance) {
        return (chance >= 100.0) || (chance >= randomDouble(0.0, 100.0));
    }

}
