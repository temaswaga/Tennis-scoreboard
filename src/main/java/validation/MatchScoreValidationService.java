package validation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MatchScoreValidationService {

    public static void uuidStringValidation(String uuidString, HttpServletResponse resp) throws IOException {
        if (uuidString == null || uuidString.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "UUID parameter is required");
        }
    }

}
