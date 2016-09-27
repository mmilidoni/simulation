/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mls.util;

import mls.generatori.Generatore3Erlangiano;
import mls.generatori.GeneratoreEsponenziale;
import mls.generatori.GeneratoreUniforme;

/**
 *
 * @author Michele Milidoni <michelemilidoni@gmail.com>
 */
public class ConfigurazioneStandardFactory extends ConfigurazioneSimulazioneFactory {

    @Override
    public Coda getCpuQueue() {
        return new CodaSPTF<>(new JobComparator());
    }

    @Override
    public Coda getIoQueue() {
        return new CodaLIFO<>();
    }

    @Override
    public Generatore getGeneratoreArrivi(double Ta, double seme) {
        return new GeneratoreEsponenziale(Ta, new GeneratoreUniforme(seme));
    }

    @Override
    public Generatore getGeneratoreCentroCPU(double Ts, double seme) {
        return new Generatore3Erlangiano(Ts, seme);
    }

    @Override
    public Generatore getGeneratoreCentroCPU2(double Ts, double seme) {
        return new Generatore3Erlangiano(Ts, seme);
    }

    @Override
    public Generatore getGeneratoreCentroIO(double Ts, double seme) {
        return new Generatore3Erlangiano(Ts, seme);
    }

}
