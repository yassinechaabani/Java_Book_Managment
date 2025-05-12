import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login() {
        // Configuration principale de la fenêtre
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Image de fond
        JLabel backgroundLabel = new JLabel(new ImageIcon("C://Users//Yassine//Desktop//java books managment//images//login bande.jpg"));
        backgroundLabel.setBounds(0, 0, 400, 300);
        add(backgroundLabel);

        // Panneau pour les champs de connexion
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBounds(50, 50, 300, 200);
        loginPanel.setOpaque(false);
        backgroundLabel.add(loginPanel);

        // Style des étiquettes et champs
        Font labelFont = new Font("Arial", Font.BOLD, 14);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 20, 100, 25);
        usernameLabel.setFont(labelFont);
        usernameLabel.setForeground(Color.WHITE);
        loginPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 20, 150, 25);
        loginPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 60, 100, 25);
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(Color.WHITE);
        loginPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 60, 150, 25);
        loginPanel.add(passwordField);

        // Bouton de connexion
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(40, 120, 100, 30);
        loginButton.setBackground(new Color(0, 123, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 12));
        loginPanel.add(loginButton);

        // Bouton d'inscription
        JButton signupButton = new JButton("Signup");
        signupButton.setBounds(160, 120, 100, 30);
        signupButton.setBackground(new Color(40, 167, 69));
        signupButton.setForeground(Color.WHITE);
        signupButton.setFont(new Font("Arial", Font.BOLD, 12));
        loginPanel.add(signupButton);
        // Gestionnaires d'événements
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (Database.authenticateUser(username, password) != null) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    new BookManagement().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials.");
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignUp().setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login login = new Login();
            login.getContentPane().setBackground(new Color(33, 37, 41)); // Couleur de fond générale
            login.setVisible(true);
        });
    }
}
