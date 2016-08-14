/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mls;

import mls.util.TipoEvento;

/**
 *
 * @author Michele Milidoni <michelemilidoni@gmail.com>
 */
public class Evento {

    private final Double durata;
    private final TipoEvento tipo;

    public Evento(Double orario, TipoEvento tipo) {
        this.durata = orario;
        this.tipo = tipo;
    }

    public Double getDurata() {
        return durata;
    }

    public TipoEvento getTipo() {
        return tipo;
    }

    public Evento clona() {
        return new Evento(durata, tipo);
    }

}
