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
public class Generatore3Erlangiano implements Generatore {

    private final double Ts;
    private final Generatore gm1, gm2, gm3;

    public Generatore3Erlangiano(double Ts, double seme) {
        this.Ts = Ts;
        this.gm1 = new GeneratoreUniforme(seme);
        this.gm2 = new GeneratoreUniforme(seme + 15);
        this.gm3 = new GeneratoreUniforme(seme + 117);
    }

    @Override
    public double next() {
        double d = (-Ts / 3) * Math.log(gm1.next() * gm2.next() * gm3.next());
        //System.out.println(d);
        return d;
    }

    @Override
    public void setSeme(double seme) {
        gm1.setSeme(seme);
        gm2.setSeme(seme + 14);
        gm3.setSeme(seme + 118);
    }

    @Override
    public double getSeme() {
        return gm1.getSeme();
    }

}
