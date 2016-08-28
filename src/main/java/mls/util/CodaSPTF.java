/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mls.util;

import java.util.Iterator;
import java.util.PriorityQueue;

/**
 *
 * @author Michele Milidoni <michelemilidoni@gmail.com>
 * @param <E>
 */
public class CodaSPTF<E> extends PriorityQueue implements Coda<E> {

    private final JobComparator jobComparator;

    public CodaSPTF(JobComparator jobComparator) {
        super(100, jobComparator);
        this.jobComparator = jobComparator;
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

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public Coda clona() {
        CodaSPTF cf = new CodaSPTF(jobComparator);
        for (Iterator it = this.iterator(); it.hasNext();) {
            cf.add(it.next());
        }
        return cf;
    }
}
