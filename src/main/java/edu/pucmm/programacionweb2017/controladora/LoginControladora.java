package edu.pucmm.programacionweb2017.controladora;

import edu.pucmm.programacionweb2017.modelo.Usuario;
import edu.pucmm.programacionweb2017.service.UsuarioService;
import edu.pucmm.programacionweb2017.util.Path;
import edu.pucmm.programacionweb2017.util.TemplateUtil;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class LoginControladora {
    public static Route paginaLogin = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();

        return TemplateUtil.renderThymeleaf(model, Path.Template.LOGIN);
    };

    public static Route login = (request, response) -> {
        QueryParamsMap map = request.queryMap();

        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = usuarioService.encontrarPorCuentaUsuario(map.get("usuario").value());

        if (usuario != null) {
            //Iniciar sesion
            if (map.get("password").value().equals(usuario.getPassword())) {
                response.redirect(Path.Web.INICIO);
                return null;
            }
        }

        //No se inicio sesion
        return "Usuario o contrasena incorrecta.";
    };
}
