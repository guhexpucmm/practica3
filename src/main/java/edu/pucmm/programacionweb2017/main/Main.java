package edu.pucmm.programacionweb2017.main;

import edu.pucmm.programacionweb2017.controladora.CrearUsuarioControladora;
import edu.pucmm.programacionweb2017.controladora.InicioControladora;
import edu.pucmm.programacionweb2017.controladora.LoginControladora;
import edu.pucmm.programacionweb2017.dao.impl.DAOUsuarioImpl;
import edu.pucmm.programacionweb2017.database.DBConexion;
import edu.pucmm.programacionweb2017.modelo.Usuario;
import edu.pucmm.programacionweb2017.util.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

import static spark.Spark.*;

/**
 * Created by gusta on 02-Jun-17.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        logger.info("Iniciando aplicacion web");
        setConfiguraciones();
        setBaseDeDatos();
        setArchivosEstaticos();
        setRoutes();
        setUsuario();
    }

    public static void setConfiguraciones() {
        logger.info("Realizando configuraciones del framework spark");
        port(4567);
    }

    public static void setBaseDeDatos() {
        try {
            logger.info("Configurando la base de datos.");
            DBConexion dbConexion = new DBConexion();
            dbConexion.getConexion().close();
        } catch (SQLException e) {
            logger.debug("Error al intentar hacer una conexion con la base de datos.");
        }
    }

    public static void setArchivosEstaticos() {
        logger.info("Configurando archivos estaticos");
        String projectDir = System.getProperty("user.dir");
        String staticDir = "/src/main/resources";
        staticFiles.externalLocation(projectDir + staticDir);
    }

    public static void setRoutes() {
        logger.info("Especificando rutas");

        get(Path.Web.LOGIN, LoginControladora.paginaLogin);
        post(Path.Web.LOGIN, LoginControladora.login);

        get(Path.Web.INICIO, InicioControladora.paginaInicio);

        get(Path.Web.CREAR_USUARIO, CrearUsuarioControladora.paginaCrearUsuario);
        post(Path.Web.CREAR_USUARIO, CrearUsuarioControladora.crearUsuario);

        setRedirection();
    }

    public static void setRedirection() {
        get("/", (request, response) -> {
            response.redirect(Path.Web.LOGIN);
            return null;
        });
    }

    public static void setUsuario() {
        Usuario usuario = new Usuario(
                "gustavojoseh",
                "Gustavo",
                "123456789",
                true,
                true
        );

        usuario.setId(new Long(1));

        DAOUsuarioImpl daoUsuario = new DAOUsuarioImpl();
        daoUsuario.insertar(usuario);
    }
}
