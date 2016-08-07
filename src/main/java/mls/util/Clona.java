/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mls.util;

import java.util.PriorityQueue;
import mls.Evento;
import mls.Job;

/**
 *
 * @author Michele Milidoni <michelemilidoni@gmail.com>
 */
public class Clona {

    public static Coda<Job> sptfQueue(CodaSPTF<Job> a) {
        Coda<Job> res = new CodaSPTF<>(new JobComparator());
        for (Object j1 : a) {
            Job j = (Job) j1;
            res.metti(j.clona());
        }
        return res;
    }

    public static Coda<Job> lifoQueue(CodaLIFO<Job> a) {
        Coda<Job> res = new CodaLIFO<>();
        for (Job j : a) {
            res.metti(j.clona());
        }
        return res;
    }

    public static Coda<Job> fifoQueue(CodaFIFO<Job> a) {
        Coda<Job> res = new CodaFIFO<>();
        for (Job j : a) {
            res.metti(j.clona());
        }
        return res;
    }

    public static PriorityQueue<Evento> calendario(PriorityQueue<Evento> a) {
        PriorityQueue<Evento> res = new PriorityQueue<>(new EventoComparator());
        for (Evento e : a) {
            res.add(e.clona());
        }
        return res;
    }
}
