/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mls.generatori;

/**
 *
 * @author Michele Milidoni <michelemilidoni@gmail.com>
 */
public class Generatore3Erlangiano {

    private final double ts;
    private final GeneratoreEsponenziale gm;

    public Generatore3Erlangiano(double ts, GeneratoreEsponenziale gm) {
        this.ts = ts;
        this.gm = gm;
    }

    public double next() {
        return -ts / 3 * Math.log(gm.next());
    }

}
