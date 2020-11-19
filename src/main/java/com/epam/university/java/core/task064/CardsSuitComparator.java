package com.epam.university.java.core.task064;

import java.util.Comparator;

public class CardsSuitComparator implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        return o1.getSuit().ordinal() - o2.getSuit().ordinal();
    }
}
