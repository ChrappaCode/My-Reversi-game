package sk.stuba.fei.uim.oop.plocha;

import sk.stuba.fei.uim.oop.nastavenia.*;
import sk.stuba.fei.uim.oop.objekty.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Hra extends JPanel implements MouseListener, MouseMotionListener {

    private JLabel cierneBody;
    private JLabel bieleBody;
    private JLabel kohoKolo;

    private JPanel[][] mriezkaPole;

    private JPanel hernyPanel;
    private JPanel bunka;

    private KamenFarba[][] farebnaPlocha;
    private KamenFarba[][] AIFarebnaPlocha;

    private KamenFarba farebnyTah;

    private int pocetCiernych = 2;
    private int pocetBielich = 2;
    private int velkost;

    private JFrame okno;

    public Hra(int velkost , JFrame okno, int indexVelkosti){

        super(new BorderLayout());
        this.farebnaPlocha = new KamenFarba[velkost][velkost];
        this.AIFarebnaPlocha = new KamenFarba[velkost][velkost];
        this.velkost = velkost;
        this.okno = okno;
        setOpaque(true);

        BoxLogika boxLogika = new BoxLogika(this.okno);

        urobHernyPanel(velkost);

        JPanel menu = new JPanel();
        menu.setBackground(Color.RED);

        JButton restart = new JButton("Ršt");
        restart.setBackground(new Color(211, 153, 238));

        OthelloZakladneNastavenia othelloZakladneNastavenia2 = new OthelloZakladneNastavenia(okno,velkost,indexVelkosti);
        restart.addActionListener(othelloZakladneNastavenia2);

        String[] velkosti ={"6x6","8x8","10x10","12x12"};

        JComboBox box = new JComboBox(velkosti);
        box.setSelectedIndex(indexVelkosti);
        box.setBackground(Color.cyan);
        box.addActionListener(boxLogika);
        box.setFocusable(false);

        cierneBody = new JLabel("čierne : " + pocetCiernych);
        bieleBody = new JLabel("biele : " + pocetBielich);
        kohoKolo = new JLabel("Kolo hráča : Čierny", SwingConstants.CENTER);
        kohoKolo.setBorder(BorderFactory.createLineBorder(Color.black));

        cierneBody.setFont(new Font("Serif",Font.BOLD, 30));
        bieleBody.setFont(new Font("Serif",Font.BOLD, 30));
        kohoKolo.setFont(new Font("Serif",Font.BOLD, 30));

        cierneBody.setForeground(Color.black);
        bieleBody.setForeground(Color.white);
        kohoKolo.setForeground(Color.yellow);

        JButton nedaSaNic = new JButton("Nič nejde urobiť");
        nedaSaNic.setBackground(Color.PINK);
        nedaSaNic.addActionListener(e -> predajKolo(velkost,farebnyTah));

        menu.setLayout(new BorderLayout());
        menu.add(kohoKolo,BorderLayout.PAGE_START);
        menu.add(restart, BorderLayout.CENTER);
        menu.add(box , BorderLayout.PAGE_END);
        menu.add(cierneBody, BorderLayout.LINE_START);
        menu.add(bieleBody , BorderLayout.LINE_END);
        add(nedaSaNic,BorderLayout.PAGE_START);

        JPanel velkostPola = new JPanel();
        velkostPola.setBackground(Color.RED);

        add(menu , BorderLayout.PAGE_END);
        setFocusable(true);
        addKeyListener(othelloZakladneNastavenia2);

        hraZacala(velkost);
    }

    public void urobHernyPanel(int velkost){

        mriezkaPole = new JPanel[velkost][velkost];

        hernyPanel = new JPanel(new GridLayout(velkost,velkost));
        for (int i = 0; i < velkost; i++) {
            for (int j = 0; j < velkost; j++) {
                bunka = new JPanel( new BorderLayout() );
                bunka.setPreferredSize(new Dimension(60,60));
                bunka.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
                bunka.setBackground(new Color(211, 153, 238));
                mriezkaPole[i][j] = bunka;
                hernyPanel.add(bunka);
            }
        }

        add(hernyPanel, BorderLayout.CENTER);
    }

    public void hraZacala(int velkost) {

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

        hernyPanel.addMouseListener(this);
        hernyPanel.addMouseMotionListener(this);
    }

    public void polozKamen(int x, int y, KamenFarba farba) {
        if(farebnaPlocha[x][y] == KamenFarba.NIC) {
            farebnaPlocha[x][y] = farba;
        }
    }

    public void  GUIrestart(int velkost) {

        for (int x1 = 0; x1 < velkost; x1++) {
            for (int y1 = 0; y1 < velkost; y1++) {
                AIFarebnaPlocha[x1][y1] = KamenFarba.NIC;
            }
        }
        pocetCiernych = 0;
        pocetBielich = 0;
        for(int x = 0; x < velkost; x ++) {
            for(int y = 0; y < velkost; y++) {
                mriezkaPole[x][y].removeAll();
            }
        }
        for(int x = 0; x < velkost; x ++) {
            for(int y = 0; y < velkost; y++) {
                if(preklop(x, y, farebnyTah, false, velkost)){
                    pridajKamenDoPola(x, y, KamenFarba.PRAZDNA.toString());
                }
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
                ktoVyhral(pocetCiernych, pocetBielich);
            }
        }
        restartPozadia();
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
            AIFarebnaPlocha[x][y] = KamenFarba.PRAZDNA;
        }

        mriezkaPole[x][y].updateUI();
        hernyPanel.repaint();
    }


    public boolean preklop(int x, int y, KamenFarba kamenFarba, boolean pomocny , int velkost) {
        boolean daSaZahrat = false;
        for(int dX = -1; dX < 2; dX++) {
            for(int dY = -1; dY < 2; dY ++) {
                if(dX == 0 && dY == 0) {
                    continue;
                }
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
                                    for(int vzdialenost2 = 1; vzdialenost2 < vzdialenost; vzdialenost2 ++) {
                                        int preklopX = x+vzdialenost2*dX;
                                        int preklopY = y+vzdialenost2*dY;
                                        farebnaPlocha[preklopX][preklopY] = kamenFarba;
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

    private void predajKolo(int velkost , KamenFarba farba) {
        farebnyTah = (farebnyTah == KamenFarba.CIERNA ? KamenFarba.BIELA : KamenFarba.CIERNA);
        GUIrestart(velkost);
        if(farba == KamenFarba.BIELA){
            kohoKolo.setText("Kolo hráča : Čierny");
        }
        if(farba == KamenFarba.CIERNA){
            kohoKolo.setText("Kolo hráča : Biely");
        }
    }

    private void ktoVyhral(int cierne, int biele) {

        if((cierne + biele == velkost*velkost && cierne > biele))
            JOptionPane.showMessageDialog(okno, "Čierny vyhral");
        if((cierne + biele == velkost*velkost && biele > cierne))
            JOptionPane.showMessageDialog(okno, "Biely vyhral");
        if((cierne + biele == velkost*velkost && biele == cierne))
            JOptionPane.showMessageDialog(okno, "Pekne remízka");
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int x = e.getX() / 60;
        int y = e.getY() / 60;


        if(farebnaPlocha[y][x] == KamenFarba.CIERNA){
            System.out.println("Cierny Kamen");
        }
        else if(farebnaPlocha[y][x] == KamenFarba.BIELA){
            System.out.println("Biely Kamen");
        }
        else if(AIFarebnaPlocha[y][x] == KamenFarba.PRAZDNA){
            if(preklop(y, x, farebnyTah, false,velkost)) {
                preklop(y, x, farebnyTah, true,velkost);
                polozKamen(y, x, farebnyTah);
                GUIrestart(velkost);
                predajKolo(velkost,farebnyTah);
                AIkolo();
            }
        }
    }

    private void AIkolo(){

        int pocetMoznychTahov = 0;
        int[] poleX = new int[velkost*velkost];
        int[] poleY = new int[velkost*velkost];
        KamenFarba[][] pomocnePoleFariebAI = new KamenFarba[velkost][velkost];
        for (int x1 = 0; x1 < velkost; x1++) {
            for (int y1 = 0; y1 < velkost; y1++) {
                pomocnePoleFariebAI[x1][y1] = KamenFarba.NIC;
            }
        }
        for (int x1 = 0; x1 < velkost; x1++) {
            for (int y1 = 0; y1 < velkost; y1++) {
                if(farebnaPlocha[y1][x1] == KamenFarba.CIERNA){
                    continue;
                }
                if(farebnaPlocha[y1][x1] == KamenFarba.BIELA){
                    continue;
                }
                if(AIFarebnaPlocha[y1][x1] == KamenFarba.PRAZDNA){
                    pomocnePoleFariebAI[y1][x1] = KamenFarba.PRAZDNA;
                }
            }
        }
        int test = 0;
        for (int x1 = 0; x1 < velkost; x1++) {
            for (int y1 = 0; y1 < velkost; y1++) {
                if(pomocnePoleFariebAI[y1][x1] == KamenFarba.PRAZDNA){
                    System.out.println(pocetMoznychTahov);
                    poleX[pocetMoznychTahov] = y1;
                    poleY[pocetMoznychTahov] = x1;
                    pocetMoznychTahov++;
                    System.out.println("x: " + poleX[test] + " y: " + poleY[test] +" "+ pomocnePoleFariebAI[y1][x1]);
                    test++;
                }
            }
        }
        if(pocetMoznychTahov != 0){
            Random rand = new Random();
            int random = rand.nextInt(pocetMoznychTahov);
            System.out.println("random: " + random);

            if(preklop(poleX[random], poleY[random], farebnyTah, false,velkost)) {
                preklop(poleX[random], poleY[random], farebnyTah, true, velkost);
                polozKamen(poleX[random], poleY[random], farebnyTah);
                GUIrestart(velkost);
                predajKolo(velkost, farebnyTah);
            }
        }
        else{
            predajKolo(velkost,farebnyTah);
        }
    }
    @Override
    public void mouseMoved(MouseEvent e) {

        restartPozadia();
        if(farebnaPlocha[e.getY()/60][e.getX()/60] == KamenFarba.CIERNA){
            restartPozadia();
        }
        else if(farebnaPlocha[e.getY()/60][e.getX()/60] == KamenFarba.BIELA){
            restartPozadia();
        }
        else if(AIFarebnaPlocha[e.getY()/60][e.getX()/60] == KamenFarba.PRAZDNA){
            mriezkaPole[e.getY()/60][e.getX()/60].setBackground(Color.red);
        }
    }

    private void restartPozadia(){
        for (int x1 = 0; x1 < velkost; x1++) {
            for (int y1 = 0; y1 < velkost; y1++) {
                mriezkaPole[x1][y1].setBackground(new Color(211, 153, 238));
            }
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseDragged(MouseEvent e) {
    }

}
