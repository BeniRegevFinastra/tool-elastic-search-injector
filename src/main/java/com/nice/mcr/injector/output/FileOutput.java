package com.nice.mcr.injector.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileOutput implements OutputHandler {

    public static String fileName = "0";

    @Override
    public boolean open() {
        return false;
    }

    @Override
    public void output(String data) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("..\\tool-elastic-search-injector\\input\\" + fileName + ".txt"));
            writer.write(data);
            writer.flush();
            writer.close();
            fileName = String.valueOf(Integer.parseInt(fileName) + 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
