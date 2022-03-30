package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.nastavenia.UniverzalnyAdapter;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BoxLogika extends UniverzalnyAdapter{

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        System.out.println(cb.getSelectedItem());
    }

}
