package com.rvutracker.persistence;

import com.rvutracker.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    private Session session;
    private CriteriaQuery<User> query;

    /**
     * Gets all users.
     *
     * @return the all users
     */
    public List<User> getAllUsers() {
        openSession();
        buildQuery();
        List<User> users = session.createQuery(query).getResultList();
        session.close();
        return users;
    }

    /**
     * Gets a user by id.
     *
     * @param id the id
     * @return a user
     */
    public User getById(int id) {
        openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    /**
     * Update user.
     *
     * @param user User to be inserted or updated.
     */
    public void saveOrUpdate(User user) {
        openSession();
        session.saveOrUpdate(user);
        session.close();
    }

    /**
     * Insert user.
     *
     * @param user User to be inserted
     * @return the int
     */
    public int insert(User user) {
        int id = 0;
        openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(user);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Deletea user.
     *
     * @param user User to be deleted.
     */
    public void delete(User user) {
        openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }


    /**
     * Open a session
     */
    private void openSession() {
        session = sessionFactory.openSession();
    }

    /**
     * Instantiates CriteriaBuilder and directs query to User class.
     */
    private void buildQuery() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        query = builder.createQuery( User.class );
        Root<User> root = query.from(User.class);
    }
}
