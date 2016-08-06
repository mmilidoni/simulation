/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mls;

import java.awt.Color;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import mls.generatori.Generatore3Erlangiano;
import mls.generatori.GeneratoreEsponenziale;
import mls.generatori.GeneratoreUniforme;
import mls.util.Clona;
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
    private GeneratoreEsponenziale genExp;
    private GeneratoreUniforme genUni;
    private Generatore3Erlangiano gen3Erl;
    private Job jobCorrenteCpu;
    private Job jobCorrenteIO;
    private int p;
    private int n0, nRun = 0, nOss = 0;
    private double uSommaG[];
    private double en[];
    private double vc[];
    private double x[];
    private double y[];
    private double uSommaStat;
    private boolean stop = false;
    private FrameWin frame;
    private boolean stabile = false;

    private PriorityQueue<Job> cpuQueueStabile;
    private Stack<Job> ioQueueStabile;
    private PriorityQueue<Evento> calendarioStabile;
    private Job jobCorrenteCpuStabile;
    private Job jobCorrenteIOStabile;

    public Main() {
        /*
        genUni = new GeneratoreUniforme();
        genExp = new GeneratoreEsponenziale(gamma, genUni);
        gen3Erl = new Generatore3Erlangiano(mu, new GeneratoreEsponenziale(mu, genUni));
        this.p = p;
        this.n0 = n0;
        uSommaG = new double[p];
        en = new double[n0];
        vc = new double[n0];
        this.frame = frame;
        this.frame.pack();
        RefineryUtilities.centerFrameOnScreen(this.frame);
        this.frame.setVisible(true);
        statoIniziale();
         */
    }
