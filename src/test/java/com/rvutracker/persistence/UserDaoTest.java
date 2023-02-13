package com.rvutracker.persistence;

import com.rvutracker.entity.User;
import com.rvutracker.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    UserDao dao;

    /**
     * Creates the dao and resets database
     * with original content.
     */
    @BeforeEach
    void setUp() {

        dao = new UserDao();

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verifies gets all users success.
     */
    @Test
    void getAllUsersSuccess() {
        List<User> users = dao.getAllUsers();
        assertEquals(2,users.size());
    }

    /**
     * Verifies gets by id success.
     */
    @Test
    void getByIdSuccess() {
        User retrievedUser = dao.getById(1);
        assertNotNull(retrievedUser);
        assertEquals("Kue", retrievedUser.getFirstName());
    }

    /**
     * Verifies gets by email success.
     */
    @Test
    void getByEmailSuccess() {
        List<User> users = dao.getByEmail("kxiong1@madisoncollege.edu");
        assertEquals(1, users.size());
    }

    /**
     * Verifies save or update success.
     */
    @Test
    void saveOrUpdate() {
        String newLastName = "Pikachu";
        User user = dao.getById(1);
        user.setLastName(newLastName);
        dao.saveOrUpdate(user);
        User retrievedUser = dao.getById(1);
        assertEquals("Pikachu",retrievedUser.getLastName());
    }

    /**
     * Verifies insert new user success.
     */
    @Test
    void insertSuccess() {
        User user = new User("Ari", "Lee", "alee@gmail.com", "pizza");
        dao.insert(user);
        List<User> users = dao.getAllUsers();
        assertEquals(3, users.size());
    }

    /**
     * Verifies delete a user success.
     */
    @Test
    void deleteSuccess() {
    User userToBeDeleted = dao.getById(2);
    dao.delete(userToBeDeleted);
    List<User> users = dao.getAllUsers();
    assertEquals(1, users.size());
    }
}