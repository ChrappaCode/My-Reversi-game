package sk.stuba.fei.uim.oop.nastavenia;

import lombok.Getter;
import sk.stuba.fei.uim.oop.objekty.BielyKamen;
import sk.stuba.fei.uim.oop.objekty.CiernyKamen;
import sk.stuba.fei.uim.oop.objekty.PrazdnyKamen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MyskaNastavenia extends UniverzalnyAdapter {

    JPanel panel;
    JPanel[][] panelCely;

    @Getter
    private int x;
    @Getter
    private int y;

    public MyskaNastavenia(JPanel panel,JPanel[][] panelCely){
        this.panel = panel;
        this.panelCely = panelCely;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        this.x = panel.getComponentAt(e.getPoint()).getX();
        this.y = panel.getComponentAt(e.getPoint()).getY();

        if(x == 4 && y == 4){
            if(panel.getComponentAt(x,y) instanceof BielyKamen)
                System.out.println("Biely Kamen");
            if(panel.getComponentAt(x,y) instanceof CiernyKamen)
                System.out.println("Cierny Kamen");
            if(panel.getComponentAt(x,y) instanceof PrazdnyKamen)
                System.out.println("Prazdny Kamen");
        }
        else{
        System.out.print("x : " + x);
        System.out.println(" | y : " + y);
        }
    }

    /*@Override
    public void mouseEntered(MouseEvent e) {
        if(panel.getComponentAt(e.getLocationOnScreen()) instanceof PrazdnyKamen){
            panel.setBackground(Color.red);
        }
    }*/

    @Override
    public void mouseExited(MouseEvent e) {
        panel.setBackground(Color.PINK);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(panel.getComponentAt(e.getY(),e.getY()) instanceof PrazdnyKamen){
            panel.setBackground(Color.red);
        }
    }
}
