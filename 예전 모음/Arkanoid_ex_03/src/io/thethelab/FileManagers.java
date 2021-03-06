package io.thethelab;

import processing.core.PApplet;

import java.io.*;
import java.nio.file.Files;

public class FileManagers {

    public static String readFile(String filename) {

        String line;
        StringBuilder builder = new StringBuilder();

        try (FileReader fileReader = new FileReader(filename)) {

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }




}
