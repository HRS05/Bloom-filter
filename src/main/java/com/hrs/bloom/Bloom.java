package com.hrs.bloom;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import com.google.common.hash.Hashing;

public class Bloom {

    private Integer size;
    Map<Integer, Boolean> filterMap;

    public Bloom(Integer size) {
        this.size = size;
        this.filterMap = new HashMap<>(size);
    }

    public void set(String key) {
        int hash = Hashing.murmur3_32().hashString(key, StandardCharsets.UTF_8).asInt();
        int val = hash % this.size;
        this.filterMap.put(val, true);
    }

    public Boolean check(String key) {
        int hash = Hashing.murmur3_32().hashString(key, StandardCharsets.UTF_8).asInt();
        int val = hash % this.size;
        return this.filterMap.get(val) != null;
    }

}
