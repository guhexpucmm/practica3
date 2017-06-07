package edu.pucmm.programacionweb2017.service;

import edu.pucmm.programacionweb2017.dao.DAOComentario;
import edu.pucmm.programacionweb2017.dao.impl.DAOComentarioImpl;
import edu.pucmm.programacionweb2017.modelo.Comentario;

import java.util.List;

/**
 * Created by gusta on 05-Jun-17.
 */
public class ComentarioService extends DAOComentarioImpl implements DAOComentario {
    @Override
    public void insertar(Comentario comentario) {
        super.insertar(comentario);
    }

    @Override
    public void actualizar(Comentario comentario) {
        super.actualizar(comentario);
    }

    @Override
    public void borrar(Comentario comentario) {
        super.borrar(comentario);
    }

    @Override
    public List<Comentario> encontrarTodos() {
        return super.encontrarTodos();
    }

    @Override
    public Comentario encontrarPorId(Long id) {
        return super.encontrarPorId(id);
    }
}
