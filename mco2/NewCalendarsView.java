import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NewCalendarsView {
    private JPanel panel;
    private JLabel titleLbl;
    private JButton createBtn, addBtn, backBtn;
    private JSeparator separator;

    public NewCalendarsView() {
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        this.panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        
        // title label
        this.titleLbl = new JLabel("Add New Calendars");
        this.titleLbl.setPreferredSize(new Dimension(500, 70));
        this.titleLbl.setMaximumSize(new Dimension(500, 70));
        this.titleLbl.setMinimumSize(new Dimension(500, 70));
        this.titleLbl.setFont(new Font("Century Gothic", 1, 48));
        this.titleLbl.setForeground(new Color(51, 51, 51));
        this.titleLbl.setHorizontalAlignment(SwingConstants.CENTER);

        // separator
        this.separator = new JSeparator();
        this.separator.setPreferredSize(new Dimension(500, 1));
        this.separator.setMaximumSize(new Dimension(500, 1));
        this.separator.setMinimumSize(new Dimension(500, 1));
        this.separator.setForeground(new Color(51, 51, 51));

        // buttons
        this.createBtn = new JButton("Create New");
        this.createBtn.setPreferredSize(new Dimension(240, 50));
        this.createBtn.setMaximumSize(new Dimension(240, 50));
        this.createBtn.setMinimumSize(new Dimension(240, 50));
        this.createBtn.setFont(new Font("Century Gothic", 0, 24));
        this.createBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.createBtn.setForeground(new Color(51, 51, 51));
        this.createBtn.setContentAreaFilled(false);

        this.addBtn = new JButton("Add Existing");
        this.addBtn.setPreferredSize(new Dimension(240, 50));
        this.addBtn.setMaximumSize(new Dimension(240, 50));
        this.addBtn.setMinimumSize(new Dimension(240, 50));
        this.addBtn.setFont(new Font("Century Gothic", 0, 24));
        this.addBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.addBtn.setForeground(new Color(51, 51, 51));
        this.addBtn.setContentAreaFilled(false);

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
        this.createBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.separator.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add components to panel
        this.panel.add(Box.createVerticalGlue());
        this.panel.add(titleLbl);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(separator);
        this.panel.add(Box.createRigidArea(new Dimension(0, 40)));
        this.panel.add(createBtn);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));  
        this.panel.add(addBtn);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(backBtn);
        this.panel.add(Box.createVerticalGlue());
    }
    
    // listeners
    public void setCreateBtnListener(ActionListener actionListener) {
        this.createBtn.addActionListener(actionListener);
    }
    
    public void setAddBtnListener(ActionListener actionListener) {
        this.addBtn.addActionListener(actionListener);
    }

    public void setBackBtnListener(ActionListener actionListener) {
        this.backBtn.addActionListener(actionListener);
    }

    // getters
    public JPanel getPanel() {
        return this.panel;
    }
}