package sk.stuba.fei.uim.oop.grafickeprostredie;

import sk.stuba.fei.uim.oop.hra.Hra;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUI{

    private JFrame okno;
    private JPanel hernyPanel;
    private int velkost;
    private int indexVelkostPola;

    public GUI(int velkost, int indexVelkostPola){

        this.velkost = velkost;
        this.indexVelkostPola = indexVelkostPola;
        urobOkno();

    }

    private void urobOkno(){

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

        novyPanel(1);

        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.pack();
        okno.setVisible(true);
    }

    public void novyPanel(int i){
        this.hernyPanel = new Hra(velkost,okno,indexVelkostPola);
        okno.add(hernyPanel);
        okno.revalidate();
    }
}
