package dodat.client;

import dodat.user.User;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ClientView extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public JTextField getTxtUsername() {
        return txtUsername;
    }
    public JPasswordField getTxtPassword() {
        return txtPassword;
    }
    public JButton getBtnLogin() {
        return btnLogin;
    }
    public ClientView() {
        this.setTitle("Login MVC");
        this.setSize(300, 150);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ClientControl clientControl = new ClientControl(this);
        ClientControl.LoginListener loginListener = clientControl.new LoginListener();
        ActionListener actionListener = loginListener;

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel userLabel = new JLabel("Username: ");
        this.txtUsername = new JTextField();

        JLabel passwordLabel = new JLabel("Password: ");
        this.txtPassword = new JPasswordField();

        panel.add(userLabel);
        panel.add(txtUsername);
        panel.add(passwordLabel);
        panel.add(txtPassword);

        this.btnLogin = new JButton("login");
        panel.add(btnLogin);
        add(panel);

        this.btnLogin.addActionListener(actionListener);
    }
    public User getUser() {
        return new User(this.txtUsername.getText(), String.valueOf(this.txtPassword.getPassword()));
    }
    public void showMessage(String msg) {
        JFrame frame = new JFrame("Message");
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel message = new JLabel(msg);
        frame.add(message);

        frame.setVisible(true);

        JButton ok = new JButton("OK");
    }
}