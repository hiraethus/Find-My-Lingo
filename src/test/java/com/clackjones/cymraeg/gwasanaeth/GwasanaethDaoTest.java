package com.clackjones.cymraeg.gwasanaeth;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
public class GwasanaethDaoTest {

    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private GwasanaethDao gwasanaethDao;

    @Autowired
    private DataSource dataSource;

    @Test
    public void shouldReturnListOfUniqueFirstCharactersFromServiceNames() {
        // given have 3 three services (see resources/liquibase/db-gwasanaeth-dao.xml)

        // when
        List<String> result = gwasanaethDao.findUniqueFirstCharacters();

        // then
        assertThat(result.get(0), equalTo("A"));
        assertThat(result.get(1), equalTo("B"));
        assertThat(result.get(2), equalTo("D"));
    }

    @Ignore
    @Test
    // useful to troubleshoot
    public void listTables() throws SQLException {
        Connection c = dataSource.getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS");

        while (rs.next()) {
            System.out.println(rs.getString("COLUMN_NAME"));
        }
    }
}
