package validation;

import dao.MatchDao;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MatchesValidationService {

    public static int pageOfMatchesValidationService(String pageParam, HttpServletResponse resp, String filterByPlayerNameParam) throws IOException {

        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                int totalNumberOfPages = 0;
                int page = Integer.parseInt(pageParam);

                if (page < 1) {
                    page = 1;
                }

                if (filterByPlayerNameParam.isEmpty()) {
                    totalNumberOfPages = MatchDao.getTotalPages();
                } else if (MatchDao.findByName(filterByPlayerNameParam).isPresent()) {
                    totalNumberOfPages = MatchDao.getTotalPagesWithNameFilter(filterByPlayerNameParam);
                }

                if (totalNumberOfPages < 1) {
                    totalNumberOfPages = 1;
                }

                if (page > totalNumberOfPages) {
                    page = totalNumberOfPages;
                }

                return page;
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid page format");
            }
        }

        return 1;
    }

    public static void filterByPlayerNameValidationService(String filter_by_player_name) {
        if (filter_by_player_name == null) {
            filter_by_player_name = "";
        }
    }
}
