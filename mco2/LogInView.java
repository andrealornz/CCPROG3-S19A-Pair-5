import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LogInView {
    private JPanel panel, usernamePanel, passwordPanel, logInPanel;
    private JLabel titleLbl, usernameLbl, passwordLbl, errorLbl;
    private JTextField usernameTf, passwordTf;
    private JButton logInBtn, cancelBtn;
    private JSeparator separator;

    public LogInView() {
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));

        // title label
        this.titleLbl = new JLabel("Log-In an Existing Account");
        this.titleLbl.setPreferredSize(new Dimension(1000, 50));
        this.titleLbl.setMaximumSize(new Dimension(1000, 50));
        this.titleLbl.setMinimumSize(new Dimension(1000, 50));
        this.titleLbl.setFont(new Font("Century Gothic", 1, 36));
        this.titleLbl.setForeground(new Color(51, 51, 51));
        this.titleLbl.setHorizontalAlignment(SwingConstants.CENTER);

        // separator
        this.separator = new JSeparator();
        this.separator.setPreferredSize(new Dimension(500, 1));
        this.separator.setMaximumSize(new Dimension(500, 1));
        this.separator.setMinimumSize(new Dimension(500, 1));
        this.separator.setForeground(new Color(51, 51, 51));

        // username & password label
        this.usernameLbl = new JLabel("Username:");
        this.usernameLbl.setPreferredSize(new Dimension(140, 50));
        this.usernameLbl.setMaximumSize(new Dimension(140, 50));
        this.usernameLbl.setMinimumSize(new Dimension(140, 50));
        this.usernameLbl.setFont(new Font("Century Gothic", 0, 24));
        this.usernameLbl.setForeground(new Color(51, 51, 51)); 
        
        this.passwordLbl = new JLabel("Password:");
        this.passwordLbl.setPreferredSize(new Dimension(140, 50));
        this.passwordLbl.setMaximumSize(new Dimension(140, 50));
        this.passwordLbl.setMinimumSize(new Dimension(140, 50));
        this.passwordLbl.setFont(new Font("Century Gothic", 0, 24));
        this.passwordLbl.setForeground(new Color(51, 51, 51)); 

        // username & password text field
        this.usernameTf = new JTextField();
        this.usernameTf.setPreferredSize(new Dimension(240, 50));
        this.usernameTf.setMaximumSize(new Dimension(240, 50));
        this.usernameTf.setMinimumSize(new Dimension(240, 50));
        this.usernameTf.setFont(new Font("Century Gothic", 0, 18));
        this.usernameTf.setForeground(new Color(51, 51, 51));
        this.usernameTf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)),
                                    BorderFactory.createEmptyBorder(0, 10, 0, 10)));

        this.passwordTf = new JTextField();
        this.passwordTf.setPreferredSize(new Dimension(240, 50));
        this.passwordTf.setMaximumSize(new Dimension(240, 50));
        this.passwordTf.setMinimumSize(new Dimension(240, 50));
        this.passwordTf.setFont(new Font("Century Gothic", 0, 18));
        this.passwordTf.setForeground(new Color(51, 51, 51)); 
        this.passwordTf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)),
                                    BorderFactory.createEmptyBorder(0, 10, 0, 10)));

        // username & password panel
        this.usernamePanel = new JPanel();
        this.usernamePanel.setLayout(new BoxLayout(this.usernamePanel, BoxLayout.X_AXIS));
        this.usernamePanel.setPreferredSize(new Dimension(400, 50));
        this.usernamePanel.setMaximumSize(new Dimension(400, 50));
        this.usernamePanel.setMinimumSize(new Dimension(400, 50));
        this.usernamePanel.add(usernameLbl);
        this.usernamePanel.add(Box.createHorizontalStrut(10));
        this.usernamePanel.add(usernameTf);

        this.passwordPanel = new JPanel();
        this.passwordPanel.setLayout(new BoxLayout(this.passwordPanel, BoxLayout.X_AXIS));
        this.passwordPanel.setPreferredSize(new Dimension(400, 50));
        this.passwordPanel.setMaximumSize(new Dimension(400, 50));
        this.passwordPanel.setMinimumSize(new Dimension(400, 50));
        this.passwordPanel.add(passwordLbl);
        this.passwordPanel.add(Box.createHorizontalStrut(10));
        this.passwordPanel.add(passwordTf);

        // cancel button (back to sign-in options)
        this.cancelBtn = new JButton("Cancel");
        this.cancelBtn.setPreferredSize(new Dimension(140, 50));
        this.cancelBtn.setMaximumSize(new Dimension(140, 50));
        this.cancelBtn.setMinimumSize(new Dimension(140, 50));
        this.cancelBtn.setFont(new Font("Century Gothic", 0, 24));
        this.cancelBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.cancelBtn.setForeground(new Color(51, 51, 51));
        this.cancelBtn.setContentAreaFilled(false);

        // log-in button
        this.logInBtn = new JButton("Log-In");
        this.logInBtn.setPreferredSize(new Dimension(240, 50));
        this.logInBtn.setMaximumSize(new Dimension(240, 50));
        this.logInBtn.setMinimumSize(new Dimension(240, 50));
        this.logInBtn.setFont(new Font("Century Gothic", 0, 24));
        this.logInBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.logInBtn.setForeground(new Color(51, 51, 51));
        this.logInBtn.setContentAreaFilled(false);

        // log-in panel
        this.logInPanel = new JPanel();
        this.logInPanel.setLayout(new BoxLayout(this.logInPanel, BoxLayout.X_AXIS));
        this.logInPanel.setPreferredSize(new Dimension(400, 50));
        this.logInPanel.setMaximumSize(new Dimension(400, 50));
        this.logInPanel.setMinimumSize(new Dimension(400, 50));
        this.logInPanel.add(cancelBtn);
        this.logInPanel.add(Box.createHorizontalStrut(10));
        this.logInPanel.add(logInBtn);

        // error message label
        this.errorLbl = new JLabel("Invalid username or password");
        this.errorLbl.setFont(new Font("Century Gothic", 0, 18));
        this.errorLbl.setForeground(new Color(153, 153, 153));
        this.errorLbl.setVisible(false); // initially hidden

        // center panel components
        this.titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.usernamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.passwordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.logInPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.errorLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add components to panel
        this.panel.add(Box.createVerticalGlue());
        this.panel.add(titleLbl);
        this.panel.add(Box.createRigidArea(new Dimension(0, 30)));
        this.panel.add(separator);
        this.panel.add(Box.createRigidArea(new Dimension(0, 40)));
        this.panel.add(usernamePanel);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20))); 
        this.panel.add(passwordPanel);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20))); 
        this.panel.add(logInPanel);
        this.panel.add(Box.createRigidArea(new Dimension(0, 40))); 
        this.panel.add(errorLbl);
        this.panel.add(Box.createVerticalGlue());
    }

    // listeners & text field getters
    public String getUsernameTfText() {
        return this.usernameTf.getText();
    }

    public String getPasswordTfText() {
        return this.passwordTf.getText();
    }

    public void setLogInBtnListener(ActionListener actionListener) {
        this.logInBtn.addActionListener(actionListener);
    }

    public void setCancelBtnListener(ActionListener actionListener) {
        this.cancelBtn.addActionListener(actionListener);
    }

    // helper methods
    public void showSuccess() { // pop-up when login is successful
        JOptionPane.showMessageDialog(this.panel, "You may now use the Digital Calendar.", "Log-in Successful!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showError() { // if username or password is incorrect
        this.errorLbl.setVisible(true);
    }

    public void hideError() {
        this.errorLbl.setVisible(false);
    }

    public void clearTextFields() {
		this.usernameTf.setText("");
		this.passwordTf.setText("");
	}

    // getters
    public JPanel getPanel() {
        return this.panel;
    }
}