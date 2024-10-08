package modernUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Home extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField repasswordField;
    private Point initialClick; 

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Home frame = new Home();
                    frame.setUndecorated(true);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Home() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 758, 465);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(45, 52, 54));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Title label
        JLabel titleLabel = new JLabel("Sign Up");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(450, 20, 200, 30);
        contentPane.add(titleLabel);

        // X button
        JButton closeButton = new JButton("X");
        closeButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(new Color(235, 59, 90));
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false);
        closeButton.setBounds(10, 10, 45, 30);  // Position at the top left corner
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Close the application
            }
        });
        contentPane.add(closeButton);

        // Username label and field
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblUsername.setBounds(450, 70, 200, 25);
        contentPane.add(lblUsername);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        usernameField.setBounds(450, 100, 200, 30);
        usernameField.setBackground(new Color(99, 110, 114));
        usernameField.setForeground(Color.WHITE);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        // Password label and field
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblPassword.setBounds(450, 140, 200, 25);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setBounds(450, 170, 200, 30);
        passwordField.setBackground(new Color(99, 110, 114));
        passwordField.setForeground(Color.WHITE);
        contentPane.add(passwordField);

        // Email label and field
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblEmail.setBounds(450, 210, 200, 25);
        contentPane.add(lblEmail);

        emailField = new JTextField();
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailField.setBounds(450, 240, 200, 30);
        emailField.setBackground(new Color(99, 110, 114));
        emailField.setForeground(Color.WHITE);
        contentPane.add(emailField);
        emailField.setColumns(10);

        // Repassword label and field
        JLabel lblRepassword = new JLabel("Confirm Password");
        lblRepassword.setForeground(Color.WHITE);
        lblRepassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblRepassword.setBounds(450, 280, 200, 25);
        contentPane.add(lblRepassword);

        repasswordField = new JPasswordField();
        repasswordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        repasswordField.setBounds(450, 310, 200, 30);
        repasswordField.setBackground(new Color(99, 110, 114));
        repasswordField.setForeground(Color.WHITE);
        contentPane.add(repasswordField);

        // Sign up button
        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnSignUp.setBackground(new Color(0, 184, 148));
        btnSignUp.setForeground(Color.WHITE);
        btnSignUp.setBounds(450, 360, 200, 40);
        btnSignUp.setFocusPainted(false);
        btnSignUp.setBorderPainted(false);
        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Sign up action here
            }
        });
        contentPane.add(btnSignUp);

        // Add mouse listeners for dragging the window
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Get the current location of the window
                int thisX = getLocation().x;
                int thisY = getLocation().y;

                // Determine the new location of the window
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                // Move the window
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                setLocation(X, Y);
            }
        });
    }
}
