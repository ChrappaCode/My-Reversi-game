package sk.stuba.fei.uim.oop.nastavenia;

import sk.stuba.fei.uim.oop.grafickeprostredie.Othello;

import javax.swing.*;
import java.awt.event.*;

public class OthelloNastavenia extends UniverzalnyAdapter{

    private JFrame okno;

    public OthelloNastavenia(JFrame okno){
        this.okno = okno;
    }

    public OthelloNastavenia() {

    }

    public void restart() {
        System.out.println("reset");
        okno.dispose();
        new Othello();
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
