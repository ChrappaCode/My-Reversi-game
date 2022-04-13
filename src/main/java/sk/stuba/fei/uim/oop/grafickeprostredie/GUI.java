package sk.stuba.fei.uim.oop.grafickeprostredie;

import sk.stuba.fei.uim.oop.plocha.HernaLogika;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUI{

    private JFrame okno;

    public GUI(int velkost, int indexVelkostPola){

        okno = new JFrame("Milujem Othello");
        okno.setResizable(false);
        okno.setLayout(new BorderLayout());

        try {
            okno.setIconImage(ImageIO.read(ZapniHru.class.getResourceAsStream("/Jayzwho.jpg")));
        }
        catch (IOException e) {
            System.out.println("Obrazok sa nepodarilo načítať");
            System.exit(0);
        }

        okno.add(novyPanel(velkost,indexVelkostPola), BorderLayout.CENTER);

        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.pack();
        okno.setVisible(true);


    }

    private JPanel novyPanel(int velkost,int indexVelkostPola){
        return new HernaLogika(velkost,okno,indexVelkostPola);
    }
}
