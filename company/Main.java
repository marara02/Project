package com.company;
import javax.swing.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        JFrame app=new JFrame();
        GraphConstruct e=new GraphConstruct();
        app.add(e);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(500, 720);
        app.setVisible(true);
    }
}
