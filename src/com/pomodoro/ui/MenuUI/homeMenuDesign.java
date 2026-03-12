package com.pomodoro.ui.MenuUI;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class homeMenuDesign extends JPanel{

	public homeMenuDesign() {
		setLayout(null);
		setBounds(113, 5, 601, 551);
		
		JLabel todayLabel = new JLabel("Today's Tasks");
		todayLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		todayLabel.setBounds(10, 11, 122, 28);
		add(todayLabel);
		
		JLabel deadlineLabel = new JLabel("Upcoming Deadlines");
		deadlineLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		deadlineLabel.setBounds(10, 167, 221, 28);
		add(deadlineLabel);
		
		JLabel focusLabel = new JLabel("Focus Statistics");
		focusLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		focusLabel.setBounds(10, 331, 122, 28);
		add(focusLabel);
	}
}
