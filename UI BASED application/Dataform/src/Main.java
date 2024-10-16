import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("User Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        
        // Create the panel
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        // Set the frame to be visible
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // First Name
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(50, 60, 100, 25);
        panel.add(firstNameLabel);

        JTextField firstNameText = new JTextField(20);
        firstNameText.setBounds(200, 60, 200, 25);
        panel.add(firstNameText);

        // Last Name
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(50, 100, 100, 25);
        panel.add(lastNameLabel);

        JTextField lastNameText = new JTextField(20);
        lastNameText.setBounds(200, 100, 200, 25);
        panel.add(lastNameText);

        // Phone Number
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 140, 100, 25);
        panel.add(phoneLabel);

        JTextField phoneText = new JTextField(10);
        phoneText.setBounds(200, 140, 200, 25);
        panel.add(phoneText);

        // Email ID
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 180, 100, 25);
        panel.add(emailLabel);

        JTextField emailText = new JTextField(20);
        emailText.setBounds(200, 180, 200, 25);
        panel.add(emailText);

        // Address
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 220, 100, 25);
        panel.add(addressLabel);

        JTextField addressText = new JTextField(20);
        addressText.setBounds(200, 220, 200, 25);
        panel.add(addressText);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(50, 260, 100, 25);
        panel.add(submitButton);

        // Reset Button
        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(200, 260, 100, 25);
        panel.add(resetButton);

        // Add action listeners to buttons
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameText.getText();
                String lastName = lastNameText.getText();
                String phone = phoneText.getText();
                String email = emailText.getText();
                String address = addressText.getText();
                // Call method to store data into database
                storeData(firstName, lastName, phone, email, address);
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstNameText.setText("");
                lastNameText.setText("");
                phoneText.setText("");
                emailText.setText("");
                addressText.setText("");
            }
        });
    }

    private static void storeData(String firstName, String lastName, String phone, String email, String address) {
        // Database connection setup
        String url = "jdbc:mysql://localhost:3306/myDB";
        String dbUsername = "root";
        String dbPassword = "12345678";

        try {
            Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            String sql = "INSERT INTO allusers (first_name, last_name, phone, email, address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, phone);
            pstmt.setString(4, email);
            pstmt.setString(5, address);
            pstmt.executeUpdate();
            conn.close();
            System.out.println("Data stored successfully!");
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }
}
