package sk.stuba.fei.uim.oop.test;

import sk.stuba.fei.uim.oop.nastavenia.UniverzalnyAdapter;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BoxLogika2 extends UniverzalnyAdapter {

    private JFrame okno;
    public BoxLogika2(JFrame okno){
        this.okno = okno;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox cb = (JComboBox) e.getSource();
        System.out.println(cb.getSelectedItem());

        if(cb.getSelectedItem().equals("6x6")){
            okno.dispose();
            new Pokus2(6,0);
        }

        else if(cb.getSelectedItem().equals("8x8")){
            okno.dispose();
            new Pokus2(8,1);
        }
        else if(cb.getSelectedItem().equals("10x10")){
            okno.dispose();
            new Pokus2(10,2);

        }
        else if(cb.getSelectedItem().equals("12x12")){
            okno.dispose();
            new Pokus2(12,3);
        }
    }

}
