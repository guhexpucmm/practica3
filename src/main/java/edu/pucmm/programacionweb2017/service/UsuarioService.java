package edu.pucmm.programacionweb2017.service;

import edu.pucmm.programacionweb2017.dao.DAOUsuario;
import edu.pucmm.programacionweb2017.dao.impl.DAOUsuarioImpl;
import edu.pucmm.programacionweb2017.modelo.Usuario;

import java.util.List;

/**
 * Created by gusta on 03-Jun-17.
 */
public class UsuarioService extends DAOUsuarioImpl implements DAOUsuario {
    @Override
    public void insertar(Usuario usuario) {
        super.insertar(usuario);
    }

    @Override
    public void actualizar(Usuario usuario) {
        super.actualizar(usuario);
    }

    @Override
    public void borrar(Usuario usuario) {
        super.borrar(usuario);
    }

    @Override
    public List<Usuario> encontrarTodos() {
        return super.encontrarTodos();
    }

    @Override
    public Usuario encontrarPorId(Long id) {
        return super.encontrarPorId(id);
    }

    @Override
    public Usuario encontrarPorNombre(String nombre) {
        return super.encontrarPorNombre(nombre);
    }

    @Override
    public Usuario encontrarPorCuentaUsuario(String usuario) {
        return super.encontrarPorCuentaUsuario(usuario);
    }
}
