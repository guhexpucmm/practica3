package edu.pucmm.programacionweb2017.util;

/**
 * Created by gusta on 03-Jun-17.
 */
public class Path {
    public static class Web {
        //Manejo de las rutas
        public static final String INICIO = "/inicio";
        public static final String ARTICULO = "/articulo/verArticulo/:id";
        public static final String ARTICULOS = "/articulo/verTodos";
        public static final String CREAR_USUARIO = "/usuario/crearUsuario";
        public static final String COMENTARIOS = "/comentario/:articuloId";
        public static final String LOGIN = "/login";
    }

    //No se especifica la extension del archivo, thymeleaf sabe que ya de por si es HTML
    public static class Template {
        public static final String INICIO = "/Inicio";
        public static final String ARTICULO = "/articulo/CrearArticulo";
        public static final String NO_ENCONRADO = "/NoEncontrado";
        public static final String CREAR_USUARIO = "/usuario/CrearUsuario";
        public static final String LOGIN = "/login/Login";
    }
}
