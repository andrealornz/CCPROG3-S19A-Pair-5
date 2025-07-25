import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AccountSettingsView {
    private JPanel panel;
    private JLabel titleLbl;
    private JButton logOutBtn, deleteBtn, backBtn;
    private JSeparator separator;

    public AccountSettingsView() {
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        
        // title label
        this.titleLbl = new JLabel("Account Settings");
        this.titleLbl.setPreferredSize(new Dimension(500, 70));
        this.titleLbl.setMaximumSize(new Dimension(500, 70));
        this.titleLbl.setFont(new Font("Century Gothic", 1, 48));
        this.titleLbl.setForeground(new Color(51, 51, 51));
        this.titleLbl.setHorizontalAlignment(SwingConstants.CENTER);

        // separator
        this.separator = new JSeparator();
        this.separator.setMaximumSize(new Dimension(500, 1));
        this.separator.setForeground(new Color(51, 51, 51));

        // buttons
        this.logOutBtn = new JButton("Log-Out");
        this.logOutBtn.setPreferredSize(new Dimension(240, 50));
        this.logOutBtn.setMaximumSize(new Dimension(240, 50));
        this.logOutBtn.setFont(new Font("Century Gothic", 0, 24));
        this.logOutBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.logOutBtn.setForeground(new Color(51, 51, 51));
        this.logOutBtn.setContentAreaFilled(false);

        this.deleteBtn = new JButton("Delete Account");
        this.deleteBtn.setPreferredSize(new Dimension(240, 50));
        this.deleteBtn.setMaximumSize(new Dimension(240, 50));
        this.deleteBtn.setFont(new Font("Century Gothic", 0, 24));
        this.deleteBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.deleteBtn.setForeground(new Color(51, 51, 51));
        this.deleteBtn.setContentAreaFilled(false);

        this.backBtn = new JButton("Back");
        this.backBtn.setPreferredSize(new Dimension(240, 50));
        this.backBtn.setMaximumSize(new Dimension(240, 50));
        this.backBtn.setFont(new Font("Century Gothic", 0, 24));
        this.backBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.backBtn.setForeground(new Color(51, 51, 51));
        this.backBtn.setContentAreaFilled(false);

        // center panel components
        this.titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.logOutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.deleteBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.separator.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add components to panel
        this.panel.add(Box.createVerticalGlue());
        this.panel.add(titleLbl);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(separator);
        this.panel.add(Box.createRigidArea(new Dimension(0, 40)));
        this.panel.add(logOutBtn);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));  
        this.panel.add(deleteBtn);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(backBtn);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(Box.createVerticalGlue());
    }
    
    // listeners
    public void setLogOutBtnListener(ActionListener actionListener) {
        this.logOutBtn.addActionListener(actionListener);
    }
    
    public void setDeleteBtnListener(ActionListener actionListener) {
        this.deleteBtn.addActionListener(actionListener);
    }

    public void setBackBtnListener(ActionListener actionListener) {
        this.backBtn.addActionListener(actionListener);
    }

    // helper methods
    // used for log-out, delete account, and exit
    public boolean showConfirmDialog() { // both options will go back to the settings menu (except for exit)
        int option = JOptionPane.showConfirmDialog(this.panel, "Are you sure you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
        return option == JOptionPane.YES_OPTION;
    }

    // getters
    public JPanel getPanel() {
        return this.panel;
    }
}