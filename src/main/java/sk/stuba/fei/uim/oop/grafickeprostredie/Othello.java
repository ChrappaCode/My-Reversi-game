package sk.stuba.fei.uim.oop.grafickeprostredie;

import sk.stuba.fei.uim.oop.Tuk;
import sk.stuba.fei.uim.oop.nastavenia.OthelloNastavenia;

import javax.swing.*;
import java.awt.*;


public class Othello {

    public Othello(){

        JFrame okno = new JFrame("Hra Othello");
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setSize(550,550);
        okno.setResizable(false);
        okno.getContentPane().setBackground(Color.cyan);
        okno.setLayout(new GridLayout(9,8));

        OthelloNastavenia nastavenia = new OthelloNastavenia();
        Tuk tuk = new Tuk();

        okno.addKeyListener(nastavenia);

        /*
        HernaPlocha hernaPlocha = new HernaPlocha();
        System.out.println("here : ");
        hernaPlocha.NovaPlocha();
        */

        for (int i = 0; i < 64; i++) {

            //okno.add(new MyPanel());
            JButton button = new JButton();
            button.addActionListener(tuk);
            okno.add(button);

        }

        //GridBagConstraints c = new GridBagConstraints();
        JPanel sideMenu = new JPanel();

        sideMenu.setBackground(Color.LIGHT_GRAY);

        JButton buttonRestart = new JButton("Reštart");

        /*c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;*/

        buttonRestart.addActionListener(nastavenia);
        buttonRestart.setFocusable(false);

        sideMenu.setLayout(new FlowLayout());
        sideMenu.add(buttonRestart);


        okno.add(sideMenu);
        okno.setVisible(true);

    }
}
