package com.epam.university.java.core.task017;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Task017Impl implements Task017 {
    @Override
    public String formatString(Object... args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }

        for (Object o : args) {
            if (o == null) {
                throw new IllegalArgumentException();
            }
        }
        String output = "You know %s, %s!";

        return String.format(output, args);
    }

    @Override
    public String formatNumbers(Object... args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }

        String s = "";
        for (Object o : args) {
            if (o == null) {
                throw new IllegalArgumentException();
            }
            if (o instanceof Double) {
                s = String.format(Locale.ENGLISH,
                        "%.1f, %2.2f, %+2.2f, %s",
                        o, o, o, Double.toHexString((Double) o));
            }
        }

        return s;
    }

    @Override
    public String formatDates(Object... args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }

        StringBuilder output = new StringBuilder();

        SimpleDateFormat format = new SimpleDateFormat("yyyy.dd.MM");
        for (Object o : args) {
            if (o == null) {
                throw new IllegalArgumentException();
            }
            output.append(format.format(o)).append(" ");
        }

        return output.toString().trim();
    }
}
