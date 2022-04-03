package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.nastavenia.UniverzalnyAdapter;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class MyskaNastavenia extends UniverzalnyAdapter {

    JPanel panel;

    public MyskaNastavenia(JPanel panel){
        this.panel = panel;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.print("x : " + panel.getComponentAt(e.getPoint()).getX());
        System.out.println(" | y : " + panel.getComponentAt(e.getPoint()).getY());
    }


}
