package br.edu.ifpb.si.pdm.ficarrico;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by admin on 24/05/16.
 */
public class Megasena {
    public static  String getNumero(){
        Random r = new Random();
        Set<Integer> numeros = new TreeSet<Integer>();

        do {
           numeros.add(r.nextInt(60)+1);
        } while (numeros.size() < 6);

        return numeros.toString();
    }
}
