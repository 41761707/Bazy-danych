package com.zalewskiwojtczak;

import javax.swing.*;
import java.awt.*;

public class StudentPanel extends JPanel {
    public StudentPanel(DataConnect connector){
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        add(panel, BorderLayout.NORTH);

        JButton button1 = new JButton("Oceny");
        JButton button2 = new JButton("Uwagi");
        JButton button3 = new JButton("Zachowanie");

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        ScrollPane scrollPane = new ScrollPane();
        add(scrollPane, BorderLayout.CENTER);
        JTable table = new JTable();
        scrollPane.add(table);

    }
}
