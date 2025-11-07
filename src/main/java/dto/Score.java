package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Score {
    private int sets;
    private int games;
    private int points;
}
