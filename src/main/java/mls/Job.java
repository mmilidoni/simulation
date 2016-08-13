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

    private Double tempoRisposta, tempoProcessamento;
    private double tempoArrivo;
    private double tempoUscita;

    public Job() {
        tempoRisposta = 0.;
        tempoProcessamento = 0.;
        tempoArrivo = 0.;
        tempoUscita = 0.;
    }

    public Double getTempoProcessamento() {
        return tempoProcessamento;
    }

    public Double getTempoRisposta() {
        return tempoRisposta;
    }

    public void setTempoProcessamento(Double tempoProcessamento) {
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
        tempoRisposta = tempoUscita - tempoArrivo;
        //System.out.println("TEMPO RISPOSTA: " + tempoRisposta);
    }

    public Job clona() {
        Job j = new Job();
        j.tempoArrivo = this.tempoArrivo;
        j.tempoProcessamento = this.tempoProcessamento;
        j.tempoRisposta = this.tempoRisposta;
        j.tempoUscita = this.tempoUscita;
        //j.tempoProcessamento = this.tempoProcessamento;
        return j;
    }

}
