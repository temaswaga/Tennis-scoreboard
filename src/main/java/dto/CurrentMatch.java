package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import models.Player;

@AllArgsConstructor
@Data
public class CurrentMatch {
    private Player playerOne;
    private Score firstPlayerScore;
    private Player playerTwo;
    private Score secondPlayerScore;
    private Player winner;
}
