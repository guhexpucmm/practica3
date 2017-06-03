package edu.pucmm.programacionweb2017.dao;

import edu.pucmm.programacionweb2017.modelo.Comentario;

import java.util.List;

/**
 * Created by gusta on 02-Jun-17.
 */
public interface DAOComentario extends DAO<Comentario, Long> {
    @Override
    void insertar(Comentario comentario);

    @Override
    void actualizar(Comentario comentario);

    @Override
    void borrar(Comentario comentario);

    @Override
    List<Comentario> encontrarTodos();

    @Override
    Comentario encontrarPorId(Long id);
}
