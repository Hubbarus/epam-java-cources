package com.epam.university.java.core.task064;

import java.util.Objects;

public class CardImpl implements Card {
    private int cardRank;
    private Suit suit;

    public CardImpl(int cardRank, Suit suit) {
        this.cardRank = cardRank;
        this.suit = suit;
    }

    @Override
    public int getCardRank() {
        return cardRank;
    }

    @Override
    public void setCardRank(int rank) {
        this.cardRank = rank;
    }

    @Override
    public Suit getSuit() {
        return suit;
    }

    @Override
    public void setCardSuit(Suit suit) {
        this.suit = suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CardImpl)) {
            return false;
        }

        CardImpl card = (CardImpl) o;
        return getCardRank() == card.getCardRank()
                && getSuit() == card.getSuit();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardRank(), getSuit());
    }
}
