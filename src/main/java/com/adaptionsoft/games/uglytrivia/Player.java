package com.adaptionsoft.games.uglytrivia;

public class Player {
    String name;
    int goldCoinCounts;
    int location;
    boolean inPenaltyBox;

    public Player(String name) {
        this.name = name;
        this.goldCoinCounts = 0;
        this.location = 0;
        this.inPenaltyBox = false;
    }

    public String getName() {
        return name;
    }

    public int getGoldCoinCounts() {
        return goldCoinCounts;
    }

    public void addOneGoldCoin(){
        goldCoinCounts++;
    }

    public int getLocation() {
        return location;
    }

    public void setNewLocation(int move, int nbCells) {
        location = location + move;
        if (location >= nbCells) location = location - nbCells;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }
}
