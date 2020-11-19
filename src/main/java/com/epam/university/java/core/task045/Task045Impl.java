package com.epam.university.java.core.task045;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Task045Impl implements Task045 {
    @Override
    public String doAnagram(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }

        String[] s = input.split(" ");
        if (s.length == 0) {
            return " ";
        }
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length; i++) {
            String str = s[i];
            char[] chars = str.toCharArray();
            str = getCleanString(str);

            StringBuilder sb = new StringBuilder(str);
            sb.reverse();
            List<Character> resultChars = getResultList(sb);
            Iterator<Character> iterator = resultChars.iterator();

            for (int j = 0; j < chars.length; j++) {
                if (Character.isLetter(chars[j])) {
                    chars[j] = iterator.next();
                }
            }

            result.append(new String(chars));
            if (i != s.length - 1) {
                result.append(" ");
            }
        }
        return result.toString().trim();
    }

    private String getCleanString(String str) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {
                res.append(str.charAt(i));
            }
        }
        return res.toString();
    }

    private List<Character> getResultList(StringBuilder sb) {
        List<Character> res = new ArrayList<>();
        String s = sb.toString();
        for (int i = 0; i < s.length(); i++) {
            res.add(s.charAt(i));
        }
        return res;
    }
}
