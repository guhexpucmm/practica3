package edu.pucmm.programacionweb2017.dao.impl;

import edu.pucmm.programacionweb2017.database.DBConexion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by gusta on 03-Jun-17.
 */
public class DAO {
    private static final Logger logger = LoggerFactory.getLogger(DAO.class);
    protected DBConexion dbConexion;
    protected Connection connection;
    protected ResultSet resultSet;
    protected PreparedStatement preparedStatement;
    protected Statement statement;
}
