package de.ait_tr.dtos;

public class BasketRecordDTO {
    private final ProductInBasketDTO productInBasketDTO;
    private int count;

    public BasketRecordDTO(ProductInBasketDTO productInBasketDTO, int count) {
        this.productInBasketDTO = productInBasketDTO;
        this.count = count;
    }
    public ProductInBasketDTO getProductInBasketDTO() {
        return productInBasketDTO;
    }
    public String getId() {
        return productInBasketDTO.id();
    }
    public String getTitle() {
        return productInBasketDTO.title();
    }
    public double getPrice() {
        return productInBasketDTO.price();
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BasketRecordDTO{" +
                "productInBasketDTO=" + productInBasketDTO +
                ", count=" + count +
                '}';
    }
}
