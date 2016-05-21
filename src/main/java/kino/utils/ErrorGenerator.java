package kino.utils;

import java.util.HashMap;
import java.util.Map;

public class ErrorGenerator {
    public static Map generateError(String errorMessage) {
        Map<String, String> error = new HashMap<>();
        error.put("Error", errorMessage);
        return error;
    }
}
