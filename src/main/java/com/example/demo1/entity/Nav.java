package com.example.demo1.entity;

import java.sql.Timestamp;

public class Nav {
    private long id;
    private byte type_id;
    private byte sort;
    private String title;
    private String picture;
    private byte link_type;
    private String  link_target;
    private char status;
    private Timestamp created_at;
    private Timestamp updated_at;

    public byte getType_id() {
        return type_id;
    }

    public void setType_id(byte type_id) {
        this.type_id = type_id;
    }

    public byte getSort() {
        return sort;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSort(byte sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public byte getLink_type() {
        return link_type;
    }

    public void setLink_type(byte link_type) {
        this.link_type = link_type;
    }

    public String getLink_target() {
        return link_target;
    }

    public void setLink_target(String link_target) {
        this.link_target = link_target;
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

    @Override
    public String toString() {
        return "Nav{" +
                "type_id=" + type_id +
                ", sort=" + sort +
                ", title='" + title + '\'' +
                ", picture='" + picture + '\'' +
                ", link_type=" + link_type +
                ", link_target='" + link_target + '\'' +
                ", status=" + status +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }

    public Nav(byte type_id, byte sort, String title, String picture, byte link_type, String link_target, char status, Timestamp created_at, Timestamp updated_at) {
        this.type_id = type_id;
        this.sort = sort;
        this.title = title;
        this.picture = picture;
        this.link_type = link_type;
        this.link_target = link_target;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Nav() {
    }
}
