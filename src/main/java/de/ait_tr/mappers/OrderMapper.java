package de.ait_tr.mappers;

import de.ait_tr.dtos.BasketRecordDTO;
import de.ait_tr.dtos.ProductDTO;
import de.ait_tr.dtos.ProductInBasketDTO;
import de.ait_tr.models.*;
import de.ait_tr.validators.OrderRecordValidator;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public static String ILLEGAL_FORMAT_OR_DAMAGED_FILE = "Файл содержит элементы неподдерживаемого формата или поврежден.";
    private static final String DELIMITER = ";";
    private static final int REQUIRED_SIZE = 7;

    private OrderMapper() {
    }

    public static OrderRecord toOrderRecord(String line) {
        try {
            String[] parsed = line.split(DELIMITER);
            OrderRecord orderRecord;
            if (parsed.length == REQUIRED_SIZE) {
                String dateTime = parsed[0];
                String orderId = parsed[1];
                int orderNumber = Integer.parseInt(parsed[2]);
                String userId = parsed[3];
                String productId = parsed[4];
                double productPrice = Double.parseDouble(parsed[5]);
                int count = Integer.parseInt(parsed[6]);
                orderRecord = new OrderRecord(dateTime, orderId, orderNumber, userId, productId, productPrice, count);
            } else {
                throw new RuntimeException(ILLEGAL_FORMAT_OR_DAMAGED_FILE);
            }

            if (OrderRecordValidator.validate(orderRecord)) {
                return orderRecord;
            } else return null;

        } catch (RuntimeException e) {
            throw new RuntimeException(ILLEGAL_FORMAT_OR_DAMAGED_FILE);
        }
    }

    public static String toLine(OrderRecord orderRecord) {
        return orderRecord.dateTime() +
                ";" +
                orderRecord.orderId() +
                ";" +
                orderRecord.orderNumber() +
                ";" +
                orderRecord.userId() +
                ";" +
                orderRecord.productId() +
                ";" +
                String.format("%.2f", orderRecord.productPrice()) +
                ";" +
                orderRecord.count();
    }

    public static List<OrderRecord> toOrderRecordList(Order order) {
        List<OrderRecord> orderRecordList = new ArrayList<>();
        for (BasketRecordDTO basketRecordDTO : order.getProductBasket()) {
            orderRecordList.add(new OrderRecord
                    (
                            order.getDateTime(),
                            order.getOrderId(),
                            order.getOrderNumber(),
                            order.getUserId(),
                            basketRecordDTO.getId(),
                            basketRecordDTO.getPrice(),
                            basketRecordDTO.getCount()
                    )
            );
        }
        return orderRecordList;
    }

    public static Order toOrder(List<OrderRecord> orderRecordList, List<ProductDTO> productDTOList) {
        List<BasketRecordDTO> productBasket = new ArrayList<>();
        String dateTime = orderRecordList.get(0).dateTime();
        String orderTd = orderRecordList.get(0).orderId();
        int orderNumber = orderRecordList.get(0).orderNumber();
        String userId = orderRecordList.get(0).userId();
        for (OrderRecord orderRecord : orderRecordList) {
            for (ProductDTO productDTO : productDTOList) {
                if (orderRecord.productId().equals(productDTO.id()))
                    productBasket.add(
                            new BasketRecordDTO(
                                    new ProductInBasketDTO(
                                            productDTO.id(),
                                            productDTO.title(),
                                            productDTO.price()
                                    ),
                                    orderRecord.count()
                            )
                    );
            }
        }
        return new Order(
                dateTime,
                orderTd,
                orderNumber,
                userId,
                productBasket
        );
    }

}
