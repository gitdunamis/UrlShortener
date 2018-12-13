package com.dunamis.util;

public class HashFunctions {

    //This method returns the hash of `str`

    public static String encodeBase62(long num) {
        //base62 character set
        var base62 = Constant.BASE62;

        if (num == 0) return String.valueOf(base62.charAt(0));

        StringBuilder hash = new StringBuilder();

        int base = 62;

        while (num > 0) { //do conversion of `num` to base 62
            var remainder = num % base;
            var charAtRem = base62.charAt(Math.toIntExact(remainder));
            hash.append(charAtRem);
            num = Math.floorDiv(num , base);
        }

        return hash.reverse().toString();
    }


    //This method returns the number of a string `hash`

    public static int decodeBase62(String hash) {
        var base62 = Constant.BASE62;

        var base = 62;

        var baseTenNumber = 0;

        var hashArr = new StringBuilder(hash).reverse().toString();

        var digit = 0;

        for (char c: hashArr.toCharArray()) { //01-10
            var cIndex = base62.indexOf(c);
            baseTenNumber += cIndex * Math.pow(base,  digit);
            digit += 1;
        }

        return baseTenNumber;
    }

}
