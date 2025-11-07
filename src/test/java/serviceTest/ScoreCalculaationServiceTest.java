package serviceTest;

import dto.CurrentMatch;
import models.Player;
import dto.Score;
import org.junit.experimental.runners.Enclosed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import service.ScoreCalculationService;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreCalculaationServiceTest {
    private CurrentMatch currentMatch;
    private Player player1;
    private Score score1;
    private Player player2;
    private Score score2;

    @BeforeEach
    void setUp() {
        player1 = new Player("Player1");
        player2 = new Player("Player2");
        score1 = new Score(0,0,0);
        score2 = new Score(0,0,0);
        currentMatch = new CurrentMatch(player1, score1, player2, score2, null);
    }

    @Nested
    @DisplayName("Тесты подсчета очков в гейме")
    public class GetScoreByPlayerName {

        @Test
        @DisplayName("Переход в тай брейк при счёта 0-6-0/0-6-0")
        public void getScoreByPlayerName() {
            score1.setGames(6);
            score2.setGames(6);

            ScoreCalculationService.winPoint(currentMatch, "player1");

            assertEquals(1, score1.getPoints());
            assertEquals(0, score2.getPoints());
        }

        @Test
        @DisplayName("Переход от 40-40 к advantage")
        void testDeuceToAdvantage() {
            score1.setPoints(40);
            score2.setPoints(40);

            ScoreCalculationService.winPoint(currentMatch, "player1");

            assertEquals(50, score1.getPoints());
            assertEquals(40, score2.getPoints());
        }

        @Test
        @DisplayName("Переход от 40-50 к 40-40")
        void testDeuceToReverseAdvantage() {
            score1.setPoints(50);
            score2.setPoints(40);

            ScoreCalculationService.winPoint(currentMatch, "player2");

            assertEquals(40, score1.getPoints());
            assertEquals(40, score2.getPoints());
        }
    }

}
