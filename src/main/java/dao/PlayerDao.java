package dao;

import models.Player;
import util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

public class PlayerDao {

    public static void save(Player player) {
            try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
                session.beginTransaction();
                session.persist(player);
                session.getTransaction().commit();
            }
    }

    public static Optional<Player> findByName(String name) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "from Player where name=:name";
            session.beginTransaction();
            Query<Player> query = session.createQuery(hql, Player.class);
            query.setParameter("name", name);
            return query.uniqueResultOptional();
        }
    }

}
