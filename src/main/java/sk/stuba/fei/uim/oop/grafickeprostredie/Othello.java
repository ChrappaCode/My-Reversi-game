package sk.stuba.fei.uim.oop.grafickeprostredie;

import sk.stuba.fei.uim.oop.nastavenia.*;
import sk.stuba.fei.uim.oop.objekty.Kamen;
import sk.stuba.fei.uim.oop.plocha.HernaPlocha;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class Othello {

    private static final int VELKOST_HRACEJ_PLOCHY_X = 6;
    private static final int VELKOST_HRACEJ_PLOCHY_Y = 6;
    private JFrame okno;
    private JPanel hernyPanel;

    public Othello(){

        okno = noveOkno(VELKOST_HRACEJ_PLOCHY_X,VELKOST_HRACEJ_PLOCHY_Y);

        OthelloNastavenia nastavenia = new OthelloNastavenia(okno);

        MyskaNastavenia myskaNastavenia = new MyskaNastavenia(hernyPanel,okno);
        hernyPanel.addMouseListener(myskaNastavenia);


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


    public JFrame noveOkno(int x,int y){

        okno = new JFrame("Hra Othello");
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

        //Tuk tuk = new Tuk();

        for (int i = 0; i < x * y; i++) {

            JPanel mriezka = new JPanel( new BorderLayout() );

            if(i == 7 || i == 23)
                mriezka.add(new Kamen());

            MyskaNastavenia mriezkaNastavenia = new MyskaNastavenia(mriezka,okno);
            mriezka.addMouseListener(mriezkaNastavenia);
            mriezka.setBorder(BorderFactory.createLineBorder(Color.black,4));
            mriezka.setBackground(Color.green);
            hernyPanel.add(mriezka);


            /*JButton tlacitko = new JButton();
            tlacitko.addActionListener(tuk);
            tlacitko.addKeyListener(nastavenia);
            hernyPanel.add(tlacitko);*/
        }

        return okno;
    }

}
