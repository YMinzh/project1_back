package com.example.demo1.entity;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

public class Details {
    private long id;
    private long category_id;
    private String property;
    private String attr;
    private int type;
    private ArrayList<String> attrList1;
    private ArrayList<String> attrList2;
    private ArrayList<String> attrList3;

    public ArrayList<String> getAttrList1() {
        return attrList1;
    }

    public void setAttrList1(ArrayList<String> attrList1) {
        this.attrList1 = attrList1;
    }

    public ArrayList<String> getAttrList2() {
        return attrList2;
    }

    public void setAttrList2(ArrayList<String> attrList2) {
        this.attrList2 = attrList2;
    }

    public ArrayList<String> getAttrList3() {
        return attrList3;
    }

    public void setAttrList3(ArrayList<String> attrList3) {
        this.attrList3 = attrList3;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
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

    public Details(long id, long category_id, String property, String attr, int type) {
        this.id = id;
        this.category_id = category_id;
        this.property = property;
        this.attr = attr;
        this.type = type;
    }

    public Details() {
    }

    @Override
    public String toString() {
        return "Details{" +
                "id=" + id +
                ", category_id=" + category_id +
                ", property=" + property +
                ", attr='" + attr + '\'' +
                ", type=" + type +
                '}';
    }
}
