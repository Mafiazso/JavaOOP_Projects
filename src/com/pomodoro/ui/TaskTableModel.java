package com.pomodoro.ui;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TaskTableModel extends JScrollPane {

    private JTable table;
    private DefaultTableModel model;

    public TaskTableModel() {

        model = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "ชื่องาน", "คำอธิบาย", "ความสำคัญ", "หมวดหมู่", "วันครบกำหนด", "แจ้งเตือน", "ประเภท"
            }
        );

        table = new JTable(model);

        setViewportView(table);
    }

    // method สำหรับเพิ่มข้อมูล
    public void addTask(Object[] row) {
        model.addRow(row);
    }

    // method เรียกใช้ table สำหรับ class อื่นๆที่อยากใช้
    public JTable getTable() {
        return table;
    }
}