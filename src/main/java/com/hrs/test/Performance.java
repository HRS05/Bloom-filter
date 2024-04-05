package com.hrs.test;

import com.hrs.bloom.Bloom;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Performance {
    private Map<Integer, String> dataSet;
    public Performance() {
        dataSet = new HashMap<>();
        for (int i=0; i<8000; i++) {
            String s = UUID.randomUUID().toString();
            dataSet.put(i, s);
        }
    }


    public void test() {
        System.out.println("Testing on total 8000 keys -> 4500 inserted in filter and 3500 used to check false positivity rate");
        for (int i=5000; i<25000; i+=2000) {
            Bloom b = new Bloom(i);
            int falseRate = 0;
            for (int j=0; j<4500; j++) {
                b.set(dataSet.get(j));
            }
            for (int j=4500; j<8000; j++) {
                if (b.check(dataSet.get(j))) {
                    falseRate++;
                }
            }

            double percentage = Math.round((((double) falseRate / 3500) * 100) * 100.0) / 100.0;
            System.out.println("Size of Bloom filter is "+i+" and for that false positivity percentage :"+percentage+"%");
        }
    }


}
