package com.pomodoro.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

<<<<<<< HEAD
import com.pomodoro.logic.PomodoroTimer;
import com.pomodoro.logic.TaskManager;
import com.pomodoro.model.Category;
import com.pomodoro.model.Priority;
import com.pomodoro.model.RepeatingTask;
import com.pomodoro.model.SingleTask;
import com.pomodoro.model.Task;
import com.pomodoro.model.TaskStatus;
=======
//import com.pomodoro.logic.PomodoroTimer;
import com.pomodoro.logic.TaskManager;
import com.pomodoro.model.Task;
>>>>>>> b6ecdd5 (all systems)
import com.pomodoro.ui.MenuUI.trashMenuDesign;
import com.pomodoro.ui.MenuUI.homeMenuDesign;
import com.pomodoro.ui.MenuUI.settingMenuDesign;
import com.pomodoro.ui.MenuUI.taskMenuDesign;
import javax.swing.BoxLayout;

public class MainWindowDesign extends JFrame {

    private static final long serialVersionUID = 1L;

    // --- Fields (WindowBuilder) ---
    private JPanel contentPane;

//    // UI Components
//    private JLabel timerLabel;
//    private JButton startButton;
//    private JButton pauseButton;
//    private JButton stopButton;
//    private JList<Task> taskList;
//    private DefaultListModel<Task> listModel;

//    // Logic
//    private TaskManager taskManager;
//    private PomodoroTimer pomodoroTimer;

//    // Date formatters
//    private static final SimpleDateFormat DATE_FMT = new SimpleDateFormat("dd/MM/yyyy");
//    private static final SimpleDateFormat DATETIME_FMT = new SimpleDateFormat("HH:mm");

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {	
                try {
                    MainWindowDesign frame = new MainWindowDesign();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainWindowDesign() {
<<<<<<< HEAD

        // เซ็ตให้เป็นภาษาไทย
=======
    	
    	// -- การเข้าถึง class อื่นๆ
    	TaskManager taskManager = new TaskManager();
        PomodoroTimer pomodoro = new PomodoroTimer(taskManager);
        
        // เปลี่ยนหน้าตาให้รองรับภาษาไทย
>>>>>>> b6ecdd5 (all systems)
        Font thaiFont = new Font("Tahoma", Font.PLAIN, 14);

        UIManager.put("Label.font", thaiFont);
        UIManager.put("Panel.font", thaiFont);
        UIManager.put("Button.font", thaiFont);
        UIManager.put("TextField.font", thaiFont);
        UIManager.put("TextArea.font", thaiFont);
        UIManager.put("ComboBox.font", thaiFont);
        UIManager.put("List.font", thaiFont);
        UIManager.put("CheckBox.font", thaiFont);
        UIManager.put("MenuItem.font", thaiFont);
        UIManager.put("ToolTip.font", thaiFont);
        UIManager.put("RadioButton.font", thaiFont);
        UIManager.put("Table.font", thaiFont);
        UIManager.put("TableHeader.font", thaiFont);
        UIManager.put("Menu.font", thaiFont);
        UIManager.put("MenuItem.font", thaiFont);

        UIManager.put("OptionPane.messageFont", thaiFont);
        UIManager.put("OptionPane.buttonFont", thaiFont);


        // ===============================================================
        // JFrame Setting
        // ===============================================================
        setTitle("TaskMaster");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 1200, 600);
        setResizable(false); //ล็อคขนาดจอไว้
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===============================================================
        // Content Panel
        // ===============================================================
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        
        // ===============================================================
        // CardLayout สำหรับสลับ Menu // ContentPanel (Center)
        // ===============================================================
        CardLayout cardLayout = new CardLayout(); // CardLayout
        JPanel contentPanel = new JPanel(cardLayout); // ContentPanel
        contentPanel.setBounds(113, 5, 780, 551);
        contentPanel.setPreferredSize(new Dimension(780, 0));
        
        contentPanel.setBorder(BorderFactory.createTitledBorder("Home")); // Default
        
        contentPane.add(contentPanel, BorderLayout.CENTER);
<<<<<<< HEAD
        
        // ===============================================================
        // MENU PANEL (WEST)
        // ===============================================================
=======

        // ใช้งานตัวจัดการงานร่วมกันในทุกหน้าย่อย
        TaskManager sharedTaskManager = new TaskManager();
        taskMenuDesign taskMenu = new taskMenuDesign(sharedTaskManager , pomodoro);
        trashMenuDesign trashMenu = new trashMenuDesign(sharedTaskManager);
        homeMenuDesign home = new homeMenuDesign(sharedTaskManager);

        // แถบเมนูซ้ายมือ
>>>>>>> b6ecdd5 (all systems)
        JPanel menuPanel = new JPanel();
        menuPanel.setBounds(5, 5, 160, 551);
        menuPanel.setBorder(BorderFactory.createTitledBorder("Menu"));
        menuPanel.setPreferredSize(new Dimension(160, 0));
        contentPane.add(menuPanel, BorderLayout.WEST);
        
        JPanel Menu = new JPanel();
        
        // ** Home button **
        JButton btnHome = new JButton("Home");
        btnHome.setHorizontalAlignment(SwingConstants.LEFT);
        btnHome.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnHome.getPreferredSize().height));
        
