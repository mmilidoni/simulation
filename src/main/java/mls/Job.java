package mls;

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

    private Double tempoProcessamento;
    private double tempoArrivo;
    private double tempoUscita;

    public Job() {
        tempoProcessamento = 0.;
        tempoArrivo = 0.;
        tempoUscita = 0.;
    }

    public Double getTempoProcessamento() {
        return tempoProcessamento;
    }

    public double getTempoRisposta() {
        return tempoUscita - tempoArrivo;
    }

    public void setTempoProcessamento(double tempoProcessamento) {
        this.tempoProcessamento = tempoProcessamento;
    }

    public double getTempoArrivo() {
        return tempoArrivo;
    }

    public void setTempoArrivo(double tempoArrivo) {
        this.tempoArrivo = tempoArrivo;
    }

    public double getTempoUscita() {
        return tempoUscita;
    }

    public void setTempoUscita(double tempoUscita) {
        this.tempoUscita = tempoUscita;
    }

    public Job clona() {
        Job j = new Job();
        j.tempoArrivo = this.tempoArrivo;
        j.tempoProcessamento = this.tempoProcessamento;
        j.tempoUscita = this.tempoUscita;
        //j.tempoProcessamento = this.tempoProcessamento;
        return j;
    }

}
