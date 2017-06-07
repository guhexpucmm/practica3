package edu.pucmm.programacionweb2017.controladora;

import edu.pucmm.programacionweb2017.modelo.Articulo;
import edu.pucmm.programacionweb2017.modelo.Comentario;
import edu.pucmm.programacionweb2017.service.ArticuloService;
import edu.pucmm.programacionweb2017.util.Path;
import edu.pucmm.programacionweb2017.util.TemplateUtil;
import spark.QueryParamsMap;
import spark.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticuloControladora {
    public static Route ver = (request, response) -> {
        Map<String, Object> parametros = new HashMap<>();
        QueryParamsMap queryParamsMap = request.queryMap();

        Long id = Long.parseLong(queryParamsMap.get("id").value());

        Articulo articulo = new ArticuloService().encontrarPorId(id);
        List<Comentario> list = articulo.getListaComentarios();

        parametros.put("articulo", articulo);
        parametros.put("comentarios", list);

        return TemplateUtil.renderThymeleaf(parametros, Path.Template.VER_ARTICULO);
    };
}
