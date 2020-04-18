package me.jellysquid.mods.sodium.client.util;

public class MathUtil {
    /**
     * @return True if the specified number is greater than zero and is a power of two, otherwise false
     */
    public static boolean isPowerOfTwo(int n) {
        return ((n & (n - 1)) == 0);
    }

    /**
     * @return num rounded up to the nearest multiple of interval
     */
    public static int roundUp(int num, int interval) {
        return ((num + (interval - 1)) / interval) * interval;
    }
}
