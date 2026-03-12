package com.pomodoro.ui.MenuUI;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

import com.pomodoro.ui.MainWindowDesign;
import com.pomodoro.ui.TaskTableModel;
import com.pomodoro.ui.formUI.addFormUI;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class taskMenuDesign extends JPanel{

	public taskMenuDesign() {
		setBounds(113, 5, 780, 551);
		setLayout(null);
		
		
		
		
		// ===============================================================
        // Table ---- TaskTableModel Class
        // ===============================================================
		TaskTableModel table = new TaskTableModel();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 725, 408);
		add(scrollPane);
		scrollPane.setViewportView(table);

		
		
//=========================================================================================
		
		// ===============================================================
        // Form ---> package formUI
        // ===============================================================
		addFormUI addForm = new addFormUI(null);
		
		// ===============================================================
        // Button 
        // ===============================================================
		JPanel btnPanel = new JPanel();
		btnPanel.setBounds(10, 427, 725, 90);
		add(btnPanel);
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
		
		// ** Action button **
		JPanel actionBtnPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) actionBtnPanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		btnPanel.add(actionBtnPanel);
		
		// - buttons
		JButton addBtn = new JButton("Add Task");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addForm.setVisible(true);
			}
		});
		addBtn.setBounds(10, 450, 89, 23);
		actionBtnPanel.add(addBtn);
		
		JButton deleteBtn = new JButton("Delete Task");
		deleteBtn.setBounds(109, 450, 89, 23);
		actionBtnPanel.add(deleteBtn);
		
		JButton exportBtn = new JButton("Export");
		exportBtn.setBounds(208, 450, 89, 23);
		actionBtnPanel.add(exportBtn);
		
		
		
		// addTask 
		// ให้เขียน logic สำหรับ addTask ตรงนี้
		// -- table.addTask(Object); การเรียกใช้ addTask -----> TaskTableModel Class
		
		
		
		// deleteTask 
		
		
		
		// export เขียนไว้เฉยๆก็ได้ (แฟงทำ) 
		
		
		// -------------------------------------------------------------------
		
		
//=========================================================================================
		
		
		// sortTask 
			// ให้เขียน logic สำหรับ sortTask ตรงนี้
		
		// ** Sort button **
		JPanel sortBtnPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) sortBtnPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		btnPanel.add(sortBtnPanel);
		
		// - buttons
		JLabel sortLabel = new JLabel("Sort : ");
		sortLabel.setBounds(10, 482, 64, 14);
		sortBtnPanel.add(sortLabel);
		
		JButton sortNameBtn = new JButton("Name");
		sortNameBtn.setBounds(75, 478, 64, 23);
		sortBtnPanel.add(sortNameBtn);
		
		JButton sortPriBtn = new JButton("Priority");
		sortPriBtn.setBounds(149, 478, 110, 23);
		sortBtnPanel.add(sortPriBtn);
		
		JButton sortDateBtn = new JButton("DueDate");
		sortDateBtn.setBounds(269, 478, 133, 23);
		sortBtnPanel.add(sortDateBtn);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 10, 10);
		add(panel);
		
		
		// ===============================================================

		
//=========================================================================================
		
		
		
		// ให้เขียน logic สำหรับคลิกขวา 
		
//		(โค้ดเก่า)
//		// Right-click context menu
//        JPopupMenu popupMenu = new JPopupMenu();
//
//        JMenuItem menuEdit = new JMenuItem("✏️ แก้ไขงาน");
//        JMenuItem menuDelete = new JMenuItem("🗑️ ลบงาน");
//        JSeparator separator = new JSeparator();
//        JMenuItem menuTodo = new JMenuItem("📋 To Do");
//        JMenuItem menuInProgress = new JMenuItem("🔄 In Progress");
//        JMenuItem menuDone = new JMenuItem("✅ Done");
//
//        popupMenu.add(menuEdit);
//        popupMenu.add(menuDelete);
//        popupMenu.add(separator);
//        popupMenu.add(menuTodo);
//        popupMenu.add(menuInProgress);
//        popupMenu.add(menuDone);
		
		
		// โค้ดเก่าๆจะอยู่ใน MainLayout หรือ จะไปเอาจาก github ก็ได้
	}
}
