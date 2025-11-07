package service;

import dto.CurrentMatch;
import dto.Score;

public class ScoreCalculationService {
    private static final int DEUCE_POINT = 40;
    private static final int ADVANTAGE_POINT = 50;
    private static final int SETS_FOR_WIN = 2;

    public static void winPoint(CurrentMatch currentMatch, String playerWhoWon) {
        Score winnerScore;
        Score loserScore;

        if ("player1".equals(playerWhoWon)) {
            winnerScore = currentMatch.getFirstPlayerScore();
            loserScore = currentMatch.getSecondPlayerScore();
        } else if ("player2".equals(playerWhoWon)) {
            winnerScore = currentMatch.getSecondPlayerScore();
            loserScore = currentMatch.getFirstPlayerScore();
        } else {
            return;
        }

        boolean isTieBreak = winnerScore.getGames() == 6 && loserScore.getGames() == 6;

        if (isTieBreak) {
            handleTieBreakPoint(currentMatch, winnerScore, loserScore);
        } else {
            handleNormalPoint(currentMatch, winnerScore, loserScore);
        }

        if (currentMatch.getWinner() != null) {
            MatchService.saveEndedMatch(currentMatch);
        }
    }

    private static void handleNormalPoint(CurrentMatch currentMatch, Score winnerScore, Score loserScore) {
        if (winnerScore.getPoints() == ADVANTAGE_POINT) {
            winGame(currentMatch, winnerScore, loserScore);
            return;
        }

        if (loserScore.getPoints() == ADVANTAGE_POINT) {
            loserScore.setPoints(DEUCE_POINT);
            return;
        }

        if (winnerScore.getPoints() == DEUCE_POINT && loserScore.getPoints() == DEUCE_POINT) {
            winnerScore.setPoints(ADVANTAGE_POINT);
            return;
        }

        if (winnerScore.getPoints() == DEUCE_POINT) {
            winGame(currentMatch, winnerScore, loserScore);
            return;
        }

        if (winnerScore.getPoints() == 30) {
            winnerScore.setPoints(winnerScore.getPoints() + 10);
        } else {
            winnerScore.setPoints(winnerScore.getPoints() + 15);
        }
    }

    private static void handleTieBreakPoint(CurrentMatch currentMatch, Score winnerScore, Score loserScore) {
        winnerScore.setPoints(winnerScore.getPoints() + 1);

        if (winnerScore.getPoints() >= 7 && (winnerScore.getPoints() - loserScore.getPoints()) >= 2) {
            winGame(currentMatch, winnerScore, loserScore);
        }
    }

    private static void winGame(CurrentMatch currentMatch, Score winnerScore, Score loserScore) {
        winnerScore.setGames(winnerScore.getGames() + 1);

        winnerScore.setPoints(0);
        loserScore.setPoints(0);

        boolean hasWonSet = (winnerScore.getGames() >= 6 && winnerScore.getGames() - loserScore.getGames() >= 2) || winnerScore.getGames() == 7;

        if (hasWonSet) {
            winSet(currentMatch, winnerScore, loserScore);
        }
    }

    private static void winSet(CurrentMatch currentMatch, Score winnerScore, Score loserScore) {
        winnerScore.setSets(winnerScore.getSets() + 1);

        winnerScore.setGames(0);

        if (winnerScore.getSets() == SETS_FOR_WIN) {
            if (currentMatch.getFirstPlayerScore() == winnerScore) {
                currentMatch.setWinner(currentMatch.getPlayerOne());
            } else {
                currentMatch.setWinner(currentMatch.getPlayerTwo());
            }
        } else {
            loserScore.setGames(0);
        }
    }
}