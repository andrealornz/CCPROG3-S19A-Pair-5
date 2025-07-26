import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CreateCalendarView {
    private JPanel panel;
    private JLabel titleLbl, menuLbl;
    private JButton personalBtn, familyBtn, publicBtn, backBtn;
    private JSeparator separator;

    public CreateCalendarView() {
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        
        // title label
        this.titleLbl = new JLabel("Create New Calendar");
        this.titleLbl.setPreferredSize(new Dimension(600, 70));
        this.titleLbl.setMaximumSize(new Dimension(600, 70));
        this.titleLbl.setMinimumSize(new Dimension(600, 70));
        this.titleLbl.setFont(new Font("Century Gothic", 1, 48));
        this.titleLbl.setForeground(new Color(51, 51, 51));
        this.titleLbl.setHorizontalAlignment(SwingConstants.CENTER);

        this.menuLbl = new JLabel("Select Calendar Type");
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
        this.personalBtn = new JButton("Personal");
        this.personalBtn.setPreferredSize(new Dimension(240, 50));
        this.personalBtn.setMaximumSize(new Dimension(240, 50));
        this.personalBtn.setMinimumSize(new Dimension(240, 50));
        this.personalBtn.setFont(new Font("Century Gothic", 0, 24));
        this.personalBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.personalBtn.setForeground(new Color(51, 51, 51));
        this.personalBtn.setContentAreaFilled(false);

        this.familyBtn = new JButton("Family");
        this.familyBtn.setPreferredSize(new Dimension(240, 50));
        this.familyBtn.setMaximumSize(new Dimension(240, 50));
        this.familyBtn.setMinimumSize(new Dimension(240, 50));
        this.familyBtn.setFont(new Font("Century Gothic", 0, 24));
        this.familyBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.familyBtn.setForeground(new Color(51, 51, 51));
        this.familyBtn.setContentAreaFilled(false);

        this.publicBtn = new JButton("Public");
        this.publicBtn.setPreferredSize(new Dimension(240, 50));
        this.publicBtn.setMaximumSize(new Dimension(240, 50));
        this.publicBtn.setMinimumSize(new Dimension(240, 50));
        this.publicBtn.setFont(new Font("Century Gothic", 0, 24));
        this.publicBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.publicBtn.setForeground(new Color(51, 51, 51));
        this.publicBtn.setContentAreaFilled(false);

        this.backBtn = new JButton("Back");
        this.backBtn.setPreferredSize(new Dimension(240, 50));
        this.backBtn.setMaximumSize(new Dimension(240, 50));
        this.backBtn.setMinimumSize(new Dimension(240, 50));
        this.backBtn.setFont(new Font("Century Gothic", 0, 24));
        this.backBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.backBtn.setForeground(new Color(51, 51, 51));
        this.backBtn.setContentAreaFilled(false);

        // center panel components
        this.titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.menuLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.personalBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.familyBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.publicBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.separator.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add components to panel
        this.panel.add(Box.createVerticalGlue());
        this.panel.add(titleLbl);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(separator);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(menuLbl);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(personalBtn);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));  
        this.panel.add(familyBtn);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(publicBtn);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(backBtn);
        this.panel.add(Box.createVerticalGlue());
    }
    
    // listeners
    public void setPersonalBtnListener(ActionListener actionListener) {
        this.personalBtn.addActionListener(actionListener);
    }
    
    public void setFamilyBtnListener(ActionListener actionListener) {
        this.familyBtn.addActionListener(actionListener);
    }

    public void setPublicBtnListener(ActionListener actionListener) {
        this.publicBtn.addActionListener(actionListener);
    }

    public void setBackBtnListener(ActionListener actionListener) {
        this.backBtn.addActionListener(actionListener);
    }

    // getters
    public JPanel getPanel() {
        return this.panel;
    }
}