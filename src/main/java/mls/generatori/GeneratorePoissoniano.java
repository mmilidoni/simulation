/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mls.generatori;

import mls.util.Generatore;

/**
 *
 * @author Michele Milidoni <michelemilidoni@gmail.com>
 */
public class GeneratorePoissoniano implements Generatore {

    private final double media;
    private final Generatore gm;

    public GeneratorePoissoniano(double media, Generatore gm) {
        this.media = media;
        this.gm = gm;
    }

    @Override
    public double next() {
        int n = 0;
        double prod = gm.next();
        while (prod >= Math.exp(-media)) {
            prod *= gm.next();
            n++;
        }
        return n;
    }

    @Override
    public void setSeme(double seme) {
        gm.setSeme(seme);
    }

    @Override
    public double getSeme() {
        return gm.getSeme();
    }

}
