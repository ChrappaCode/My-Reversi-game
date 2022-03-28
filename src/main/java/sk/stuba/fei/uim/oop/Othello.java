package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class Othello {

    public Othello(){

        JFrame okno = new JFrame("Hra Othello");
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setSize(550,550);
        okno.setResizable(false);
        okno.setLayout(new GridLayout());


        okno.setVisible(true);
    }
}
