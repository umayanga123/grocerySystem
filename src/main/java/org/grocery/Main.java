package org.grocery;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import org.grocery.view.AddItemView;

import javax.swing.*;

import static javax.swing.SwingUtilities.invokeLater;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
            invokeLater(AddItemView::new);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

    }
}