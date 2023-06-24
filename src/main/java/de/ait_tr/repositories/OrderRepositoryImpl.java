package de.ait_tr.repositories;

import de.ait_tr.mappers.OrderMapper;
import de.ait_tr.models.Order;
import de.ait_tr.models.ProductBasket;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRepositoryImpl implements OrderRepository {
    private final String orderFileDirectoryPath;
    private final static String filePath = "order%d.csv";
    private final static String DIRECTORY_CONTAINS_UNSUPPORTED_FILENAMES_MSG = "Директорий хранения заказов содержит неподдерживаемые имена файлов!";

    public OrderRepositoryImpl(String orderFileDirectoryPath) {
        this.orderFileDirectoryPath = orderFileDirectoryPath;
    }

    @Override
    public void save(Order order) {
        String lines = OrderMapper.toOrderRecordList(order)
                .stream()
                .map(OrderMapper::toLine)
                .collect(Collectors.joining(System.lineSeparator()));
        File directory = new File(orderFileDirectoryPath);
        directory.mkdir();
        File file = new File(directory, String.format(filePath, order.getOrderNumber()));

        try (FileWriter writer = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(lines);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в файл" + file.getName() + ". " + e.getMessage());
        }
    }

    @Override
    public Order create(ProductBasket productBasket) {
        int lastOrderNumber = getLastOrderNumber();
        return new Order(productBasket.getProductsInBasket(), lastOrderNumber);
    }

    private int getLastOrderNumber() {
        File directory = new File(orderFileDirectoryPath);
        if (directory.exists()) {
            List<String> filenames = Arrays.stream(directory.listFiles())
                    .map(File::getName)
                    .toList();
            if (filenames.isEmpty()) {
                return 0;
            } else {
                String temp = filenames.stream()
                        .max(Comparator.naturalOrder())
                        .toString()
                        .replaceAll("[^0-9]", "");//[A-Za-z\].\[]
                if (!temp.matches("[+]?\\d+")) {
                    throw new RuntimeException(DIRECTORY_CONTAINS_UNSUPPORTED_FILENAMES_MSG);
                } else {
                    return Integer.parseInt(temp);
                }
            }
        } else {
            return 0;
        }
    }
}
