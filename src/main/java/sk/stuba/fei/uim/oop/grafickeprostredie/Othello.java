package sk.stuba.fei.uim.oop.grafickeprostredie;

import sk.stuba.fei.uim.oop.nastavenia.BoxLogika;
import sk.stuba.fei.uim.oop.nastavenia.Tuk;
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
        okno.setLayout(new BorderLayout());

        OthelloNastavenia nastavenia = new OthelloNastavenia();
        Tuk tuk = new Tuk();
        BoxLogika boxLogika = new BoxLogika(okno);


        JPanel hernyPanel = new JPanel();
        hernyPanel.setLayout(new GridLayout(8,8,5,5));

        for (int i = 0; i < 64; i++) {

            //okno.add(new MyPanel());
            JButton tlacitko = new JButton();
            tlacitko.addActionListener(tuk);
            tlacitko.addKeyListener(nastavenia);
            hernyPanel.add(tlacitko);
        }


        JPanel menu = new JPanel();
        menu.setBackground(Color.RED);
        menu.setLayout(new GridLayout(2, 1));

        JButton restart = new JButton("Reštart");
        restart.addActionListener(nastavenia);
        restart.addKeyListener(nastavenia);

        String[] velkosti ={"6x6","8x8","10x10","12x12"};
        JComboBox box = new JComboBox(velkosti);
        box.addActionListener(boxLogika);

        System.out.println(box.getSelectedItem());


        okno.add(hernyPanel);
        menu.setLayout(new FlowLayout());
        menu.add(restart , BorderLayout.LINE_END);
        menu.add(box ,BorderLayout.LINE_START);
        okno.add(menu , BorderLayout.PAGE_END);



        okno.setVisible(true);

    }
}
