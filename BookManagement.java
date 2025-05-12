import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class BookManagement extends JFrame {
    private JTextField titleField, authorField, oldTitleField, newTitleField, newAuthorField;

    public BookManagement() {
        setTitle("Book Management");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 248, 255)); // Light blue background

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 12);

        // Add Book Section
        JLabel addLabel = new JLabel("Add Book:");
        addLabel.setBounds(20, 20, 150, 30);
        addLabel.setFont(labelFont);
        add(addLabel);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(20, 60, 80, 30);
        add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(100, 60, 200, 30);
        add(titleField);

        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setBounds(20, 100, 80, 30);
        add(authorLabel);

        authorField = new JTextField();
        authorField.setBounds(100, 100, 200, 30);
        add(authorField);

        JButton addButton = createStyledButton("Add Book");
        addButton.setBounds(100, 140, 150, 40);
        add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                if (Database.addBook(new Book(title, author))) {
                    JOptionPane.showMessageDialog(null, "Book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add book.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Delete Book Section
        JLabel deleteLabel = new JLabel("Delete Book:");
        deleteLabel.setBounds(20, 200, 150, 30);
        deleteLabel.setFont(labelFont);
        add(deleteLabel);

        JLabel deleteTitleLabel = new JLabel("Title:");
        deleteTitleLabel.setBounds(20, 240, 80, 30);
        add(deleteTitleLabel);

        JTextField deleteTitleField = new JTextField();
        deleteTitleField.setBounds(100, 240, 200, 30);
        add(deleteTitleField);

        JButton deleteButton = createStyledButton("Delete");
        deleteButton.setBounds(100, 280, 150, 40);
        add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = deleteTitleField.getText();
                if (Database.deleteBook(title)) {
                    JOptionPane.showMessageDialog(null, "Book deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete book.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Update Book Section
        JLabel updateLabel = new JLabel("Update Book:");
        updateLabel.setBounds(20, 340, 150, 30);
        updateLabel.setFont(labelFont);
        add(updateLabel);

        JLabel oldTitleLabel = new JLabel("Old Title:");
        oldTitleLabel.setBounds(20, 380, 80, 30);
        add(oldTitleLabel);

        oldTitleField = new JTextField();
        oldTitleField.setBounds(100, 380, 200, 30);
        add(oldTitleField);

        JLabel newTitleLabel = new JLabel("New Title:");
        newTitleLabel.setBounds(20, 420, 80, 30);
        add(newTitleLabel);

        newTitleField = new JTextField();
        newTitleField.setBounds(100, 420, 200, 30);
        add(newTitleField);

        JLabel newAuthorLabel = new JLabel("New Author:");
        newAuthorLabel.setBounds(20, 460, 80, 30);
        add(newAuthorLabel);

        newAuthorField = new JTextField();
        newAuthorField.setBounds(100, 460, 200, 30);
        add(newAuthorField);

        JButton updateButton = createStyledButton("Update");
        updateButton.setBounds(100, 500, 150, 40);
        add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldTitle = oldTitleField.getText();
                String newTitle = newTitleField.getText();
                String newAuthor = newAuthorField.getText();
                if (Database.updateBook(oldTitle, newTitle, newAuthor)) {
                    JOptionPane.showMessageDialog(null, "Book updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update book.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // View Books Section
        JButton viewBooksButton = createStyledButton("View Books");
        viewBooksButton.setBounds(100, 560, 150, 40);
        add(viewBooksButton);

        viewBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Book> books = Database.getBooks();
                if (books.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No books available.", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    showBooksTable(books);
                }
            }
        });

        // Logout Button
        JButton logoutButton = createStyledButton("Logout");
        logoutButton.setBounds(100, 620, 150, 40);
        add(logoutButton);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose(); // Close the current frame
                    SwingUtilities.invokeLater(() -> {
                        Login login = new Login();
                        login.setVisible(true);
                    });
                }
            }
        });
    }

    // Method to create styled buttons
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(new Color(100, 149, 237)); // Cornflower Blue
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(65, 105, 225), 2));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(65, 105, 225)); // Royal Blue
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237));
            }
        });
        return button;
    }

    // Method to display books in a table
    private void showBooksTable(List<Book> books) {
        JFrame booksFrame = new JFrame("Books List");
        booksFrame.setSize(500, 400);
        booksFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"Title", "Author"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable booksTable = new JTable(model);

        for (Book book : books) {
            model.addRow(new Object[]{book.getTitle(), book.getAuthor()});
        }

        JScrollPane scrollPane = new JScrollPane(booksTable);
        booksFrame.add(scrollPane);
        booksFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookManagement management = new BookManagement();
            management.setVisible(true);
        });
    }
}
