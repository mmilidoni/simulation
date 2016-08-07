package mls;

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
    private Double caricoTotale, caricoCorrente;

    public Job() {
        id = new Random().nextInt(500000);
        caricoTotale = 0.;
        caricoCorrente = 0.;
    }

    public Long getId() {
        return id;
    }

    public Double getCaricoCorrente() {
        return caricoCorrente;
    }

    public Double getCaricoTotale() {
        return caricoTotale;
    }

    public void setCaricoCorrente(Double carico) {
        this.caricoCorrente = carico;
        this.caricoTotale += carico;
    }

    public Job clona() {
        Job j = new Job();
        j.caricoTotale = this.caricoTotale;
        return j;
    }
}
