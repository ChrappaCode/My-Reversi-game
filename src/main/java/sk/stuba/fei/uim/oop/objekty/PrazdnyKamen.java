package sk.stuba.fei.uim.oop.objekty;

import javax.swing.*;
import java.awt.*;

public class PrazdnyKamen extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {

        this.kresliKruzok(g);
    }

    private void kresliKruzok(Graphics g){

        g.setColor(Color.BLACK);
        g.drawArc(0,0,54,54,0,360);
        g.drawArc(0,0,55,55,0,360);

    }

}
