package validation;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class NewMatchValidationService {

    public static void playerNamesValidation(String firstPlayerName, String secondPlayerName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (firstPlayerName.equals(secondPlayerName)) {
            req.setAttribute("error_message", "Players can't be the same");
        } else if (firstPlayerName.length() < 3 || secondPlayerName.length() < 3) {
            req.setAttribute("error_message", "Too short names, please try again");
        }
    }

    public static boolean arePlayersNamesValid(String firstPlayerName, String secondPlayerName){
        return !firstPlayerName.equals(secondPlayerName) && firstPlayerName.length() >= 3 && secondPlayerName.length() >= 3;
    }

}
