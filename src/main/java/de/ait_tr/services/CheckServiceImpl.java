package de.ait_tr.services;

import de.ait_tr.mappers.CheckMapper;
import de.ait_tr.models.Order;
import de.ait_tr.printer.Printer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CheckServiceImpl implements CheckService {

    private final Printer printer;

    public CheckServiceImpl(String checkFileName) {
        this.printer = new Printer(checkFileName);
    }

    @Override
    public void print(Order order) {
        printer.print(order);
    }
}
