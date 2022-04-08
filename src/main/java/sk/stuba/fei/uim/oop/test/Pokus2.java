package sk.stuba.fei.uim.oop.test;

import lombok.Getter;
import sk.stuba.fei.uim.oop.grafickeprostredie.ZapniHru;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Pokus2 {

    @Getter
    private JFrame okno;

    public Pokus2(int velkost, int indexVelkostPola){

        okno = new JFrame("Hra Othello pokus 2");
        okno.setResizable(false);
        okno.setLayout(new BorderLayout());

        try {
            okno.setIconImage(ImageIO.read(ZapniHru.class.getResourceAsStream("/Jayzniggawhatniggawho.jpg")));
        }
        catch (IOException e) {
            System.out.println("Obrazok sa nepodarilo načítať");
            System.exit(0);
        }

        JPanel panel = new PanelPokus2(velkost,okno,indexVelkostPola);
        okno.add(panel, BorderLayout.CENTER);

        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.pack();
        okno.setVisible(true);


    }

}
