package sk.stuba.fei.uim.oop.test;

import sk.stuba.fei.uim.oop.nastavenia.UniverzalnyAdapter;
import sk.stuba.fei.uim.oop.objekty.BielyKamen;
import sk.stuba.fei.uim.oop.objekty.CiernyKamen;
import sk.stuba.fei.uim.oop.objekty.PrazdnyKamen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class LogikaMys2 extends UniverzalnyAdapter {

    private JFrame okno;
    private int aktualnaVelkost;
    private int aktualnyIndex;
    private int x;
    private int y;

    private JPanel hernyPanel;
    private JPanel[][] panelCely;

    public LogikaMys2(JFrame okno, JPanel bunka, JPanel[][] panelCely , int aktualnaVelkost , int aktualnyIndex){
        this.okno = okno;
        this.aktualnaVelkost = aktualnaVelkost;
        this.aktualnyIndex = aktualnyIndex;
        this.panelCely = panelCely;
        this.hernyPanel = bunka;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        this.x = hernyPanel.getComponentAt(e.getPoint()).getX() / 60;
        this.y = hernyPanel.getComponentAt(e.getPoint()).getY() / 60;

        if(hernyPanel.getComponentAt(e.getX(),e.getY()) instanceof BielyKamen){
            System.out.println("Biely Kamen");
        }
        else if(hernyPanel.getComponentAt(e.getX(),e.getY()) instanceof CiernyKamen){
            System.out.println("Cierny Kamen");
        }
        else if(hernyPanel.getComponentAt(e.getX(),e.getY()) instanceof PrazdnyKamen){
            System.out.println("Nic");
        }

        else{

            //panelPokus2.polozKamen(x, y, KamenFarba.CIERNA);
            //panelPokus2.updateGUI(aktualnaVelkost);

            //polozCiernyKamen(x,y);

            System.out.print("x : " + x);
            System.out.println(" | y : " + y);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        hernyPanel.setBackground(new Color(211, 153, 238));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(hernyPanel.getComponentAt(e.getY(),e.getY()) instanceof PrazdnyKamen){
            hernyPanel.setBackground(Color.red);
        }
    }

    /*public void polozCiernyKamen(int x, int  y){
        panelCely[y][x].add(new CiernyKamen());
        hernyPanel.repaint();
        panelCely[x][y].updateUI();
    }*/

}


