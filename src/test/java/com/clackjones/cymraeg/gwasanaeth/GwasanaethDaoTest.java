package com.clackjones.cymraeg.gwasanaeth;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import liquibase.Liquibase;
import org.junit.Test;


public class GwasanaethDaoTest {

    private static Connection conn;
    private static Liquibase liquibase;

    @BeforeClass
    public static void setup() throws ClassNotFoundException, SQLException, LiquibaseException {

        // create in memory database
        System.out.println("Getting DB connection");
        Class.forName("org.h2.Driver");
        GwasanaethDaoTest.conn = DriverManager.
                getConnection("jdbc:h2:mem:testdb", "sa", "");

        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));

        // populate with teh database schema using liquibase
        System.out.println("Creating liquibase object");
        GwasanaethDaoTest.liquibase = new Liquibase("liquibase/db-changelog.xml",
                new ClassLoaderResourceAccessor(),
                database);

        System.out.println("Updating DB");
        liquibase.update(new Contexts(), new LabelExpression());


        // connect with hibernate

    }

    @AfterClass
    public static void teardown() throws SQLException {
        // stop db
        GwasanaethDaoTest.conn.close();
    }

    @Test
    public void bornToFail() {
        fail();
    }
}
