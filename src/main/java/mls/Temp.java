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
        DecimalFormat df = new DecimalFormat("#.###########");
        GeneratoreUniforme gu = new GeneratoreUniforme(117);
        GeneratoreEsponenziale ge = new GeneratoreEsponenziale(160, gu);
        Generatore3Erlangiano ger = new Generatore3Erlangiano(10, 1);
        GeneratorePoissoniano gep = new GeneratorePoissoniano(160, 
            new GeneratoreUniforme(135));
        for (int i = 0; i < 10; i++) {
            System.out.println(df.format(ge.next()));
        }
    }
}
