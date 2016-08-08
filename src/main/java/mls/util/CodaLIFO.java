/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mls.util;

import java.util.Stack;

/**
 *
 * @author Michele Milidoni <michelemilidoni@gmail.com>
 * @param <E>
 */
public class CodaLIFO<E> extends Stack<E> implements Coda<E> {

    @Override
    public boolean metti(E a) {
        super.addElement(a);
        return true;
    }

    @Override
    public Object togli() {
        return super.pop();
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
