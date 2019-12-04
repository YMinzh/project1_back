package com.example.demo1.entity;

import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;
import java.util.Map;

public class Category {

//    private Map<String,String> property;

    private long id;
    private String name;
    private String property;
    private int sort;
    private char status;
    private Timestamp created_at;
    private Timestamp updated_at;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Category() {
    }

    public Category(long id, String name, String property, int sort, char status, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.name = name;
        this.property = property;
        this.sort = sort;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", property='" + property + '\'' +
                ", sort=" + sort +
                ", status=" + status +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
