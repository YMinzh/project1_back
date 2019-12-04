package com.example.demo1.entity;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

public class ForSku {
    private ArrayList<Sku> data;
    private long product_id;

    public ArrayList<Sku> getData() {
        return data;
    }

    public void setData(ArrayList<Sku> data) {
        this.data = data;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public ForSku(ArrayList<Sku> data, long product_id) {
        this.data = data;
        this.product_id = product_id;
    }

    public ForSku() {
    }

    @Override
    public String toString() {
        return "ForSku{" +
                "data=" + data +
                ", product_id=" + product_id +
                '}';
    }
}
