package service;

import dao.PlayerDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Player;

import java.io.IOException;
import java.util.Optional;

public class PlayerService {

    public static void findOrSave(Player player) {
        Optional<Player> foundedPlayer = PlayerDao.findByName(player.getName());

        if (foundedPlayer.isPresent()) {
            player.setId(foundedPlayer.get().getId());
        } else {
            PlayerDao.save(player);
        }
    }
}
