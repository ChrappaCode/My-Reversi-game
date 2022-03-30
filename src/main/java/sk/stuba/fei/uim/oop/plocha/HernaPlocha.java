package sk.stuba.fei.uim.oop.plocha;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class HernaPlocha {

    private static final int VELKOST_PLOCHY = 8;
    private int[][] plocha;


    public void NovaPlocha(){

        this.plocha = new int[VELKOST_PLOCHY][VELKOST_PLOCHY];

        this.plocha[0][0] = 5;

        System.out.println(plocha[0][0]);

    }




}
