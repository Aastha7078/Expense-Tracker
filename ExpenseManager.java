import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ExpenseManager extends JFrame {
    private JTextField descriptionField, amountField;
    private JComboBox<String> categoryBox;
    private JButton addButton, viewButton;
    private JTextArea reportArea;

    private ExpenseDAO expenseDAO;

    public ExpenseManager() {
        setTitle("Expense Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        expenseDAO = new ExpenseDAO();

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(20, 20, 80, 25);
        add(descriptionLabel);

        descriptionField = new JTextField(15);
        descriptionField.setBounds(100, 20, 165, 25);
        add(descriptionField);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(20, 50, 80, 25);
        add(amountLabel);

        amountField = new JTextField(10);
        amountField.setBounds(100, 50, 165, 25);
        add(amountField);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(20, 80, 80, 25);
        add(categoryLabel);

        String[] categories = {"Food", "Rent", "Transport", "Entertainment", "Other"};
        categoryBox = new JComboBox<>(categories);
        categoryBox.setBounds(100, 80, 165, 25);
        add(categoryBox);

        addButton = new JButton("Add Expense");
        addButton.setBounds(100, 110, 120, 25);
        add(addButton);

        viewButton = new JButton("View Expenses");
        viewButton.setBounds(230, 110, 120, 25);
        add(viewButton);

        reportArea = new JTextArea();
        reportArea.setBounds(20, 150, 550, 200);
        add(new JScrollPane(reportArea));

        // Add expense action
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addExpense();
            }
        });

        // View expenses action
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewExpenses();
            }
        });

        setVisible(true);
    }

    private void addExpense() {
        String description = descriptionField.getText();
        double amount = Double.parseDouble(amountField.getText());
        String category = (String) categoryBox.getSelectedItem();

        Expense expense = new Expense(description, amount, category);
        expenseDAO.addExpense(expense);
        JOptionPane.showMessageDialog(this, "Expense added successfully!");
    }

    private void viewExpenses() {
        List<Expense> expenses = expenseDAO.getAllExpenses();
        reportArea.setText("");
        for (Expense expense : expenses) {
            reportArea.append(expense.toString() + "\n");
        }
    }
}
