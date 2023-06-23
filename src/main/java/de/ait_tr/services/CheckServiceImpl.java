package de.ait_tr.services;

import de.ait_tr.models.Order;

public class CheckServiceImpl implements CheckService{
    private final String checkFileName;

    public CheckServiceImpl(String checkFileName) {
        this.checkFileName = checkFileName;
    }

    @Override
    public void print(Order order) {

    }
}
