package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.event.*;

public class OthelloNastavenia extends ZakladneNastavenia{

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                System.out.println("hello");

        }
    }

}