        // กดปุ่มเพื่อสลับหน้า
        btnHome.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cardLayout.show(contentPanel, "HOME");
        		contentPanel.setBorder(BorderFactory.createTitledBorder("Home"));
        	}
        });
        menuPanel.setLayout(new BorderLayout(0, 0));
        Menu.setLayout(new BoxLayout(Menu, BoxLayout.Y_AXIS));
        Menu.add(btnHome);
        
        // ** Task button **
        JButton btnTask = new JButton("Task");
        btnTask.setHorizontalAlignment(SwingConstants.LEFT);
        btnTask.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnHome.getPreferredSize().height));
        
        // กดปุ่มเพื่อสลับหน้า
        btnTask.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cardLayout.show(contentPanel, "TASK");
        		contentPanel.setBorder(BorderFactory.createTitledBorder("Task"));
        	}
        });
        Menu.add(btnTask);
        
        // ** Trash button **
        JButton btnTrash = new JButton("Trash");
        btnTrash.setHorizontalAlignment(SwingConstants.LEFT);
        btnTrash.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnHome.getPreferredSize().height));
        
        btnTrash.addActionListener(new ActionListener() {
        	
        	// กดปุ่มเพื่อสลับหน้า
        	public void actionPerformed(ActionEvent e) {
        		cardLayout.show(contentPanel, "TRASH");
        		contentPanel.setBorder(BorderFactory.createTitledBorder("Trash"));
        	}
        });
        Menu.add(btnTrash);
        
        // ** Setting button **
        JButton btnSetting = new JButton("Setting");
        btnSetting.setHorizontalAlignment(SwingConstants.LEFT);
        btnSetting.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnHome.getPreferredSize().height));
        
        // กดปุ่มเพื่อสลับหน้า
        btnSetting.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cardLayout.show(contentPanel, "SETTING");
        		contentPanel.setBorder(BorderFactory.createTitledBorder("Setting"));
        	}
        });
        Menu.add(btnSetting);
        
        menuPanel.add(Menu, BorderLayout.NORTH);
<<<<<<< HEAD
        
        // ** CardLayout Content Menu **
        taskMenuDesign taskMenu = new taskMenuDesign();
        contentPanel.add(taskMenu , "TASK");
        
        homeMenuDesign home = new homeMenuDesign();
        contentPanel.add(home , "HOME");
        
        trashMenuDesign trashMenu = new trashMenuDesign();
        contentPanel.add(trashMenu , "TRASH");
        
        settingMenuDesign settingMenu = new settingMenuDesign();
        contentPanel.add(settingMenu , "SETTING");
        
        
        // ===============================================================
        // TIMER PANEL (EAST)
        // ===============================================================
        pomodoroTimer pomodoro = new pomodoroTimer();
        
