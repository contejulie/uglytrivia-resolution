package com.adaptionsoft.games.trivia;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.PlayerList;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import org.junit.contrib.java.lang.system.SystemOutRule;

public class GameTest {

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

    private void init() {
        PlayerList playerList = new PlayerList(playerListName);

        aGame = new Game(playerList);
        aGameMaster = new GameMaster();
        gameMasterPrinter = "";
        gamePrinter = "";
    }

    @Test
    public void shouldCompareGameAndGameMaster() {

        for (int i = 0; i < 40; i++) {
            init();
            initGameMasterPlayer();
            systemOutRule.clearLog();

            gameMasterRunner(i);
            gameMasterPrinter = getPrinter(gameMasterPrinter);

            gameRunner(i);
            gamePrinter = getPrinter(gamePrinter);

            assertThat(gameMasterPrinter).isEqualTo(gamePrinter);

        }
    }

    private void initGameMasterPlayer() {
        for(String name : playerListName) {
            aGameMaster.add(name);
        }
    }



    @Test
    public void shouldCheckIfTheGameIsPlayable() {
        createPlayer(2);
        aGame = new Game(createPlayerList(2));
        assertThat(aGameMaster.isPlayable()).isEqualTo(aGame.isPlayable()).isEqualTo(true);
    }

    @Test
    public void shouldCheckIfTheGameIsNotPlayable() {
        createPlayer(1);
        aGame = new Game(createPlayerList(1));
        assertThat(aGameMaster.isPlayable()).isEqualTo(aGame.isPlayable()).isEqualTo(false);
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

    private void gameRunner(int seed) {
        boolean notAWinner;

        Random rand = new Random(seed);
        do {
            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }
        } while (notAWinner);
    }

    private void gameMasterRunner(int seed) {
        boolean notAWinner;

        Random rand = new Random(seed);

        do {
            aGameMaster.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = aGameMaster.wrongAnswer();
            } else {
                notAWinner = aGameMaster.wasCorrectlyAnswered();
            }
        } while (notAWinner);
    }

    private String getPrinter(String printer) {
        printer = printer + systemOutRule.getLog();
        systemOutRule.clearLog();
        return printer;
    }
}
