package com.example.demo1.entity;

import java.math.BigDecimal;

public class Sku {
    private long id;
    private long product_id;
    private BigDecimal original_price;
    private BigDecimal price;
    private String attr1;
    private String attr2;
    private String attr3;
    private int quantity;
    private Integer sort;
    private char status;

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public BigDecimal getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(BigDecimal original_price) {
        this.original_price = original_price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Sku(long id, long product_id, BigDecimal original_price, BigDecimal price, String attr1, String attr2, String attr3, int quantity, int sort) {
        this.id = id;
        this.product_id = product_id;
        this.original_price = original_price;
        this.price = price;
        this.attr1 = attr1;
        this.attr2 = attr2;
        this.attr3 = attr3;
        this.quantity = quantity;
        this.sort = sort;
    }

    public Sku() {
    }

    @Override
    public String toString() {
        return "pre_sku{" +
                "id=" + id +
                ", product_id=" + product_id +
                ", original_price=" + original_price +
                ", price=" + price +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3='" + attr3 + '\'' +
                ", quantity=" + quantity +
                ", sort=" + sort +
                '}';
    }
}
