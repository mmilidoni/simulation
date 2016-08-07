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

    private final double media;
    private final GeneratoreUniforme gm;

    public GeneratoreEsponenziale(double media, GeneratoreUniforme gm) {
        this.media = media;
        this.gm = gm;
    }

    @Override
    public double next() {
        return (double) (-media * Math.log(gm.next()));
    }
}
