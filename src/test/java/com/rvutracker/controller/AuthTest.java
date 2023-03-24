package com.rvutracker.controller;

import com.rvutracker.entity.User;
import com.rvutracker.persistence.GenericDao;
import com.rvutracker.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao genericDao;

    /**
     * Creates the GenericDao and resets user database
     * with original content.
     */
    @BeforeEach
    void setUp() {

        genericDao = new GenericDao(User.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Check database for user.
     */
    @Test
    void checkDatabaseForUser() {

        Auth auth = new Auth();
        auth.checkDatabaseForUser("Krusty ","Krab",
                "kkrab@gmail.com","kkrab" );

        List<User> retrievedUsers = (List<User>)genericDao.getByEmail("kkrab@gmail.com");
        logger.info("The retrieved user is: " + retrievedUsers);
        assertEquals(1, retrievedUsers.size());
    }
}