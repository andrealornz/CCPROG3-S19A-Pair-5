import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SignInView {
    private JPanel panel;
    private JLabel titleLbl, menuLbl;
    private JButton logInBtn, signUpBtn, exitBtn;
    private JSeparator separator;

    public SignInView() {
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));

        // title label
        this.titleLbl = new JLabel("The Digital Calendar");
        this.titleLbl.setPreferredSize(new Dimension(500, 70));
        this.titleLbl.setMaximumSize(new Dimension(500, 70));
        this.titleLbl.setFont(new Font("Century Gothic", 1, 48));
        this.titleLbl.setForeground(new Color(51, 51, 51));
        this.titleLbl.setHorizontalAlignment(SwingConstants.CENTER);

        this.menuLbl = new JLabel("Sign-In Options");
        this.menuLbl.setPreferredSize(new Dimension(500, 50));
        this.menuLbl.setMaximumSize(new Dimension(500, 50));
        this.menuLbl.setFont(new Font("Century Gothic", 0, 24));
        this.menuLbl.setForeground(new Color(51, 51, 51));
        this.menuLbl.setHorizontalAlignment(SwingConstants.CENTER);

        // separator
        this.separator = new JSeparator();
        this.separator.setMaximumSize(new Dimension(500, 1));
        this.separator.setForeground(new Color(51, 51, 51));

        // buttons
        this.logInBtn = new JButton("Log-In");
        this.logInBtn.setPreferredSize(new Dimension(240, 50));
        this.logInBtn.setMaximumSize(new Dimension(240, 50));
        this.logInBtn.setFont(new Font("Century Gothic", 0, 24));
        this.logInBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.logInBtn.setForeground(new Color(51, 51, 51));
        this.logInBtn.setContentAreaFilled(false);

        this.signUpBtn = new JButton("Sign-Up");
        this.signUpBtn.setPreferredSize(new Dimension(240, 50));
        this.signUpBtn.setMaximumSize(new Dimension(240, 50));
        this.signUpBtn.setFont(new Font("Century Gothic", 0, 24));
        this.signUpBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.signUpBtn.setForeground(new Color(51, 51, 51));
        this.signUpBtn.setContentAreaFilled(false);

        this.exitBtn = new JButton("Exit");
        this.exitBtn.setPreferredSize(new Dimension(240, 50));
        this.exitBtn.setMaximumSize(new Dimension(240, 50));
        this.exitBtn.setFont(new Font("Century Gothic", 0, 24));
        this.exitBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.exitBtn.setForeground(new Color(51, 51, 51));
        this.exitBtn.setContentAreaFilled(false);

        // center panel components
        this.titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.menuLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.logInBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.signUpBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.separator.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add components to panel
        this.panel.add(Box.createVerticalGlue());        
        this.panel.add(titleLbl);
        this.panel.add(Box.createRigidArea(new Dimension(0, 10)));
        this.panel.add(separator);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(menuLbl);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(logInBtn);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));  
        this.panel.add(signUpBtn);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(exitBtn);
        this.panel.add(Box.createVerticalGlue());
    }
    
    // listeners
    public void setLogInBtnListener(ActionListener actionListener) {
        this.logInBtn.addActionListener(actionListener);
    }
    
    public void setSignUpBtnListener(ActionListener actionListener) {
        this.signUpBtn.addActionListener(actionListener);
    }

    public void setExitBtnListener(ActionListener actionListener) {
        this.exitBtn.addActionListener(actionListener);
    }

    // helper methods
    public boolean showConfirmDialog() { // use for exit
        int option = JOptionPane.showConfirmDialog(this.panel, "Exit application?", "Confirmation", JOptionPane.YES_NO_OPTION);
        return option == JOptionPane.YES_OPTION;
    }

    // getters
    public JPanel getPanel() {
        return this.panel;
    }
}