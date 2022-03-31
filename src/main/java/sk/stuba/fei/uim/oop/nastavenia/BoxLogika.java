package sk.stuba.fei.uim.oop.nastavenia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BoxLogika extends UniverzalnyAdapter{

    private final JFrame okno;

    public BoxLogika(JFrame okno){
        this.okno = okno;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox cb = (JComboBox) e.getSource();
        System.out.println(cb.getSelectedItem());

        if(cb.getSelectedItem().equals("6x6")){
            okno.setSize(550,550);

        }
        else if(cb.getSelectedItem().equals("8x8")){
            okno.setSize(650,650);

        }
        else if(cb.getSelectedItem().equals("10x10")){
            okno.setSize(750,750);

        }
        else if(cb.getSelectedItem().equals("12x12")){
            okno.setSize(850,850);

        }
    }

}
