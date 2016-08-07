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
    private final GeneratoreUniforme gm;

    public GeneratorePoissoniano(double media, GeneratoreUniforme gm) {
        this.media = media;
        this.gm = gm;
    }

    @Override
    public double next() {
        int n = 0;
        double prod = 1;
        while (prod > Math.exp(-media)) {
            prod *= gm.next();
            n++;
        }
        return n;
    }
}
