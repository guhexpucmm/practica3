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
    private final Logger logger = LoggerFactory.getLogger(DBConexion.class);

    private final String DRIVER = "org.h2.Driver";
    private final String DB_NAME = "PRACTICA3";
    private final String URL = "jdbc:h2:file:./src/main/resources/database/data/" + DB_NAME;
    private final String USERNAME = "guhex";
    private final String PASSWORD = "123456789";

    public Connection connection;

    public Connection getConexion() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            logger.debug("Error al extraer la conexion de la base de datos.", e);
            return null;
        }
    }
}
