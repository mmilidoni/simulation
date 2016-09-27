/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mls.util;

/**
 *
 * @author Michele Milidoni <michelemilidoni@gmail.com>
 */
abstract public class ConfigurazioneSimulazioneFactory {

    public static ConfigurazioneSimulazioneFactory getFactory(boolean convalida) {
        if (convalida) {
            return new ConfigurazioneConvalidaFactory();
        } else {
            return new ConfigurazioneStandardFactory();
        }
    }

    public abstract Coda getCpuQueue();

    public abstract Coda getIoQueue();

    public abstract Generatore getGeneratoreArrivi(double Ta, double seme);

    public abstract Generatore getGeneratoreCentroCPU(double Ts, double seme);

    public abstract Generatore getGeneratoreCentroCPU2(double Ts, double seme);

    public abstract Generatore getGeneratoreCentroIO(double Ts, double seme);
}
