package sk.stuba.fei.uim.oop.nastavenia;


import lombok.Setter;

import javax.swing.*;
import java.awt.event.*;

public class OthelloNastavenia extends UniverzalnyAdapter {

    @Setter
    private boolean klik;

    public OthelloNastavenia() {
    }

    public void restart() {
        System.out.println("reset");
    }

    public void zavriHru() {
        System.out.println("Zavri sa");
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

    @Override
    public void itemStateChanged(ItemEvent e) {

        System.out.println("h");

    }

}
