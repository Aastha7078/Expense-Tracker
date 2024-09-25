import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPage() {
        setTitle("Login Page");
        setSize(400, 250);  // Increased the window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Adding components with adjusted positions

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 100, 25);  // Adjusted position and size
        add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(150, 50, 180, 25);  // Adjusted position and size
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 90, 100, 25);  // Adjusted position and size
        add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(150, 90, 180, 25);  // Adjusted position and size
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(150, 140, 120, 30);  // Adjusted position and size
        add(loginButton);

        // Action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Hardcoded login check (can be replaced with DB check)
                if (username.equals("admin") && password.equals("admin123")) {
                    dispose(); // Close the login window
                    new ExpenseManager(); // Open the main expense manager window
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials");
                }
            }
        });

        setLocationRelativeTo(null);  // Center the window on the screen
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
