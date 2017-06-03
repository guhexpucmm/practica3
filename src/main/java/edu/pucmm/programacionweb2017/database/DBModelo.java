package edu.pucmm.programacionweb2017.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by gusta on 02-Jun-17.
 */
public class DBModelo {
    private static final Logger logger = LoggerFactory.getLogger(DBModelo.class);
    private final String CREATE_TABLE_USUARIO = "CREATE TABLE IF NOT EXISTS USUARIO (\n" +
            "\tID BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,\n" +
            "\tUSERNAME VARCHAR2(255) NOT NULL UNIQUE,\n" +
            "\tNOMBRE VARCHAR2(255) NOT NULL,\n" +
            "\tPASSWORD VARCHAR2(255)NOT NULL,\n" +
            "\tADMINISTRATOR BOOLEAN NOT NULL,\n" +
            "\tAUTOR BOOLEAN NOT NULL\n" +
            ");";
    private final String CREATE_TABLE_ARTICULO = "CREATE TABLE IF NOT EXISTS ARTICULO (\n" +
            "\tID BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,\n" +
            "\tTITULO VARCHAR2(255) NOT NULL UNIQUE,\n" +
            "\tCUERPO VARCHAR2(255),\n" +
            "\tAUTOR_ID BIGINT NOT NULL REFERENCES USUARIO(ID) ON UPDATE CASCADE ON DELETE CASCADE,\n" +
            "\tFECHA DATE NOT NULL\n" +
            ");";
    private final String CREATE_TABLE_ETIQUETA = "CREATE TABLE IF NOT EXISTS ETIQUETA (\n" +
            "\tID BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,\n" +
            "\tETIQUETA VARCHAR2(255)\n" +
            ");";
    private final String CREATE_TABLE_COMENTARIO = "CREATE TABLE IF NOT EXISTS COMENTARIO (\n" +
            "\tID BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,\n" +
            "\tCOMENTARIO VARCHAR2(255),\n" +
            "\tAUTOR_ID BIGINT NOT NULL REFERENCES USUARIO(ID) ON UPDATE CASCADE ON DELETE CASCADE,\n" +
            "\tARTICULO_ID BIGINT NOT NULL\n" +
            ");";
    private final String CREATE_TABLE_ARTICULO_ETIQUETAS = "CREATE TABLE IF NOT EXISTS ARTICULO_ETIQUETAS (\n" +
            "\tID BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,\n" +
            "\tARTICULO_ID BIGINT NOT NULL REFERENCES ARTICULO(ID) ON UPDATE CASCADE ON DELETE CASCADE,\n" +
            "\tETIQUETA_ID BIGINT NOT NULL REFERENCES ETIQUETA(ID) ON UPDATE CASCADE ON DELETE CASCADE\n" +
            ");";
    private final String CREATE_TABLE_ARTICULO_COMENTARIOS = "CREATE TABLE IF NOT EXISTS ARTICULO_COMENTARIOS (\n" +
            "\tID BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,\n" +
            "\tARTICULO_ID BIGINT NOT NULL REFERENCES ARTICULO(ID) ON UPDATE CASCADE ON DELETE CASCADE,\n" +
            "\tCOMENTARIO_ID BIGINT NOT NULL REFERENCES COMENTARIO(ID) ON UPDATE CASCADE ON DELETE CASCADE\n" +
            ");";
    private PreparedStatement preparedStatement;

    public void crearDatabase() {
        try {
            DBConexion dbConexion = new DBConexion();

            logger.info("Creando tabla USUARIO");
            preparedStatement = dbConexion.getConexion().prepareStatement(CREATE_TABLE_USUARIO);
            preparedStatement.execute();
            logger.info("Tabla USUARIO creada.");

            logger.info("Creando tabla ARTICULO");
            preparedStatement = dbConexion.getConexion().prepareStatement(CREATE_TABLE_ARTICULO);
            preparedStatement.execute();
            logger.info("Tabla ARTICULO creada.");

            logger.info("Creando tabla ETIQUETA");
            preparedStatement = dbConexion.getConexion().prepareStatement(CREATE_TABLE_ETIQUETA);
            preparedStatement.execute();
            logger.info("Tabla ETIQUETA creada.");

            logger.info("Creando tabla COMENTARIO");
            preparedStatement = dbConexion.getConexion().prepareStatement(CREATE_TABLE_COMENTARIO);
            preparedStatement.execute();
            logger.info("Tabla COMENTARIO creada.");

            logger.info("Creando tabla ARTICULO_ETIQUETAS");
            preparedStatement = dbConexion.getConexion().prepareStatement(CREATE_TABLE_ARTICULO_ETIQUETAS);
            preparedStatement.execute();
            logger.info("Tabla ARTICULO_ETIQUETAS creada.");

            logger.info("Creando tabla ARTICULO_COMENTARIOS");
            preparedStatement = dbConexion.getConexion().prepareStatement(CREATE_TABLE_ARTICULO_COMENTARIOS);
            preparedStatement.execute();
            logger.info("Tabla ARTICULO_COMENTARIOS creada.");
        } catch (SQLException e) {
            logger.debug("Error al ejecutar comando SQL.", e);
        }
    }
}
