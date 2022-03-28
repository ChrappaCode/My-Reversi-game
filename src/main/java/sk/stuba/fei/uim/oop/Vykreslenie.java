package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class Vykreslenie extends JPanel {

    private HernaPlocha hernaPlocha;

    public Vykreslenie( HernaPlocha hernaPlocha) {
        this.hernaPlocha = hernaPlocha;
        this.setBackground(Color.CYAN);
    }

}
