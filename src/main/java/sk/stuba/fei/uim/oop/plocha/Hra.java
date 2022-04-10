package sk.stuba.fei.uim.oop.plocha;

import sk.stuba.fei.uim.oop.nastavenia.BoxLogika;
import sk.stuba.fei.uim.oop.nastavenia.OthelloZakladneNastavenia;
import sk.stuba.fei.uim.oop.objekty.BielyKamen;
import sk.stuba.fei.uim.oop.objekty.CiernyKamen;
import sk.stuba.fei.uim.oop.objekty.KamenFarba;
import sk.stuba.fei.uim.oop.objekty.PrazdnyKamen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Hra extends JPanel implements MouseListener, MouseMotionListener {

    private JLabel cierneBody;
    private JLabel bieleBody;
    private JLabel kohoKolo;

    private JPanel[][] mriezkaPole;

    private JPanel hernyPanel;
    private JPanel bunka;

    private KamenFarba[][] farebnaPlocha;

    private KamenFarba farebnyTah;

    private int pocetCiernych = 2;
    private int pocetBielich = 2;
    private int velkost;


    private JFrame okno;

    public Hra(int velkost , JFrame okno, int indexVelkosti){

        super(new BorderLayout());
        this.farebnaPlocha = new KamenFarba[velkost][velkost];
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

        JButton nedaSaNic = new JButton("Nič nejde urobiť");
        nedaSaNic.setBackground(Color.PINK);
        nedaSaNic.addActionListener(e -> predajKolo(velkost));

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

                //LogikaMys logikaPokus2 = new LogikaMys(bunka);
                //bunka.addMouseListener(logikaPokus2);
                //bunka.addMouseMotionListener(this);

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
        //hernyPanel.addMouseMotionListener(this);

       /* if(preklop(2, 4, farebnyTah, false , velkost)) {
            preklop(2, 4,  farebnyTah, true , velkost);
            polozKamen(2, 4,  farebnyTah);
        }*/
        //farebnyTah = KamenFarba.BIELA;
        //GUIrestart(velkost);

        //LogikaMys logikaPokus2 = new LogikaMys(okno,hernyPanel,mriezkaPole,velkost,indexVelkosti);
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

    private void predajKolo(int velkost) {
        farebnyTah = (farebnyTah == KamenFarba.CIERNA ? KamenFarba.BIELA : KamenFarba.CIERNA);
        GUIrestart(velkost);
    }

    private void ktoVyhral(int cierne, int biele) {


        if((cierne + biele == velkost*velkost && cierne > biele))
            JOptionPane.showMessageDialog(okno, "Čierny vyhral");
        if((cierne + biele == velkost*velkost && biele > cierne))
            JOptionPane.showMessageDialog(okno, "Biely vyhral");
        if((cierne + biele == velkost*velkost && biele == cierne))
            JOptionPane.showMessageDialog(okno, "pekne remízka");
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int x = e.getX() / 60;
        int y = e.getY() / 60;

        if(farebnaPlocha[y][x] == KamenFarba.BIELA){
            System.out.println("Biely Kamen");
        }
        else if(farebnaPlocha[y][x] == KamenFarba.CIERNA){
            System.out.println("Cierny Kamen");
        }

        else if(preklop(y, x, farebnyTah, false,velkost)) {
            preklop(y, x, farebnyTah, true,velkost);
            polozKamen(y, x, farebnyTah);
            GUIrestart(velkost);
            predajKolo(velkost);
        }


    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
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
