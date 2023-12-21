package org.grocery;

import org.grocery.view.AddItemView;

import javax.swing.*;

import static javax.swing.SwingUtilities.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        invokeLater(() -> {
            new AddItemView();
        });

    }
}