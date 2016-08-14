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
public interface Generatore {

    public double next();
    
    public void setSeme(double seme);
    
    public double getSeme();
}
