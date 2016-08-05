/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mls;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import mls.generatori.Generatore3Erlangiano;
import mls.generatori.GeneratoreEsponenziale;
import mls.generatori.GeneratoreUniforme;
import mls.util.EventoComparator;
import mls.util.JobComparator;
import mls.util.TipoEvento;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author Michele Milidoni <michelemilidoni@gmail.com>
 */
public class Main {

    private PriorityQueue<Evento> calendario;
    private PriorityQueue<Job> cpuQueue;
    private Stack<Job> ioQueue;
//    private int clock;
    private GeneratoreEsponenziale genExp;
    private GeneratoreUniforme genUni;
    private Generatore3Erlangiano gen3Erl;
    private Job jobCorrenteCpu;
    private Job jobCorrenteIO;
    private int p, n0, i, j, nOss = 0, nRun = 0;
    private double tempiRisposta[][];
    private boolean stop = false;
    private FramePlot frame;

    public Main(double gamma, double mu, int p, int n0, FramePlot frame) {
        genUni = new GeneratoreUniforme();
        genExp = new GeneratoreEsponenziale(gamma, genUni);
        gen3Erl = new Generatore3Erlangiano(mu, new GeneratoreEsponenziale(mu, genUni));
//      clock = 0;
        this.p = p;
        this.n0 = n0;
        tempiRisposta = new double[p][n0];
        this.frame = frame;
        this.frame.pack();
        RefineryUtilities.centerFrameOnScreen(this.frame);
        this.frame.setVisible(true);
        statoIniziale();
    }

    private void statoIniziale() {
        cpuQueue = new PriorityQueue<>(new JobComparator());
        calendario = new PriorityQueue<>(new EventoComparator());
        ioQueue = new Stack<>();
        jobCorrenteCpu = null;
        jobCorrenteIO = null;
        nOss = 0;
    }

    public static void main(String[] args) {
        int nn0 = 100;
        int pp = 40;
        FramePlot frame = new FramePlot("Plot");
        for (int n = 1; n <= nn0; n++) {
            Main m = new Main(0.5, 0.5, pp, n, frame);
            m.sequenziatore();
        }
    }

    private void sequenziatore() {
        while (!stop) {
            if (nRun == p) {
                calendario.add(new Evento(0d, TipoEvento.FINE_SIMULAZIONE));
            }
            calendario.add(new Evento(genExp.next(), TipoEvento.ARRIVO));
            Evento e = calendario.poll();
            if (null != e.getTipo()) {
                switch (e.getTipo()) {
                    case ARRIVO:
                        arrivo();
                        break;
                    case FINE_CPU:
                        fineCPU();
                        break;
                    case FINE_IO:
                        fineIO();
                        break;
                    case FINE_SIMULAZIONE:
                        fineSimulazione();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void arrivo() {
        Job job = new Job();
        job.setCarico(gen3Erl.next());
        //System.out.println(" ARRIVO ");
        //System.out.println(job);

        if (jobCorrenteCpu == null) {
            jobCorrenteCpu = job;
            calendario.add(new Evento(job.getCarico(), TipoEvento.FINE_CPU));
            //System.out.println("imposto jobCorrenteCpu");
        } else {
            cpuQueue.add(job);
            //System.out.println("metto il job in cpuQueue");
        }
    }

    private void fineCPU() {
        double routing = genUni.next();
        if (routing <= 0.9) {
            if (jobCorrenteIO == null) {
                jobCorrenteIO = (Job) jobCorrenteCpu.clona();
                jobCorrenteIO.setCarico(gen3Erl.next());
                calendario.add(new Evento(jobCorrenteIO.getCarico(), TipoEvento.FINE_IO));
                //System.out.println("imposto jobCorrenteIO da CPU");
            } else {
                Job temp = jobCorrenteCpu.clona();
                temp.setCarico(gen3Erl.next());
                ioQueue.add(temp);
                //System.out.println("metto il job in ioQueue");
            }

        } else {
            //temp.setTempoUscita(new GregorianCalendar());
            //System.out.println("entro nella fase di stab o stat");
            //TODO: implementare fase di stabilizzazione 
            tempiRisposta[nRun][nOss++] = jobCorrenteCpu.getCarico();

            //trSomma += temp.getTempoRisposta();
            if (nOss == n0) {
                statoIniziale();
                nRun++;
            }

            //TODO: implementare fase statistica
        }
        jobCorrenteCpu = null;

        if (!cpuQueue.isEmpty()) {
            jobCorrenteCpu = cpuQueue.poll();
            jobCorrenteCpu.setCarico(gen3Erl.next());
            calendario.add(new Evento(jobCorrenteCpu.getCarico(), TipoEvento.FINE_CPU));
            //System.out.println("prelevo un job da cpuQueue");
        }
    }

    private void fineIO() {
        if (jobCorrenteCpu == null) {
            jobCorrenteCpu = (Job) jobCorrenteIO.clona();
            jobCorrenteCpu.setCarico(gen3Erl.next());
            calendario.add(new Evento(jobCorrenteCpu.getCarico(), TipoEvento.FINE_CPU));
            //System.out.println("imposto jobCorrenteCpu da IO");
        } else {
            Job temp = jobCorrenteIO.clona();
            temp.setCarico(gen3Erl.next());
            cpuQueue.add(temp);
            //System.out.println("metto il job in cpuQueue");
        }
        jobCorrenteIO = null;

        if (!ioQueue.isEmpty()) {
            jobCorrenteIO = ioQueue.pop();
            jobCorrenteIO.setCarico(gen3Erl.next());
            calendario.add(new Evento(jobCorrenteIO.getCarico(), TipoEvento.FINE_IO));
            //System.out.println("prelevo un job da ioQueue");
        }
    }

    private void fineSimulazione() {
        //printArray(tempiRisposta);

        double uSommaMedia = 0;
        for (int jj = 0; jj < p; jj++) {
            double uSomma = 0;
            for (int ii = 0; ii < n0; ii++) {
                uSomma += tempiRisposta[jj][ii];
            }
            uSommaMedia += uSomma / n0;
        }
        double en = Math.round(uSommaMedia / p * 10000) / 10000d;

        double uSommaDiff = 0;
        for (int jj = 0; jj < p; jj++) {
            double uSomma = 0;
            for (int ii = 0; ii < n0; ii++) {
                uSomma += tempiRisposta[jj][ii];
            }
            uSommaDiff += Math.pow(uSomma / n0 - en, 2);
        }
        
        double vc = Math.round((uSommaDiff / (p-1)) * 10000) / 10000d;

       // System.out.println(n0 + " " + en + "\t" + vc);
        frame.addSerieMedia(n0, en);
        frame.addSerieVarianza(n0, vc);

        //      System.out.println("----------------");
        stop = true;
    }

    private static void printArray(double matrix[][]) {
        for (double[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
