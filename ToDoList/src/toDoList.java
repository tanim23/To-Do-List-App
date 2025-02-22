import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Task {
    private String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public void markCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return (completed ? "[✓] " : "[ ] ") + description;
    }
}

public class toDoList extends JFrame {

    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskJList;
    private JTextField taskField;
    private JButton addButton;
    private JButton removeButton;
    private JButton completeButton;

    public toDoList() {
        setTitle("To-Do List");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        // Model and list to hold tasks
        taskListModel = new DefaultListModel<>();
        taskJList = new JList<>(taskListModel);
        taskJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(taskJList);

        // Input field and button to add tasks
        taskField = new JTextField(20);
        addButton = new JButton("Add Task");
        JPanel inputPanel = new JPanel();
        inputPanel.add(taskField);
        inputPanel.add(addButton);

        // Buttons for task operations
        removeButton = new JButton("Remove Task");
        completeButton = new JButton("Mark Complete");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(removeButton);
        buttonPanel.add(completeButton);

        // Layout setup
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add button action: add new task from text field
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String description = taskField.getText().trim();
                if (!description.isEmpty()) {
                    taskListModel.addElement(new Task(description));
                    taskField.setText("");
                }
            }
        });

        // Remove button action: remove selected task
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskJList.getSelectedIndex();
                if (selectedIndex != -1) {
                    taskListModel.remove(selectedIndex);
                }
            }
        });

        // Complete button action: mark selected task as complete and update list
        completeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskJList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Task task = taskListModel.getElementAt(selectedIndex);
                    task.markCompleted();
                    // Refresh the list model to update the display
                    taskListModel.set(selectedIndex, task);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new toDoList().setVisible(true);
            }
        });
    }
}
