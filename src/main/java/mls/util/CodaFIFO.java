/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mls.util;

import java.util.LinkedList;

/**
 *
 * @author Michele Milidoni <michelemilidoni@gmail.com>
 * @param <E>
 */
public class CodaFIFO<E> extends LinkedList<E> implements Coda<E> {

    @Override
    public boolean metti(E a) {
        return super.offer(a);
    }

    @Override
    public Object togli() {
        return super.poll();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public int size() {
        return super.size();
    }

}
