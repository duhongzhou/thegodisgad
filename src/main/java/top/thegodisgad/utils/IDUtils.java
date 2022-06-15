package top.thegodisgad.utils;

import java.util.UUID;

public class IDUtils {
    public static Long createId() {
        return Math.abs(UUID.randomUUID().getMostSignificantBits()) / 10000;
    }
}
