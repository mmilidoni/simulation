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
    private long a, xi;
    private final int b;

    public GeneratoreUniforme() {
        this(15l, 31, 1220703125);
    }

    public GeneratoreUniforme(long xi, int b, int a) {
        this.xi = xi;
        this.h = (long) Math.pow(2, b - 2);
        this.m = (long) Math.pow(2, b);
        this.a = a;
        this.b = b;
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
}
