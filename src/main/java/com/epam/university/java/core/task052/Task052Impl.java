package com.epam.university.java.core.task052;

public class Task052Impl implements Task052 {
    @Override
    public boolean validateCard(long number) {
        String numberString = String.valueOf(number);
        if (numberString.length() > 19 || numberString.length() < 14 || number < 0) {
            throw new IllegalArgumentException();
        }

        StringBuilder builder = new StringBuilder(
                numberString.substring(0, numberString.length() - 1));
        builder.reverse();
        char[] chars = builder.toString().toCharArray();

        for (int i = 0; i < chars.length; i += 2) {
            int currentDigit = Integer.parseInt(String.valueOf(chars[i]));
            currentDigit *= 2;
            if (currentDigit >= 10) {
                String curDigStr = String.valueOf(currentDigit);
                int i1 = Integer.parseInt(String.valueOf(curDigStr.charAt(0)));
                int i2 = Integer.parseInt(String.valueOf(curDigStr.charAt(1)));
                currentDigit = i1 + i2;
            }
            String s = String.valueOf(currentDigit);
            chars[i] = s.toCharArray()[0];
        }

        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            sum += Integer.parseInt(String.valueOf(chars[i]));
        }

        String s = String.valueOf(sum);
        int i = Integer.parseInt(String.valueOf(s.charAt(s.length() - 1)));
        int checkDigit = Integer.parseInt(String.valueOf(
                numberString.charAt(numberString.length() - 1)));

        return 10 - i == checkDigit;
    }
}
