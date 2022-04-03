package sk.stuba.fei.uim.oop.objekty;

import javax.swing.*;
import java.awt.*;

public class Mriezka extends JPanel{


    @Override
    protected void paintComponent(Graphics g) {

        this.kresliMriezku(g);

    }

    private void kresliMriezku(Graphics g){

        for (int i = 0; i < 6*6; i++) {
            g.setColor(Color.black);
            g.drawLine(0,100,10,10);
        }

    }

}