=======

        // สร้างแต่ละหน้าจอผูกกับข้อความ
        contentPanel.add(taskMenu, "TASK");
        contentPanel.add(home, "HOME");

        contentPanel.add(trashMenu, "TRASH");

        settingMenuDesign settingMenu = new settingMenuDesign(pomodoro);
        contentPanel.add(settingMenu, "SETTING");

        // แถบจับเวลาด้านขวา
>>>>>>> b6ecdd5 (all systems)
        JPanel timerPanel = new JPanel();
        contentPane.add(timerPanel, BorderLayout.EAST);
        timerPanel.setBounds(719, 5, 260, 551);
        timerPanel.setPreferredSize(new Dimension(260, 0));
        timerPanel.setBorder(BorderFactory.createTitledBorder("Pomodoro Timer"));
        timerPanel.setLayout(new BorderLayout(0, 0));
        timerPanel.add(pomodoro);
        
    }
        

        // ===============================================================
        // LOGIC
        // ===============================================================
//        taskManager = new TaskManager();
//        pomodoroTimer = new PomodoroTimer(
//                new Consumer<String>() {
//                    @Override
//                    public void accept(final String formattedTime) {
//                        SwingUtilities.invokeLater(new Runnable() {
//                            @Override
//                            public void run() {
//                                timerLabel.setText(formattedTime);
//                            }
//                        });
//                    }
//                },
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        SwingUtilities.invokeLater(new Runnable() {
//                            @Override
//                            public void run() {
//                                Task completed = pomodoroTimer.getActiveTask();
//                                if (completed != null) {
//                                    taskManager.updateTask(completed);
//                                }
//                                JOptionPane.showMessageDialog(MainWindowDesign.this, "หมดเวลา! เก็บ focus time แล้ว");
//                                refreshTaskList();
//                                updateButtons();
//                            }
//                        });
//                    }
//                },
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        SwingUtilities.invokeLater(new Runnable() {
//                            @Override
//                            public void run() {
//                                updateButtons();
//                            }
//                        });
//                    }
//                });
//        pomodoroTimer.setOnFocusMinuteAdded(new Runnable() {
//            @Override
//            public void run() {
//                SwingUtilities.invokeLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        Task active = pomodoroTimer.getActiveTask();
//                        if (active != null) {
//                            taskManager.updateTask(active);
//                            int idx = listModel.indexOf(active);
//                            if (idx >= 0) {
//                                listModel.setElementAt(active, idx);
//                            }
//                        }
//                    }
//                });
//            }
//        });
//
//        // ===============================================================
//        // EVENT LISTENERS
//        // ===============================================================
//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                pomodoroTimer.shutdown();
//                taskManager.close();
//                dispose();
//            }
//        });
//
//        startButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                startTimer();
//            }
//        });
//        pauseButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                pomodoroTimer.pause();
//            }
//        });
//        stopButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                pomodoroTimer.stop();
//                refreshTaskList();
//            }
//        });
//
//        // ===============================================================
//        // INITIAL STATE
//        // ===============================================================
//        updateButtons();
//    }

    // ===================================================================
    // DIALOG: เพิ่มงาน (ครบทุก field)
    // ===================================================================

//    private void showAddTaskDialog() {
//        final JTextField titleField = new JTextField(20);
//        final JTextArea descField = new JTextArea(3, 20);
//        final JComboBox<Priority> priorityBox = new JComboBox<Priority>(Priority.values());
//        final JComboBox<Category> categoryBox = new JComboBox<Category>();
//        for (Category c : taskManager.getCategories()) {
//            categoryBox.addItem(c);
//        }
//        final JTextField dueDateField = new JTextField(10);
//        final JTextField reminderField = new JTextField(16);
//        final JCheckBox repeatCheck = new JCheckBox("งานซ้ำ");
//        final JTextField intervalField = new JTextField("7", 5);
//        intervalField.setEnabled(false);
//
//        repeatCheck.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                intervalField.setEnabled(repeatCheck.isSelected());
//            }
//        });
//
//        JPanel repeatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
//        repeatPanel.add(repeatCheck);
//        repeatPanel.add(new JLabel("ทุก"));
//        repeatPanel.add(intervalField);
//        repeatPanel.add(new JLabel("วัน"));
//
//        JPanel form = new JPanel(new GridLayout(0, 2, 5, 5));
//        form.add(new JLabel("ชื่องาน:"));
//        form.add(titleField);
//        form.add(new JLabel("คำอธิบาย:"));
//        form.add(new JScrollPane(descField));
//        form.add(new JLabel("ความสำคัญ:"));
//        form.add(priorityBox);
//        form.add(new JLabel("หมวดหมู่:"));
//        form.add(categoryBox);
//        form.add(new JLabel("วันครบกำหนด (dd/MM/yyyy):"));
//        form.add(dueDateField);
//        form.add(new JLabel("เวลาแจ้งเตือน (HH:mm):")); //แก้ไข
//        form.add(reminderField);
//        form.add(new JLabel("ประเภท:"));
//        form.add(repeatPanel);
//
//        int result = JOptionPane.showConfirmDialog(
//                this, form, "เพิ่มงานใหม่", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//
//        if (result == JOptionPane.OK_OPTION) {
//            String title = titleField.getText().trim();
//            if (!title.isEmpty()) {
//                Priority priority = (Priority) priorityBox.getSelectedItem();
//                Category category = (Category) categoryBox.getSelectedItem();
//
//                Task task;
//                if (repeatCheck.isSelected()) {
//                    int intervalDays = parseIntervalDays(intervalField.getText().trim());
//                    task = new RepeatingTask(title, priority, category, intervalDays, new Date());
//                } else {
//                    task = new SingleTask(title, priority, category);
//                }
//
//                task.setDescription(descField.getText().trim());
//                task.setDueDate(parseDateField(dueDateField.getText().trim(), DATE_FMT));
//                task.setReminderTime(parseDateField(reminderField.getText().trim(), DATETIME_FMT));
//
//                taskManager.addTask(task);
//                refreshTaskList();
//            }
//        }
//    }
//
//    // ===================================================================
//    // DIALOG: แก้ไขงาน
//    // ===================================================================
//
//    private void showEditTaskDialog() {
//        Task selected = taskList.getSelectedValue();
//        if (selected == null) {
//            JOptionPane.showMessageDialog(this, "กรุณาเลือกงานที่ต้องการแก้ไขก่อน");
//            return;
//        }
//
//        final JTextField titleField = new JTextField(selected.getTitle(), 20);
//        final JTextArea descField = new JTextArea(
//                selected.getDescription() != null ? selected.getDescription() : "", 3, 20);
//        final JComboBox<Priority> priorityBox = new JComboBox<Priority>(Priority.values());
//        priorityBox.setSelectedItem(selected.getPriority());
//
//        final JComboBox<Category> categoryBox = new JComboBox<Category>();
//        for (Category c : taskManager.getCategories()) {
//            categoryBox.addItem(c);
//        }
//        if (selected.getCategory() != null) {
//            categoryBox.setSelectedItem(selected.getCategory());
//        }
//
//        final JComboBox<TaskStatus> statusBox = new JComboBox<TaskStatus>(TaskStatus.values());
//        statusBox.setSelectedItem(selected.getStatus());
//        
//
//        String dueDateStr = selected.getDueDate() != null
//                ? DATE_FMT.format(selected.getDueDate())
//                : "";
//        String reminderStr = selected.getReminderTime() != null
//                ? DATETIME_FMT.format(selected.getReminderTime())
//                : "";
//
//        final JTextField dueDateField = new JTextField(dueDateStr, 10);
//        final JTextField reminderField = new JTextField(reminderStr, 16);
//
//        // Repeat fields
//        boolean isRepeating = selected instanceof RepeatingTask;
//        final JCheckBox repeatCheck = new JCheckBox("งานซ้ำ", isRepeating);
//        final JTextField intervalField = new JTextField(
//                isRepeating ? String.valueOf(((RepeatingTask) selected).getIntervalDays()) : "7", 5);
//        intervalField.setEnabled(isRepeating);
//
//        repeatCheck.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                intervalField.setEnabled(repeatCheck.isSelected());
//            }
//        });
//
//        JPanel repeatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
//        repeatPanel.add(repeatCheck);
//        repeatPanel.add(new JLabel("ทุก"));
//        repeatPanel.add(intervalField);
//        repeatPanel.add(new JLabel("วัน"));
//
//        JPanel form = new JPanel(new GridLayout(0, 2, 5, 5));
//        form.add(new JLabel("ชื่องาน:"));
//        form.add(titleField);
//        form.add(new JLabel("คำอธิบาย:"));
//        form.add(new JScrollPane(descField));
//        form.add(new JLabel("ความสำคัญ:"));
//        form.add(priorityBox);
//        form.add(new JLabel("หมวดหมู่:"));
//        form.add(categoryBox);
//        form.add(new JLabel("สถานะ:"));
//        form.add(statusBox);
//        form.add(new JLabel("วันครบกำหนด (dd/MM/yyyy):"));
//        form.add(dueDateField);
//        form.add(new JLabel("เวลาแจ้งเตือน (HH:mm):")); //แก้ไข
//        form.add(reminderField);
//        form.add(new JLabel("ประเภท:"));
//        form.add(repeatPanel);
//
//        int result = JOptionPane.showConfirmDialog(
//                this, form, "แก้ไขงาน", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//
//        if (result == JOptionPane.OK_OPTION) {
//            String newTitle = titleField.getText().trim();
//            if (!newTitle.isEmpty()) {
//                selected.setTitle(newTitle);
//                selected.setDescription(descField.getText().trim());
//                selected.setPriority((Priority) priorityBox.getSelectedItem());
//                selected.setCategory((Category) categoryBox.getSelectedItem());
//                selected.setStatus((TaskStatus) statusBox.getSelectedItem());
//                selected.setDueDate(parseDateField(dueDateField.getText().trim(), DATE_FMT));
//                selected.setReminderTime(parseDateField(reminderField.getText().trim(), DATETIME_FMT));
//
//                taskManager.updateTask(selected);
//                refreshTaskList();
//            }
//        }
//    }
//
//    private Date parseDateField(String text, SimpleDateFormat fmt) {
//        if (text == null || text.isEmpty())
//            return null;
//        try {
//            fmt.setLenient(false);
//            return fmt.parse(text);
//        } catch (ParseException ex) {
//            JOptionPane.showMessageDialog(this,
//                    "รูปแบบวันที่ไม่ถูกต้อง: " + text,
//                    "คำเตือน", JOptionPane.WARNING_MESSAGE);
//            return null;
//        }
//    }
//
//    private int parseIntervalDays(String text) {
//        try {
//            int val = Integer.parseInt(text);
//            if (val > 0)
//                return val;
//        } catch (NumberFormatException ex) {
//            /* ignore */ }
//        JOptionPane.showMessageDialog(this,
//                "จำนวนวันต้องเป็นตัวเลขมากกว่า 0 ใช้ค่าตั้งต้น 7 วัน",
//                "คำเตือน", JOptionPane.WARNING_MESSAGE);
//        return 7;
//    }
//
//    private void exportTasks() {
//        // TODO: implement export functionality
//    }
//
//    private void deleteSelectedTask() {
//        Task selected = taskList.getSelectedValue();
//        if (selected == null) {
//            JOptionPane.showMessageDialog(this, "กรุณาเลือกงานที่ต้องการลบก่อน");
//            return;
//        }
//        int confirm = JOptionPane.showConfirmDialog(
//                this, "ลบงาน \"" + selected.getTitle() + "\" ?",
//                "ยืนยันการลบ", JOptionPane.YES_NO_OPTION);
//        if (confirm == JOptionPane.YES_OPTION) {
//            Task active = pomodoroTimer.getActiveTask();
//            if (active != null && active.getId().equals(selected.getId())) {
//                pomodoroTimer.stop();
//            }
//            taskManager.removeTask(selected);
//            refreshTaskList();
//        }
//    }
//
//    private void changeSelectedTaskStatus(TaskStatus newStatus) {
//        Task selected = taskList.getSelectedValue();
//        if (selected == null)
//            return;
//        selected.setStatus(newStatus);
//        taskManager.updateTask(selected);
//        refreshTaskList();
//    }
//
//    private void startTimer() {
//        Task selected = taskList.getSelectedValue();
//        if (selected == null) {
//            JOptionPane.showMessageDialog(this, "กรุณาเลือกงานจากรายการก่อนกด Start");
//            return;
//        }
//        if (pomodoroTimer.isPaused()) {
//            Task active = pomodoroTimer.getActiveTask();
//            if (active != null && !active.getId().equals(selected.getId())) {
//                pomodoroTimer.stop();
//            }
//        }
//        pomodoroTimer.start(selected);
//        refreshTaskList();
//    }
//
//    private void refreshTaskList() {
//        Task previously = taskList.getSelectedValue();
//        listModel.clear();
//        for (Task t : taskManager.getAllTasks()) {
//            listModel.addElement(t);
//        }
//        if (previously != null) {
//            int idx = -1;
//            for (int i = 0; i < listModel.size(); i++) {
//                if (listModel.get(i).getId().equals(previously.getId())) {
//                    idx = i;
//                    break;
//                }
//            }
//            if (idx >= 0) {
//                taskList.setSelectedIndex(idx);
//            }
//        }
//    }
//
//    private void updateButtons() {
//        boolean running = pomodoroTimer.isRunning();
//        boolean paused = pomodoroTimer.isPaused();
//        startButton.setEnabled(!running);
//        pauseButton.setEnabled(running);
//        stopButton.setEnabled(running || paused);
//    }
//
//    private static class TaskCellRenderer extends javax.swing.DefaultListCellRenderer {
//        private static final long serialVersionUID = 1L;
//        private static final SimpleDateFormat DT = new SimpleDateFormat("dd/MM HH:mm");
//        private static final SimpleDateFormat DATE = new SimpleDateFormat("dd/MM/yyyy");
//        
//        // สี due date urgency
//        private static final java.awt.Color COLOR_OVERDUE = new java.awt.Color(255, 180, 180); // แดงอ่อน
//        private static final java.awt.Color COLOR_TODAY = new java.awt.Color(255, 210, 140); // ส้มอ่อน
//        private static final java.awt.Color COLOR_SOON = new java.awt.Color(255, 245, 180); // เหลืองอ่อน
//
//        // สี priority สำหรับ text
//        private static final java.awt.Color PRI_HIGH = new java.awt.Color(180, 0, 0); // แดงเข้ม
//        private static final java.awt.Color PRI_MEDIUM = new java.awt.Color(160, 100, 0); // น้ำตาลส้ม
//        private static final java.awt.Color PRI_LOW = new java.awt.Color(0, 120, 0); // เขียว
//
//        @Override
//        public java.awt.Component getListCellRendererComponent(
//                JList<?> list, Object value, int index, boolean isSelected, boolean hasFocus) {
//            super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
//            if (value instanceof Task) {
//                Task t = (Task) value;
//                String categoryName = (t.getCategory() != null) ? t.getCategory().getName() : "-";
//
//                // reminder preview
//                String reminderStr = "";
//                if (t.getReminderTime() != null) {
//                    reminderStr = "  ⏰" + DT.format(t.getReminderTime());
//                }
//
//                // description preview
//                String desc = t.getDescription();
//                String descPreview = "";
//                if (desc != null && !desc.isEmpty()) {
//                    descPreview = "  | " + (desc.length() > 25 ? desc.substring(0, 25) + "..." : desc);
//                }
//
//                // priorityใน text
//                String priBadge = "";
//                com.pomodoro.model.Priority pri = t.getPriority();
//                if (pri == com.pomodoro.model.Priority.HIGH)
//                    priBadge = "🔴";
//                else if (pri == com.pomodoro.model.Priority.MEDIUM)
//                    priBadge = "🟡";
//                else
//                    priBadge = "🟢";
//
//                setText(String.format("%s [%s] %s  (%s)  [%s]  focus: %d min%s%s",
//                        priBadge, t.getStatus(), t.getTitle(), t.getPriority(),
//                        categoryName, t.getTotalFocusMinutes(), reminderStr, descPreview));
//
//                if (!isSelected) {
//                    java.awt.Color bg = getBackground(); // default
//                    if (t.getCategory() != null) {
//                        String colorCode = t.getCategory().getColorCode();
//                        if (colorCode != null && colorCode.startsWith("#")) {
//                            try {
//                                java.awt.Color base = java.awt.Color.decode(colorCode);
//                                bg = new java.awt.Color(
//                                        (base.getRed() + 255 * 3) / 4,
//                                        (base.getGreen() + 255 * 3) / 4,
//                                        (base.getBlue() + 255 * 3) / 4);
//                            } catch (NumberFormatException ex) {
//                                /* ข้าม */ }
//                        }
//                    }
//
//                    
//                    // Deadline
//                    if (t.getDueDate() != null) {
//                        long now = System.currentTimeMillis();
//                        long due = t.getDueDate().getTime();
//                        long diffDays = (due - now) / (1000 * 60 * 60 * 24); //แก้เวลา
//
//                    	if (diffDays < 0) {
//                            bg = COLOR_OVERDUE; // เกินวันแล้ว แดง
//                        } else if (diffDays == 0) {
//                            bg = COLOR_TODAY; // วันนี้ ส้ม
//                        } else if (diffDays <= 3) {
//                            bg = COLOR_SOON; // <= 3 วัน -> เหลือง
//                        }
//                    }
//
//                    setBackground(bg);
//                    
//                    if (t.getReminderTime() != null) {
//                    	// ใส่ logic ตรงนี้ 
//                    	// เช็ควิธีเวลาที่ตั้งค่าและเวลาในวันนี้
//                    	// ทำแจ้งเตือนขึ้นหน้าจอ
//                    }
//                    
//                    // --- Priority text color ---
//                    if (pri == com.pomodoro.model.Priority.HIGH)
//                        setForeground(PRI_HIGH);
//                    else if (pri == com.pomodoro.model.Priority.MEDIUM)
//                        setForeground(PRI_MEDIUM);
//                    else
//                        setForeground(PRI_LOW);
//                }
//
//                // Tooltip
//                StringBuilder tip = new StringBuilder("<html>");
//                if (desc != null && !desc.isEmpty()) {
//                    tip.append("<b>Description:</b><br>")
//                            .append(desc.replace("\n", "<br>")).append("<br>");
//                }
//                if (t.getDueDate() != null) {
//                    long diffDays = (t.getDueDate().getTime() - System.currentTimeMillis()) / (1000 * 60 * 60 * 24);
//                    String urgency = diffDays < 0 ? " ⚠️ เกินกำหนด!" : diffDays == 0 ? " ⚠️ วันนี้!" : "";
//                    tip.append("<b>Due:</b> ").append(DATE.format(t.getDueDate())).append(urgency).append("<br>");
//                }
//                if (t.getReminderTime() != null) {
//                    tip.append("<b>Reminder:</b> ").append(DT.format(t.getReminderTime()));
//                }
//                tip.append("</html>");
//                setToolTipText(tip.length() > 13 ? tip.toString() : null);
//            }
//            return this;
//        }
//    }
}
