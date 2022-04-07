package sk.stuba.fei.uim.oop.objekty;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class CiernyKamen extends JPanel {

    @Getter
    private final String meno = "Cierny Kamen";

    @Override
    protected void paintComponent(Graphics g) {

        this.kresliKruzok(g);

    }

    private void kresliKruzok(Graphics g){

        g.setColor(Color.black);
        g.fillArc(0,0,55,55,0,360);

    }

}
