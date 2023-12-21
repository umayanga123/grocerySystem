package org.grocery.util;

import java.io.File;

public class ImageFileFilter extends javax.swing.filechooser.FileFilter {


    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        String extension = Utils.getExtension(file);
        return extension != null && (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png"));
    }

    @Override
    public String getDescription() {
        return "Image files (*.jpg, *.jpeg, *.png)";
    }

    static class Utils {
        public static String getExtension(File file) {
            String name = file.getName();
            int lastIndexOf = name.lastIndexOf(".");
            if (lastIndexOf == -1 || lastIndexOf == 0 || lastIndexOf == name.length() - 1) {
                return null;
            }
            return name.substring(lastIndexOf + 1);
        }
    }
}

