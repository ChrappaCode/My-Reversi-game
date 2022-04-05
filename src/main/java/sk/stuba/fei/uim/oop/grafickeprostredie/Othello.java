package sk.stuba.fei.uim.oop.grafickeprostredie;

import lombok.Getter;
import sk.stuba.fei.uim.oop.nastavenia.*;
import sk.stuba.fei.uim.oop.objekty.Kamen;
import sk.stuba.fei.uim.oop.plocha.HernaPlocha;

import javax.swing.*;
import java.awt.*;

public class Othello {

    private static final int VELKOST_HRACEJ_PLOCHY_X = 6;
    private static final int VELKOST_HRACEJ_PLOCHY_Y = 6;
    private JFrame okno;
    //private JPanel hernyPanel;

    @Getter
    JPanel[] mriezkaPole = new JPanel[VELKOST_HRACEJ_PLOCHY_Y*VELKOST_HRACEJ_PLOCHY_X];


    public Othello(){

        HernaPlocha hernaPlocha = new HernaPlocha();
        okno = hernaPlocha.noveOkno(6,6);

        OthelloNastavenia nastavenia = new OthelloNastavenia(okno);

        hernaPlocha.getMriezkaPole()[2].add(new Kamen());


        JPanel menu = new JPanel();
        menu.setBackground(Color.RED);

        JButton restart = new JButton("Reštart");
        restart.addActionListener(nastavenia);
        restart.addKeyListener(nastavenia);

        String[] velkosti ={"6x6","8x8","10x10","12x12"};
        JComboBox box = new JComboBox(velkosti);
        box.setBackground(Color.cyan);
        box.setSelectedIndex(0);

        BoxLogika boxLogika = new BoxLogika(okno);
        box.addActionListener(boxLogika);


        JLabel cierneBody = new JLabel("      čierne : " + 0 + "      ");
        JLabel bieleBody = new JLabel("       biele : " + 0 + "       ");

        cierneBody.setFont(new Font("Serif",Font.BOLD, 30));
        bieleBody.setFont(new Font("Serif",Font.BOLD, 30));

        cierneBody.setForeground(Color.black);
        bieleBody.setForeground(Color.white);

        menu.setLayout(new BorderLayout());
        menu.add(restart, BorderLayout.CENTER);
        menu.add(box , BorderLayout.PAGE_END);
        menu.add(cierneBody, BorderLayout.LINE_START);
        menu.add(bieleBody , BorderLayout.LINE_END);
        okno.add(menu , BorderLayout.PAGE_END);

        okno.setVisible(true);
    }

}
