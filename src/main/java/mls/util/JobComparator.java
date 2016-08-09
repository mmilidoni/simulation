/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mls.util;

import java.util.Comparator;
import mls.Job;

/**
 *
 * @author Michele Milidoni <michelemilidoni@gmail.com>
 */
public class JobComparator implements Comparator<Job> {

    @Override
    public int compare(Job o1, Job o2) {
        return o1.getTempoServizio().compareTo(o2.getTempoServizio());
    }

}
