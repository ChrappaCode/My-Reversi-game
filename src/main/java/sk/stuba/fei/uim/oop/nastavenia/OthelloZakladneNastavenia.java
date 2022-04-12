package sk.stuba.fei.uim.oop.nastavenia;

import sk.stuba.fei.uim.oop.grafickeprostredie.GUI;

import javax.swing.*;
import java.awt.event.*;

public class OthelloZakladneNastavenia extends UniverzalnyAdapter {

    private JFrame okno;
    private int aktualnaVelkost;
    private int aktualnyIndex;

    public OthelloZakladneNastavenia(JFrame okno , int aktualnaVelkost , int aktualnyIndex ){
        this.aktualnaVelkost = aktualnaVelkost;
        this.aktualnyIndex = aktualnyIndex;
        this.okno = okno;
    }

    private void restart() {
        System.out.println("reset");
        okno.dispose();
        new GUI(aktualnaVelkost,aktualnyIndex);
    }

    private void zavriHru() {
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
}