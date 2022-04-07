package sk.stuba.fei.uim.oop.nastavenia;

import lombok.Getter;
import sk.stuba.fei.uim.oop.objekty.BielyKamen;
import sk.stuba.fei.uim.oop.objekty.CiernyKamen;
import sk.stuba.fei.uim.oop.objekty.PrazdnyKamen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class HlavnaLogika extends UniverzalnyAdapter {

    private JPanel mriezka;
    private JPanel panel;
    private JPanel[][] panelCely;
    private JFrame okno;


    @Getter
    private int x;
    @Getter
    private int y;

    public HlavnaLogika(JPanel panel, JPanel mriezka, JPanel[][] panelCely , JFrame okno){
        this.panel = panel;
        this.mriezka = mriezka;
        this.panelCely = panelCely;
        this.okno = okno;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        this.x = mriezka.getComponentAt(e.getPoint()).getX() / 60;
        this.y = mriezka.getComponentAt(e.getPoint()).getY() / 60;

        if(mriezka.getComponentAt(e.getX(),e.getY()) instanceof BielyKamen){
            System.out.println("Biely Kamen");
        }
        else if(mriezka.getComponentAt(e.getX(),e.getY()) instanceof CiernyKamen){
            System.out.println("Cierny Kamen");
        }
        else if(mriezka.getComponentAt(e.getX(),e.getY()) instanceof PrazdnyKamen){
            System.out.println("Prazdny Kamen");
        }

        else{

            polozKamen(x,y);

            System.out.print("x : " + x);
            System.out.println(" | y : " + y);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mriezka.setBackground(Color.PINK);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(mriezka.getComponentAt(e.getY(),e.getY()) instanceof PrazdnyKamen){
            mriezka.setBackground(Color.red);
        }
    }

    public void polozKamen(int x, int  y){
            panelCely[y][x].add(new CiernyKamen());
            okno.repaint();
            panelCely[x][y].updateUI();
    }

}
