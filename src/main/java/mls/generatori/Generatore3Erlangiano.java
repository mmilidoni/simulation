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

    private final double ts;
    private final GeneratoreEsponenziale gm;

    public Generatore3Erlangiano(double ts, GeneratoreEsponenziale gm) {
        this.ts = ts;
        this.gm = gm;
    }

    @Override
    public double next() {
        return (-ts / 3) * Math.log(gm.next() * gm.next() * gm.next());
    }

}
