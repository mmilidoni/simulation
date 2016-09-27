/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mls.util;

import mls.generatori.Generatore3Erlangiano;
import mls.generatori.GeneratoreEsponenziale;
import mls.generatori.GeneratorePoissoniano;
import mls.generatori.GeneratoreUniforme;

/**
 *
 * @author Michele Milidoni <michelemilidoni@gmail.com>
 */
public class ConfigurazioneConvalidaFactory extends ConfigurazioneSimulazioneFactory {

    @Override
    public Coda getCpuQueue() {
        return new CodaFIFO<>();
    }

    @Override
    public Coda getIoQueue() {
        return new CodaFIFO<>();
    }

    @Override
    public Generatore getGeneratoreArrivi(double Ta, double seme) {
        return new GeneratorePoissoniano(Ta, new GeneratoreUniforme(seme));
    }

    @Override
    public Generatore getGeneratoreCentroCPU(double Ts, double seme) {
        return new GeneratoreEsponenziale(Ts, new GeneratoreUniforme(seme));
    }

    @Override
    public Generatore getGeneratoreCentroCPU2(double Ts, double seme) {
        return new GeneratoreEsponenziale(Ts, new GeneratoreUniforme(seme));
    }

    @Override
    public Generatore getGeneratoreCentroIO(double Ts, double seme) {
        return new GeneratoreEsponenziale(Ts, new GeneratoreUniforme(seme));

    }

}
