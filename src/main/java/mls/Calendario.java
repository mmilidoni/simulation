/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mls;

import static mls.util.TipoEvento.ARRIVO;
import static mls.util.TipoEvento.FINE_CPU;
import static mls.util.TipoEvento.FINE_CPU2;
import static mls.util.TipoEvento.FINE_IO;
import static mls.util.TipoEvento.FINE_SIMULAZIONE;

/**
 *
 * @author Michele Milidoni <michelemilidoni@gmail.com>
 */
public class Calendario {

    private Evento arrivo;
    private Evento cpu;
    private Evento cpu2;
    private Evento io;
    private Evento simulazione;
    private double clock;

    public Calendario() {
        arrivo = new Evento(Double.MAX_VALUE, ARRIVO);
        cpu = new Evento(Double.MAX_VALUE, FINE_CPU);
        cpu2 = new Evento(Double.MAX_VALUE, FINE_CPU2);
        io = new Evento(Double.MAX_VALUE, FINE_IO);
        simulazione = new Evento(Double.MAX_VALUE, FINE_SIMULAZIONE);
        clock = 0d;
    }

    public Evento next() {
        Evento out = null;
        double min = Double.MAX_VALUE;

        if (arrivo.getDurata() < min) {
            min = arrivo.getDurata();
            out = arrivo;
        }
        if (cpu.getDurata() < min) {
            min = cpu.getDurata();
            out = cpu;
        }
        if (cpu2.getDurata() < min) {
            min = cpu2.getDurata();
            out = cpu2;
        }
        if (io.getDurata() < min) {
            min = io.getDurata();
            out = io;
        }
        if (simulazione.getDurata() < min) {
            min = simulazione.getDurata();
            out = simulazione;
        }

        clock = min;
        switch (out.getTipo()) {
            case ARRIVO:
                setArrivo(new Evento(Double.MAX_VALUE, ARRIVO));
                break;
            case FINE_CPU:
                setCpu(new Evento(Double.MAX_VALUE, FINE_CPU));
                break;
            case FINE_CPU2:
                setCpu2(new Evento(Double.MAX_VALUE, FINE_CPU2));
                break;
            case FINE_IO:
                setIo(new Evento(Double.MAX_VALUE, FINE_IO));
                break;
            case FINE_SIMULAZIONE:
                setSimulazione(new Evento(Double.MAX_VALUE, FINE_SIMULAZIONE));
                break;
            default:
                throw new AssertionError(out.getTipo().name());
        }

//        System.out.println("Processo " + out.getTipo() + " al clock " + clock);
        return out;
    }

    public double getClock() {
        return clock;
    }

    public void setClock(double clock) {
        this.clock = clock;
    }

    public Evento getArrivo() {
        return arrivo;
    }

    private void setArrivo(Evento arrivo) {
        this.arrivo = arrivo;
    }

    public void addArrivo(double servizio) {
        setArrivo(new Evento(getClock() + servizio, ARRIVO));
    }

    public void resetArrivo() {
        setArrivo(new Evento(Double.MAX_VALUE, ARRIVO));
    }

    public Evento getCpu() {
        return cpu;
    }

    private void setCpu(Evento cpu) {
        this.cpu = cpu;
    }

    public void addCpu(double servizio) {
        setCpu(new Evento(getClock() + servizio, FINE_CPU));
    }

    public void resetCpu() {
        setCpu(new Evento(Double.MAX_VALUE, FINE_CPU));
    }

    public Evento getCpu2() {
        return cpu2;
    }

    private void setCpu2(Evento cpu2) {
        this.cpu2 = cpu2;
    }

    public void addCpu2(double servizio) {
        setCpu2(new Evento(getClock() + servizio, FINE_CPU2));
    }

    public void resetCpu2() {
        setCpu2(new Evento(Double.MAX_VALUE, FINE_CPU2));
    }

    public Evento getIo() {
        return io;
    }

    private void setIo(Evento io) {
        this.io = io;
    }

    public void addIo(double servizio) {
        setIo(new Evento(getClock() + servizio, FINE_IO));
    }

    public void resetIo() {
        setIo(new Evento(Double.MAX_VALUE, FINE_IO));
    }

    public Evento getSimulazione() {
        return simulazione;
    }

    public void setSimulazione(Evento simulazione) {
        this.simulazione = simulazione;
    }

    public Calendario clona() {
        Calendario c = new Calendario();
        c.setArrivo(arrivo.clona());
        c.setCpu(cpu.clona());
        c.setCpu2(cpu2.clona());
        c.setIo(io.clona());
        c.setSimulazione(simulazione.clona());
        c.setClock(clock);

        return c;
    }

}
