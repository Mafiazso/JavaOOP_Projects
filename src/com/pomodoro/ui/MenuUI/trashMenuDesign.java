package com.pomodoro.ui.MenuUI;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.pomodoro.logic.TaskManager;
import com.pomodoro.ui.TaskTableModel;
import com.pomodoro.ui.formUI.addFormUI;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class trashMenuDesign extends JPanel{

	private TaskManager taskManager = new TaskManager();
	
	public trashMenuDesign() {
		
		setBounds(113, 5, 780, 551);
		setLayout(null);
		
		// ===============================================================
        // Table ---- TaskTableModel Class
        // ===============================================================
		TaskTableModel table = new TaskTableModel(taskManager);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 725, 456);
		add(scrollPane);
		scrollPane.setViewportView(table);

		addFormUI addForm = new addFormUI(null);
		
		
		JPanel btnPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		btnPanel.setBounds(10, 478, 724, 62);
		add(btnPanel);
		
		JButton deleteBtn = new JButton("ลบงาน");
		btnPanel.add(deleteBtn);
		// ใช้ method deleteForever จาก TaskManager
		
		JButton restoreBtn = new JButton("กู้คืนงาน");
		btnPanel.add(restoreBtn);
		// ใช้ method restoreTask จาก TaskManager
	}

}
