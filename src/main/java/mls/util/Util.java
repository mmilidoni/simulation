/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mls.util;

/**
 *
 * @author Michele Milidoni <michelemilidoni@gmail.com>
 */
public class Util {

    public static double round(double num, int decimals) {
        int mul = (int) Math.pow(10, decimals);
        return (double) (((double) Math.round(num * mul)) / (double) mul);
    }
}
