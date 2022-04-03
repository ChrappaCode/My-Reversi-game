package sk.stuba.fei.uim.oop.nastavenia;

import sk.stuba.fei.uim.oop.nastavenia.UniverzalnyAdapter;
import sk.stuba.fei.uim.oop.objekty.Kamen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MyskaNastavenia extends UniverzalnyAdapter {

    JPanel panel;
    JFrame okno;

    public MyskaNastavenia(JPanel panel, JFrame okno){
        this.panel = panel;
        this.okno = okno;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.print("x : " + panel.getComponentAt(e.getPoint()).getX());
        System.out.println(" | y : " + panel.getComponentAt(e.getPoint()).getY());

    }




}
