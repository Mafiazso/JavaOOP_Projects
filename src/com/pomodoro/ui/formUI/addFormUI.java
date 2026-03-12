package com.pomodoro.ui.formUI;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

public class addFormUI extends JDialog{
	private JTextField nameField;
	private JTextField descripField;
	private JTextField dueDateField;
	private JTextField textField_3;

	public addFormUI(Frame parent) {
		
		super(parent, "Add Task");
		
		setSize(508,372);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		
		// ===============================================================
        // Form
        // ===============================================================
		
		JPanel formPanel = new JPanel();
		getContentPane().add(formPanel);
		formPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		formPanel.setLayout(new GridLayout(0, 2, 10, 10));
		
		// ** Name **
		JLabel nameLabel = new JLabel("ชื่องาน");
		formPanel.add(nameLabel);
		
		nameField = new JTextField();
		formPanel.add(nameField);
		nameField.setColumns(10);
		
		// ** Description **
		JLabel descripLabel = new JLabel("คำอธิบาย");
		formPanel.add(descripLabel);
		
		descripField = new JTextField();
		descripField.setColumns(10);
		formPanel.add(descripField);
		
		// ** Priority **
		JLabel prioLabel = new JLabel("ความสำคัญ");
		formPanel.add(prioLabel);
		
		JComboBox prioComboBox = new JComboBox();
		prioComboBox.setModel(new DefaultComboBoxModel(new String[] {"HIGH", "MEDIUM", "LOW"}));
		formPanel.add(prioComboBox);
		
		// ** Category **
		JLabel cateLabel = new JLabel("หมวดหมู่");
		formPanel.add(cateLabel);
		
		JComboBox cateComboBox = new JComboBox();
		cateComboBox.setModel(new DefaultComboBoxModel(new String[] {"General", "Study", "Work"}));
		formPanel.add(cateComboBox);
		
		// ** DueDate **
		JLabel dueDateLabel = new JLabel("วันครบกำหนด (dd/MM/yyyy)");
		formPanel.add(dueDateLabel);
		
		dueDateField = new JTextField();
		dueDateField.setColumns(10);
		formPanel.add(dueDateField);
		
		// ** Notification **
		JLabel notiLabel = new JLabel("แจ้งเตือน (HH:mm)");
		formPanel.add(notiLabel);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		formPanel.add(textField_3);
		
		
		
		// ===============================================================
        // Button 
        // ===============================================================
		
		JPanel btnPanel = new JPanel();
		getContentPane().add(btnPanel);
		
		JButton saveBtn = new JButton("Save");
		btnPanel.add(saveBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		btnPanel.add(cancelBtn);
		
	}

}
