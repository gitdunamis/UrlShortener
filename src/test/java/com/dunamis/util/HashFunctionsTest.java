package com.dunamis.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class HashFunctionsTest {

    @Test
    public void encodeBase62() {
        var num = 6526670;
        var expected = hashFunc(num);

        String value = HashFunctions.encodeBase62(num);

        assertThat(expected, is(value));
    }

    @Test
    public void decodeBase62() {
        var hash = "65Z";
        var expected = deHash(hash);

        int value = HashFunctions.decodeBase62(hash);
        assertThat(expected, is(value));
    }

    private String hashFunc(int num) {
        int base = 62;

        var base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder hash = new StringBuilder();

        while (num > 0) { //do conversion of `num` to base 62
            var remainder = num % base;
            var charAtRem = base62.charAt((int) remainder);
            hash.append(charAtRem);
            num /= base;
        }

        return hash.reverse().toString();
    }

    private int deHash(String hash) {
        var base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        var base = 62;

        var hashArr = new StringBuilder(hash).reverse().toString();
        var baseTenNumber = 0;
        var digit = 0;
        for (char c: hashArr.toCharArray()) {
            var cIndex = base62.indexOf(c);
            baseTenNumber += cIndex * Math.pow(base,  digit);
            digit += 1;
        }

        return baseTenNumber;
    }
}