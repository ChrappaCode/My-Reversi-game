package sk.stuba.fei.uim.oop.plocha;

import lombok.Getter;

import java.awt.*;

public class HernaPlocha {

    private static final int VELKOST_PLOCHY = 8;
    private int[][] plocha;

    @Getter
    private int a = 0;

    public void NovaPlocha(){

        this.plocha = new int[VELKOST_PLOCHY][VELKOST_PLOCHY];

        this.plocha[0][0] = 5;

        System.out.println(plocha[0][0]);

    }


}
