import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuView {
    private JPanel panel;
    private JLabel titleLbl, menuLbl;
    private JButton calendarsBtn, settingsBtn, exitBtn;
    private JSeparator separator;

    public MainMenuView() {
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        
        // title label
        this.titleLbl = new JLabel("The Digital Calendar");
        this.titleLbl.setPreferredSize(new Dimension(500, 70));
        this.titleLbl.setMaximumSize(new Dimension(500, 70));
        this.titleLbl.setMinimumSize(new Dimension(500, 70));
        this.titleLbl.setFont(new Font("Century Gothic", 1, 48));
        this.titleLbl.setForeground(new Color(51, 51, 51));
        this.titleLbl.setHorizontalAlignment(SwingConstants.CENTER);

        this.menuLbl = new JLabel("Main Menu");
        this.menuLbl.setPreferredSize(new Dimension(500, 50));
        this.menuLbl.setMaximumSize(new Dimension(500, 50));
        this.menuLbl.setMinimumSize(new Dimension(500, 50));
        this.menuLbl.setFont(new Font("Century Gothic", 0, 24));
        this.menuLbl.setForeground(new Color(51, 51, 51));
        this.menuLbl.setHorizontalAlignment(SwingConstants.CENTER);

        // separator
        this.separator = new JSeparator();
        this.separator.setPreferredSize(new Dimension(500, 1));
        this.separator.setMaximumSize(new Dimension(500, 1));
        this.separator.setMinimumSize(new Dimension(500, 1));
        this.separator.setForeground(new Color(51, 51, 51));

        // buttons
        this.calendarsBtn = new JButton("Calendars");
        this.calendarsBtn.setPreferredSize(new Dimension(240, 50));
        this.calendarsBtn.setMaximumSize(new Dimension(240, 50));
        this.calendarsBtn.setMinimumSize(new Dimension(240, 50));
        this.calendarsBtn.setFont(new Font("Century Gothic", 0, 24));
        this.calendarsBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.calendarsBtn.setForeground(new Color(51, 51, 51));
        this.calendarsBtn.setContentAreaFilled(false);

        this.settingsBtn = new JButton("Settings");
        this.settingsBtn.setPreferredSize(new Dimension(240, 50));
        this.settingsBtn.setMaximumSize(new Dimension(240, 50));
        this.settingsBtn.setMinimumSize(new Dimension(240, 50));
        this.settingsBtn.setFont(new Font("Century Gothic", 0, 24));
        this.settingsBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.settingsBtn.setForeground(new Color(51, 51, 51));
        this.settingsBtn.setContentAreaFilled(false);

        this.exitBtn = new JButton("Exit");
        this.exitBtn.setPreferredSize(new Dimension(240, 50));
        this.exitBtn.setMaximumSize(new Dimension(240, 50));
        this.exitBtn.setMinimumSize(new Dimension(240, 50));
        this.exitBtn.setFont(new Font("Century Gothic", 0, 24));
        this.exitBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.exitBtn.setForeground(new Color(51, 51, 51));
        this.exitBtn.setContentAreaFilled(false);

        // center panel components
        this.titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.menuLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.calendarsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.settingsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.separator.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add components to panel
        this.panel.add(Box.createVerticalGlue());
        this.panel.add(titleLbl);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(separator);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(menuLbl);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(calendarsBtn);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));  
        this.panel.add(settingsBtn);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(exitBtn);
        this.panel.add(Box.createVerticalGlue());
    }
    
    // listeners
    public void setCalendarsBtnListener(ActionListener actionListener) {
        this.calendarsBtn.addActionListener(actionListener);
    }
    
    public void setSettingsBtnListener(ActionListener actionListener) {
        this.settingsBtn.addActionListener(actionListener);
    }

    public void setExitBtnListener(ActionListener actionListener) {
        this.exitBtn.addActionListener(actionListener);
    }

    // helper methods
    public boolean showConfirmDialog() { // use for exit
        int option = JOptionPane.showConfirmDialog(this.panel, "Log-out and exit application?", "Confirmation", JOptionPane.YES_NO_OPTION);
        return option == JOptionPane.YES_OPTION;
    }

    // getters
    public JPanel getPanel() {
        return this.panel;
    }
}