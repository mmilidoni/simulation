/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mls.util;

import java.util.PriorityQueue;

/**
 *
 * @author Michele Milidoni <michelemilidoni@gmail.com>
 * @param <E>
 */
public class CodaSPTF<E> extends PriorityQueue implements Coda<E> {

    public CodaSPTF(JobComparator jobComparator) {
        super(jobComparator);
    }

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


    
}
