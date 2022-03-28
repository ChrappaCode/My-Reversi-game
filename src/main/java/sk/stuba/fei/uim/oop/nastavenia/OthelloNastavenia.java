package sk.stuba.fei.uim.oop.nastavenia;

import lombok.Getter;

import javax.swing.*;
import java.awt.event.*;

public class OthelloNastavenia extends UniverzalnyAdapter {


    public void gameRestart() {
        System.out.println("reset");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                System.out.println("n i g g a");

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
