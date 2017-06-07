package edu.pucmm.programacionweb2017.main;

import edu.pucmm.programacionweb2017.controladora.*;
import edu.pucmm.programacionweb2017.dao.impl.DAOArticuloImpl;
import edu.pucmm.programacionweb2017.database.DBConexion;
import edu.pucmm.programacionweb2017.modelo.Articulo;
import edu.pucmm.programacionweb2017.modelo.Comentario;
import edu.pucmm.programacionweb2017.modelo.Usuario;
import edu.pucmm.programacionweb2017.service.ArticuloService;
import edu.pucmm.programacionweb2017.service.ComentarioService;
import edu.pucmm.programacionweb2017.service.UsuarioService;
import edu.pucmm.programacionweb2017.util.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.SQLException;

import static spark.Spark.*;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static Usuario usuario = null;
    private static Articulo articulo = null;
    private static Comentario comentario = null;

    public static void main(String[] args) throws SQLException {
        logger.info("Iniciando aplicacion web");
        setConfiguraciones();
        setBaseDeDatos();
        setArchivosEstaticos();
        setRoutes();
        setUsuario();

        pruebaArticulos();

        new DAOArticuloImpl().encontrarTodos().stream().forEach(articulo1 -> {
            System.out.println(articulo1.getResumen());
            articulo1.getListaComentarios().stream().forEach(comentario1 -> {
                System.out.println(comentario1.getComentario());
            });
        });
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

        get(Path.Web.VER_ARTICULO, ArticuloControladora.ver);

        setRedirection();

        get(Path.Web.NO_ENCONTRADO, NoEncontradoControladora.noEncontrado);
    }

    public static void setRedirection() {
        get("/", (request, response) -> {
            response.redirect(Path.Web.LOGIN);
            return null;
        });
    }

    public static void setUsuario() {
        usuario = new Usuario(
                new Long(1),
                "gustavojoseh",
                "Gustavo",
                "123456789",
                true,
                true
        );

        UsuarioService usuarioService = new UsuarioService();
        usuarioService.insertar(usuario);
    }

    public static void pruebaArticulos() {
        articulo = new Articulo(
                new Long(1),
                "Articulo_Prueba",
                "El dia de hoy fue un dia caluroso y hubo mucha lluvia, pues llovio bastante. Todo estaba lleno de agua, casi ni se podia caminar. Hubo que comprar un bote para poder andar en la ciudad, estaba tan llena de agua que casi ni podia respirar.",
                usuario,
                new Date(new java.util.Date().getTime()),
                null,
                null
        );

        ArticuloService articuloService = new ArticuloService();
        articuloService.insertar(articulo);

        agregarComentarios();
    }

    public static void agregarComentarios() {
        comentario = new Comentario(
                new Long(1),
                "Muy hermoso este articulo",
                usuario,
                articulo
        );

        ComentarioService comentarioService = new ComentarioService();
        comentarioService.insertar(comentario);
    }
}
