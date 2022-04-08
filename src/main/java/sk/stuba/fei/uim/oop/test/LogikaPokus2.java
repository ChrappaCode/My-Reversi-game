package sk.stuba.fei.uim.oop.test;

import sk.stuba.fei.uim.oop.nastavenia.UniverzalnyAdapter;
import sk.stuba.fei.uim.oop.objekty.BielyKamen;
import sk.stuba.fei.uim.oop.objekty.CiernyKamen;
import sk.stuba.fei.uim.oop.objekty.PrazdnyKamen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class LogikaPokus2 extends UniverzalnyAdapter {

    private JFrame okno;
    private int aktualnaVelkost;
    private int aktualnyIndex;
    private int x;
    private int y;

    private JPanel bunka;
    //private JPanel panel;
    private JPanel[][] panelCely;

    public LogikaPokus2(JFrame okno, JPanel bunka, JPanel[][] panelCely , int aktualnaVelkost , int aktualnyIndex){
        this.okno = okno;
        this.aktualnaVelkost = aktualnaVelkost;
        this.aktualnyIndex = aktualnyIndex;
        this.panelCely = panelCely;
        this.bunka = bunka;
    }

    public void restart() {
        System.out.println("reset");
        okno.dispose();
        new Pokus2(aktualnaVelkost,aktualnyIndex);
    }

    public void zavriHru() {
        System.out.println("Zavri sa");
        okno.dispose();
        System.exit(0);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                this.restart();
                break;
            case KeyEvent.VK_ESCAPE:
                this.zavriHru();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.restart();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        this.x = bunka.getComponentAt(e.getPoint()).getX() / 60;
        this.y = bunka.getComponentAt(e.getPoint()).getY() / 60;

        if(bunka.getComponentAt(e.getX(),e.getY()) instanceof BielyKamen){
            System.out.println("Biely Kamen");
        }
        else if(bunka.getComponentAt(e.getX(),e.getY()) instanceof CiernyKamen){
            System.out.println("Cierny Kamen");
        }
        else if(bunka.getComponentAt(e.getX(),e.getY()) instanceof PrazdnyKamen){
            System.out.println("Nic");
        }

        else{

            polozCiernyKamen(x,y);

            System.out.print("x : " + x);
            System.out.println(" | y : " + y);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        bunka.setBackground(new Color(211, 153, 238));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(bunka.getComponentAt(e.getY(),e.getY()) instanceof PrazdnyKamen){
            bunka.setBackground(Color.red);
        }
    }

    public void polozCiernyKamen(int x, int  y){
        panelCely[y][x].add(new CiernyKamen());
        bunka.repaint();
        panelCely[x][y].updateUI();
    }


}


