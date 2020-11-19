package com.epam.university.java.core.task063;

public class Task063Impl implements Task063 {
    @Override
    public Integer calcDigitalRoot(Integer number) {
        if (number == null) {
            throw new IllegalArgumentException();
        }

        String str = String.valueOf(number);

        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            sum += Integer.parseInt(str.charAt(i) + "");
        }

        return sum - 10 < 0 ? sum : calcDigitalRoot(sum);
    }
}
