package model;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String nameProduct;
    private int quantity;
    private BigDecimal price;
    private String descrip;

    public Product() {
    }

    public Product(Long id, String nameProduct, int quantity, BigDecimal price, String descrip) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
        this.price = price;
        this.descrip = descrip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
}
