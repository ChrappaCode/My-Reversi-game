package sk.stuba.fei.uim.oop.plocha;

import lombok.Getter;
import sk.stuba.fei.uim.oop.grafickeprostredie.Othello;
import sk.stuba.fei.uim.oop.nastavenia.MyskaNastavenia;
import sk.stuba.fei.uim.oop.objekty.Kamen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class HernaPlocha extends JFrame{

    private static final int VELKOST_HRACEJ_PLOCHY_X = 6;
    private static final int VELKOST_HRACEJ_PLOCHY_Y = 6;

    @Getter
    private JPanel hernyPanel;

    @Getter
    JPanel[][] mriezkaPole = new JPanel[VELKOST_HRACEJ_PLOCHY_Y][VELKOST_HRACEJ_PLOCHY_X];

    public JFrame noveOkno(int x, int y){

        JFrame okno = new JFrame("Hra Othello");
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setSize(600,630);
        okno.setResizable(false);
        okno.setLayout(new BorderLayout());


        try {
            okno.setIconImage(ImageIO.read(Othello.class.getResourceAsStream("/Jayzniggawhatniggawho.jpg")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        hernyPanel = new JPanel();
        hernyPanel.setLayout(new GridLayout(x,y));
        hernyPanel.setBackground(Color.DARK_GRAY);
        hernyPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        hernyPanel.setOpaque(true);

        for (int i = 0; i < VELKOST_HRACEJ_PLOCHY_X; i++) {
            for (int j = 0; j < VELKOST_HRACEJ_PLOCHY_Y; j++) {

            JPanel mriezka = new JPanel( new BorderLayout() );

            if(j == 1 && i == 0)
                mriezka.add(new Kamen());

            MyskaNastavenia mriezkaNastavenia = new MyskaNastavenia(mriezka, okno);
            mriezka.addMouseListener(mriezkaNastavenia);
            //mriezka.addMouseMotionListener(mriezkaNastavenia);

            mriezka.setPreferredSize(new Dimension(70,70));
            mriezka.setBorder(BorderFactory.createLineBorder(Color.black,4));
            mriezka.setBackground(Color.green);
            mriezkaPole[i][j] = mriezka;
            hernyPanel.add(mriezka);

            }
        }

        /*MyskaNastavenia myskaNastavenia = new MyskaNastavenia(hernyPanel, okno);
        hernyPanel.addMouseListener(myskaNastavenia);*/

        okno.add(hernyPanel);

        return okno;
    }

    //TODO prerobiť tak ako ma Peťo aby tam bol bait class
    //TODO ENUM
    //TODO PREROBIT NAJPRV VYTVOR PANEL AZ POTOM OKNO
}
