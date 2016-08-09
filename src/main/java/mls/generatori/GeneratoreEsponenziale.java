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
public class GeneratoreEsponenziale implements Generatore {

    private final double Ta;
    private final GeneratoreUniforme gm;

    public GeneratoreEsponenziale(double Ta, GeneratoreUniforme gm) {
        this.Ta = Ta;
        this.gm = gm;
    }

    @Override
    public double next() {
        return (double) (-Ta * Math.log(gm.next()));
    }
}
