package com.epam.university.java.core.task064;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PlayingUtil {

    /**
     * Calculates score if combinations are equal.
     * @param firstPlayer obj.
     * @param secondPlayer obj.
     * @return the winner.
     */
    public static Player checkCombinationsRank(PlayerImpl firstPlayer, PlayerImpl secondPlayer) {
        int combinationSum1 = firstPlayer.getCombinationSum();
        int combinationSum2 = secondPlayer.getCombinationSum();

        if (combinationSum1 == combinationSum2) {
            return recalculateSum(firstPlayer, secondPlayer);
        }
        return combinationSum1 > combinationSum2 ? firstPlayer : secondPlayer;
    }

    private static Player recalculateSum(Player firstPlayer, Player secondPlayer) {
        int sumFirst = 0;
        int sumSecond = 0;

        for (Card card : firstPlayer.getPlayerHand()) {
            sumFirst += card.getCardRank();
        }

        for (Card card : secondPlayer.getPlayerHand()) {
            sumSecond += card.getCardRank();
        }

        if (sumFirst == sumSecond) {
            return null;
        }

        return sumFirst > sumSecond ? firstPlayer : secondPlayer;
    }

    /**
     * Sets the highest combination from players hand and river.
     * @param player to set the combination.
     * @param river on table.
     */
    public static void getHighestCombination(PlayerImpl player, Collection<Card> river) {
        if (checkRoyalFlush(player, river)
                || checkStraightFlush(player, river)
                || checkFourOfAKind(player, river)
                || checkFullHouse(player, river)
                || checkFlush(player, river)
                || checkStraight(player, river)
                || checkThreeOfAKind(player, river)
                || checkTwoPairs(player, river)
                || checkPair(player, river)) {
            return;
        }
    }

    private static boolean checkPair(PlayerImpl player, Collection<Card> river) {
        List<Card> allCards = getSortedListOfCards(player.getPlayerHand(), river);

        int counter = 1;
        int sum = 0;
        for (int i = 1; i < allCards.size(); i++) {
            int currentRank = allCards.get(i).getCardRank();
            int prevRank = allCards.get(i - 1).getCardRank();

            if (currentRank == prevRank) {
                counter++;
                sum += prevRank;

                if (counter == 2) {
                    sum += currentRank;
                    player.setCombinationSum(sum);
                    player.setHighestCombination(Combination.PAIR);
                    return true;
                }
            } else {
                counter = 1;
                sum = 0;
            }
        }
        return false;
    }

    private static boolean checkTwoPairs(PlayerImpl player, Collection<Card> river) {
        List<Card> allCards = getSortedListOfCards(player.getPlayerHand(), river);

        List<Map.Entry<Integer, Integer>> entries = getEntries(allCards);

        for (int i = 0; i < entries.size(); i++) {
            for (int j = i; j < entries.size(); j++) {
                Map.Entry<Integer, Integer> entry = entries.get(i);
                Map.Entry<Integer, Integer> entry1 = entries.get(j);

                if (entry.getValue() + entry1.getValue() == 4) {
                    player.setHighestCombination(Combination.TWO_PAIRS);
                    player.setCombinationSum(entry.getKey() * entry.getValue()
                            + entry1.getKey() * entry1.getValue());
                    return true;
                }
            }
        }

        return false;

    }

    private static boolean checkThreeOfAKind(PlayerImpl player, Collection<Card> river) {
        List<Card> allCards = getSortedListOfCards(player.getPlayerHand(), river);
        List<Card> fullHand = new ArrayList<>();

        int counter = 1;
        int sum = 0;
        for (int i = 1; i < allCards.size(); i++) {
            int currentRank = allCards.get(i).getCardRank();
            int prevRank = allCards.get(i - 1).getCardRank();

            if (currentRank == prevRank) {
                counter++;
                sum += prevRank;
                fullHand.add(allCards.get(i - 1));

                if (counter == 3) {
                    sum += currentRank;
                    player.setCombinationSum(sum);
                    player.setHighestCombination(Combination.THREE_OF_A_KIND);
                    if (i == 3) {
                        fullHand.add(allCards.get(0));
                        fullHand.add(allCards.get(4));
                    } else if (i > 3) {
                        fullHand.add(allCards.get(0));
                        fullHand.add(allCards.get(1));
                    } else {
                        fullHand.add(allCards.get(i + 1));
                        fullHand.add(allCards.get(i + 2));
                    }
                    player.setPlayerHand(fullHand);
                    return true;
                }
            } else {
                counter = 1;
                sum = 0;
            }
        }
        return false;

    }

    private static boolean checkStraight(PlayerImpl player, Collection<Card> river) {
        List<Card> allCards = getSortedListOfCards(player.getPlayerHand(), river);

        int counter = 1;
        int sum = 0;
        Card ace = null;
        for (int i = 1; i < allCards.size(); i++) {
            Card current = allCards.get(i);
            Card prev = allCards.get(i - 1);

            if (current.getCardRank() == 14) {
                ace = current;
            }

            if (prev.getCardRank() == 14) {
                ace = prev;
            }

            if (current.getCardRank() + 1 == prev.getCardRank()) {
                counter++;
                sum += prev.getCardRank();

                if (counter == 5) {
                    sum += current.getCardRank();
                    player.setHighestCombination(Combination.STRAIGHT);
                    player.setCombinationSum(sum);
                    return true;
                }
            } else {
                counter = 1;
                sum = 0;
            }
        }

        if (ace != null) {
            return secondCheckWithAceInTheBeginning(allCards, ace, player);
        }

        return false;

    }

    private static boolean secondCheckWithAceInTheBeginning(List<Card> allCards,
                                                            Card ace,
                                                            PlayerImpl player) {
        for (Card card : allCards) {
            if (card.equals(ace)) {
                ace.setCardRank(1);
                ace.setCardSuit(card.getSuit());
                allCards.remove(card);
                allCards.add(ace);
                break;
            }
        }

        Collections.sort(allCards, new CardsRankComparator().reversed());

        int counter = 1;
        int sum = 0;
        for (int i = 1; i < allCards.size(); i++) {
            Card current = allCards.get(i);
            Card prev = allCards.get(i - 1);

            if (current.getCardRank() + 1 == prev.getCardRank()) {
                counter++;
                sum += prev.getCardRank();

                if (counter == 5) {
                    sum += current.getCardRank();
                    player.setHighestCombination(Combination.STRAIGHT);
                    player.setCombinationSum(sum);
                    return true;
                }
            } else {
                counter = 1;
                sum = 0;
            }
        }
        return false;
    }

    private static boolean checkFlush(PlayerImpl player, Collection<Card> river) {
        List<Card> allCards = getSortedListOfCards(player.getPlayerHand(), river);
        Collections.sort(allCards, new CardsSuitComparator());

        int counter = 1;
        int sum = 0;
        for (int i = 1; i < allCards.size(); i++) {
            Card current = allCards.get(i);
            Card prev = allCards.get(i - 1);

            if (current.getSuit().equals(prev.getSuit())) {
                counter++;
                sum += prev.getCardRank();

                if (counter == 5) {
                    sum += current.getCardRank();
                    player.setHighestCombination(Combination.FLUSH);
                    player.setCombinationSum(sum);
                    return true;
                }
            } else {
                counter = 1;
            }
        }
        return false;

    }

    private static boolean checkFullHouse(PlayerImpl player, Collection<Card> river) {
        List<Card> allCards = getSortedListOfCards(player.getPlayerHand(), river);
        List<Map.Entry<Integer, Integer>> entries = getEntries(allCards);

        for (int i = 0; i < entries.size(); i++) {
            for (int j = i; j < entries.size(); j++) {
                Map.Entry<Integer, Integer> entry = entries.get(i);
                Map.Entry<Integer, Integer> entry1 = entries.get(j);

                if (entry.getValue() + entry1.getValue() == 5) {
                    player.setHighestCombination(Combination.FULL_HOUSE);
                    player.setCombinationSum(entry.getKey() * entry.getValue()
                            + entry1.getKey() * entry1.getValue());
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean checkFourOfAKind(PlayerImpl player, Collection<Card> river) {
        List<Card> allCards = getSortedListOfCards(player.getPlayerHand(), river);
        List<Card> fullHand = new ArrayList<>();

        int counter = 1;
        int sum = 0;
        for (int i = 1; i < allCards.size(); i++) {
            int currentRank = allCards.get(i).getCardRank();
            int prevRank = allCards.get(i - 1).getCardRank();

            if (currentRank == prevRank) {
                counter++;
                sum += prevRank;
                fullHand.add(allCards.get(i - 1));

                if (counter == 4) {
                    sum += currentRank;
                    player.setCombinationSum(sum);
                    player.setHighestCombination(Combination.FOUR_OF_A_KIND);

                    if (i > 3) {
                        fullHand.add(allCards.get(0));
                    } else {
                        fullHand.add(allCards.get(4));
                    }
                    player.setPlayerHand(fullHand);
                    return true;
                }
            } else {
                counter = 1;
                sum = 0;
                fullHand = new ArrayList<>();
            }
        }
        return false;
    }

    private static boolean checkStraightFlush(PlayerImpl player, Collection<Card> river) {
        List<Card> allCards = getSortedListOfCards(player.getPlayerHand(), river);

        int counter = 1;
        int sum = 0;
        for (int i = 1; i < allCards.size(); i++) {
            Card current = allCards.get(i);
            Card prev = allCards.get(i - 1);

            if (current.getCardRank() + 1 == prev.getCardRank()
                    && current.getSuit().equals(prev.getSuit())) {
                counter++;
                sum += prev.getCardRank();

                if (counter == 5) {
                    sum += current.getCardRank();
                    player.setHighestCombination(Combination.STRAIGHT_FLUSH);
                    player.setCombinationSum(sum);
                    return true;
                }
            } else {
                counter = 1;
                sum = 0;
            }
        }
        return false;
    }

    private static boolean checkRoyalFlush(PlayerImpl player, Collection<Card> river) {
        List<Card> allCards = getSortedListOfCards(player.getPlayerHand(), river);

        int sum = 0;
        Suit checkSuit = allCards.get(0).getSuit();
        int suitChecker = 0;
        for (int i = 0; i < 5; i++) {
            sum += allCards.get(i).getCardRank();
            if (allCards.get(i).getSuit().equals(checkSuit)) {
                suitChecker++;
            }
        }

        if (sum == 60 && suitChecker == 5) {
            player.setCombinationSum(sum);
            player.setHighestCombination(Combination.ROYAL_FLUSH);
            return true;
        } else {
            return false;
        }

    }

    private static List<Card> getSortedListOfCards(Collection<Card> playerHand,
                                                   Collection<Card> river) {
        List<Card> cards = new ArrayList<>();
        cards.addAll(playerHand);
        cards.addAll(river);
        Collections.sort(cards, new CardsRankComparator().reversed());
        return cards;
    }

    private static List<Map.Entry<Integer, Integer>> getEntries(List<Card> allCards) {
        Map<Integer, Integer> rankMap = new LinkedHashMap<>();

        for (Card card : allCards) {
            int cardRank = card.getCardRank();
            if (!rankMap.containsKey(cardRank)) {
                rankMap.put(cardRank, 1);
            } else {
                Integer count = rankMap.get(cardRank);
                count++;
                rankMap.put(cardRank, count);
            }
        }

        return new LinkedList<>(rankMap.entrySet());
    }
}
