package com.epam.university.java.core.task043;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Task043Impl implements Task043 {

    private List<String> morseAlphabet = Arrays.asList(
            ".----", "..---", "...--", "....-", ".....",
            "-....", "--...", "---..", "----.", "-----",

            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
            "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-",
            "..-", "...-", ".--", "-..-", "-.--", "--..",

            ".-.-.-", "--..--", "..--.."
    );

    private List<Character> alphabet = Arrays.asList(
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '.', ',', '?');

    @Override
    public String encode(String sourceString) {
        validate(sourceString);
        StringBuilder builder = new StringBuilder();
        String[] words = sourceString.split(" ");
        for (int i = 0; i < words.length; i++) {
            String[] letters = words[i].split("");
            for (int j = 0; j < letters.length; j++) {
                int index = alphabet.indexOf(letters[j].charAt(0));
                builder.append(morseAlphabet.get(index));
                if (j != letters.length - 1) {
                    builder.append(" ");
                }
            }

            if (i != words.length - 1) {
                builder.append("   ");
            }
        }

        return builder.toString().trim();
    }

    @Override
    public String decode(String sourceString) {
        validate(sourceString);
        StringBuilder builder = new StringBuilder();
        String[] words = sourceString.split("   ");
        for (int i = 0; i < words.length; i++) {
            String[] letters = words[i].split(" ");
            for (int j = 0; j < letters.length; j++) {
                int index = morseAlphabet.indexOf(letters[j]);
                builder.append(alphabet.get(index));
            }

            if (i != words.length - 1) {
                builder.append(" ");
            }
        }

        return builder.toString().trim();
    }

    private void validate(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
    }
}
