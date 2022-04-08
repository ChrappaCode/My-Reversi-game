package sk.stuba.fei.uim.oop.test;

import sk.stuba.fei.uim.oop.objekty.BielyKamen;
import sk.stuba.fei.uim.oop.objekty.CiernyKamen;

import javax.swing.*;
import java.awt.*;

public class PanelPokus2 extends JPanel {

    //int [] data = new int[64];

    private JLabel cierneBody;
    private JLabel bieleBody;
    private JLabel kohoKolo;

    private JPanel[][] mriezkaPole;

    private JPanel hernyPanel;
    private JPanel bunka;

    //private KamenFarba[][] board = new KamenFarba[6][6];

    //private KamenFarba move_piece;

    //private boolean validMovesAvailable;

    private int pocetCiernych = 2;
    private int pocetBielich = 2;
    //private int counter = 0;

    private JFrame okno;

    public PanelPokus2(int velkost , JFrame okno, int indexVelkosti){

        super(new BorderLayout());
        this.okno = okno;
        setOpaque(true);

        BoxLogika2 boxLogika = new BoxLogika2(this.okno);

        urobHernyPanel(velkost,indexVelkosti);

        JPanel menu = new JPanel();
        menu.setBackground(Color.RED);

        JButton restart = new JButton("Reštart");
        restart.setBackground(new Color(211, 153, 238));

        LogikaPokus2 logikaPokus2 = new LogikaPokus2(okno,bunka,mriezkaPole,velkost,indexVelkosti);
        restart.addActionListener(logikaPokus2);

        String[] velkosti ={"6x6","8x8","10x10","12x12"};

        JComboBox box = new JComboBox(velkosti);
        box.setSelectedIndex(indexVelkosti);
        box.setBackground(Color.cyan);
        box.addActionListener(boxLogika);
        box.setFocusable(false);

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

        add(menu , BorderLayout.PAGE_END);
        setFocusable(true);
        addKeyListener(logikaPokus2);

    }

    public void urobHernyPanel(int velkost,int indexVelkosti){

        mriezkaPole = new JPanel[velkost][velkost];

        hernyPanel = new JPanel(new GridLayout(velkost,velkost));
        for (int i = 0; i < velkost; i++) {
            for (int j = 0; j < velkost; j++) {
                bunka = new JPanel( new BorderLayout() );
                bunka.setPreferredSize(new Dimension(60,60));
                bunka.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
                bunka.setBackground(new Color(211, 153, 238));
                mriezkaPole[i][j] = bunka;

                if((i == velkost/2 && j == velkost/2) || (i == velkost/2 -1 && j == velkost/2 -1)){
                    mriezkaPole[i][j].add(new CiernyKamen());
                }
                if((i == velkost/2 && j == velkost/2 - 1) || (i == velkost/2 - 1 && j == velkost/2)){
                    mriezkaPole[i][j].add(new BielyKamen());
                }

                LogikaPokus2 logikaPokus2 = new LogikaPokus2(okno,bunka,mriezkaPole,velkost,indexVelkosti);
                bunka.addMouseListener(logikaPokus2);
                bunka.addMouseMotionListener(logikaPokus2);

                hernyPanel.add(bunka);
            }
        }

        add(hernyPanel, BorderLayout.CENTER);
    }

}
