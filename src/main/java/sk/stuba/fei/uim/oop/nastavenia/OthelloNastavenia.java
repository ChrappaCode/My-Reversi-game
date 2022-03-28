package sk.stuba.fei.uim.oop.nastavenia;

import java.awt.event.*;

public class OthelloNastavenia extends UniverzalnyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                System.out.println("hello");

        }
    }

}
