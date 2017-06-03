package edu.pucmm.programacionweb2017.dao;

import edu.pucmm.programacionweb2017.modelo.Etiqueta;

import java.util.List;

/**
 * Created by gusta on 02-Jun-17.
 */
public interface DAOEtiqueta extends DAO<Etiqueta, Long> {
    @Override
    void insertar(Etiqueta etiqueta);

    @Override
    void actualizar(Etiqueta etiqueta);

    @Override
    void borrar(Etiqueta etiqueta);

    @Override
    List<Etiqueta> encontrarTodos();

    @Override
    Etiqueta encontrarPorId(Long id);
}
