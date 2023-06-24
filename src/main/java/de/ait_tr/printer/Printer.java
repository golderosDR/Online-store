package de.ait_tr.printer;

import de.ait_tr.mappers.CheckMapper;
import de.ait_tr.models.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Printer {
    private final static String FILE_NOT_FOUND_ERROR_MSG = "Файл не найден или поврежден!";
    private final String checkFileName;

    public Printer(String checkFileName) {
        this.checkFileName = checkFileName;
    }

    public void print(Order order) {
        String lines = CheckMapper.toLines(order);
        print(lines);
        printInFile(lines);
    }

    private void printInFile(String lines) {
        try (FileWriter writer = new FileWriter(checkFileName, true);
             BufferedWriter buffWriter = new BufferedWriter(writer)) {
            buffWriter.write(lines);
        } catch (IOException e) {
            throw new RuntimeException(FILE_NOT_FOUND_ERROR_MSG);
        }
    }

    private void print(String lines) {
        System.out.println(lines);
    }
}
