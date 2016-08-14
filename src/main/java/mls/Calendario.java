/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mls;

import static mls.util.TipoEvento.ARRIVO;
import static mls.util.TipoEvento.FINE_CPU;
import static mls.util.TipoEvento.FINE_IO;
import static mls.util.TipoEvento.FINE_SIMULAZIONE;

/**
 *
 * @author Michele Milidoni <michelemilidoni@gmail.com>
 */
public class Calendario {

    private Evento arrivo;
    private Evento cpu;
    private Evento io;
    private Evento simulazione;
    private double clock;

    public Calendario() {
        arrivo = new Evento(Double.MAX_VALUE, ARRIVO);
        cpu = new Evento(Double.MAX_VALUE, FINE_CPU);
        io = new Evento(Double.MAX_VALUE, FINE_IO);
        simulazione = new Evento(Double.MAX_VALUE, FINE_SIMULAZIONE);
        clock = 0d;
    }

    public Evento next() {
        Evento out = null;
        double min = Double.MAX_VALUE;

        if (arrivo.getOrario() < min) {
            min = arrivo.getOrario();
            out = arrivo;
        }
        if (cpu.getOrario() < min) {
            min = cpu.getOrario();
            out = cpu;
        }
        if (io.getOrario() < min) {
            min = io.getOrario();
            out = io;
        }
        if (simulazione.getOrario() < min) {
            min = simulazione.getOrario();
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
            case FINE_IO:
                setIo(new Evento(Double.MAX_VALUE, FINE_IO));
                break;
            case FINE_SIMULAZIONE:
                setSimulazione(new Evento(Double.MAX_VALUE, FINE_SIMULAZIONE));
                break;
            default:
                throw new AssertionError(out.getTipo().name());
        }

        // System.out.println("Processo " + out.getTipo() + " al clock " + clock);
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

    public void setArrivo(Evento arrivo) {
        this.arrivo = arrivo;
    }

    public Evento getCpu() {
        return cpu;
    }

    public void setCpu(Evento cpu) {
        this.cpu = cpu;
    }

    public Evento getIo() {
        return io;
    }

    public void setIo(Evento io) {
        this.io = io;
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
        c.setIo(io.clona());
        c.setSimulazione(simulazione.clona());
        c.setClock(clock);

        return c;
    }

}
