package edu.pucmm.programacionweb2017.controladora;

import edu.pucmm.programacionweb2017.util.Path;
import edu.pucmm.programacionweb2017.util.TemplateUtil;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class NoEncontradoControladora {
    public static Route noEncontrado = (request, response) -> {
        Map<String, Object> parametros = new HashMap<>();
        response.status(404);
        return TemplateUtil.renderThymeleaf(parametros, Path.Template.NO_ENCONTRADO);
    };
}
