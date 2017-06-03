package edu.pucmm.programacionweb2017.dao;

import edu.pucmm.programacionweb2017.modelo.Usuario;

import java.util.List;

/**
 * Created by gusta on 02-Jun-17.
 */
public interface DAOUsuario extends DAO<Usuario, Long> {
    @Override
    void insertar(Usuario usuario);

    @Override
    void actualizar(Usuario usuario);

    @Override
    void borrar(Usuario usuario);

    @Override
    List<Usuario> encontrarTodos();

    @Override
    Usuario encontrarPorId(Long id);

    Usuario encontrarPorNombre(String nombre);

    Usuario encontrarPorCuentaUsuario(String usuario);
}
