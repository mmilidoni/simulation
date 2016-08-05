/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mls.util;

import java.util.PriorityQueue;
import java.util.Stack;
import mls.Evento;
import mls.Job;

/**
 *
 * @author Michele Milidoni <michelemilidoni@gmail.com>
 */
public class Clona {

    public static PriorityQueue<Job> cpuQueue(PriorityQueue<Job> a) {
        PriorityQueue<Job> res = new PriorityQueue<>(new JobComparator());
        for (Job j : a) {
            res.add(j.clona());
        }
        return res;
    }

    public static Stack<Job> ioQueue(Stack<Job> a) {
        Stack<Job> res = new Stack<>();
        for (Job j : a) {
            res.add(j.clona());
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
