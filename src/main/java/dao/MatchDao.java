package dao;

import models.Match;
import util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class MatchDao {

    public static void save(Match match) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
        }
    }

    public static Optional<List<Match>> findAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "FROM Match";
            Query<Match> query = session.createQuery(hql, Match.class);
            return Optional.of(query.list());
        }
    }

    public static Optional<List<Match>> findByName(String name) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "FROM Match WHERE LOWER(player1.name) LIKE :namePattern OR LOWER(player2.name) LIKE :namePattern";
            Query<Match> query = session.createQuery(hql, Match.class);
            String searchPattern = name.toLowerCase() + "%";
            query.setParameter("namePattern", searchPattern);
            return Optional.ofNullable(query.list());
        }
    }

    public static int getTotalPages() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(m) FROM Match m";
            Long totalCount = session.createQuery(hql, Long.class).uniqueResult();

            return (int) Math.ceil(totalCount.doubleValue() / 6);
        }
    }

    public static int getTotalPagesWithNameFilter(String name) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(m) FROM Match m WHERE LOWER(m.player1.name) LIKE :namePattern OR LOWER(m.player2.name) LIKE :namePattern";
            Query<Long> query = session.createQuery(hql, Long.class);
            String searchPattern = name.toLowerCase() + "%";
            query.setParameter("namePattern", searchPattern);

            Long totalCount = query.uniqueResult();
            return (int) Math.ceil(totalCount.doubleValue() / 6);
        }
    }
}
