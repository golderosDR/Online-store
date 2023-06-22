package de.ait_tr.dtos;

public class InBasketDTO {
    private final String id;
    private int count;

    public InBasketDTO(String id, int count) {
        this.id = id;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    @Override
    public String toString() {
        return "ProductInBasketDTO{" +
                "id='" + id + '\'' +
                ", count=" + count +
                '}';
    }
}
