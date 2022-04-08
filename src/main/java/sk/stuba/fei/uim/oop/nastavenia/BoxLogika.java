package sk.stuba.fei.uim.oop.nastavenia;

import sk.stuba.fei.uim.oop.plocha.Hra;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BoxLogika extends UniverzalnyAdapter{

    private JFrame okno;
    private Hra hra = new Hra();

    public BoxLogika(JFrame okno){
        this.okno = okno;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox cb = (JComboBox) e.getSource();
        System.out.println(cb.getSelectedItem());

        if(cb.getSelectedItem().equals("6x6")){
            okno.dispose();
            hra.hraStart(6,0);

        }
        else if(cb.getSelectedItem().equals("8x8")){
            okno.dispose();
            hra.hraStart(8,1);

        }
        else if(cb.getSelectedItem().equals("10x10")){
            okno.dispose();
            hra.hraStart(10,2);

        }
        else if(cb.getSelectedItem().equals("12x12")){
            okno.dispose();
            hra.hraStart(12,3);
        }
    }

}
