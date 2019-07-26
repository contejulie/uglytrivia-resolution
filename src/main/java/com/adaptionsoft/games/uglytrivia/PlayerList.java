package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class PlayerList {
    private int currentPlayer = 0;
    private List<Player> players = new ArrayList<Player>();

    public PlayerList(List<String> names) {
        names.forEach(name -> players.add(new Player(name)));
    }

    public PlayerList() {
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public int playerCount() {
        return players.size();
    }

    public Player nextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return players.get(currentPlayer);
    }

    public boolean add(String playerName) {
        Player player = new Player(playerName);

        players.add(player);

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }
}
