package top.thegodisgad.utils.user;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author yhdhz
 */
public class UserUtils {


    public static final char[] CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
            'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    /**
     * 创建Long型随机id
     *
     * @return Long
     */
    public static Long createId() {
        return Math.abs(UUID.randomUUID().getMostSignificantBits()) / 10000;
    }

    /**
     * @return java.lang.String
     * @description: 返回由id创建的名称, id 为null 或等于0时会从新创建一个id
     * @author 杜洪洲
     * @date 2022/5/22 14:06
     * @throw
     */
    public static String creatName(Long id) {
        if (id == null || id == 0) {
            id = createId();
        }
        byte[] bytes = new byte[8];
        byte eight = 8;
        byte sixtyFour = 64;
        byte sixtyTwo = 62;
        for (int i = 0; i < eight; i++) {
            int x = sixtyFour - (i + 1) * eight;
            bytes[i] = (byte) ((id >> x) & 0x7f);
        }
        StringBuilder str = new StringBuilder();
        byte i = 0;
        while (i < eight) {
            str.append(CHARS[bytes[i] % sixtyTwo]);
            i++;
        }
        return str.toString();
    }

    /**
     * 返回 Map<id,name>
     *
     * @param map 建议这样做不要传null
     * @return Map<Long, String>
     */
    public static Map<Long, String> createIdAndName(Map<Long, String> map) {
        if (map.isEmpty()) {
            map = new HashMap<>(1);
        }

        while (true) {
            Long id = createId();
            String name = creatName(id);
            if (map.put(id, name) != null) {
                break;
            }
        }
        return map;
    }

}
