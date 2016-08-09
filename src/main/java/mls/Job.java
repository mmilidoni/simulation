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

    private Double tempoRisposta, tempoServizio;

    public Job() {
        tempoRisposta = 0.;
        tempoServizio = 0.;
    }

    public Double getTempoServizio() {
        return tempoServizio;
    }

    public Double getTempoRisposta() {
        return tempoRisposta;
    }

    public void setTempoServizio(Double tempoServizio) {
        this.tempoServizio = tempoServizio;
        this.tempoRisposta += tempoServizio;
    }

    public Job clona() {
        Job j = new Job();
        j.tempoRisposta = this.tempoRisposta;
        //j.tempoServizio = this.tempoServizio;
        return j;
    }

}
