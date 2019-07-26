package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.PlayerList;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerListTest {
    private Game aGame;
    private GameMaster aGameMaster;
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule();

    private String gameMasterPrinter;
    private String gamePrinter;
    private List<String> playerListName = asList("test0", "test1", "test2");

    @Before
    public void setup() {
        systemOutRule.enableLog();
        systemOutRule.clearLog();
        aGameMaster = new GameMaster();
    }
    @Test
    public void shouldAddPlayerToTheGame() {
        PlayerList playerList = new PlayerList();

        boolean isAddedPlayerToGame = playerList.add("test");
        gamePrinter = getPrinter(gamePrinter);
        boolean isAddedPlayerToGameMaster = aGameMaster.add("test");
        gameMasterPrinter = getPrinter(gameMasterPrinter);
        assertThat(isAddedPlayerToGame).isEqualTo(isAddedPlayerToGameMaster);
        assertThat(gameMasterPrinter).isEqualTo(gamePrinter);
    }

    @Test
    public void shouldGetPlayerCount() {
        createPlayer(1);
        aGame = new Game(createPlayerList(1));
        assertThat(aGameMaster.howManyPlayers()).isEqualTo(aGame.howManyPlayers()).isEqualTo(1);
    }

    private String getPrinter(String printer) {
        printer = printer + systemOutRule.getLog();
        systemOutRule.clearLog();
        return printer;
    }

    private void createPlayer(int nbPlayer) {
        for (int i = 0; i < nbPlayer; i++) {
            aGameMaster.add("test" + i);
        }
    }

    private PlayerList createPlayerList(int nbPlayer) {
        PlayerList playerList = new PlayerList();
        for (int i = 0; i < nbPlayer; i++) {
            playerList.add("test" + i);
        }
        return playerList;
    }


}
