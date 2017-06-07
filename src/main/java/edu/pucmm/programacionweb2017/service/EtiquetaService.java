package edu.pucmm.programacionweb2017.service;

import edu.pucmm.programacionweb2017.dao.DAOEtiqueta;
import edu.pucmm.programacionweb2017.dao.impl.DAOEtiquetaImpl;
import edu.pucmm.programacionweb2017.modelo.Etiqueta;

import java.util.List;

/**
 * Created by gusta on 05-Jun-17.
 */
public class EtiquetaService extends DAOEtiquetaImpl implements DAOEtiqueta {
    @Override
    public void insertar(Etiqueta etiqueta) {
        super.insertar(etiqueta);
    }

    @Override
    public void actualizar(Etiqueta etiqueta) {
        super.actualizar(etiqueta);
    }

    @Override
    public void borrar(Etiqueta etiqueta) {
        super.borrar(etiqueta);
    }

    @Override
    public List<Etiqueta> encontrarTodos() {
        return super.encontrarTodos();
    }

    @Override
    public Etiqueta encontrarPorId(Long id) {
        return super.encontrarPorId(id);
    }
}
