package sk.stuba.fei.uim.oop.grafickeprostredie;

import sk.stuba.fei.uim.oop.MyPanel;
import sk.stuba.fei.uim.oop.nastavenia.OthelloNastavenia;
import sk.stuba.fei.uim.oop.plocha.HernaPlocha;

import javax.swing.*;
import javax.swing.Box;
import java.awt.*;

public class Othello {

    public Othello(){

        JFrame okno = new JFrame("Hra Othello");
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setSize(550,550);
        okno.setResizable(false);
        okno.setLayout(new GridLayout(8,8));

        OthelloNastavenia nastavenia = new OthelloNastavenia();
        okno.addKeyListener(nastavenia);

        HernaPlocha hernaPlocha = new HernaPlocha();
        System.out.println("here : ");
        hernaPlocha.NovaPlocha();

        //okno.add(nastavenia.getRender());

        for (int i = 0; i < 63; i++) {

            okno.add(new MyPanel());
        }

        JPanel sideMenu = new JPanel();
        sideMenu.setBackground(Color.LIGHT_GRAY);

        JButton buttonRestart = new JButton("Reštart");

        buttonRestart.addActionListener(nastavenia);
        buttonRestart.setFocusable(false);

        sideMenu.setLayout(new GridLayout());

        sideMenu.add(buttonRestart);


        okno.add(sideMenu, BorderLayout.LINE_START);


        okno.setVisible(true);
    }
}
