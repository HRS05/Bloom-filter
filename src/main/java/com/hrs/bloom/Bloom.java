package com.hrs.bloom;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import com.google.common.hash.Hashing;
import com.hrs.hasher.Hasher;

public class Bloom {

    private Integer size;
    private byte[] filter;
    private Hasher hasher;
    public Bloom(Integer size) {
        this.size = size;
        int filterSize = (size / 8) + (size % 8 == 0 ? 0 : 1);
        this.filter = new byte[filterSize];
        Arrays.fill(this.filter, (byte) 0);
        this.hasher = new Hasher(8383, StandardCharsets.UTF_8);
    }

    public void set(String key) {
        int hash = this.hasher.getHash(key);
        int val = hash % this.size;
        int index = val / 8;
        int bit = val % 8;
        this.filter[index] = (byte) (this.filter[index] | (1 << bit));
    }

    public Boolean check(String key) {
        int hash = this.hasher.getHash(key);
        int val = hash % this.size;
        int index = val / 8;
        int bit = val % 8;
        return !((this.filter[index] & (1 << bit)) == 0);
    }

}
