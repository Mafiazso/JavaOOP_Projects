package com.pomodoro.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.pomodoro.logic.PomodoroTimer;
import com.pomodoro.logic.TaskManager;
import com.pomodoro.model.Category;
import com.pomodoro.model.Priority;
import com.pomodoro.model.RepeatingTask;
import com.pomodoro.model.SingleTask;
import com.pomodoro.model.Task;
import com.pomodoro.model.TaskStatus;

public class MainWindowDesign extends JFrame {

	private static final long serialVersionUID = 1L;

	// --- Fields (WindowBuilder) ---
	private JPanel contentPane;

	// UI Components
	private JLabel timerLabel;
	private JButton startButton;
	private JButton pauseButton;
	private JButton stopButton;
	private JList<Task> taskList;
	private DefaultListModel<Task> listModel;

	// Logic
	private TaskManager taskManager;
	private PomodoroTimer pomodoroTimer;

	// Date formatters
	private static final SimpleDateFormat DATE_FMT = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat DATETIME_FMT = new SimpleDateFormat("dd/MM/yyyy HH:mm");

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
		// --- ตั้งค่า JFrame ---
		setTitle("Pomodoro Timer");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 720, 500);

		// --- ContentPanel หลัก ---
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(5, 5));
		setContentPane(contentPane);

		// ===============================================================
		// TASK PANEL (CENTER)
		// ===============================================================
		JPanel taskPanel = new JPanel(new BorderLayout(5, 5));
		taskPanel.setBorder(BorderFactory.createTitledBorder("รายการงาน"));
		taskPanel.setPreferredSize(new Dimension(420, 0));
		contentPane.add(taskPanel, BorderLayout.CENTER);

		// Task List
		listModel = new DefaultListModel<Task>();
		taskList = new JList<Task>(listModel);
		taskList.setCellRenderer(new TaskCellRenderer());
		taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane taskScrollPane = new JScrollPane(taskList);
		taskPanel.add(taskScrollPane, BorderLayout.CENTER);

		// Right-click context menu
		JPopupMenu popupMenu = new JPopupMenu();

		JMenuItem menuEdit = new JMenuItem("✏️ แก้ไขงาน");
		JMenuItem menuDelete = new JMenuItem("🗑️ ลบงาน");
		JSeparator separator = new JSeparator();
		JMenuItem menuTodo = new JMenuItem("📋 To Do");
		JMenuItem menuInProgress = new JMenuItem("🔄 In Progress");
		JMenuItem menuDone = new JMenuItem("✅ Done");

		popupMenu.add(menuEdit);
		popupMenu.add(menuDelete);
		popupMenu.add(separator);
		popupMenu.add(menuTodo);
		popupMenu.add(menuInProgress);
		popupMenu.add(menuDone);

		taskList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					int idx = taskList.locationToIndex(e.getPoint());
					if (idx >= 0) {
						taskList.setSelectedIndex(idx);
						popupMenu.show(taskList, e.getX(), e.getY());
					}
				}
			}
		});

		menuEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showEditTaskDialog();
			}
		});
		menuDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteSelectedTask();
			}
		});
		menuTodo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeSelectedTaskStatus(TaskStatus.TODO);
			}
		});
		menuInProgress.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeSelectedTaskStatus(TaskStatus.IN_PROGRESS);
			}
		});
		menuDone.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeSelectedTaskStatus(TaskStatus.DONE);
			}
		});

		// Bottom panel (Action + Sort buttons)
		JPanel bottomPanel = new JPanel(new BorderLayout());
		taskPanel.add(bottomPanel, BorderLayout.SOUTH);

		// Action buttons row
		JPanel actionButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		bottomPanel.add(actionButtons, BorderLayout.NORTH);

		JButton addBtn = new JButton("เพิ่มงาน");
		actionButtons.add(addBtn);

		JButton deleteBtn = new JButton("ลบงาน");
		actionButtons.add(deleteBtn);

		JButton exportBtn = new JButton("ส่งออก");
		actionButtons.add(exportBtn);

		// Sort buttons row
		JPanel sortButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		bottomPanel.add(sortButtons, BorderLayout.SOUTH);

		JLabel sortLabel = new JLabel("เรียงตาม:");
		sortButtons.add(sortLabel);

		JButton sortNameBtn = new JButton("ชื่อ");
		sortButtons.add(sortNameBtn);

		JButton sortPriorityBtn = new JButton("ความสำคัญ");
		sortButtons.add(sortPriorityBtn);

		JButton sortDateBtn = new JButton("วันครบกำหนด");
		sortButtons.add(sortDateBtn);

		// ===============================================================
		// TIMER PANEL (EAST)
		// ===============================================================
		JPanel timerPanel = new JPanel(new BorderLayout(5, 5));
		timerPanel.setBorder(BorderFactory.createTitledBorder("Pomodoro Timer"));
		timerPanel.setPreferredSize(new Dimension(260, 0));
		contentPane.add(timerPanel, BorderLayout.EAST);

		timerLabel = new JLabel("25:00", SwingConstants.CENTER);
		timerLabel.setFont(new Font("Monospaced", Font.BOLD, 52));
		timerPanel.add(timerLabel, BorderLayout.CENTER);

		JPanel timerButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));
		timerPanel.add(timerButtons, BorderLayout.SOUTH);

		startButton = new JButton("Start");
		timerButtons.add(startButton);

		pauseButton = new JButton("Pause");
		timerButtons.add(pauseButton);

		stopButton = new JButton("Stop");
		timerButtons.add(stopButton);

		// ===============================================================
		// LOGIC
		// ===============================================================
		taskManager = new TaskManager();
		pomodoroTimer = new PomodoroTimer(
				new Consumer<String>() {
					@Override
					public void accept(final String formattedTime) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								timerLabel.setText(formattedTime);
							}
						});
					}
				},
				new Runnable() {
					@Override
					public void run() {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								Task completed = pomodoroTimer.getActiveTask();
								if (completed != null) {
									taskManager.updateTask(completed);
								}
								JOptionPane.showMessageDialog(MainWindowDesign.this, "หมดเวลา! เก็บ focus time แล้ว");
								refreshTaskList();
								updateButtons();
							}
						});
					}
				},
				new Runnable() {
					@Override
					public void run() {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								updateButtons();
							}
						});
					}
				});
		pomodoroTimer.setOnFocusMinuteAdded(new Runnable() {
			@Override
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						Task active = pomodoroTimer.getActiveTask();
						if (active != null) {
							taskManager.updateTask(active);
							int idx = listModel.indexOf(active);
							if (idx >= 0) {
								listModel.setElementAt(active, idx);
							}
						}
					}
				});
			}
		});

		// ===============================================================
		// EVENT LISTENERS
		// ===============================================================
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pomodoroTimer.shutdown();
				taskManager.close();
				dispose();
			}
		});

		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showAddTaskDialog();
			}
		});
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteSelectedTask();
			}
		});
		exportBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exportTasks();
			}
		});
		sortNameBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				taskManager.sortByName();
				refreshTaskList();
			}
		});
		sortPriorityBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				taskManager.sortByPriority();
				refreshTaskList();
			}
		});
		sortDateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				taskManager.sortByDueDate();
				refreshTaskList();
			}
		});
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startTimer();
			}
		});
		pauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pomodoroTimer.pause();
			}
		});
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pomodoroTimer.stop();
				refreshTaskList();
			}
		});

		// ===============================================================
		// INITIAL STATE
		// ===============================================================
		refreshTaskList();
		updateButtons();
	}

	// ===================================================================
	// DIALOG: เพิ่มงาน (ครบทุก field)
	// ===================================================================

	private void showAddTaskDialog() {
		final JTextField titleField = new JTextField(20);
		final JTextArea descField = new JTextArea(3, 20);
		final JComboBox<Priority> priorityBox = new JComboBox<Priority>(Priority.values());
		final JComboBox<Category> categoryBox = new JComboBox<Category>();
		for (Category c : taskManager.getCategories()) {
			categoryBox.addItem(c);
		}
		final JTextField dueDateField = new JTextField(10);
		final JTextField reminderField = new JTextField(16);
		final JCheckBox repeatCheck = new JCheckBox("งานซ้ำ");
		final JTextField intervalField = new JTextField("7", 5);
		intervalField.setEnabled(false);

		repeatCheck.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				intervalField.setEnabled(repeatCheck.isSelected());
			}
		});

		JPanel repeatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
		repeatPanel.add(repeatCheck);
		repeatPanel.add(new JLabel("ทุก"));
		repeatPanel.add(intervalField);
		repeatPanel.add(new JLabel("วัน"));

		JPanel form = new JPanel(new GridLayout(0, 2, 5, 5));
		form.add(new JLabel("ชื่องาน:"));
		form.add(titleField);
		form.add(new JLabel("คำอธิบาย:"));
		form.add(new JScrollPane(descField));
		form.add(new JLabel("ความสำคัญ:"));
		form.add(priorityBox);
		form.add(new JLabel("หมวดหมู่:"));
		form.add(categoryBox);
		form.add(new JLabel("วันครบกำหนด (dd/MM/yyyy):"));
		form.add(dueDateField);
		form.add(new JLabel("แจ้งเตือน (dd/MM/yyyy HH:mm):"));
		form.add(reminderField);
		form.add(new JLabel("ประเภท:"));
		form.add(repeatPanel);

		int result = JOptionPane.showConfirmDialog(
				this, form, "เพิ่มงานใหม่", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			String title = titleField.getText().trim();
			if (!title.isEmpty()) {
				Priority priority = (Priority) priorityBox.getSelectedItem();
				Category category = (Category) categoryBox.getSelectedItem();

				Task task;
				if (repeatCheck.isSelected()) {
					int intervalDays = parseIntervalDays(intervalField.getText().trim());
					task = new RepeatingTask(title, priority, category, intervalDays, new Date());
				} else {
					task = new SingleTask(title, priority, category);
				}

				task.setDescription(descField.getText().trim());
				task.setDueDate(parseDateField(dueDateField.getText().trim(), DATE_FMT));
				task.setReminderTime(parseDateField(reminderField.getText().trim(), DATETIME_FMT));

				taskManager.addTask(task);
				refreshTaskList();
			}
		}
	}

	// ===================================================================
	// DIALOG: แก้ไขงาน
	// ===================================================================

	private void showEditTaskDialog() {
		Task selected = taskList.getSelectedValue();
		if (selected == null) {
			JOptionPane.showMessageDialog(this, "กรุณาเลือกงานที่ต้องการแก้ไขก่อน");
			return;
		}

		final JTextField titleField = new JTextField(selected.getTitle(), 20);
		final JTextArea descField = new JTextArea(
				selected.getDescription() != null ? selected.getDescription() : "", 3, 20);
		final JComboBox<Priority> priorityBox = new JComboBox<Priority>(Priority.values());
		priorityBox.setSelectedItem(selected.getPriority());

		final JComboBox<Category> categoryBox = new JComboBox<Category>();
		for (Category c : taskManager.getCategories()) {
			categoryBox.addItem(c);
		}
		if (selected.getCategory() != null) {
			categoryBox.setSelectedItem(selected.getCategory());
		}

		final JComboBox<TaskStatus> statusBox = new JComboBox<TaskStatus>(TaskStatus.values());
		statusBox.setSelectedItem(selected.getStatus());

		String dueDateStr = selected.getDueDate() != null
				? DATE_FMT.format(selected.getDueDate())
				: "";
		String reminderStr = selected.getReminderTime() != null
				? DATETIME_FMT.format(selected.getReminderTime())
				: "";

		final JTextField dueDateField = new JTextField(dueDateStr, 10);
		final JTextField reminderField = new JTextField(reminderStr, 16);

		// Repeat fields
		boolean isRepeating = selected instanceof RepeatingTask;
		final JCheckBox repeatCheck = new JCheckBox("งานซ้ำ", isRepeating);
		final JTextField intervalField = new JTextField(
				isRepeating ? String.valueOf(((RepeatingTask) selected).getIntervalDays()) : "7", 5);
		intervalField.setEnabled(isRepeating);

		repeatCheck.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				intervalField.setEnabled(repeatCheck.isSelected());
			}
		});

		JPanel repeatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
		repeatPanel.add(repeatCheck);
		repeatPanel.add(new JLabel("ทุก"));
		repeatPanel.add(intervalField);
		repeatPanel.add(new JLabel("วัน"));

		JPanel form = new JPanel(new GridLayout(0, 2, 5, 5));
		form.add(new JLabel("ชื่องาน:"));
		form.add(titleField);
		form.add(new JLabel("คำอธิบาย:"));
		form.add(new JScrollPane(descField));
		form.add(new JLabel("ความสำคัญ:"));
		form.add(priorityBox);
		form.add(new JLabel("หมวดหมู่:"));
		form.add(categoryBox);
		form.add(new JLabel("สถานะ:"));
		form.add(statusBox);
		form.add(new JLabel("วันครบกำหนด (dd/MM/yyyy):"));
		form.add(dueDateField);
		form.add(new JLabel("แจ้งเตือน (dd/MM/yyyy HH:mm):"));
		form.add(reminderField);
		form.add(new JLabel("ประเภท:"));
		form.add(repeatPanel);

		int result = JOptionPane.showConfirmDialog(
				this, form, "แก้ไขงาน", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			String newTitle = titleField.getText().trim();
			if (!newTitle.isEmpty()) {
				selected.setTitle(newTitle);
				selected.setDescription(descField.getText().trim());
				selected.setPriority((Priority) priorityBox.getSelectedItem());
				selected.setCategory((Category) categoryBox.getSelectedItem());
				selected.setStatus((TaskStatus) statusBox.getSelectedItem());
				selected.setDueDate(parseDateField(dueDateField.getText().trim(), DATE_FMT));
				selected.setReminderTime(parseDateField(reminderField.getText().trim(), DATETIME_FMT));

				taskManager.updateTask(selected);
				refreshTaskList();
			}
		}
	}

	private Date parseDateField(String text, SimpleDateFormat fmt) {
		if (text == null || text.isEmpty())
			return null;
		try {
			fmt.setLenient(false);
			return fmt.parse(text);
		} catch (ParseException ex) {
			JOptionPane.showMessageDialog(this,
					"รูปแบบวันที่ไม่ถูกต้อง: " + text,
					"คำเตือน", JOptionPane.WARNING_MESSAGE);
			return null;
		}
	}

	private int parseIntervalDays(String text) {
		try {
			int val = Integer.parseInt(text);
			if (val > 0)
				return val;
		} catch (NumberFormatException ex) {
			/* ignore */ }
		JOptionPane.showMessageDialog(this,
				"จำนวนวันต้องเป็นตัวเลขมากกว่า 0 ใช้ค่าตั้งต้น 7 วัน",
				"คำเตือน", JOptionPane.WARNING_MESSAGE);
		return 7;
	}

	private void exportTasks() {
		// TODO: implement export functionality
	}

	private void deleteSelectedTask() {
		Task selected = taskList.getSelectedValue();
		if (selected == null) {
			JOptionPane.showMessageDialog(this, "กรุณาเลือกงานที่ต้องการลบก่อน");
			return;
		}
		int confirm = JOptionPane.showConfirmDialog(
				this, "ลบงาน \"" + selected.getTitle() + "\" ?",
				"ยืนยันการลบ", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			Task active = pomodoroTimer.getActiveTask();
			if (active != null && active.getId().equals(selected.getId())) {
				pomodoroTimer.stop();
			}
			taskManager.removeTask(selected);
			refreshTaskList();
		}
	}

	private void changeSelectedTaskStatus(TaskStatus newStatus) {
		Task selected = taskList.getSelectedValue();
		if (selected == null)
			return;
		selected.setStatus(newStatus);
		taskManager.updateTask(selected);
		refreshTaskList();
	}

	private void startTimer() {
		Task selected = taskList.getSelectedValue();
		if (selected == null) {
			JOptionPane.showMessageDialog(this, "กรุณาเลือกงานจากรายการก่อนกด Start");
			return;
		}
		if (pomodoroTimer.isPaused()) {
			Task active = pomodoroTimer.getActiveTask();
			if (active != null && !active.getId().equals(selected.getId())) {
				pomodoroTimer.stop();
			}
		}
		pomodoroTimer.start(selected);
		refreshTaskList();
	}

	private void refreshTaskList() {
		Task previously = taskList.getSelectedValue();
		listModel.clear();
		for (Task t : taskManager.getAllTasks()) {
			listModel.addElement(t);
		}
		if (previously != null) {
			int idx = -1;
			for (int i = 0; i < listModel.size(); i++) {
				if (listModel.get(i).getId().equals(previously.getId())) {
					idx = i;
					break;
				}
			}
			if (idx >= 0) {
				taskList.setSelectedIndex(idx);
			}
		}
	}

	private void updateButtons() {
		boolean running = pomodoroTimer.isRunning();
		boolean paused = pomodoroTimer.isPaused();
		startButton.setEnabled(!running);
		pauseButton.setEnabled(running);
		stopButton.setEnabled(running || paused);
	}

	private static class TaskCellRenderer extends javax.swing.DefaultListCellRenderer {
		private static final long serialVersionUID = 1L;
		private static final SimpleDateFormat DT = new SimpleDateFormat("dd/MM HH:mm");
		private static final SimpleDateFormat DATE = new SimpleDateFormat("dd/MM/yyyy");

		// สี due date urgency
		private static final java.awt.Color COLOR_OVERDUE = new java.awt.Color(255, 180, 180); // แดงอ่อน
		private static final java.awt.Color COLOR_TODAY = new java.awt.Color(255, 210, 140); // ส้มอ่อน
		private static final java.awt.Color COLOR_SOON = new java.awt.Color(255, 245, 180); // เหลืองอ่อน

		// สี priority สำหรับ text
		private static final java.awt.Color PRI_HIGH = new java.awt.Color(180, 0, 0); // แดงเข้ม
		private static final java.awt.Color PRI_MEDIUM = new java.awt.Color(160, 100, 0); // น้ำตาลส้ม
		private static final java.awt.Color PRI_LOW = new java.awt.Color(0, 120, 0); // เขียว

		@Override
		public java.awt.Component getListCellRendererComponent(
				JList<?> list, Object value, int index, boolean isSelected, boolean hasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
			if (value instanceof Task) {
				Task t = (Task) value;
				String categoryName = (t.getCategory() != null) ? t.getCategory().getName() : "-";

				// reminder preview
				String reminderStr = "";
				if (t.getReminderTime() != null) {
					reminderStr = "  ⏰" + DT.format(t.getReminderTime());
				}

				// description preview
				String desc = t.getDescription();
				String descPreview = "";
				if (desc != null && !desc.isEmpty()) {
					descPreview = "  | " + (desc.length() > 25 ? desc.substring(0, 25) + "..." : desc);
				}

				// priorityใน text
				String priBadge = "";
				com.pomodoro.model.Priority pri = t.getPriority();
				if (pri == com.pomodoro.model.Priority.HIGH)
					priBadge = "🔴";
				else if (pri == com.pomodoro.model.Priority.MEDIUM)
					priBadge = "🟡";
				else
					priBadge = "🟢";

				setText(String.format("%s [%s] %s  (%s)  [%s]  focus: %d min%s%s",
						priBadge, t.getStatus(), t.getTitle(), t.getPriority(),
						categoryName, t.getTotalFocusMinutes(), reminderStr, descPreview));

				if (!isSelected) {
					java.awt.Color bg = getBackground(); // default
					if (t.getCategory() != null) {
						String colorCode = t.getCategory().getColorCode();
						if (colorCode != null && colorCode.startsWith("#")) {
							try {
								java.awt.Color base = java.awt.Color.decode(colorCode);
								bg = new java.awt.Color(
										(base.getRed() + 255 * 3) / 4,
										(base.getGreen() + 255 * 3) / 4,
										(base.getBlue() + 255 * 3) / 4);
							} catch (NumberFormatException ex) {
								/* ข้าม */ }
						}
					}

					if (t.getDueDate() != null) {
						long now = System.currentTimeMillis();
						long due = t.getDueDate().getTime();
						long diffDays = (due - now) / (1000 * 60 * 60 * 24);
						if (diffDays < 0) {
							bg = COLOR_OVERDUE; // เกินวันแล้ว แดง
						} else if (diffDays == 0) {
							bg = COLOR_TODAY; // วันนี้ ส้ม
						} else if (diffDays <= 3) {
							bg = COLOR_SOON; // <= 3 วัน -> เหลือง
						}
					}

					setBackground(bg);

					// --- Priority text color ---
					if (pri == com.pomodoro.model.Priority.HIGH)
						setForeground(PRI_HIGH);
					else if (pri == com.pomodoro.model.Priority.MEDIUM)
						setForeground(PRI_MEDIUM);
					else
						setForeground(PRI_LOW);
				}

				// Tooltip
				StringBuilder tip = new StringBuilder("<html>");
				if (desc != null && !desc.isEmpty()) {
					tip.append("<b>Description:</b><br>")
							.append(desc.replace("\n", "<br>")).append("<br>");
				}
				if (t.getDueDate() != null) {
					long diffDays = (t.getDueDate().getTime() - System.currentTimeMillis()) / (1000 * 60 * 60 * 24);
					String urgency = diffDays < 0 ? " ⚠️ เกินกำหนด!" : diffDays == 0 ? " ⚠️ วันนี้!" : "";
					tip.append("<b>Due:</b> ").append(DATE.format(t.getDueDate())).append(urgency).append("<br>");
				}
				if (t.getReminderTime() != null) {
					tip.append("<b>Reminder:</b> ").append(DT.format(t.getReminderTime()));
				}
				tip.append("</html>");
				setToolTipText(tip.length() > 13 ? tip.toString() : null);
			}
			return this;
		}
	}
}
