package sk.stuba.fei.uim.oop.objekty;

import javax.swing.*;
import java.awt.*;

public class Kamen extends JPanel {


    @Override
    protected void paintComponent(Graphics g) {

        this.kresliKruzok(g);

    }

    private void kresliKruzok(Graphics g){

        g.setColor(Color.white);
        g.fillArc(26,28,60,60,0,360);

    }

}
