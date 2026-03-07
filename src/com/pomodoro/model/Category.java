package com.pomodoro.model;

import java.util.UUID;

public class Category {
    private String id;
    private String name;
    private String colorCode;

    // Constructor เดิม สำหรับสร้าง category ใหม่
    public Category(String name, String colorCode) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.colorCode = colorCode;
    }

    // ทับทิมเพิ่ม constructor สำหรับโหลดจาก DB (รับ id ที่มีอยู่แล้ว)
    public Category(String id, String name, String colorCode) {
        this.id = id;
        this.name = name;
        this.colorCode = colorCode;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    @Override
    public String toString() {
        return name;
    }
}