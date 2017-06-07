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
    private final String DB_NAME = "H2/BLOG";
    private final int PORT = 9092;
    private final String URL_SERVIDOR = "jdbc:h2:tcp://localhost:9092/~/" + DB_NAME;
    private final String URL_EMBEBIDA = "jdbc:h2:~/test";
    private final String USERNAME = "guhex";
    private final String PASSWORD = "123456789";

    private Connection connection = null;

    public Connection getConexion() {
        try {
            Class.forName(DRIVER);

            connection = DriverManager.getConnection(URL_SERVIDOR, USERNAME, PASSWORD);

            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            logger.debug("Error al extraer la conexion de la base de datos.", e);
            return null;
        } catch (NullPointerException e) {
            logger.debug("El servidor no ha sido iniciado.");
            return null;
        }
    }
}
