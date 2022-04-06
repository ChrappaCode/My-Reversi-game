package sk.stuba.fei.uim.oop.nastavenia;

import sk.stuba.fei.uim.oop.plocha.Hra;

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
            okno.dispose();
            new Hra(6);

        }
        else if(cb.getSelectedItem().equals("8x8")){
            okno.dispose();
            new Hra(8);

        }
        else if(cb.getSelectedItem().equals("10x10")){
            okno.dispose();
            new Hra(10);

        }
        else if(cb.getSelectedItem().equals("12x12")){
            okno.dispose();
            new Hra(12);
        }
    }

}
