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
public class StatoCorrente {

    private Coda cpuQueue, ioQueue;
    private PriorityQueue<Evento> calendario;
    Job jobCorrenteCpu, jobCorrenteIO;

    public Coda getCpuQueue() {
        return cpuQueue;
    }

    public void setCpuQueue(Coda cpuQueue) {
        this.cpuQueue = cpuQueue;
    }

    public Coda getIoQueue() {
        return ioQueue;
    }

    public void setIoQueue(Coda ioQueue) {
        this.ioQueue = ioQueue;
    }

    public PriorityQueue<Evento> getCalendario() {
        return calendario;
    }

    public void setCalendario(PriorityQueue<Evento> calendario) {
        this.calendario = calendario;
    }

    public Job getJobCorrenteCpu() {
        return jobCorrenteCpu;
    }

    public void setJobCorrenteCpu(Job jobCorrenteCpu) {
        this.jobCorrenteCpu = jobCorrenteCpu;
    }

    public Job getJobCorrenteIO() {
        return jobCorrenteIO;
    }

    public void setJobCorrenteIO(Job jobCorrenteIO) {
        this.jobCorrenteIO = jobCorrenteIO;
    }
    
    
}
