package sk.stuba.fei.uim.oop.plocha;

import lombok.Getter;
import sk.stuba.fei.uim.oop.KamenFarba;
import sk.stuba.fei.uim.oop.grafickeprostredie.ZapniHru;
import sk.stuba.fei.uim.oop.nastavenia.*;
import sk.stuba.fei.uim.oop.objekty.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Hra{

    @Getter
    private JPanel hernyPanel;

    private JLabel cierneBody;
    private JLabel bieleBody;
    private JLabel kohoKolo;

    private JFrame okno;

    private JPanel[][] mriezkaPole;

    private int pocetCiernych = 2;
    private int pocetBielich = 2;

    private int velkost;
    private boolean tvojeKolo = true;

    KamenFarba[][] plocha = new KamenFarba[velkost][velkost];

    //private KamenFarba pohyb;

    public Hra(int velkost , int boxIndex){

        this.velkost = velkost;

        okno = new JFrame("Hra Othello");
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setResizable(false);
        okno.setLayout(new BorderLayout());
        okno.setFocusable(true);

        try {
            okno.setIconImage(ImageIO.read(ZapniHru.class.getResourceAsStream("/Jayzniggawhatniggawho.jpg")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        hernyPanel = new JPanel();
        hernyPanel.setLayout(new GridLayout(velkost,velkost));
        hernyPanel.setBackground(Color.DARK_GRAY);
        hernyPanel.setOpaque(true);

        mriezkaPole = new JPanel[velkost][velkost];

        for (int i = 0; i < velkost; i++) {
            for (int j = 0; j < velkost; j++) {
                JPanel mriezka = new JPanel( new BorderLayout() );
                MyskaNastavenia mriezkaNastavenia = new MyskaNastavenia(mriezka,mriezkaPole);
                mriezka.addMouseListener(mriezkaNastavenia);
                mriezka.addMouseMotionListener(mriezkaNastavenia);
                mriezka.setPreferredSize(new Dimension(60,60));
                mriezka.setBorder(BorderFactory.createLineBorder(Color.black,4));
                mriezka.setBackground(Color.PINK);
                mriezkaPole[i][j] = mriezka;

                if((i == velkost/2 && j == velkost/2) || (i == velkost/2 -1 && j == velkost/2 -1)){
                    mriezkaPole[i][j].add(new CiernyKamen());
                }
                if((i == velkost/2 && j == velkost/2 - 1) || (i == velkost/2 - 1 && j == velkost/2)){
                    mriezkaPole[i][j].add(new BielyKamen());
                }

                hernyPanel.add(mriezka);
            }
        }
        okno.add(hernyPanel);

        OthelloNastavenia nastavenia = new OthelloNastavenia(okno);

        JPanel menu = new JPanel();
        menu.setBackground(Color.RED);

        JButton restart = new JButton("Reštart");
        restart.addActionListener(nastavenia);
        okno.addKeyListener(nastavenia);

        String[] velkosti ={"6x6","8x8","10x10","12x12"};

        JComboBox box = new JComboBox(velkosti);
        box.setBackground(Color.cyan);
        box.setSelectedIndex(boxIndex);

        BoxLogika boxLogika = new BoxLogika(okno);
        box.addActionListener(boxLogika);


        cierneBody = new JLabel(" čierne : " + pocetCiernych + " ");
        bieleBody = new JLabel("  biele : " + pocetBielich + "  ");
        kohoKolo = new JLabel("Kolo hráča : Čierny", SwingConstants.CENTER);
        kohoKolo.setBorder(BorderFactory.createLineBorder(Color.black));

        cierneBody.setFont(new Font("Serif",Font.BOLD, 30));
        bieleBody.setFont(new Font("Serif",Font.BOLD, 30));
        kohoKolo.setFont(new Font("Serif",Font.BOLD, 30));

        cierneBody.setForeground(Color.black);
        bieleBody.setForeground(Color.white);
        kohoKolo.setForeground(Color.yellow);

        menu.setLayout(new BorderLayout());
        menu.add(kohoKolo,BorderLayout.PAGE_START);
        menu.add(restart, BorderLayout.CENTER);
        menu.add(box , BorderLayout.PAGE_END);
        menu.add(cierneBody, BorderLayout.LINE_START);
        menu.add(bieleBody , BorderLayout.LINE_END);

        okno.add(menu , BorderLayout.PAGE_END);

        novaHra();

        okno.pack();
        okno.setVisible(true);


    }

    public void novaHra() {


        prveKolo();

        /*pocetBielich++;
        bieleBody.setText("  biele : " + pocetBielich + "  ");*/

    }

    public void dajKamen(int x, int y, KamenFarba color) { // placing piece in the logical board
        if(plocha[x][y] == KamenFarba.PRAZDNA)
            plocha[x][y] = color;
    }

    public void prveKolo(){
        for (int i = 0; i < velkost; i++) {
            for (int j = 0; j < velkost; j++) {

                if((i == velkost/2 -2 && j == velkost/2) || (i == velkost/2 -1 && j == velkost/2 +1)){
                    mriezkaPole[i][j].add(new PrazdnyKamen());
                }
                if((i == velkost/2  && j == velkost/2 - 2) || (i == velkost/2 + 1 && j == velkost/2 - 1)){
                    mriezkaPole[i][j].add(new PrazdnyKamen());
                }
            }
        }
    }



}
