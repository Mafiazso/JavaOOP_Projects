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

<<<<<<< HEAD
=======
    private TaskManager taskManager;
    private TaskTableModel tableModel;

    public trashMenuDesign(TaskManager taskManager) {
        this.taskManager = taskManager;

        setBounds(113, 5, 780, 551);
        setLayout(null);

        // ตารางแสดงข้อมูลงาน
        tableModel = new TaskTableModel(taskManager);
        tableModel.setBounds(10, 11, 725, 415);
        add(tableModel);
        tableModel.reloadTrashTable();

        JTable rawTable = tableModel.getTable();

        // เมนูคลิกขวา
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuRestore = new JMenuItem("กู้คืนงาน");
        JMenuItem menuDelete = new JMenuItem("ลบถาวร");
        popupMenu.add(menuRestore);
        popupMenu.add(menuDelete);

        rawTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger())
                    showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger())
                    showPopup(e);
            }

            private void showPopup(MouseEvent e) {
                int row = rawTable.rowAtPoint(e.getPoint());
                if (row >= 0)
                    rawTable.setRowSelectionInterval(row, row);
                else
                    rawTable.clearSelection();
                popupMenu.show(rawTable, e.getX(), e.getY());
            }
        });

        tableModel.getViewport().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger())
                    handleViewportPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger())
                    handleViewportPopup(e);
            }

            private void handleViewportPopup(MouseEvent e) {
                java.awt.Point pt = javax.swing.SwingUtilities.convertPoint(
                        tableModel.getViewport(), e.getPoint(), rawTable);
                int row = rawTable.rowAtPoint(pt);
                if (row >= 0)
                    rawTable.setRowSelectionInterval(row, row);
                popupMenu.show(rawTable, pt.x, pt.y);
            }
        });

        // เชื่อมปุ่มกด
        menuRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                doRestore(rawTable);
            }
        });
        menuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                doDelete(rawTable);
            }
        });

        // ส่วนปุ่มกดต่างๆ
        JPanel btnPanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        btnPanel.setBounds(10, 436, 724, 62);
        add(btnPanel);

        JButton deleteBtn = new JButton("ลบงานถาวร");
        btnPanel.add(deleteBtn);

        JButton restoreBtn = new JButton("กู้คืนงาน");
        btnPanel.add(restoreBtn);

        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                doDelete(rawTable);
            }
        });
        restoreBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                doRestore(rawTable);
            }
        });
    }

    // คำสั่งส่วนกลางที่ใช้ร่วมกันทั้งปุ่มกดและเมนูคลิกขวา
    private void doDelete(JTable rawTable) {
        int row = rawTable.getSelectedRow();
        Task selected = tableModel.getTrashTaskAt(row);
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "กรุณาเลือกงานที่ต้องการลบก่อน");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "ลบงาน \"" + selected.getTitle() + "\" ถาวร? ไม่สามารถกู้คืนได้",
                "ยืนยันการลบถาวร",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            taskManager.deleteForever(selected);
            tableModel.reloadTrashTable();
        }
    }

    private void doRestore(JTable rawTable) {
        int row = rawTable.getSelectedRow();
        Task selected = tableModel.getTrashTaskAt(row);
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "กรุณาเลือกงานที่ต้องการกู้คืนก่อน");
            return;
        }
        taskManager.restoreTask(selected);
        taskManager.updateTask(selected);
        tableModel.reloadTrashTable();
        JOptionPane.showMessageDialog(this, "กู้คืนงาน \"" + selected.getTitle() + "\" เรียบร้อย!");
    }

    // เรียกใช้เมื่อสลับมาหน้าถังขยะเพื่อให้ข้อมูลอัปเดตใหม่เสมอ
    public void reloadTrash() {
        tableModel.reloadTrashTable();
    }
>>>>>>> b6ecdd5 (all systems)
}
