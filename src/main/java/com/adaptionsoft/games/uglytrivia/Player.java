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

    public void setNewLocation(int location) {
        this.location = this.location + location;
        if (this.location > 11) this.location = this.location - 12;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }
}
