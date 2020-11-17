package com.epam.university.java.core.task066;


public class Task066Impl implements Task066 {
    /**
     * <p>
     * Given an long limiter and String, find the number
     * of letter 'a' in the first limiter letters of infinite input String.
     *
     * For example, if the String s = "abcac", and int limiter = 10,
     * the substring we consider is "abcacabcac", the first 10 characters
     * of this infinite string. There are 4 occurrences of 'a' in the substring.
     *
     * Ex.2  ("layla", 9) -> laylalayl -> output 3;
     * Ex.3 ("pancho", 20) -> panchopanchopanchopa -> output 4;
     *
     * </p>
     *
     * @param limiter long
     * @param infiniteString String
     * @return long - the count of 'a's.
     */

    @Override
    public long repeatString(String infiniteString, long limiter) {
        if (infiniteString == null || limiter < 0) {
            throw new IllegalArgumentException();
        }

        long quantityOfA = 0;

        quantityOfA = getQuantityOfA(infiniteString, quantityOfA, infiniteString.length());

        long mult = limiter / infiniteString.length();
        quantityOfA *= mult;

        long finMult = mult * infiniteString.length();

        if (finMult == limiter) {
            return quantityOfA;
        } else {
            quantityOfA = getQuantityOfA(infiniteString, quantityOfA, limiter - finMult);
            return quantityOfA;
        }
    }

    private long getQuantityOfA(String infiniteString, long quantityOfA, long length) {
        for (int i = 0; i < length; i++) {
            if (infiniteString.charAt(i) == 'a') {
                quantityOfA++;
            }
        }
        return quantityOfA;
    }
}
