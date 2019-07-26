package com.adaptionsoft.games.uglytrivia;

import java.lang.reflect.Type;
import java.util.*;

public class Game {

    private static final int NB_CELLS = 12;
    private PlayerList playerList;
    private Map<TypeCategory, Category> categoryList = new HashMap<>();
    private Map<TypeCategory, String> positionCategory = new HashMap<>();
    private boolean isGettingOutOfPenaltyBox;

    public Game(PlayerList playerList) {
        this.playerList = playerList;
        for (TypeCategory typeCategory : TypeCategory.values()) {
            categoryList.put(typeCategory, new Category(typeCategory));
        }
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }


    public int howManyPlayers() {
        return playerList.playerCount();
    }

    public void roll(int roll) {
        System.out.println(playerList.getCurrentPlayer().getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (playerList.getCurrentPlayer().isInPenaltyBox()) {
            if (getOutOfPenaltyBox(roll)) {
                System.out.println(playerList.getCurrentPlayer().getName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
                return;
            } else {
                isGettingOutOfPenaltyBox = true;
                System.out.println(playerList.getCurrentPlayer().getName() + " is getting out of the penalty box");
            }
        }
        playTurn(roll);
        askQuestion();
    }

    private boolean getOutOfPenaltyBox(int roll) {
        return roll % 2 == 0;
    }

    private void playTurn(int roll) {
        Player currentPlayer = playerList.getCurrentPlayer();
        currentPlayer.setNewLocation(roll, NB_CELLS);

        System.out.println(currentPlayer.getName()
                + "'s new location is "
                + currentPlayer.getLocation());
        System.out.println("The category is " + currentCategory());
    }

    private void askQuestion() {
        System.out.println(categoryList.get(currentCategory()).removeFirstQuestion());
    }


    private TypeCategory currentCategory() {
        Player currentPlayer = playerList.getCurrentPlayer();
        if (currentPlayer.getLocation() == 0) return TypeCategory.POP;
        if (currentPlayer.getLocation() == 4) return TypeCategory.POP;
        if (currentPlayer.getLocation() == 8) return TypeCategory.POP;
        if (currentPlayer.getLocation() == 1) return TypeCategory.SCIENCE;
        if (currentPlayer.getLocation() == 5) return TypeCategory.SCIENCE;
        if (currentPlayer.getLocation() == 9) return TypeCategory.SCIENCE;
        if (currentPlayer.getLocation() == 2) return TypeCategory.SPORTS;
        if (currentPlayer.getLocation() == 6) return TypeCategory.SPORTS;
        if (currentPlayer.getLocation() == 10) return TypeCategory.SPORTS;
        return TypeCategory.ROCK;
    }

    public boolean wasCorrectlyAnswered() {
        if (playerList.getCurrentPlayer().isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                return correctAnswer();
            } else {
                playerList.nextPlayer();
                return true;
            }
        } else {
            System.out.println("Answer was corrent!!!!");
            return correctAnswer();
        }
    }

    private boolean correctAnswer() {
        Player currentPlayer = playerList.getCurrentPlayer();
        currentPlayer.addOneGoldCoin();
        System.out.println(currentPlayer.getName()
                + " now has "
                + currentPlayer.getGoldCoinCounts()
                + " Gold Coins.");

        boolean winner = didPlayerWin();
        playerList.nextPlayer();
        return winner;
    }

    public boolean wrongAnswer() {
        Player currentPlayer = playerList.getCurrentPlayer();

        System.out.println("Question was incorrectly answered");

        System.out.println(currentPlayer.getName() + " was sent to the penalty box");
        currentPlayer.setInPenaltyBox(true);

        playerList.nextPlayer();
        return true;
    }


    private boolean didPlayerWin() {
        return !(playerList.getCurrentPlayer().getGoldCoinCounts() == 6);
    }
}
