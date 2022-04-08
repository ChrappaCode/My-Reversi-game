package sk.stuba.fei.uim.oop.test;

import sk.stuba.fei.uim.oop.nastavenia.UniverzalnyAdapter;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class BoxLogika2 extends UniverzalnyAdapter {

    private JPanel panel;

    public BoxLogika2(JPanel panel){
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox cb = (JComboBox) e.getSource();
        System.out.println(cb.getSelectedItem());

        if(cb.getSelectedItem().equals("6x6")){
            panel.removeAll();
            panel.repaint();

        }
        else if(cb.getSelectedItem().equals("8x8")){
            panel.removeAll();
            panel.repaint();
        }
        else if(cb.getSelectedItem().equals("10x10")){
            panel.removeAll();
            panel.repaint();

        }
        else if(cb.getSelectedItem().equals("12x12")){
            panel.removeAll();
            panel.repaint();
        }
    }

}
