package com.pomodoro.ui;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.pomodoro.logic.TaskManager;
import com.pomodoro.model.Task;

public class TaskTableModel extends JScrollPane {

    private JTable table;
    private DefaultTableModel model;
    private TaskManager taskManager;

	// ===============================================================
    // TABLE MODEL
    // ===============================================================
    
    public TaskTableModel(TaskManager taskManager) {
    	
    	this.taskManager = taskManager;

        model = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "ชื่องาน", "คำอธิบาย", "ความสำคัญ", "หมวดหมู่", "วันครบกำหนด", "แจ้งเตือน" , "สถานะ"
            }
        );

        table = new JTable(model);

        setViewportView(table);
    }

    
	// ===============================================================
    // Method for Table
    // ===============================================================
    
    // method สำหรับเพิ่มข้อมูล
    public void addTask(Object[] row) {
    	
    	for (Task t : taskManager.getAllTasks()) {
    		
    		model.addRow(new Object[] {
    				t.getId(),
    				t.getTitle(),
    				t.getDescription(),
    				t.getPriority(),
    				t.getCategory(),
    				t.getDueDate(),
    				t.getReminderTime()
    		});
    	}
    }

    // method เรียกใช้ table สำหรับ class อื่นๆที่อยากใช้
    public JTable getTable() {
        return table;
    }
    
    // method สำหรับ reload Table เมื่อมีการอัปเดตข้อมูล
    public void reloadTable() {
    	
    	model.setRowCount(0); // เซ็ตค่าเริ่มต้น
    	
    	for (Task t : taskManager.getActiveTasks()) {
    		
    		model.addRow(new Object[] {
    				t.getTitle(),
    				t.getDescription(),
    				t.getPriority(),
    				t.getCategory(),
    				t.getDueDate(),
    				t.getReminderTime()
    		});
    	}
    	
    }
}