package initializer;

import dao.MatchDao;
import dao.PlayerDao;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import models.Match;
import models.Player;

@WebListener
public class DatabaseContextInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Player lena = new Player("Lena");
        Player vanya = new Player("Vanya");
        Player dimas = new Player("Dima");
        Player tioma = new Player("Tioma");
        Player arthur = new Player("Arthur");

        PlayerDao.save(lena);
        PlayerDao.save(vanya);
        PlayerDao.save(dimas);
        PlayerDao.save(tioma);
        PlayerDao.save(arthur);

        MatchDao.save(new Match(vanya, lena, lena));
        MatchDao.save(new Match(dimas, lena, lena));
        MatchDao.save(new Match(tioma, lena, lena));
        MatchDao.save(new Match(tioma, vanya, vanya));
        MatchDao.save(new Match(arthur, lena, lena));
        MatchDao.save(new Match(vanya, dimas, dimas));
        MatchDao.save(new Match(lena, dimas, dimas));
        MatchDao.save(new Match(tioma, arthur, arthur));
        MatchDao.save(new Match(vanya, arthur, vanya));
    }
}