/*
    private void init(double gamma, double mu, int p, int n0, FrameWin frame) {
        genUni = new GeneratoreUniforme();
        genExp = new GeneratoreEsponenziale(gamma, genUni);
        gen3Erl = new Generatore3Erlangiano(mu, new GeneratoreEsponenziale(mu, genUni));
        this.p = p;
        this.n0 = n0;
        uSommaG = new double[p];
        en = new double[n0];
        vc = new double[n0];
        this.frame = frame;
        this.frame.pack();
        RefineryUtilities.centerFrameOnScreen(this.frame);
        this.frame.setVisible(true);
        statoIniziale();
    }
*/
    
    public void avviaSimulazione(int p, double gamma, double lambda) {
        int nMax = 10000;

        genUni = new GeneratoreUniforme();
        genExp = new GeneratoreEsponenziale(gamma, genUni);
        gen3Erl = new Generatore3Erlangiano(lambda, new GeneratoreEsponenziale(lambda, genUni));
        this.p = p;
        this.n0 = nMax;
        uSommaG = new double[p];
        en = new double[n0];
        vc = new double[n0];
        
        statoIniziale();
        
        //int nMax = 10000;
        //Main m = new Main(gamma, lambda, p, nMax, frame2);
        //frame2.setMain(m);
        for (int n = 1; n <= nMax; n++) {
            statoIniziale();
            setN0(n);
            sequenziatore();
        }
    }

    public void statoIniziale() {
        cpuQueue = new PriorityQueue<>(new JobComparator());
        calendario = new PriorityQueue<>(new EventoComparator());
        ioQueue = new Stack<>();
        jobCorrenteCpu = null;
        jobCorrenteIO = null;
    }

    private void statoEquilibrio() {
        //TODO: implementare lo stato di equilibrio
        cpuQueue = Clona.cpuQueue(cpuQueueStabile);
        calendario = Clona.calendario(calendarioStabile);
        ioQueue = Clona.ioQueue(ioQueueStabile);
        if (jobCorrenteCpuStabile != null) {
            jobCorrenteCpu = jobCorrenteCpuStabile.clona();
        }
        if (jobCorrenteIOStabile != null) {
            jobCorrenteIO = jobCorrenteIOStabile.clona();
        }
        uSommaStat = 0d;
        setN0(genUni.next(50, 100));
        nOss = 0;
    }

    private void setStatoEquilibrio() {
        x = new double[p];
        y = new double[p];
        cpuQueueStabile = Clona.cpuQueue(cpuQueue);
        ioQueueStabile = Clona.ioQueue(ioQueue);
        calendarioStabile = Clona.calendario(calendario);
        if (jobCorrenteCpu != null) {
            jobCorrenteCpuStabile = jobCorrenteCpu.clona();
        }
        if (jobCorrenteIO != null) {
            jobCorrenteIOStabile = jobCorrenteIO.clona();
        }
    }

    public static void main(String[] args) {
        
        FrameWin frame = new FrameWin();
        Main m = new Main();
        m.frame = frame;
        m.frame.setMain(m);
        m.frame.pack();
        RefineryUtilities.centerFrameOnScreen(m.frame);
        m.frame.setVisible(true);
    }

    public void setN0(int n0) {
        this.n0 = n0;
        stop = false;
        System.out.println("imposto n0 = " + n0);
    }

    public void sequenziatore() {
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

        } else if (!stabile) {
            uSommaG[nRun++] += jobCorrenteCpu.getCarico();

            if (nRun == p) {
                double temp = 0;
                for (int jj = 0; jj < p; jj++) {
                    temp += uSommaG[jj] / n0;
                }
                en[n0 - 1] = temp / p;

                double temp2 = 0;
                for (int jj = 0; jj < p; jj++) {
                    temp2 += Math.pow(uSommaG[jj] / n0 - en[n0 - 1], 2);
                }
                vc[n0 - 1] = temp2 / (p - 1);

//                System.out.println(n0 + "\t" + en[n0 - 1]);
                frame.getPlot().addSerieMedia(n0 - 1, en[n0 - 1]);
                frame.getPlot().addSerieVarianza(n0 - 1, vc[n0 - 1]);
                if (n0 % 10 == 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                nRun = 0;
                stop = true;
            }

            statoIniziale();

        } else {
            uSommaStat += jobCorrenteCpu.getCarico();
            nOss++;

            if (nOss == n0) {
                y[nRun] = n0;
                x[nRun] = uSommaStat;
                nRun++;
                stop = true;
                statoEquilibrio();
            }

            if (nRun == p) {
                calendario.add(new Evento(0d, TipoEvento.FINE_SIMULAZIONE));
            }
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
        double xSegn = 0;
        double ySegn = 0;
        for (int jj = 0; jj < p; jj++) {
            xSegn += x[jj];
            ySegn += y[jj];
        }
        xSegn /= p;
        ySegn /= p;

        double xSommaQDiff = 0;
        double ySommaQDiff = 0;
        double xySommaQDiff = 0;
        for (int jj = 0; jj < p; jj++) {
            xSommaQDiff += Math.pow(x[jj] - xSegn, 2);
            ySommaQDiff += Math.pow(y[jj] - ySegn, 2);
            xySommaQDiff += (x[jj] - xSegn) * (y[jj] - ySegn);
        }

        double s2_11 = xSommaQDiff / (p - 1);
        double s2_22 = ySommaQDiff / (p - 1);
        double s2_12 = xySommaQDiff / (p - 1);

        double f = xSegn / ySegn;
        double s2 = s2_11 - 2 * f * s2_12 + f * f * s2_22;
        double d = Math.sqrt(s2) / (ySegn * Math.sqrt(p));
        double mediaInferiore = f - d * 1.645;
        double mediaSuperiore = f + d * 1.645;

        frame.getPlot().addMarker(f, Color.GREEN);
        frame.getPlot().addMarker(mediaInferiore, Color.RED);
        frame.getPlot().addMarker(mediaSuperiore, Color.RED);
        System.out.println("inf: " + mediaInferiore);
        System.out.println("cen: " + f);
        System.out.println("sup: " + mediaSuperiore);

        stop = true;
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }

    public void setStabile() {
        p = 40;
        setStatoEquilibrio();
        this.stabile = true;
        statoEquilibrio();
    }

    private static void printArray(double matrix[][]) {
        for (double[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
