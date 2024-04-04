package com.hrs;

import com.hrs.bloom.Bloom;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Bloom b = new Bloom(10);
        char c = 'A';
        for (int i=0; i<10; i++) {
            String s = ""+c;
            b.set(s);
            c++;
        }
        c = 'A';
        for (int i=0; i<20; i++) {
            String s = ""+c;
            System.out.println(b.check(s));
            c++;
        }
    }
}