package com.epam.university.java.core.task064;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Task064Impl implements Task064 {

    @Override
    public Player determineWinner(String firstHand, String secondHand, String board) {
        verify(firstHand, secondHand, board);

        Collection<Card> firstPlayerHand = parseHandToList(firstHand);
        Collection<Card> secondPlayerHand = parseHandToList(secondHand);

        PlayerImpl firstPlayer = new PlayerImpl(1, firstPlayerHand);
        PlayerImpl secondPlayer = new PlayerImpl(2, secondPlayerHand);

        Collection<Card> boardRiver = parseHandToList(board);

        PlayingUtil.getHighestCombination(firstPlayer, boardRiver);
        PlayingUtil.getHighestCombination(secondPlayer, boardRiver);

        int ordinalFirst = firstPlayer.getHighestCombination().ordinal();
        int ordinalSecond = secondPlayer.getHighestCombination().ordinal();
        if (ordinalFirst == ordinalSecond) {
            return PlayingUtil.checkCombinationsRank(firstPlayer, secondPlayer);
        }

        return ordinalFirst > ordinalSecond ? firstPlayer : secondPlayer;
    }

    private Collection<Card> parseHandToList(String hand) {
        Collection<Card> playerCards = new ArrayList<>();
        String[] firstSplit = hand.split(",");

        for (String str : firstSplit) {
            Card card = getParsedCard(str);
            playerCards.add(card);
        }
        return playerCards;
    }

    private Card getParsedCard(String str) {
        int rank = 0;
        Suit suit = null;

        if (str.length() == 2) {
            try {
                rank = Integer.parseInt(str.charAt(0) + "");
            } catch (NumberFormatException e) {
                rank = getImageCardRank(str.charAt(0));
            }

            suit = getCardSuit(str.charAt(1));
        } else {
            rank = Integer.parseInt(str.substring(0, 2));
            suit = getCardSuit(str.charAt(2));
        }

        return new CardImpl(rank, suit);
    }

    private Suit getCardSuit(char charAt) {
        switch (charAt) {
            case 'c': return Suit.CLUB;
            case 'd': return Suit.DIAMOND;
            case 'h': return Suit.HEART;
            case 's': return Suit.SPADE;
            default: return null;
        }
    }

    private int getImageCardRank(char charAt) {
        switch (charAt) {
            case 'J': return 11;
            case 'Q': return 12;
            case 'K': return 13;
            case 'A': return 14;
            default: return 0;
        }
    }

    private void verify(String firstHand, String secondHand, String board) {
        if (firstHand == null || secondHand == null || board == null) {
            throw new IllegalArgumentException();
        }

        String[] firstH = firstHand.split(",");
        String[] secondH = secondHand.split(",");
        String[] boardSplit = board.split(",");

        if (firstH.length != 2 || secondH.length != 2 || boardSplit.length != 5) {
            throw new IllegalArgumentException();
        }

        Set<String> cardsSet = new HashSet<>();
        Collections.addAll(cardsSet, firstH);
        Collections.addAll(cardsSet, secondH);
        Collections.addAll(cardsSet, boardSplit);

        if (cardsSet.size() != 9) {
            throw new IllegalArgumentException();
        }
    }
}
