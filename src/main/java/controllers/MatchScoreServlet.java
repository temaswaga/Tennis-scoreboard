package controllers;

import dto.CurrentMatch;
import service.MatchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import validation.MatchScoreValidationService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidString = req.getParameter("uuid");

        MatchScoreValidationService.uuidStringValidation(uuidString, resp);

        try {
            UUID uuid = UUID.fromString(uuidString);

            if (MatchService.ongoingMatches.containsKey(uuid)) {
                CurrentMatch currentMatch = MatchService.ongoingMatches.get(uuid);
                MatchService.setAttributesForMatchScoreServlet(req, currentMatch, uuid);
                req.getRequestDispatcher("match-score.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Match not found");
            }

        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid UUID format");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uuidString = req.getParameter("uuid");
        String player = req.getParameter("player");

        UUID uuid = UUID.fromString(uuidString);
        CurrentMatch currentMatch = MatchService.ongoingMatches.get(uuid);

        if(currentMatch.getWinner() == null) {
        MatchService.winPoint(player, uuidString);
        }

        MatchService.setAttributesForMatchScoreServlet(req, currentMatch, uuid);

        resp.sendRedirect("match-score?uuid=" + uuid);
    }
}