package sk.stuba.fei.uim.oop.hra;

import sk.stuba.fei.uim.oop.nastavenia.*;
import sk.stuba.fei.uim.oop.objekty.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Hra extends JPanel implements MouseListener, MouseMotionListener{

    private JLabel cierneBody;
    private JLabel bieleBody;
    private JLabel kohoKolo;

    private JPanel[][] mriezkaPole;

    private JPanel hernyPanel;
    private JPanel bunka;

    private KamenFarba[][] farebnaPlocha;
    private KamenFarba[][] UIFarebnaPlocha;

    private KamenFarba farebnyTah;

    private int pocetCiernych;
    private int pocetBielich;
    private int velkost;

    private JFrame okno;

    private Random nahodne;
    private int koniecHry;
    private int dosliKroky;
    private int indexVelkosti;

    public Hra(int velkost , JFrame okno, int indexVelkosti){

        super(new BorderLayout());
        this.indexVelkosti = indexVelkosti;
        this.farebnaPlocha = new KamenFarba[velkost][velkost];
        this.UIFarebnaPlocha = new KamenFarba[velkost][velkost];
        this.velkost = velkost;
        this.okno = okno;
        this.koniecHry = 0;
        this.dosliKroky = 0;
        this.pocetBielich = 2;
        this.pocetCiernych = 2;
        this.nahodne = new Random();
        setOpaque(true);

        urobHernyPanel(velkost);
        urobMenu();

        hraZacala(velkost);
    }
    private void urobMenu(){

        JPanel menu = new JPanel();
        menu.setBackground(Color.RED);
        JButton restart = new JButton("Ršt");
        restart.setBackground(new Color(211, 153, 238));

        OthelloZakladneNastavenia othelloZakladneNastavenia2 = new OthelloZakladneNastavenia(okno,velkost,indexVelkosti);
        restart.addActionListener(othelloZakladneNastavenia2);

        BoxLogika boxLogika = new BoxLogika(this.okno);
        String[] velkosti ={"6x6","8x8","10x10","12x12"};
        JComboBox box = new JComboBox(velkosti);
        box.setSelectedIndex(indexVelkosti);
        box.setBackground(Color.cyan);
        box.addActionListener(boxLogika);
        box.setFocusable(false);
        cierneBody = new JLabel("čierne : " + pocetCiernych);
        bieleBody = new JLabel("biele : " + pocetBielich);
        kohoKolo = new JLabel("Kolo hráča : Čierny", SwingConstants.CENTER);

        kohoKolo.setBorder(BorderFactory.createLineBorder(Color.yellow));
        cierneBody.setFont(new Font("Serif",Font.BOLD, 30));
        bieleBody.setFont(new Font("Serif",Font.BOLD, 30));
        kohoKolo.setFont(new Font("Serif",Font.BOLD, 30));

        cierneBody.setForeground(Color.black);
        bieleBody.setForeground(Color.white);
        kohoKolo.setForeground(Color.yellow);

        JPanel hornyPanel = new JPanel();
        hornyPanel.setLayout(new GridLayout(2,1));
        JButton nedaSaNic = new JButton("Nejde zahrať, daj kolo súperovi");
        nedaSaNic.setBackground(Color.PINK);

        nedaSaNic.addActionListener(e -> {
            predajKolo(velkost,farebnyTah);
            UIkolo();
        });
        nedaSaNic.setFocusable(false);

        nedaSaNic.setBorder(BorderFactory.createLineBorder(Color.yellow,3));

        JLabel velkostPlochy = new JLabel("Veľkosť hracej plochy : " + box.getSelectedItem() , SwingConstants.CENTER);
        velkostPlochy.setBorder(BorderFactory.createLineBorder(Color.yellow,3));
        velkostPlochy.setBackground(new Color(211, 153, 238));
        velkostPlochy.setFont(new Font("Serif",Font.BOLD, 20));

        menu.setLayout(new BorderLayout());
        menu.add(kohoKolo,BorderLayout.PAGE_START);
        menu.add(restart, BorderLayout.CENTER);
        menu.add(box , BorderLayout.PAGE_END);
        menu.add(cierneBody, BorderLayout.LINE_START);
        menu.add(bieleBody , BorderLayout.LINE_END);

        hornyPanel.add(velkostPlochy);
        hornyPanel.setBackground(new Color(211, 153, 238));
        hornyPanel.add(nedaSaNic);
        add(hornyPanel,BorderLayout.PAGE_START);

        JPanel velkostPola = new JPanel();
        velkostPola.setBackground(Color.RED);
        add(menu , BorderLayout.PAGE_END);
        setFocusable(true);
        addKeyListener(othelloZakladneNastavenia2);

    }

    private void urobHernyPanel(int velkost){

        mriezkaPole = new JPanel[velkost][velkost];

        hernyPanel = new JPanel(new GridLayout(velkost,velkost));
        for (int i = 0; i < velkost; i++) {
            for (int j = 0; j < velkost; j++) {
                bunka = new JPanel( new BorderLayout() );
                bunka.setPreferredSize(new Dimension(60,60));
                bunka.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
                mriezkaPole[i][j] = bunka;
                hernyPanel.add(bunka);
            }
        }
        add(hernyPanel, BorderLayout.CENTER);
    }

    private void hraZacala(int velkost) {

        for(int x = 0; x < velkost; x++) {
            for(int y = 0; y < velkost; y++) {
                farebnaPlocha[x][y] = KamenFarba.NIC;
            }
        }
        farebnyTah = KamenFarba.CIERNA;
        polozKamen(velkost/2,velkost/2 - 1, KamenFarba.BIELA);
        polozKamen(velkost/2,velkost/2, KamenFarba.CIERNA);
        polozKamen(velkost/2 - 1,velkost/2, KamenFarba.BIELA);
        polozKamen(velkost/2 -1, velkost/2 -1, KamenFarba.CIERNA);
        GUIrestart(velkost);

        hernyPanel.addMouseListener(this);
        hernyPanel.addMouseMotionListener(this);
    }

    private void polozKamen(int x, int y, KamenFarba farba) {
        if(farebnaPlocha[x][y] == KamenFarba.NIC) {
            farebnaPlocha[x][y] = farba;
        }
    }

    private void GUIrestart(int velkost) {

        for (int x = 0; x < velkost; x++) {
            for (int y = 0; y < velkost; y++) {
                UIFarebnaPlocha[x][y] = KamenFarba.NIC;
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
                if(algoritmusNaPreskakovanie(x, y, farebnyTah, false, velkost)){
                    pridajKamenDoPola(x, y, KamenFarba.PRAZDNA.toString());
                }
                if(farebnaPlocha[x][y] == KamenFarba.CIERNA) {
                    pridajKamenDoPola(x, y, KamenFarba.CIERNA.toString());
                    pocetCiernych++;
                    cierneBody.setText(" Čierne : " + pocetCiernych + " ");
                }
                else if(farebnaPlocha[x][y] == KamenFarba.BIELA) {
                    pridajKamenDoPola(x, y, KamenFarba.BIELA.toString());
                    pocetBielich++;
                    bieleBody.setText("  Biele : " + pocetBielich + "  ");
                }
                ktoVyhral(pocetCiernych, pocetBielich);
            }
        }
        restartPozadia();
    }

    private void pridajKamenDoPola(int x, int y, String farba) {

        mriezkaPole[x][y].removeAll();

        if(farba.equals("BIELA")){
            mriezkaPole[x][y].add(new BielyKamen());
        }
        if(farba.equals("CIERNA")){
            mriezkaPole[x][y].add(new CiernyKamen());
        }
        if(farba.equals("PRAZDNA")){
            mriezkaPole[x][y].add(new PrazdnyKamen());
            UIFarebnaPlocha[x][y] = KamenFarba.PRAZDNA;
        }

        mriezkaPole[x][y].updateUI();
        hernyPanel.repaint();
    }

    private boolean algoritmusNaPreskakovanie(int x, int y, KamenFarba kamenFarba, boolean pomocny , int velkost) {

        boolean daSaZahrat = false;

        for(int x1 = -1; x1 < 2; x1++) {
            for(int y1 = -1; y1 < 2; y1 ++) {
                if(x1 == 0 && y1 == 0) {
                    continue;
                }

                int liniaX = x + x1;
                int liniaY = y + y1;

                if(liniaX >= 0 && liniaY >= 0 && liniaX < velkost && liniaY < velkost) {
                    if(farebnaPlocha[liniaX][liniaY] == (kamenFarba == KamenFarba.CIERNA ? KamenFarba.BIELA : KamenFarba.CIERNA)) {
                        for(int vzdialenost = 0; vzdialenost < velkost; vzdialenost++) {

                            int druhaLiniaX = x + vzdialenost * x1;
                            int druhaLiniaY = y + vzdialenost * y1;

                            if(druhaLiniaX < 0 || druhaLiniaY < 0  || druhaLiniaY > velkost-1 || druhaLiniaX > velkost-1){
                                continue;
                            }

                            if(farebnaPlocha[druhaLiniaX][druhaLiniaY] == kamenFarba) {
                                if(pomocny) {
                                    for(int vzdialenost2 = 1; vzdialenost2 < vzdialenost; vzdialenost2++) {

                                        int preklopX = x + vzdialenost2 * x1;
                                        int preklopY = y + vzdialenost2 * y1;

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
        return daSaZahrat;
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

        if((cierne + biele == velkost*velkost && cierne > biele || (dosliKroky == 1 && cierne > biele)) && koniecHry == 0){
            JOptionPane.showMessageDialog(okno, "Čierny vyhral");
            koniecHry = 1;
        }
        if((cierne + biele == velkost*velkost && biele > cierne || (dosliKroky == 1 && cierne < biele)) && koniecHry == 0){
            JOptionPane.showMessageDialog(okno, "Biely vyhral");
            koniecHry = 1;
        }
        if((cierne + biele == velkost*velkost && biele == cierne || (dosliKroky == 1 && cierne == biele)) && koniecHry == 0){
            JOptionPane.showMessageDialog(okno, "Pekne remízka");
            koniecHry = 1;
        }
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
        else if(UIFarebnaPlocha[y][x] == KamenFarba.PRAZDNA){
            if(algoritmusNaPreskakovanie(y, x, farebnyTah, false,velkost)) {
                algoritmusNaPreskakovanie(y, x, farebnyTah, true,velkost);
                polozKamen(y, x, farebnyTah);
                GUIrestart(velkost);
                predajKolo(velkost,farebnyTah);
                UIkolo();
            }
        }
    }

    private void UIkolo(){

        int pocetMoznychTahov = 0;
        int[] poleX = new int[velkost*velkost];
        int[] poleY = new int[velkost*velkost];
        KamenFarba[][] pomocnePoleFariebAI = new KamenFarba[velkost][velkost];
        for (int x = 0; x < velkost; x++) {
            for (int y = 0; y < velkost; y++) {
                pomocnePoleFariebAI[x][y] = KamenFarba.NIC;
            }
        }
        for (int x = 0; x < velkost; x++) {
            for (int y = 0; y < velkost; y++) {
                if(farebnaPlocha[y][x] == KamenFarba.CIERNA){
                    continue;
                }
                if(farebnaPlocha[y][x] == KamenFarba.BIELA){
                    continue;
                }
                if(UIFarebnaPlocha[y][x] == KamenFarba.PRAZDNA){
                    pomocnePoleFariebAI[y][x] = KamenFarba.PRAZDNA;
                }
            }
        }
        for (int x = 0; x < velkost; x++) {
            for (int y = 0; y < velkost; y++) {
                if(pomocnePoleFariebAI[y][x] == KamenFarba.PRAZDNA){
                    poleX[pocetMoznychTahov] = y;
                    poleY[pocetMoznychTahov] = x;
                    pocetMoznychTahov++;
                }
            }
        }
        if(pocetMoznychTahov != 0){
            int nahodnyUItah = nahodne.nextInt(pocetMoznychTahov);

            if(algoritmusNaPreskakovanie(poleX[nahodnyUItah], poleY[nahodnyUItah], farebnyTah, false,velkost)) {
                algoritmusNaPreskakovanie(poleX[nahodnyUItah], poleY[nahodnyUItah], farebnyTah, true, velkost);
                polozKamen(poleX[nahodnyUItah], poleY[nahodnyUItah], farebnyTah);
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
        else if(UIFarebnaPlocha[e.getY()/60][e.getX()/60] == KamenFarba.PRAZDNA){
            mriezkaPole[e.getY()/60][e.getX()/60].setBackground(Color.red);
        }
    }

    private void restartPozadia(){
        int pozadie = 0;
        for (int x1 = 0; x1 < velkost; x1++) {
            pozadie++;
            for (int y1 = 0; y1 < velkost; y1++) {
                if(pozadie % 2 == 0)
                    mriezkaPole[x1][y1].setBackground(new Color(211, 153, 238));
                if(pozadie % 2 != 0)
                    mriezkaPole[x1][y1].setBackground(Color.PINK);
                pozadie++;
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
