import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddEntryView {
    private JPanel panel;
    private JLabel titleLbl, menuLbl;
    private JButton taskBtn, eventBtn, meetingBtn, journalBtn, backBtn;
    private JSeparator separator;

    public AddEntryView() {
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        
        // title label
        this.titleLbl = new JLabel("Add New Enrty");
        this.titleLbl.setPreferredSize(new Dimension(600, 50));
        this.titleLbl.setMaximumSize(new Dimension(600, 50));
        this.titleLbl.setMinimumSize(new Dimension(600, 50));
        this.titleLbl.setFont(new Font("Century Gothic", 1, 48));
        this.titleLbl.setForeground(new Color(51, 51, 51));
        this.titleLbl.setHorizontalAlignment(SwingConstants.CENTER);

        this.menuLbl = new JLabel("Select Entry Type");
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
        this.taskBtn = new JButton("Task");
        this.taskBtn.setPreferredSize(new Dimension(240, 50));
        this.taskBtn.setMaximumSize(new Dimension(240, 50));
        this.taskBtn.setMinimumSize(new Dimension(240, 50));
        this.taskBtn.setFont(new Font("Century Gothic", 0, 24));
        this.taskBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.taskBtn.setForeground(new Color(51, 51, 51));
        this.taskBtn.setContentAreaFilled(false);

        this.eventBtn = new JButton("Event");
        this.eventBtn.setPreferredSize(new Dimension(240, 50));
        this.eventBtn.setMaximumSize(new Dimension(240, 50));
        this.eventBtn.setMinimumSize(new Dimension(240, 50));
        this.eventBtn.setFont(new Font("Century Gothic", 0, 24));
        this.eventBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.eventBtn.setForeground(new Color(51, 51, 51));
        this.eventBtn.setContentAreaFilled(false);

        this.meetingBtn = new JButton("Meeting");
        this.meetingBtn.setPreferredSize(new Dimension(240, 50));
        this.meetingBtn.setMaximumSize(new Dimension(240, 50));
        this.meetingBtn.setMinimumSize(new Dimension(240, 50));
        this.meetingBtn.setFont(new Font("Century Gothic", 0, 24));
        this.meetingBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.meetingBtn.setForeground(new Color(51, 51, 51));
        this.meetingBtn.setContentAreaFilled(false);

        this.journalBtn = new JButton("Journal");
        this.journalBtn.setPreferredSize(new Dimension(240, 50));
        this.journalBtn.setMaximumSize(new Dimension(240, 50));
        this.journalBtn.setMinimumSize(new Dimension(240, 50));
        this.journalBtn.setFont(new Font("Century Gothic", 0, 24));
        this.journalBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.journalBtn.setForeground(new Color(51, 51, 51));
        this.journalBtn.setContentAreaFilled(false);

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
        this.taskBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.eventBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.meetingBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.journalBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.separator.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add components to panel
        this.panel.add(Box.createVerticalGlue());
        this.panel.add(titleLbl);
        this.panel.add(Box.createRigidArea(new Dimension(0, 35)));
        this.panel.add(separator);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(menuLbl);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(taskBtn);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));  
        this.panel.add(eventBtn);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(meetingBtn);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(journalBtn);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(backBtn);
        this.panel.add(Box.createVerticalGlue());
    }
    
    // listeners
    public void setTaskBtnListener(ActionListener actionListener) {
        this.taskBtn.addActionListener(actionListener);
    }
    
    public void setEventBtnListener(ActionListener actionListener) {
        this.eventBtn.addActionListener(actionListener);
    }

    public void setMeetingBtnListener(ActionListener actionListener) {
        this.meetingBtn.addActionListener(actionListener);
    }

    public void setJournalBtnListener(ActionListener actionListener) {
        this.journalBtn.addActionListener(actionListener);
    }

    public void setBackBtnListener(ActionListener actionListener) {
        this.backBtn.addActionListener(actionListener);
    }

    // getters
    public JPanel getPanel() {
        return this.panel;
    }
}