package org.java;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MemoryLeakExample {
    public static void main(String[] args) {
        JButton button = new JButton("Clique Aqui");
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(button);
        frame.setSize(300, 200);
        frame.setVisible(true);

        for (int i = 0; i <= 999; i++) {
            button.addActionListener(e -> System.out.print("Botão clicado! -"));
        }
    }

    static final ActionListener actionListener = e -> System.out.print("Botão clicado! -");

    public static void exampleOk() {
        JButton button = new JButton("Clique Aqui");
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(button);
        frame.setSize(300, 200);
        frame.setVisible(true);
        button.addActionListener(actionListener);

        // ...

        button.removeActionListener(actionListener);
    }
}