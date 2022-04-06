package sk.stuba.fei.uim.oop.objekty;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class PrazdnyKamen extends JPanel {

    @Getter
    private final String meno = "Biely Kamen";

    @Override
    protected void paintComponent(Graphics g) {

        this.kresliKruzok(g);

    }

    private void kresliKruzok(Graphics g){

        g.setColor(Color.BLACK);
        g.drawArc(0,0,51,51,0,360);
        g.drawArc(0,0,50,50,0,360);

    }

}
