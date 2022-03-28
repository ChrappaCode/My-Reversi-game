package sk.stuba.fei.uim.oop.plocha;

import java.awt.*;
import java.util.ArrayList;

public class HernaPlocha {

    private static final int VELKOST_PLOCHY = 8;
    private String[][] plocha;


    public void NovaPlocha(){

        this.plocha = new String[VELKOST_PLOCHY][VELKOST_PLOCHY];

        this.plocha[2][6] = "5";

        System.out.println(this.plocha[2][6]);

    }




}
