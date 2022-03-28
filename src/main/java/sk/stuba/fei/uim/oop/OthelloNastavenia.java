package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.event.*;

public class OthelloNastavenia extends ZakladneNastavenia{

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 'r':
                System.out.println("hello");

        }
    }

}
