package aurocosh.divinefavor.common.util;

import net.minecraft.util.math.Vec3d;

import java.util.List;
import java.util.Random;

public class UtilRandom {
    public static Random random = new Random();

    public static boolean rollDiceFloat(float chance) {
        float value = random.nextFloat();
        return value < chance;
    }

    public static boolean rollDice(float chance) {
        float value = random.nextFloat() * 100;
        return value < chance;
    }

    public static int nextInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static double nextDouble(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }

    public static float nextFloat(float min, float max) {
        return min + (max - min) * random.nextFloat();
    }

    public static int nextIntExclusive(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public static int getRandomIndex(List list) {
        return nextInt(0, list.size() - 1);
    }

    public static <T> T getRandom(List<T> list) {
        return list.get(getRandomIndex(list));
    }

    public static <T> int getRandomIndex(T[] list) {
        return nextInt(0, list.length - 1);
    }

    public static <T> T getRandom(T[] list) {
        return list.length == 0 ? null : list[getRandomIndex(list)];
    }

    public static Vec3d nextDirection() {
        float x = nextFloat(-1, 1);
        float y = nextFloat(-1, 1);
        float z = nextFloat(-1, 1);
        return UtilVec3d.normalize(new Vec3d(x, y, z));
    }
}
