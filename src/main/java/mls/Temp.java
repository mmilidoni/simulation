/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mls;

import java.text.DecimalFormat;
import mls.generatori.Generatore3Erlangiano;
import mls.generatori.GeneratoreEsponenziale;
import mls.generatori.GeneratorePoissoniano;
import mls.generatori.GeneratoreUniforme;

/**
 *
 * @author Michele Milidoni <michelemilidoni@gmail.com>
 */
public class Temp {

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.#####");
        GeneratoreUniforme gu = new GeneratoreUniforme();
        GeneratoreEsponenziale ge = new GeneratoreEsponenziale(30, gu);
        Generatore3Erlangiano ger = new Generatore3Erlangiano(0.5, new GeneratoreUniforme());
        GeneratorePoissoniano gep = new GeneratorePoissoniano(0.5, new GeneratoreUniforme());
        for (int i = 0; i < 5000; i++) {
            System.out.println(df.format(gep.next()));
        }
    }
}
