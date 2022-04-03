package sk.stuba.fei.uim.oop.grafickeprostredie;

import sk.stuba.fei.uim.oop.objekty.Kamen;
import sk.stuba.fei.uim.oop.nastavenia.MyskaNastavenia;
import sk.stuba.fei.uim.oop.nastavenia.BoxLogika;
import sk.stuba.fei.uim.oop.nastavenia.Tuk;
import sk.stuba.fei.uim.oop.nastavenia.OthelloNastavenia;
import sk.stuba.fei.uim.oop.plocha.HernaPlocha;

import javax.swing.*;
import java.awt.*;

public class Othello {

    private static final int VELKOST_HRACEJ_PLOCHY_X = 6;
    private static final int VELKOST_HRACEJ_PLOCHY_Y = 6;
    private static final int ODSKOK_HRACEJ_PLOCHY_X = 6;
    private static final int ODSKOK_HRACEJ_PLOCHY_Y = 6;

    public Othello(){

        JFrame okno = new JFrame("Hra Othello");
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setSize(720,760);
        okno.setResizable(false);
        okno.setLayout(new BorderLayout());

        OthelloNastavenia nastavenia = new OthelloNastavenia(okno);
        Tuk tuk = new Tuk();

        Color whiteNigga = new Color(100,255,100 );

        JPanel hernyPanel = new JPanel();

        MyskaNastavenia myskaNastavenia = new MyskaNastavenia(hernyPanel);
        hernyPanel.setLayout(new GridLayout(VELKOST_HRACEJ_PLOCHY_X,VELKOST_HRACEJ_PLOCHY_Y,ODSKOK_HRACEJ_PLOCHY_X,ODSKOK_HRACEJ_PLOCHY_Y));
        hernyPanel.setBackground(Color.magenta);



        for (int i = 0; i < VELKOST_HRACEJ_PLOCHY_X*VELKOST_HRACEJ_PLOCHY_Y; i++) {

            hernyPanel.add(new Kamen());


            /*JButton tlacitko = new JButton();
            tlacitko.addActionListener(tuk);
            tlacitko.addKeyListener(nastavenia);
            hernyPanel.add(tlacitko);*/
        }


        hernyPanel.addMouseListener(myskaNastavenia);


        JPanel menu = new JPanel();
        menu.setBackground(Color.RED);

        JButton restart = new JButton("Reštart");
        restart.addActionListener(nastavenia);
        restart.addKeyListener(nastavenia);

        String[] velkosti ={"6x6","8x8","10x10","12x12"};
        JComboBox box = new JComboBox(velkosti);
        box.setBackground(Color.cyan);
        box.setSelectedIndex(1);

        BoxLogika boxLogika = new BoxLogika(okno);
        box.addActionListener(boxLogika);

        HernaPlocha body = new HernaPlocha();

        JLabel cierneBody = new JLabel("      čierne : " + body.getA() + "      ");
        JLabel bieleBody = new JLabel("       biele : " + body.getA() + "       ");

        cierneBody.setFont(new Font("Serif",Font.BOLD, 30));
        bieleBody.setFont(new Font("Serif",Font.BOLD, 30));

        cierneBody.setForeground(Color.black);
        bieleBody.setForeground(Color.white);

        okno.add(hernyPanel);

        menu.setLayout(new BorderLayout());
        menu.add(restart, BorderLayout.CENTER);
        menu.add(box , BorderLayout.PAGE_END);
        menu.add(cierneBody, BorderLayout.LINE_START);
        menu.add(bieleBody , BorderLayout.LINE_END);
        okno.add(menu , BorderLayout.PAGE_END);

        okno.setVisible(true);
    }
}
