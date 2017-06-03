package edu.pucmm.programacionweb2017.util;

import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.Map;

/**
 * Created by gusta on 03-Jun-17.
 */
public class TemplateUtil {
    public static String renderThymeleaf(Map<String, Object> parametros, String path) {
        return new ThymeleafTemplateEngine().render(new ModelAndView(parametros, path));
    }
}
