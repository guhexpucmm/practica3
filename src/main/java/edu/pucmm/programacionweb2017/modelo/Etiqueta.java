package edu.pucmm.programacionweb2017.modelo;

import java.io.Serializable;

/**
 * Created by gusta on 02-Jun-17.
 */
public class Etiqueta implements Serializable {
    private Long id;
    private String etiqueta;

    public Etiqueta() {
    }

    public Etiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Override
    public String toString() {
        return "Etiqueta{" +
                "id=" + id +
                ", etiqueta='" + etiqueta + '\'' +
                '}';
    }
}
