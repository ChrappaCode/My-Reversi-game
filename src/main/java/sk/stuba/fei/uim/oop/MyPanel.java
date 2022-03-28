package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {


    @Override
    protected void paintComponent(Graphics g) {

        this.niecoKresli(g);

    }

    private void niecoKresli(Graphics g){

        g.setColor(Color.black);
        g.fillArc(12,12,40,40,0,360);

    }


}
