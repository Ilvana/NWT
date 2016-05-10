package kino.utils;

import java.util.HashMap;
import java.util.Map;

public class JsonMessageGenerator {
    public static Map generateMessage(String key, String value) {
        Map map = new HashMap();
        map.put(key, value);

        return map;
    }
}
