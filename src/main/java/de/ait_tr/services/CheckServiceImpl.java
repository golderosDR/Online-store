package de.ait_tr.services;

import de.ait_tr.mappers.CheckMapper;
import de.ait_tr.models.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CheckServiceImpl implements CheckService {
    private final static String FILE_NOT_FOUND_ERROR_MSG = "Файл не найден или поврежден!";
    private final String checkFileName;

    public CheckServiceImpl(String checkFileName) {
        this.checkFileName = checkFileName;
    }

    @Override
    public void print(Order order) {
        String lines = CheckMapper.toLines(order);
        System.out.println(lines);

        try (FileWriter writer = new FileWriter(checkFileName, true);
             BufferedWriter buffWriter = new BufferedWriter(writer)) {
            buffWriter.write(lines);
        } catch (IOException e) {
            throw new RuntimeException(FILE_NOT_FOUND_ERROR_MSG);
        }
    }
}
