package edu.pucmm.programacionweb2017.service;

import edu.pucmm.programacionweb2017.dao.DAOArticulo;
import edu.pucmm.programacionweb2017.dao.impl.DAOArticuloImpl;
import edu.pucmm.programacionweb2017.modelo.Articulo;

import java.util.List;

/**
 * Created by gusta on 05-Jun-17.
 */
public class ArticuloService extends DAOArticuloImpl implements DAOArticulo {
    public ArticuloService() {
        super();
    }

    @Override
    public void insertar(Articulo articulo) {
        super.insertar(articulo);
    }

    @Override
    public void actualizar(Articulo articulo) {
        super.actualizar(articulo);
    }

    @Override
    public void borrar(Articulo articulo) {
        super.borrar(articulo);
    }

    @Override
    public List<Articulo> encontrarTodos() {
        return super.encontrarTodos();
    }

    @Override
    public Articulo encontrarPorId(Long id) {
        return super.encontrarPorId(id);
    }
}
