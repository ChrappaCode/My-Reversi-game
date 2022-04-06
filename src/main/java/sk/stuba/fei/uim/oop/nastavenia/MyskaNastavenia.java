package sk.stuba.fei.uim.oop.nastavenia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MyskaNastavenia extends UniverzalnyAdapter {

    JPanel panel;
    private int x;
    private int y;

    public MyskaNastavenia(JPanel panel){
        this.panel = panel;
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
        panel.setBackground(Color.YELLOW);
    }

    /*@Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("Mouse movement detected! Actual mouse position is: " + e.getX()+ "," + e.getY() + ".");
    }*/
}
