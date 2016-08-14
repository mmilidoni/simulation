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
public class GeneratoreUniforme implements Generatore {

    private final long h, m;
    private long a;
    private double xi;
    private final int b;

    public GeneratoreUniforme() {
        this(15l);
    }

    public GeneratoreUniforme(double xi) {
        this.a = 3;
        this.b = 28;
        this.xi = xi;
        this.h = (long) Math.pow(2, b - 2);
        this.m = (long) Math.pow(2, b);
    }

    @Override
    public double next() {
        xi = (long) (a * xi) % m;
        double r = (double) xi / m;
        return r;
    }

    public int next(int a, int b) {
        double i = next();
        return (int) (i * (b - a) + a);
    }

    @Override
    public void setSeme(double seme) {
        xi = seme;
    }

    @Override
    public double getSeme() {
        return (int) xi;
    }

}
