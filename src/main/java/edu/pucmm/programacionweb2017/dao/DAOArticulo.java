package edu.pucmm.programacionweb2017.dao;

import edu.pucmm.programacionweb2017.modelo.Articulo;

import java.util.List;

/**
 * Created by gusta on 02-Jun-17.
 */
public interface DAOArticulo extends DAO<Articulo, Long> {
    @Override
    void insertar(Articulo articulo);

    @Override
    void actualizar(Articulo articulo);

    @Override
    void borrar(Articulo articulo);

    @Override
    List<Articulo> encontrarTodos();

    @Override
    Articulo encontrarPorId(Long id);
}
