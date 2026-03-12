package com.pomodoro.ui.MenuUI;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class trashMenuDesign extends JPanel{

	public trashMenuDesign() {
		setBounds(113, 5, 601, 551);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 567, 456);
		add(scrollPane);
		
		JPanel btnPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		btnPanel.setBounds(10, 478, 567, 62);
		add(btnPanel);
		
		JButton deleteBtn = new JButton("Delete");
		btnPanel.add(deleteBtn);
		
		JButton restoreBtn = new JButton("Restore");
		btnPanel.add(restoreBtn);
	}

}
