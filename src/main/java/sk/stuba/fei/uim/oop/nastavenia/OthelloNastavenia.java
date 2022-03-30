package sk.stuba.fei.uim.oop.nastavenia;


import lombok.Setter;

import javax.swing.*;
import java.awt.event.*;

public class OthelloNastavenia extends UniverzalnyAdapter {

    @Setter
    private boolean klik;

    public OthelloNastavenia() {
    }

    public void gameRestart() {
        System.out.println("reset");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                this.gameRestart();

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.gameRestart();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        System.out.println("h");

    }

}
