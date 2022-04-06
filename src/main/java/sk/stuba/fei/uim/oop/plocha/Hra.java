package sk.stuba.fei.uim.oop.plocha;

import lombok.Getter;
import sk.stuba.fei.uim.oop.grafickeprostredie.Othello;
import sk.stuba.fei.uim.oop.nastavenia.BoxLogika;
import sk.stuba.fei.uim.oop.nastavenia.MyskaNastavenia;
import sk.stuba.fei.uim.oop.nastavenia.OthelloNastavenia;
import sk.stuba.fei.uim.oop.objekty.Kamen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Hra{

    @Getter
    private JPanel hernyPanel;

    private JFrame okno;

    private int pocetCiernych = 0;
    private int pocetBielich = 0;

    public Hra(int velkost){

        okno = new JFrame("Hra Othello");
        okno.setLayout(new BorderLayout());
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setResizable(false);

        try {
            okno.setIconImage(ImageIO.read(Othello.class.getResourceAsStream("/Jayzniggawhatniggawho.jpg")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        hernyPanel = new JPanel();
        hernyPanel.setLayout(new GridLayout(velkost,velkost));
        hernyPanel.setBackground(Color.DARK_GRAY);
        //hernyPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        hernyPanel.setOpaque(true);

        JPanel[][] mriezkaPole = new JPanel[velkost][velkost];

        for (int i = 0; i < velkost; i++) {
            for (int j = 0; j < velkost; j++) {
                JPanel mriezka = new JPanel( new BorderLayout() );
                MyskaNastavenia mriezkaNastavenia = new MyskaNastavenia(mriezka);
                mriezka.addMouseListener(mriezkaNastavenia);
                //mriezka.addMouseMotionListener(mriezkaNastavenia);

                mriezka.setPreferredSize(new Dimension(60,60));
                mriezka.setBorder(BorderFactory.createLineBorder(Color.black,4));
                mriezka.setBackground(Color.YELLOW);
                mriezkaPole[i][j] = mriezka;
                hernyPanel.add(mriezka);
            }
        }

        okno.add(hernyPanel);
        OthelloNastavenia nastavenia = new OthelloNastavenia(okno);

        mriezkaPole[2][4].add(new Kamen());

        JPanel menu = new JPanel();
        menu.setBackground(Color.RED);

        JButton restart = new JButton("Reštart");
        restart.addActionListener(nastavenia);
        restart.addKeyListener(nastavenia);

        String[] velkosti ={"6x6","8x8","10x10","12x12"};

        JComboBox box = new JComboBox(velkosti);
        box.setBackground(Color.cyan);

        BoxLogika boxLogika = new BoxLogika(okno);
        box.addActionListener(boxLogika);

        JLabel cierneBody = new JLabel(" čierne : " + pocetCiernych + " ");
        JLabel bieleBody = new JLabel("  biele : " + pocetBielich + "  ");

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

        novaHra();

        okno.pack();
        okno.setVisible(true);


    }

    public void novaHra(){

        pocetBielich++;
        okno.repaint();

    }


    //TODO prerobiť tak ako ma Peťo aby tam bol bait class
    //TODO PREROBIT NAJPRV VYTVOR PANEL AZ POTOM OKNO
}
