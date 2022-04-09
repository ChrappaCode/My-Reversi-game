package sk.stuba.fei.uim.oop.test;

import sk.stuba.fei.uim.oop.grafickeprostredie.ZapniHru;
import sk.stuba.fei.uim.oop.nastavenia.UniverzalnyAdapter;

import javax.swing.*;
import java.awt.event.*;

public class OthelloZakladneNastavenia2 extends UniverzalnyAdapter {

    private JFrame okno;
    private int aktualnaVelkost;
    private int aktualnyIndex;

    public OthelloZakladneNastavenia2(JFrame okno , int aktualnaVelkost , int aktualnyIndex){
        this.aktualnaVelkost = aktualnaVelkost;
        this.aktualnyIndex = aktualnyIndex;
        this.okno = okno;
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
}