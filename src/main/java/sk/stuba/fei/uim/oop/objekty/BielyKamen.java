package sk.stuba.fei.uim.oop.objekty;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class BielyKamen extends JPanel {

    @Getter
    private final String meno = "Biely Kamen";

    @Override
    protected void paintComponent(Graphics g) {

        this.kresliKruzok(g);

    }

    private void kresliKruzok(Graphics g){

        g.setColor(Color.WHITE);
        g.fillArc(0,0,54,54,0,360);
        g.setColor(Color.BLACK);
        g.drawArc(0,0,55,55,0,360);

    }

}
