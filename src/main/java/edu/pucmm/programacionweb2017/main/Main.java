package edu.pucmm.programacionweb2017.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

/**
 * Created by gusta on 02-Jun-17.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Iniciando servidor");
        logger.info("Iniciando aplicacion web");
        setConfiguraciones();
        setArchivosEstaticos();
        setRoutes();
    }

    public static void setConfiguraciones() {
        logger.info("Realizando configuraciones del framework spark");
        port(4567);
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
}
