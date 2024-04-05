package com.hrs.hasher;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import java.nio.charset.Charset;

public class Hasher {
    private Integer seed;
    private HashFunction hasher;

    private Charset encoder;

    public Hasher(Integer seed, Charset encoder) {
        this.seed = seed;
        this.encoder = encoder;
        this.hasher = Hashing.murmur3_32(this.seed);
    }

    public Integer getHash(CharSequence key) {
        return Math.abs(this.hasher.hashString(key, this.encoder).asInt());
    }

}
