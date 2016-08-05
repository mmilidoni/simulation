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
    private Double carico;

    public Job() {
        id = new Random().nextInt(500000);
        carico = 0.;
    }

    public Long getId() {
        return id;
    }

    public Double getCarico() {
        return carico;
    }

    public void setCarico(Double carico) {
        this.carico = carico;
    }

    public Job clona() {
        Job j = new Job();
        j.carico = this.carico;
        return j;
    }
}
