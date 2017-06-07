package edu.pucmm.programacionweb2017.controladora;

import edu.pucmm.programacionweb2017.modelo.Articulo;
import edu.pucmm.programacionweb2017.service.ArticuloService;
import edu.pucmm.programacionweb2017.util.Path;
import edu.pucmm.programacionweb2017.util.TemplateUtil;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InicioControladora {
    public static Route paginaInicio = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        ArticuloService articuloService = new ArticuloService();

        List<Articulo> list = articuloService.encontrarTodos();

        model.put("articulos", list);

        String valor = "";

        return TemplateUtil.renderThymeleaf(model, Path.Template.INICIO);
    };
}
