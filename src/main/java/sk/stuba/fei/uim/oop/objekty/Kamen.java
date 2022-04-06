package sk.stuba.fei.uim.oop.objekty;

import javax.swing.*;
import java.awt.*;

public class Kamen extends JPanel {


    @Override
    protected void paintComponent(Graphics g) {

        this.kresliKruzok(g);

    }

    private void kresliKruzok(Graphics g){

        g.setColor(Color.black);
        g.fillArc(0,0,51,51,0,360);

    }

}
