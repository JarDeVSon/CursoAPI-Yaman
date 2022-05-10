package api;

import java.util.HashMap;
import java.util.Map;

public class Header {
    Map<String, String> header = new HashMap<>();

    public Map<String, String> gorestHeader(String token) {
        header.put("Authorization", token);
        header.put("Content-Type", "application/json");
        return header;
    }
}
