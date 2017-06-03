package edu.pucmm.programacionweb2017.dao;

import java.util.List;

/**
 * Created by gusta on 02-Jun-17.
 */
public interface DAO<T, K extends Long> {
    void insertar(T t);

    void actualizar(T t);

    void borrar(T t);

    List<T> encontrarTodos();

    T encontrarPorId(K id);
}
