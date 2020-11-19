package com.epam.university.java.core.task064;

import java.util.Collection;
import java.util.Collections;

public class PlayerImpl implements Player {
    private int id;
    private Collection<Card> hand;
    private Combination highestCombination;
    private int combinationSum;

    public PlayerImpl(int id, Collection<Card> hand) {
        this.id = id;
        this.hand = hand;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Collection<Card> getPlayerHand() {
        return hand;
    }

    @Override
    public void setPlayerHand(Collection<Card> hand) {
        this.hand = hand;
    }

    public Combination getHighestCombination() {
        return highestCombination;
    }

    public void setHighestCombination(Combination highestCombination) {
        this.highestCombination = highestCombination;
    }

    public int getCombinationSum() {
        return combinationSum;
    }

    public void setCombinationSum(int combinationSum) {
        this.combinationSum = combinationSum;
    }
}
