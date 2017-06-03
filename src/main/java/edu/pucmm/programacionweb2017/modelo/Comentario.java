package edu.pucmm.programacionweb2017.modelo;

import java.io.Serializable;

/**
 * Created by gusta on 02-Jun-17.
 */
public class Comentario implements Serializable {
    private Long id;
    private String comentario;
    private Usuario autor;
    private Articulo articulo;

    public Comentario() {
    }

    public Comentario(String comentario, Usuario autor, Articulo articulo) {
        this.comentario = comentario;
        this.autor = autor;
        this.articulo = articulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", comentario='" + comentario + '\'' +
                ", autor=" + autor +
                ", articulo=" + articulo +
                '}';
    }
}
