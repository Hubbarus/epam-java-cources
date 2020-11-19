package com.epam.university.java.core.task048;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Task048Impl implements Task048 {
    @Override
    public Collection<Integer> getArmstrongNumbers(Integer from, Integer to) {
        verify(from, to);

        List<Integer> result = new ArrayList<>();
        for (int i = from; i < to; i++) {
            int range = 0;
            range = getRange(i, range);

            int sum = sumThis(i, range);

            if (sum == i) {
                result.add(i);
            }
        }
        return result;
    }

    private void verify(Integer from, Integer to) {
        if (from == null || to == null || from < 0 || to < 0) {
            throw new IllegalArgumentException();
        }
    }

    private int sumThis(int i, int range) {
        int sum = 0;
        for (int j = 0; j < range; j++) {
            int digit = i % 10;
            sum += Math.pow(digit, range);
            i = i / 10;
        }
        return sum;
    }

    private int getRange(int i, int range) {
        int num = i;

        while (num != 0) {
            num /= 10;
            ++range;
        }
        return range;
    }
}
