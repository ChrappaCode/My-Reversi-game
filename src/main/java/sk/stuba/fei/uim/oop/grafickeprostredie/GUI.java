package sk.stuba.fei.uim.oop.grafickeprostredie;

import lombok.Getter;
import sk.stuba.fei.uim.oop.hra.Hra;
import sk.stuba.fei.uim.oop.nastavenia.UniverzalnyAdapter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GUI extends UniverzalnyAdapter {

    @Getter
    private JFrame okno;
    private JPanel hernyPanel;
    private JLabel velkostPlochy;
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
            okno.setIconImage(ImageIO.read(ZapniHru.class.getResourceAsStream("/Jayz.jpg")));
        }
        catch (IOException e) {
            System.out.println("Obrazok sa nepodarilo načítať");
            System.exit(0);
        }

        novyPanel();
        okno.addKeyListener(this);
        urobMenu();

        okno.setFocusable(true);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.pack();
        okno.setVisible(true);
    }
    private void urobMenu(){

        JPanel menu = new JPanel();
        menu.setBackground(Color.RED);

        JButton restart = new JButton("Reštart");
        restart.setBackground(new Color(211, 153, 238));
        restart.addActionListener(e -> resetPanel(velkost));

        restart.setFont((new Font("Serif",Font.BOLD, 30)));
        restart.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        restart.setFocusable(false);

        String[] velkosti ={"6x6","8x8","10x10","12x12"};
        JComboBox box = new JComboBox(velkosti);
        box.setSelectedIndex(indexVelkostPola);
        box.setBackground(Color.cyan);
        box.addActionListener(this);
        box.setFocusable(false);

        JPanel velkostHracejPlochy = new JPanel(new GridLayout(1,1));
        velkostHracejPlochy.setBackground(new Color(211, 153, 238));
        velkostPlochy = new JLabel("Veľkosť hracej plochy : " + box.getSelectedItem() , SwingConstants.CENTER);
        velkostPlochy.setBorder(BorderFactory.createLineBorder(Color.yellow,3));
        velkostPlochy.setFont(new Font("Serif",Font.BOLD, 20));
        velkostHracejPlochy.add(velkostPlochy);

        menu.setLayout(new BorderLayout());
        menu.add(restart, BorderLayout.CENTER);
        menu.add(box , BorderLayout.PAGE_END);

        okno.add(menu,BorderLayout.PAGE_END);
        okno.add(velkostHracejPlochy,BorderLayout.PAGE_START);
    }

    private void novyPanel(){
        this.hernyPanel = new Hra(velkost,okno);
        okno.add(hernyPanel);
        okno.revalidate();
    }

    private void resetPanel(int velkost){
        okno.remove(hernyPanel);
        this.hernyPanel = new Hra(velkost,okno);
        okno.add(hernyPanel);
        okno.pack();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                resetPanel(velkost);
                break;
            case KeyEvent.VK_ESCAPE:
                this.zavriHru();
        }
    }
    private void zavriHru() {
        System.out.println("Zavri sa");
        okno.dispose();
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox cb = (JComboBox) e.getSource();
        System.out.println(cb.getSelectedItem());

        if(cb.getSelectedItem().equals("6x6")){
            resetPanel(6);
            this.velkost = 6;
            okno.pack();
            velkostPlochy.setText("Veľkosť hracej plochy : 6x6");
            okno.revalidate();
        }
        else if(cb.getSelectedItem().equals("8x8")){
            resetPanel(8);
            this.velkost = 8;
            okno.pack();
            velkostPlochy.setText("Veľkosť hracej plochy : 8x8");
            okno.revalidate();
        }
        else if(cb.getSelectedItem().equals("10x10")){
            resetPanel(10);
            this.velkost = 10;
            okno.pack();
            velkostPlochy.setText("Veľkosť hracej plochy : 10x10");
            okno.revalidate();

        }
        else if(cb.getSelectedItem().equals("12x12")){
            resetPanel(12);
            this.velkost = 12;
            okno.pack();
            velkostPlochy.setText("Veľkosť hracej plochy : 12x12");
            okno.revalidate();
        }
    }
}
