package com.pomodoro.logic;

import com.pomodoro.model.Task;
import com.pomodoro.model.Category;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;

public class TaskManager {

    private List<Task> tasks;
    private List<Category> categories;
    private DatabaseManager database;

    public TaskManager() {
        this.database = new DatabaseManager();
        this.categories = database.loadAllCategories();

        // ถ้ายังไม่มี category ใน DB ให้ใส่ค่าเริ่มต้น
        if (categories.isEmpty()) {
            Category general = new Category("General", "#2ECC71"); //
            Category work = new Category("Work", "#3498DB"); // น้ำเงิน
            categories.add(general);
            categories.add(work);
            database.saveCategory(general);
            database.saveCategory(work);
        }

        this.tasks = database.loadAllTasks(categories);
    }

    public void addTask(Task task) {
        tasks.add(task);
        database.saveTask(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        database.deleteTask(task.getId());
    }

    // บันทึกการเปลี่ยนแปลงของ task ลง DB
    public void updateTask(Task task) {
        database.saveTask(task);
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public List<Category> getCategories() {
        return categories;
    }

    // หา category จากชื่อ (ไม่สนตัวพิมพ์เล็ก/ใหญ่)
    public Category getCategoryByName(String name) {
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(name)) {
                return category;
            }
        }
        return null;
    }

    public void addCategory(Category category) {
        categories.add(category);
        database.saveCategory(category);
    }

    // เรียงรายการงานตามชื่อ (A→Z)
    public void sortByName() {
        tasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task a, Task b) {
                return a.getTitle().compareTo(b.getTitle());
            }
        });
    }

    // เรียงรายการงานตามความสำคัญ (HIGH -> MEDIUM ->LOW)
    public void sortByPriority() {
        tasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task a, Task b) {
                return Integer.compare(a.getPriority().ordinal(), b.getPriority().ordinal());
            }
        });
    }

    // เรียงรายการงานตามวันครบกำหนด (งานที่ไม่มีวันจะอยู่ท้ายสุด)
    public void sortByDueDate() {
        tasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task a, Task b) {
                long aTime;
                if (a.getDueDate() != null) {
                    aTime = a.getDueDate().getTime();
                } else {
                    aTime = Long.MAX_VALUE;
                }

                long bTime;
                if (b.getDueDate() != null) {
                    bTime = b.getDueDate().getTime();
                } else {
                    bTime = Long.MAX_VALUE;
                }

                return Long.compare(aTime, bTime);
            }
        });
    }

    // ส่งออกงานทั้งหมดไปยังไฟล์ข้อความ
    public boolean exportAllToFile(String filepath) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filepath));
            try {
                for (Task task : tasks) {
                    writer.println(task.exportToText());
                    writer.println("---");
                }
            } finally {
                writer.close();
            }
            return true;
        } catch (IOException e) {
            System.err.println("ส่งออกไฟล์ไม่ได้: " + e.getMessage());
            return false;
        }
    }

    // ปิด DB connection ตอนปิดโปรแกรม
    public void close() {
        database.close();
    }
}