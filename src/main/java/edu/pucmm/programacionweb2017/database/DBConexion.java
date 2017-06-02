package edu.pucmm.programacionweb2017.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by gusta on 02-Jun-17.
 */
public class DBConexion {
    private static final String URL = "";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";
    private static final Logger logger = LoggerFactory.getLogger(DBConexion.class);

    public DBConexion() {
    }

    public Connection getConexion() {
        try {
            Class.forName("");
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            return connection;
        } catch (ClassNotFoundException | SQLException e){
            logger.debug("Error al extraer la conexion de la base de datos.", e);
            return null;
        }
    }
}
