package sk.stuba.fei.uim.oop.test;

import sk.stuba.fei.uim.oop.grafickeprostredie.ZapniHru;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Pokus2 {


    public Pokus2(int velkost){

        JFrame okno = new JFrame("otel");
        okno.setResizable(false);
        okno.setLayout(new BorderLayout());

        try {
            okno.setIconImage(ImageIO.read(ZapniHru.class.getResourceAsStream("/Jayzniggawhatniggawho.jpg")));
        }
        catch (IOException e) {
            System.out.println("Obrazok sa nepodarilo načítať");
            System.exit(0);
        }

        JPanel panel = new PanelPokus2(velkost);
        okno.add(panel, BorderLayout.CENTER);

        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.pack();
        okno.setVisible(true);




    }

}
