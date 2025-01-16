package xyz.mtrdevelopment.authentication.server.run;

import xyz.mtrdevelopment.auth.api.model.User;
import xyz.mtrdevelopment.authentication.server.Authentication;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

/**
 * AuthenticationApp is a part of AuthenticationSystem
 * Which was created / maintained by
 *
 * @author MTR
 */

public class AuthenticationApp {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(new Authentication(), panel);

        frame.setVisible(true);
    }

    private static void placeComponents(Authentication authentication, JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("E-Mail Address or Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 80, 80, 25);
        panel.add(loginButton);

        JLabel messageLabel = new JLabel("");
        messageLabel.setBounds(10, 110, 300, 25);
        panel.add(messageLabel);

        loginButton.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passwordText.getPassword());
            User user;
            boolean isMail = username.contains("@");

            // E-Mail
            if (isMail) {
                user = authentication.getUserManager().getByEmail(username);
            } else {
                // Username
                user = authentication.getUserManager().getByUsername(username);
            }

            if (user == null) {
                user = new User(UUID.randomUUID(), isMail ? username : "", isMail ? "" : username, password);

                authentication.getUserManager().save(user);
                messageLabel.setText("User created!");
                messageLabel.setForeground(Color.YELLOW);
            } else {
                boolean authenticated = user.getPassword().equals(password);

                if (authenticated) {
                    messageLabel.setText("Login successful!");
                    messageLabel.setForeground(Color.GREEN);
                } else {
                    messageLabel.setText("Invalid password.");
                    messageLabel.setForeground(Color.RED);
                }
            }
        });
    }
}