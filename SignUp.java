import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.*;
import javax.swing.*;

public class SignUp extends JFrame {
    private JTextField usernameField, emailField, phoneField;
    private JPasswordField passwordField, confirmPasswordField;

    public SignUp() {
        setTitle("Sign Up");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Background Panel
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0, 0, 450, 400);
        backgroundPanel.setLayout(null);
        backgroundPanel.setBackground(new Color(33, 37, 41));
        add(backgroundPanel);

        // Style
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 12);

        // Username Label and Field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 30, 120, 25);
        usernameLabel.setFont(labelFont);
        usernameLabel.setForeground(Color.WHITE);
        backgroundPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(180, 30, 200, 25);
        usernameField.setFont(fieldFont);
        backgroundPanel.add(usernameField);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 80, 120, 25);
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(Color.WHITE);
        backgroundPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 80, 200, 25);
        passwordField.setFont(fieldFont);
        backgroundPanel.add(passwordField);

        // Confirm Password Label and Field
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(50, 130, 150, 25);
        confirmPasswordLabel.setFont(labelFont);
        confirmPasswordLabel.setForeground(Color.WHITE);
        backgroundPanel.add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(180, 130, 200, 25);
        confirmPasswordField.setFont(fieldFont);
        backgroundPanel.add(confirmPasswordField);

        // Email Label and Field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 180, 120, 25);
        emailLabel.setFont(labelFont);
        emailLabel.setForeground(Color.WHITE);
        backgroundPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(180, 180, 200, 25);
        emailField.setFont(fieldFont);
        backgroundPanel.add(emailField);

        // Phone Label and Field
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 230, 120, 25);
        phoneLabel.setFont(labelFont);
        phoneLabel.setForeground(Color.WHITE);
        backgroundPanel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(180, 230, 200, 25);
        phoneField.setFont(fieldFont);
        backgroundPanel.add(phoneField);

        // Signup Button
        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(150, 300, 120, 35);
        signupButton.setBackground(new Color(40, 167, 69));
        signupButton.setForeground(Color.WHITE);
        signupButton.setFont(new Font("Arial", Font.BOLD, 14));
        backgroundPanel.add(signupButton);


        // Back Button
JButton backButton = new JButton("← Back");
backButton.setBounds(20, 10, 90, 20);
backButton.setFont(new Font("Arial", Font.BOLD, 12));
backButton.setForeground(Color.WHITE);
backButton.setBackground(new Color(52, 58, 64)); // Dark gray
backButton.setFocusPainted(false);
backgroundPanel.add(backButton);

backButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        new Login().setVisible(true); // Open Login page
        dispose(); // Close current window
    }
});


        // Action Listener for Signup Button
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                String email = emailField.getText();
                String phone = phoneField.getText();

                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled.");
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match.");
                    return;
                }

                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(null, "Invalid email format.");
                    return;
                }

                if (!isValidPhone(phone)) {
                    JOptionPane.showMessageDialog(null, "Invalid phone number. It should be 10 digits.");
                    return;
                }

                if (Database.addUser(new User(username, password, email, phone))) {
                    JOptionPane.showMessageDialog(null, "Signup successful!");
                    new Login().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Username already exists.");
                }
            }
        });
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPhone(String phone) {
        return phone.matches("\\d{8}");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SignUp signUp = new SignUp();
            signUp.setVisible(true);
        });
    }
    
}
