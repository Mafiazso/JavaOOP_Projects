package com.pomodoro.ui.MenuUI;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

import com.pomodoro.logic.TaskManager;
import com.pomodoro.model.SingleTask;
import com.pomodoro.ui.MainWindowDesign;
import com.pomodoro.ui.TaskTableModel;
import com.pomodoro.ui.formUI.addFormUI;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class taskMenuDesign extends JPanel{
	
	private TaskManager taskManager = new TaskManager();
	
	public taskMenuDesign() {
		setBounds(113, 5, 780, 551);
		setLayout(null);
		
		// ===============================================================
        // Table ---- TaskTableModel Class
        // ===============================================================
		TaskTableModel table = new TaskTableModel(taskManager);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 725, 315);
		add(scrollPane);
		scrollPane.setViewportView(table);

		
		
		
		// ===============================================================
        // Details Panel 
        // ===============================================================
		JPanel detailsPanel = new JPanel();
		detailsPanel.setBounds(10, 337, 724, 94);
		detailsPanel.setBorder(BorderFactory.createTitledBorder("Details"));
		add(detailsPanel);
		detailsPanel.setLayout(null);
		
		// ** Header **
		
		JLabel nameHeader = new JLabel("ชื่องาน : ");
		nameHeader.setBounds(10, 17, 54, 24);
		detailsPanel.add(nameHeader);
		
		JLabel desHeader = new JLabel("คำอธิบาย :");
		desHeader.setBounds(10, 38, 69, 24);
		detailsPanel.add(desHeader);
		
		JLabel priHeader = new JLabel("ความสำคัญ : ");
		priHeader.setBounds(10, 61, 80, 24);
		detailsPanel.add(priHeader);
		
		JLabel cateHeader = new JLabel("หมวดหมู่ : ");
		cateHeader.setBounds(157, 61, 65, 24);
		detailsPanel.add(cateHeader);
		
		JLabel dueDateHeader = new JLabel("วันครบกำหนด : ");
		dueDateHeader.setBounds(341, 17, 97, 23);
		detailsPanel.add(dueDateHeader);
		
		JLabel notiHeader = new JLabel("แจ้งเตือน : ");
		notiHeader.setBounds(341, 38, 63, 24);
		detailsPanel.add(notiHeader);
		
		JLabel statusHeader = new JLabel("สถานะ : ");
		statusHeader.setBounds(341, 61, 53, 23);
		detailsPanel.add(statusHeader);
		
		
		// ** Title **
		
		JLabel nameTitle = new JLabel("");
		nameTitle.setBounds(68, 17, 250, 24);
		detailsPanel.add(nameTitle);
		
		JLabel desTitle = new JLabel("");
		desTitle.setBounds(83, 38, 235, 24);
		detailsPanel.add(desTitle);
		
		JLabel priTitle = new JLabel("");
		priTitle.setBounds(96, 61, 48, 24);
		detailsPanel.add(priTitle);
		
		JLabel cateTitle = new JLabel("");
		cateTitle.setBounds(226, 61, 92, 24);
		detailsPanel.add(cateTitle);
		
		JLabel dueDateTitle = new JLabel("");
		dueDateTitle.setBounds(440, 17, 259, 23);
		detailsPanel.add(dueDateTitle);

		JLabel notiTitle = new JLabel("");
		notiTitle.setBounds(413, 38, 286, 24);
		detailsPanel.add(notiTitle);
		
		JLabel statusTitle = new JLabel("");
		statusTitle.setBounds(398, 61, 301, 23);
		detailsPanel.add(statusTitle);
		
		
		
		
		
//=========================================================================================
		
		// ===============================================================
        // Form ---> package formUI
        // ===============================================================
		addFormUI addForm = new addFormUI(null);
		
		// ===============================================================
        // Button 
        // ===============================================================
		JPanel btnPanel = new JPanel();
		btnPanel.setBounds(10, 438, 725, 79);
		add(btnPanel);
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
		
		// ** Action button **
		JPanel actionBtnPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) actionBtnPanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		btnPanel.add(actionBtnPanel);
		
		// - buttons
		JButton addBtn = new JButton("เพิ่มงาน");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addForm.setVisible(true);
			}
		});
		addBtn.setBounds(10, 450, 89, 23);
		actionBtnPanel.add(addBtn);
		
		JButton deleteBtn = new JButton("ลบงาน");
		deleteBtn.setBounds(109, 450, 89, 23);
		actionBtnPanel.add(deleteBtn);
		
		JButton exportBtn = new JButton("ส่งออก");
		exportBtn.setBounds(208, 450, 89, 23);
		actionBtnPanel.add(exportBtn);
		
		
		
		// addTask 
		// ให้เขียน logic สำหรับ addTask ตรงนี้
		// -- table.addTask(Object); การเรียกใช้ addTask -----> TaskTableModel Class
		
//		Task task = new SingleTask("Hello", "HIGH", "General");
//		taskManager.addTask(task);
		
		
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
		
		JButton sortNameBtn = new JButton("ชื่องาน");
		sortNameBtn.setBounds(75, 478, 64, 23);
		sortBtnPanel.add(sortNameBtn);
		
		JButton sortPriBtn = new JButton("ความสำคัญ");
		sortPriBtn.setBounds(149, 478, 110, 23);
		sortBtnPanel.add(sortPriBtn);
		
		JButton sortDateBtn = new JButton("วันที่ครบกำหนด");
		sortDateBtn.setBounds(269, 478, 133, 23);
		sortBtnPanel.add(sortDateBtn);
		
		
		
		
		
		
		
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
