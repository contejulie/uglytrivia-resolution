package com.adaptionsoft.games.trivia;

import static org.assertj.core.api.Assertions.assertThat;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Random;

import org.junit.contrib.java.lang.system.SystemOutRule;

public class GameTest {

    private Game aGame;
    private GameMaster aGameMaster;
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule();

    private String gameMasterPrinter;
    private String gamePrinter;

    @Before
    public void setup() {
        init();
        systemOutRule.enableLog();
        systemOutRule.clearLog();
    }

    private void init() {
        aGame = new Game();
        aGameMaster = new GameMaster();
        gameMasterPrinter = "";
        gamePrinter = "";
    }

    @Test
    public void shouldCompareGameAndGameMaster() {

        for (int i = 0; i < 40; i++) {
            createPlayer(3);
            systemOutRule.clearLog();

            gameMasterRunner(i);
            gameMasterPrinter = getPrinter(gameMasterPrinter);

            gameRunner(i);
            gamePrinter = getPrinter(gamePrinter);

            assertThat(gameMasterPrinter).isEqualTo(gamePrinter);
            init();
        }
    }

    @Test
    public void shouldAddPlayerToTheGame() {
        boolean isAddedPlayerToGame = aGame.add("test");
        gamePrinter = getPrinter(gamePrinter);
        boolean isAddedPlayerToGameMaster = aGameMaster.add("test");
        gameMasterPrinter = getPrinter(gameMasterPrinter);
        assertThat(isAddedPlayerToGame).isEqualTo(isAddedPlayerToGameMaster);
        assertThat(gameMasterPrinter).isEqualTo(gamePrinter);
    }

    @Test
    public void shouldReturnRockQuestion() {
        assertThat(aGame.createRockQuestion(1)).isEqualTo(aGameMaster.createRockQuestion(1));
    }

    @Test
    public void shouldGetPlayerCount() {
        createPlayer(1);
        assertThat(aGameMaster.howManyPlayers()).isEqualTo(aGame.howManyPlayers()).isEqualTo(1);
    }

    @Test
    public void shouldCheckIfTheGameIsPlayable() {
        createPlayer(2);
        assertThat(aGameMaster.isPlayable()).isEqualTo(aGame.isPlayable()).isEqualTo(true);
    }

    @Test
    public void shouldCheckIfTheGameIsNotPlayable() {
        createPlayer(1);
        assertThat(aGameMaster.isPlayable()).isEqualTo(aGame.isPlayable()).isEqualTo(false);
    }

    private void createPlayer(int nbPlayer) {
        for (int i = 0; i < nbPlayer; i++) {
            assertThat(aGameMaster.add("test" + i)).isEqualTo(aGame.add("test" + i));
        }
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
