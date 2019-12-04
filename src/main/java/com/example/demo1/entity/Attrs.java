package com.example.demo1.entity;

public class Attrs {
    private Long id;
    private Long product_id;
    private String attr;
    private int type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Attrs(Long id, Long product_id, String attr, int type) {
        this.id = id;
        this.product_id = product_id;
        this.attr = attr;
        this.type = type;
    }

    public Attrs() {
    }

    @Override
    public String toString() {
        return "pre_attrs{" +
                "id=" + id +
                ", product_id=" + product_id +
                ", attr='" + attr + '\'' +
                ", type=" + type +
                '}';
    }
}
