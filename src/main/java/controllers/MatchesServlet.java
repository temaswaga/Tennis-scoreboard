package controllers;

import dao.MatchDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Match;
import service.MatchService;
import validation.MatchesValidationService;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageParam = req.getParameter("page");
        String filterByPlayerNameParam = req.getParameter("filter_by_player_name");

        int currentPage = MatchesValidationService.pageOfMatchesValidationService(pageParam, resp, filterByPlayerNameParam);
        MatchesValidationService.filterByPlayerNameValidationService(filterByPlayerNameParam);

        MatchService.setAttributesForMatchesServletHelper(filterByPlayerNameParam, req, currentPage);

        req.getRequestDispatcher("matches.jsp").forward(req, resp);
    }
}
