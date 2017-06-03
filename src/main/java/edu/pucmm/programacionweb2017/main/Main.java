package edu.pucmm.programacionweb2017.main;

import edu.pucmm.programacionweb2017.dao.impl.DAOUsuarioImpl;
import edu.pucmm.programacionweb2017.database.DBModelo;
import edu.pucmm.programacionweb2017.modelo.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

/**
 * Created by gusta on 02-Jun-17.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final DBModelo dbModelo = new DBModelo();

    public static void main(String[] args) {
        logger.info("Iniciando servidor");
        logger.info("Iniciando aplicacion web");
        setConfiguraciones();
        setBaseDeDatos();
        setArchivosEstaticos();
        setRoutes();
        bdTest();
    }

    public static void setConfiguraciones() {
        logger.info("Realizando configuraciones del framework spark");
        port(4567);
    }

    public static void setBaseDeDatos() {
        logger.info("Configurando la base de datos.");
        dbModelo.crearDatabase();
    }

    public static void setArchivosEstaticos() {
        logger.info("Configurando archivos estaticos");
        String projectDir = System.getProperty("user.dir");
        String staticDir = "/src/main/resources";
        staticFiles.externalLocation(projectDir + staticDir);
    }

    public static void setRoutes() {
        logger.info("Especificando rutas");
        get("/", (request, response) -> {
            return "Hello word";
        });
    }

    public static void bdTest() {
        logger.info("Realizando pruebas de base de datos...");
        Usuario usuario = new Usuario("guhex", "Guassastavo", "123456789", true, true);
        usuario.setId(new Long(1));
        new DAOUsuarioImpl().borrar(usuario);
        logger.info("Prueba de base de datos realizada.");
    }
}
