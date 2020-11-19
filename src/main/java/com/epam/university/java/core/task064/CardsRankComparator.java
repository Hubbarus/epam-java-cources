package com.epam.university.java.core.task064;

import java.util.Comparator;

public class CardsRankComparator implements Comparator<Card> {

    @Override
    public int compare(Card o1, Card o2) {
        return o1.getCardRank() - o2.getCardRank();
    }
}
