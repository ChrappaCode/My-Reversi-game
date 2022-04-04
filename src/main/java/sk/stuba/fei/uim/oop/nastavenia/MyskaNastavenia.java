package sk.stuba.fei.uim.oop.nastavenia;

import sk.stuba.fei.uim.oop.nastavenia.UniverzalnyAdapter;
import sk.stuba.fei.uim.oop.objekty.Kamen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MyskaNastavenia extends UniverzalnyAdapter {

    JPanel panel;
    JFrame okno;
    private int x;
    private int y;

    public MyskaNastavenia(JPanel panel, JFrame okno){
        this.panel = panel;
        this.okno = okno;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        this.x = panel.getComponentAt(e.getPoint()).getX();
        this.y = panel.getComponentAt(e.getPoint()).getY();

        if(x == 4 && y == 4){
            System.out.println("Kamen");

        }
        else{
        System.out.print("x : " + x);
        System.out.println(" | y : " + y);
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        if(x != 4 || y != 4){
            panel.setBackground(Color.red);
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {
        panel.setBackground(Color.green);
    }




}
