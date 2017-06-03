package edu.pucmm.programacionweb2017.controladora;

import edu.pucmm.programacionweb2017.util.Path;
import edu.pucmm.programacionweb2017.util.TemplateUtil;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gusta on 03-Jun-17.
 */
public class InicioControladora {
    public static Route paginaInicio = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();

        return TemplateUtil.renderThymeleaf(model, Path.Template.INICIO);
    };
}
