package com.epam.university.java.core.task040;

public class Task040Impl implements Task040 {

    private int[] rolls;

    @Override
    public int countScore(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException();
        }
        String[] strRolls = str.replaceAll(" ", "").split("");
        rolls = new int[strRolls.length];
        for (int i = 0; i < strRolls.length; i++) {
            try {
                rolls[i] = Integer.valueOf(strRolls[i]);
            } catch (NumberFormatException e) {
                if (strRolls[i].equals("/")) {
                    rolls[i] = 10 - rolls[i - 1];
                } else {
                    rolls[i] = 10;
                }
            }
        }

        int frame = 0;
        int score = 0;
        for (int i = 0; i < 10; i++) {
            if (rolls[frame] == 10) {
                score += 10 + rolls[frame + 1] + rolls[frame + 2];
                frame++;
            } else if (rolls[frame] + rolls[frame + 1] == 10) {
                score += 10 + rolls[frame + 2];
                frame += 2;
            } else {
                score += rolls[frame] + rolls[frame + 1];
                frame += 2;
            }
        }
        return score;
    }
}
