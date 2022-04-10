package sk.stuba.fei.uim.oop.nastavenia;

import sk.stuba.fei.uim.oop.objekty.BielyKamen;
import sk.stuba.fei.uim.oop.objekty.CiernyKamen;
import sk.stuba.fei.uim.oop.objekty.PrazdnyKamen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class LogikaMys extends UniverzalnyAdapter {

    private JPanel hernyPanel;

    public LogikaMys(JPanel bunka){
        this.hernyPanel = bunka;
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


