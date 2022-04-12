package sk.stuba.fei.uim.oop.objekty;

import javax.swing.*;
import java.awt.*;

public class CiernyKamen extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {

        this.kresliKruzok(g);

    }

    private void kresliKruzok(Graphics g){

        g.setColor(Color.black);
        g.fillArc(0,0,55,55,0,360);

    }

}
