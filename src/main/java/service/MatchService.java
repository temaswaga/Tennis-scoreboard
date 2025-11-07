package service;

import dao.MatchDao;
import entity.CurrentMatch;
import entity.Score;
import models.Match;
import models.Player;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MatchService {
    public static HashMap<UUID, CurrentMatch> ongoingMatches = new HashMap<>();

    public static UUID registrateNewMatch(Player firstPlayer, Player secondPlayer) {
        Score firstPlayerScore = new Score(0,0, 0);
        Score secondPlayerScore = new Score(0,0, 0);

        CurrentMatch currentMatch = new CurrentMatch(firstPlayer, firstPlayerScore, secondPlayer, secondPlayerScore, null);
        UUID CurrentUUID = UUID.randomUUID();
        ongoingMatches.put(CurrentUUID, currentMatch);
        return CurrentUUID;
    }

    public static void winPoint(String player, String uuidString) {
        UUID uuid = UUID.fromString(uuidString);
        ScoreCalculationService.winPoint(ongoingMatches.get(uuid), player);
    }

    public static void saveEndedMatch(CurrentMatch currentMatch) {
        Match endedMatch = new Match(currentMatch.getPlayerOne(), currentMatch.getPlayerTwo(), currentMatch.getWinner());
        MatchDao.save(endedMatch);
    }

    public static void setAttributesForMatchesServletHelper(String filterByPlayerNameParam, HttpServletRequest req, int requestPage) {
        List<Match> matches = List.of();
        int totalNumberOfPages = 0;

        if (filterByPlayerNameParam.isEmpty()) {
            totalNumberOfPages = MatchDao.getTotalPages();
            matches = MatchDao.findAll().get();

        } else if (MatchDao.findByName(filterByPlayerNameParam).isPresent()){
            totalNumberOfPages = MatchDao.getTotalPagesWithNameFilter(filterByPlayerNameParam);
            matches = MatchDao.findByName(filterByPlayerNameParam).get();
        }

        MatchService.setAttributesForMatchesServlet(req, matches, requestPage, totalNumberOfPages, filterByPlayerNameParam);
    }

    public static void setAttributesForMatchScoreServlet(HttpServletRequest req, CurrentMatch currentMatch, UUID uuid) {
        if(currentMatch.getWinner() != null) {
            if (currentMatch.getWinner() == currentMatch.getPlayerOne()) {
                req.setAttribute("player1Name", currentMatch.getPlayerOne().getName() + " \uD83C\uDFC6");
                req.setAttribute("player2Name", currentMatch.getPlayerTwo().getName());
            } else {
                req.setAttribute("player1Name", currentMatch.getPlayerOne().getName());
                req.setAttribute("player2Name", currentMatch.getPlayerTwo().getName() + " \uD83C\uDFC6");
            }
            req.setAttribute("matchTitle", currentMatch.getWinner().getName() + " won!!!");
            req.setAttribute("matchUuid", uuid);
            req.setAttribute("player1Sets", currentMatch.getFirstPlayerScore().getSets());
            req.setAttribute("player2Sets", currentMatch.getSecondPlayerScore().getSets());
            req.setAttribute("player1Games", currentMatch.getFirstPlayerScore().getGames());
            req.setAttribute("player2Games", currentMatch.getSecondPlayerScore().getGames());
            req.setAttribute("player1Points", currentMatch.getFirstPlayerScore().getPoints());
            req.setAttribute("player2Points", currentMatch.getSecondPlayerScore().getPoints());
        } else {
            req.setAttribute("matchTitle", currentMatch.getPlayerOne().getName() + " vs " + currentMatch.getPlayerTwo().getName());
            req.setAttribute("matchUuid", uuid);
            req.setAttribute("player1Name", currentMatch.getPlayerOne().getName());
            req.setAttribute("player2Name", currentMatch.getPlayerTwo().getName());
            req.setAttribute("player1Sets", currentMatch.getFirstPlayerScore().getSets());
            req.setAttribute("player2Sets", currentMatch.getSecondPlayerScore().getSets());
            req.setAttribute("player1Games", currentMatch.getFirstPlayerScore().getGames());
            req.setAttribute("player2Games", currentMatch.getSecondPlayerScore().getGames());

            if (currentMatch.getFirstPlayerScore().getPoints() == 50) {
                req.setAttribute("player1Points", "AD");
            } else {
                req.setAttribute("player1Points", currentMatch.getFirstPlayerScore().getPoints());
            }

            if (currentMatch.getSecondPlayerScore().getPoints() == 50) {
                req.setAttribute("player2Points", "AD");
            } else {
                req.setAttribute("player2Points", currentMatch.getSecondPlayerScore().getPoints());
            }
        }
    }

    public static void setAttributesForMatchesServlet(HttpServletRequest req, List<Match> matches, int page, int totalNumberOfPages, String filterByPlayerNameParam) {
        if (page*6-6 < matches.size()) {
            Match match = matches.get(page * 6 - 6);
            req.setAttribute("player1_1Name", match.getPlayer1().getName());
            req.setAttribute("player1_2Name", match.getPlayer2().getName());
            req.setAttribute("winner1_name", match.getWinner().getName());
        }

        if (page*6-5 < matches.size()) {
            Match match = matches.get(page * 6 - 5);
            req.setAttribute("player2_1Name", match.getPlayer1().getName());
            req.setAttribute("player2_2Name", match.getPlayer2().getName());
            req.setAttribute("winner2_name", match.getWinner().getName());
        }

        if (page*6-4 < matches.size()) {
            Match match = matches.get(page * 6 - 4);
            req.setAttribute("player3_1Name", match.getPlayer1().getName());
            req.setAttribute("player3_2Name", match.getPlayer2().getName());
            req.setAttribute("winner3_name", match.getWinner().getName());
        }

        if (page*6-3 < matches.size()) {
            Match match = matches.get(page * 6 - 3);
            req.setAttribute("player4_1Name", match.getPlayer1().getName());
            req.setAttribute("player4_2Name", match.getPlayer2().getName());
            req.setAttribute("winner4_name", match.getWinner().getName());
        }

        if (page*6-2 < matches.size()) {
            Match match = matches.get(page * 6 - 2);
            req.setAttribute("player5_1Name", match.getPlayer1().getName());
            req.setAttribute("player5_2Name", match.getPlayer2().getName());
            req.setAttribute("winner5_name", match.getWinner().getName());
        }

        if (page*6-1 < matches.size()) {
            Match match = matches.get(page * 6 - 1);
            req.setAttribute("player6_1Name", match.getPlayer1().getName());
            req.setAttribute("player6_2Name", match.getPlayer2().getName());
            req.setAttribute("winner6_name", match.getWinner().getName());
        }

        req.setAttribute("totalNumberOfPages", totalNumberOfPages);
        req.setAttribute("currentPage", page);

        if (page == 1) {
            req.setAttribute("page_number_prev", page);
        } else {
            req.setAttribute("page_number_prev", page-1);
        }

        if (page == totalNumberOfPages) {
            req.setAttribute("page_number_next", totalNumberOfPages);
        } else {
            req.setAttribute("page_number_next", page+1);
        }


        req.setAttribute("player_name_filter", filterByPlayerNameParam);
    }
}
