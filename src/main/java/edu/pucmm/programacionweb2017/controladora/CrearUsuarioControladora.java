package edu.pucmm.programacionweb2017.controladora;

import edu.pucmm.programacionweb2017.main.Main;
import edu.pucmm.programacionweb2017.modelo.Usuario;
import edu.pucmm.programacionweb2017.service.UsuarioService;
import edu.pucmm.programacionweb2017.util.Path;
import edu.pucmm.programacionweb2017.util.TemplateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class CrearUsuarioControladora {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static Route paginaCrearUsuario = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();

        return TemplateUtil.renderThymeleaf(model, Path.Template.CREAR_USUARIO);
    };

    public static Route crearUsuario = (request, response) -> {
        QueryParamsMap map = request.queryMap();

        Usuario usuario = new Usuario();
        usuario.setNombre(map.get("nombre").value());
        usuario.setUsername(map.get("usuario").value());
        usuario.setPassword(map.get("password").value());
        usuario.setAdministrator(Boolean.parseBoolean(map.get("administrator").value()));
        usuario.setAutor(Boolean.parseBoolean(map.get("autor").value()));

        UsuarioService usuarioService = new UsuarioService();

        if (usuarioService.encontrarPorCuentaUsuario(usuario.getUsername()) == null) {
            usuarioService.insertar(usuario);

            logger.info("Usuario creado");

            response.redirect("/login");

            return null;
        } else {
            return "Usuario ya existe!";
        }
    };
}
