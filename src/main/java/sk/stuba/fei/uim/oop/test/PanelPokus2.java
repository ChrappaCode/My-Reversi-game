package sk.stuba.fei.uim.oop.test;

import sk.stuba.fei.uim.oop.objekty.BielyKamen;
import sk.stuba.fei.uim.oop.objekty.CiernyKamen;
import sk.stuba.fei.uim.oop.objekty.KamenFarba;
import sk.stuba.fei.uim.oop.objekty.PrazdnyKamen;

import javax.swing.*;
import java.awt.*;

public class PanelPokus2 extends JPanel {

    private JLabel cierneBody;
    private JLabel bieleBody;
    private JLabel kohoKolo;

    private JPanel[][] mriezkaPole;

    private JPanel hernyPanel;
    private JPanel bunka;

    private KamenFarba[][] farebnaPlocha;

    private KamenFarba farebnyTah;

    //private boolean validMovesAvailable;

    private int pocetCiernych = 2;
    private int pocetBielich = 2;
    //private int counter = 0;

    private JFrame okno;

    public PanelPokus2(int velkost , JFrame okno, int indexVelkosti){

        super(new BorderLayout());
        this.farebnaPlocha = new KamenFarba[velkost][velkost];
        this.okno = okno;
        setOpaque(true);

        BoxLogika2 boxLogika = new BoxLogika2(this.okno);

        urobHernyPanel(velkost,indexVelkosti);

        JPanel menu = new JPanel();
        menu.setBackground(Color.RED);

        JButton restart = new JButton("Reštart");
        restart.setBackground(new Color(211, 153, 238));

        OthelloZakladneNastavenia2 othelloZakladneNastavenia2 = new OthelloZakladneNastavenia2(okno,velkost,indexVelkosti);
        restart.addActionListener(othelloZakladneNastavenia2);

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
        addKeyListener(othelloZakladneNastavenia2);

        hraZacala(velkost,indexVelkosti);

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

                LogikaMys2 logikaPokus2 = new LogikaMys2(okno,bunka,mriezkaPole,velkost,indexVelkosti);
                bunka.addMouseListener(logikaPokus2);
                bunka.addMouseMotionListener(logikaPokus2);

                hernyPanel.add(bunka);
            }
        }

        add(hernyPanel, BorderLayout.CENTER);
    }

    public void hraZacala(int velkost, int indexVelkosti) {

        for(int x = 0; x < velkost; x++) {
            for(int y = 0; y < velkost; y++) {
                farebnaPlocha[x][y] = KamenFarba.NIC;
            }
        }
        farebnyTah = KamenFarba.CIERNA;
        polozKamen(velkost/2,velkost/2 - 1, KamenFarba.BIELA);
        GUIrestart(velkost);
        polozKamen(velkost/2,velkost/2, KamenFarba.CIERNA);
        GUIrestart(velkost);
        polozKamen(velkost/2 - 1,velkost/2, KamenFarba.BIELA);
        GUIrestart(velkost);
        polozKamen(velkost/2 -1, velkost/2 -1, KamenFarba.CIERNA);
        GUIrestart(velkost);


        if(preklop(2, 4, farebnyTah, false , velkost)) {
            preklop(2, 4,  farebnyTah, true , velkost);
            polozKamen(2, 4,  farebnyTah);
        }
        farebnyTah = KamenFarba.BIELA;
        GUIrestart(velkost);



        LogikaMys2 logikaPokus2 = new LogikaMys2(okno,hernyPanel,mriezkaPole,velkost,indexVelkosti);
        hernyPanel.addMouseListener(logikaPokus2);
        hernyPanel.addMouseMotionListener(logikaPokus2);


    }

    public void polozKamen(int x, int y, KamenFarba farba) { // placing piece in the logical board
        if(farebnaPlocha[x][y] == KamenFarba.NIC) {
            farebnaPlocha[x][y] = farba;
        }
    }

    public void GUIrestart(int velkost) {
        pocetCiernych = 0;
        pocetBielich = 0;
        for(int x = 0; x < velkost; x ++) {
            for(int y = 0; y < velkost; y++) {
                mriezkaPole[x][y].removeAll();
            }
        }
        for(int x = 0; x < velkost; x ++) {
            for(int y = 0; y < velkost; y++) {
                if(preklop(x, y, farebnyTah, false, velkost))
                    pridajKamenDoPola(x, y, KamenFarba.PRAZDNA.toString());

                if(farebnaPlocha[x][y] == KamenFarba.CIERNA) {
                    pridajKamenDoPola(x, y, KamenFarba.CIERNA.toString());
                    pocetCiernych++;
                    cierneBody.setText(" Čierne : " + pocetCiernych + " ");
                }
                if(farebnaPlocha[x][y] == KamenFarba.BIELA) {
                    pridajKamenDoPola(x, y, KamenFarba.BIELA.toString());
                    pocetBielich++;
                    bieleBody.setText("  Biele : " + pocetBielich + "  ");
                }
                //checkWin(darkScore, lightScore);
            }
        }
    }

    public void pridajKamenDoPola(int x, int y, String farba) {

        mriezkaPole[x][y].removeAll();

        if(farba.equals("BIELA")){
            mriezkaPole[x][y].add(new BielyKamen());
        }
        if(farba.equals("CIERNA")){
            mriezkaPole[x][y].add(new CiernyKamen());
        }
        if(farba.equals("PRAZDNA")){
            mriezkaPole[x][y].add(new PrazdnyKamen());
        }

        mriezkaPole[x][y].updateUI();
        hernyPanel.repaint();
    }


    public boolean preklop(int x, int y, KamenFarba kamenFarba, boolean pomocny , int velkost) {
        boolean daSaZahrat = false;
        for(int dX = -1; dX < 2; dX++) {
            for(int dY = -1; dY < 2; dY ++) {
                if(dX == 0 && dY == 0) { continue; }
                int liniaX = x + dX;
                int liniaY = y + dY;
                if(liniaX >= 0 && liniaY >= 0 && liniaX < velkost && liniaY < velkost) {
                    if(farebnaPlocha[liniaX][liniaY] == (kamenFarba == KamenFarba.CIERNA ? KamenFarba.BIELA : KamenFarba.CIERNA)) {
                        for(int vzdialenost = 0; vzdialenost < velkost; vzdialenost++) {
                            int druhaLiniaX = x+vzdialenost*dX;
                            int druhaLiniaY = y+vzdialenost*dY;
                            if(druhaLiniaX < 0 || druhaLiniaY < 0  || druhaLiniaX > velkost-1 || druhaLiniaY > velkost-1){
                                continue;
                            }
                            if(farebnaPlocha[druhaLiniaX][druhaLiniaY] == kamenFarba) {
                                if(pomocny) {
                                    for(int distance2 = 1; distance2 < vzdialenost; distance2 ++) {
                                        int flipRow = x+distance2*dX;
                                        int flipCol = y+distance2*dY;
                                        farebnaPlocha[flipRow][flipCol] = kamenFarba;
                                    }
                                }
                                daSaZahrat = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return daSaZahrat; //false
    }


}
