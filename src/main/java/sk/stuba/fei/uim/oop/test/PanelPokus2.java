package sk.stuba.fei.uim.oop.test;

import javax.swing.*;
import java.awt.*;

public class PanelPokus2 extends JPanel {

    //int [] data = new int[64];

    private JLabel cierneBody;
    private JLabel bieleBody;
    private JLabel kohoKolo;

    private JPanel hernyPanel;

    //private KamenFarba[][] board = new KamenFarba[6][6];

    //private KamenFarba move_piece;

    //private boolean validMovesAvailable;

    private int pocetCiernych = 2;
    private int pocetBielich = 2;
    //private int counter = 0;

    public PanelPokus2(int velkost){

        super(new BorderLayout());
        setOpaque(true);
        
        urobHernyPanel(velkost);

        JPanel menu = new JPanel();
        menu.setBackground(Color.RED);

        JButton restart = new JButton("Reštart");

        String[] velkosti ={"6x6","8x8","10x10","12x12"};

        JComboBox box = new JComboBox(velkosti);
        box.setBackground(Color.cyan);
        BoxLogika2 boxLogika = new BoxLogika2(hernyPanel);
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

        add(menu , BorderLayout.PAGE_END);

    }

    public void urobHernyPanel(int velkost){

        hernyPanel = new JPanel(new GridLayout(velkost,velkost));
        for (int i = 0; i < velkost; i++) {
            for (int j = 0; j < velkost; j++) {
                JPanel mriezka = new JPanel( new BorderLayout() );
                mriezka.setPreferredSize(new Dimension(60,60));
                mriezka.setBorder(BorderFactory.createLineBorder(Color.black,2));
                mriezka.setBackground(Color.PINK);
                hernyPanel.add(mriezka);
            }
        }
        add(hernyPanel, BorderLayout.CENTER);
    }

}
