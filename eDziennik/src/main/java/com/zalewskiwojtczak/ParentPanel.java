package com.zalewskiwojtczak;

import javax.swing.*;
import java.awt.*;

public class ParentPanel extends JPanel {
    public ParentPanel(DataConnect connector){
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        add(panel, BorderLayout.NORTH);

        JButton button1 = new JButton("Uczniowie");
        JButton button2 = new JButton("Oceny");
        JButton button3 = new JButton("Uwagi");
        JButton button4 = new JButton("Zachowanie");

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);

        ScrollPane scrollPane = new ScrollPane();
        add(scrollPane, BorderLayout.CENTER);
        JTable table = new JTable();
        scrollPane.add(table);

    }
}
