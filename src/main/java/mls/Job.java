package mls;

import java.util.GregorianCalendar;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Michele Milidoni <michelemilidoni@gmail.com>
 */
public class Job {

    private final long id;
    private GregorianCalendar tempoArrivo;
    private GregorianCalendar tempoUscita;
    private Double carico;

    public Job() {
        id = new Random().nextInt(500000);
        tempoArrivo = new GregorianCalendar();
        tempoUscita = null;
        carico = 0.;
    }

    public Long getId() {
        return id;
    }

    public GregorianCalendar getTempoArrivo() {
        return tempoArrivo;
    }

    public void setTempoArrivo(GregorianCalendar tempoArrivo) {
        this.tempoArrivo = tempoArrivo;
    }

    public GregorianCalendar getTempoUscita() {
        return tempoUscita;
    }

    public void setTempoUscita(GregorianCalendar tempoUscita) {
        this.tempoUscita = tempoUscita;
    }

    public long getTempoRisposta() {
        return tempoUscita.getTimeInMillis() - tempoArrivo.getTimeInMillis();
    }

    public Double getCarico() {
        return carico;
    }

    public void setCarico(Double carico) {
        this.carico = carico;
    }

    public Job clona() {
        Job j = new Job();
        j.setTempoArrivo(tempoArrivo);
        
        return j;
    }
}
