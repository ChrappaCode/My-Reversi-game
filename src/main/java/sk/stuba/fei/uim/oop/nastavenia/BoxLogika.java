package sk.stuba.fei.uim.oop.nastavenia;

import sk.stuba.fei.uim.oop.grafickeprostredie.Othello;
import sk.stuba.fei.uim.oop.objekty.Kamen;
import sk.stuba.fei.uim.oop.plocha.HernaPlocha;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BoxLogika extends UniverzalnyAdapter{

    private JFrame okno;

    public BoxLogika(JFrame okno){
        this.okno = okno;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox cb = (JComboBox) e.getSource();
        System.out.println(cb.getSelectedItem());

        if(cb.getSelectedItem().equals("6x6")){
            /*okno.dispose();
            new Othello();*/
            okno.setSize(600,630);

        }
        else if(cb.getSelectedItem().equals("8x8")){
            /*okno.dispose();
            new Othello();*/
            okno.setSize(720,760);

        }
        else if(cb.getSelectedItem().equals("10x10")){
            /*okno.dispose();
            new Othello();*/
            okno.setSize(800,830);

        }
        else if(cb.getSelectedItem().equals("12x12")){
            /*okno.dispose();
            new Othello();*/
            okno.setSize(920,960);

        }
    }

}
