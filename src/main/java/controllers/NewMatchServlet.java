package controllers;

import jakarta.servlet.ServletException;
import models.Player;
import service.MatchService;
import service.PlayerService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import validation.NewMatchValidationService;

import java.io.IOException;
import java.util.UUID;


@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String firstPlayerName = req.getParameter("playerOne");
        String secondPlayerName = req.getParameter("playerTwo");

        if (!NewMatchValidationService.arePlayersNamesValid(firstPlayerName, secondPlayerName)) {
            req.getRequestDispatcher("new-match.jsp").forward(req, resp);
            NewMatchValidationService.playerNamesValidation(firstPlayerName, secondPlayerName, req, resp);
        } else {
            Player firstPlayer = new Player(firstPlayerName);
            Player secondPlayer = new Player(secondPlayerName);

            PlayerService.findOrSave(firstPlayer);
            PlayerService.findOrSave(secondPlayer);

            UUID currentUUID = MatchService.registrateNewMatch(firstPlayer, secondPlayer);

            resp.sendRedirect("match-score?uuid=" + currentUUID);
        }
    }
}