package de.ait_tr.dtos;

public class InBasketDTO {
    private final ProductDTO productDTO;
    private int count;

    public InBasketDTO(ProductDTO productDTO, int count) {
        this.productDTO = productDTO;
        this.count = count;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "InBasketDTO{" +
                "productDTO=" + productDTO +
                ", count=" + count +
                '}';
    }
}
