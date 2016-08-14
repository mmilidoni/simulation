/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mls.util;

import java.util.Comparator;
import mls.Evento;

/**
 *
 * @author Michele Milidoni <michelemilidoni@gmail.com>
 */
public class EventoComparator implements Comparator<Evento> {

    @Override
    public int compare(Evento o1, Evento o2) {
        return o1.getDurata().compareTo(o2.getDurata());
    }

}
