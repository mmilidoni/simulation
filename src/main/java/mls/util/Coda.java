/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mls.util;

/**
 *
 * @author Michele Milidoni <michelemilidoni@gmail.com>
 * @param <E>
 */
public interface Coda<E> {

    public boolean metti(E a);

    public Object togli();

    public boolean isEmpty();
}
