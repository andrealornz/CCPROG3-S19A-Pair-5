import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class AddCalendarView {
    private JPanel panel, panel2, publicPanel, familyPanel;
    private JLabel titleLbl, publicLbl, familyLbl;
    private JList<String> publicList, familyList;
    private DefaultListModel<String> publicListModel, familyListModel;
    private JScrollPane publicScrollPane, familyScrollPane;
    private JButton backBtn;

    private JSeparator separator;
    
    public AddCalendarView() {
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));

        // title label
        this.titleLbl = new JLabel("Available Calendars");
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

        // public & family labels
        this.publicLbl = new JLabel("Public");
        this.publicLbl.setPreferredSize(new Dimension(100, 50));
        this.publicLbl.setMaximumSize(new Dimension(100, 50));
        this.publicLbl.setMinimumSize(new Dimension(100, 50));
        this.publicLbl.setHorizontalAlignment(SwingConstants.CENTER);
        this.publicLbl.setFont(new Font("Century Gothic", 0, 24));
        this.publicLbl.setForeground(new Color(51, 51, 51)); 
        
        this.familyLbl = new JLabel("Family");
        this.familyLbl.setPreferredSize(new Dimension(100, 50));
        this.familyLbl.setMaximumSize(new Dimension(100, 50));
        this.familyLbl.setMinimumSize(new Dimension(100, 50));
        this.familyLbl.setHorizontalAlignment(SwingConstants.CENTER);
        this.familyLbl.setFont(new Font("Century Gothic", 0, 24));
        this.familyLbl.setForeground(new Color(51, 51, 51));

        // public & family list models
        this.publicListModel = new DefaultListModel<>();
        this.publicList = new JList<>(publicListModel);
        this.publicList.setFont(new Font("Century Gothic", 0, 18));
        this.publicList.setForeground(new Color(51, 51, 51));
        this.publicList.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.familyListModel = new DefaultListModel<>();
        this.familyList = new JList<>(familyListModel);
        this.familyList.setFont(new Font("Century Gothic", 0, 18));
        this.familyList.setForeground(new Color(51, 51, 51));
        this.familyList.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // scroll panes for the lists
        this.publicScrollPane = new JScrollPane(publicList);
        this.publicScrollPane.setPreferredSize(new Dimension(400, 280));
        this.publicScrollPane.setMaximumSize(new Dimension(400, 280));
        this.publicScrollPane.setMinimumSize(new Dimension(400, 280));
        this.publicScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.publicScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.publicScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        this.publicScrollPane.getVerticalScrollBar().setBackground(new Color(240, 240, 240));
        this.publicScrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));
        this.publicScrollPane.getHorizontalScrollBar().setBackground(new Color(240, 240, 240));

        this.familyScrollPane = new JScrollPane(familyList);
        this.familyScrollPane.setPreferredSize(new Dimension(400, 280));
        this.familyScrollPane.setMaximumSize(new Dimension(400, 280));
        this.familyScrollPane.setMinimumSize(new Dimension(400, 280));
        this.familyScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.familyScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.familyScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        this.familyScrollPane.getVerticalScrollBar().setBackground(new Color(240, 240, 240));
        this.familyScrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));
        this.familyScrollPane.getHorizontalScrollBar().setBackground(new Color(240, 240, 240));

        // back button
        this.backBtn = new JButton("Back");
        this.backBtn.setPreferredSize(new Dimension(240, 50));
        this.backBtn.setMaximumSize(new Dimension(240, 50));
        this.backBtn.setMinimumSize(new Dimension(240, 50));
        this.backBtn.setFont(new Font("Century Gothic", 0, 24));
        this.backBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.backBtn.setForeground(new Color(51, 51, 51));
        this.backBtn.setContentAreaFilled(false);

        // center panel components
        this.publicLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.familyLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        // owned & added panels (with back & add buttons)
        this.publicPanel = new JPanel();
        this.publicPanel.setLayout(new BoxLayout(this.publicPanel, BoxLayout.Y_AXIS));
        this.publicPanel.setPreferredSize(new Dimension(450, 350));
        this.publicPanel.setMaximumSize(new Dimension(450, 350));
        this.publicPanel.setMinimumSize(new Dimension(450, 350));
        this.publicPanel.add(publicLbl);
        this.publicPanel.add(Box.createVerticalStrut(10));
        this.publicPanel.add(publicScrollPane);
        
        this.familyPanel = new JPanel();
        this.familyPanel.setLayout(new BoxLayout(this.familyPanel, BoxLayout.Y_AXIS));
        this.familyPanel.setPreferredSize(new Dimension(450, 350));
        this.familyPanel.setMaximumSize(new Dimension(450, 350));
        this.familyPanel.setMinimumSize(new Dimension(450, 350));
        this.familyPanel.add(familyLbl);
        this.familyPanel.add(Box.createVerticalStrut(10));
        this.familyPanel.add(familyScrollPane);

        // add both panels to another panel
        this.panel2 = new JPanel();
        this.panel2.setLayout(new BoxLayout(this.panel2, BoxLayout.X_AXIS));
        this.panel2.add(publicPanel);
        this.panel2.add(familyPanel);

        // center panel components
        this.titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.separator.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.panel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add components to panel
        this.panel.add(Box.createVerticalGlue());
        this.panel.add(titleLbl);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(separator);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(panel2);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(backBtn);
        this.panel.add(Box.createVerticalGlue());
    }

    // listeners
    public void setPublicListListener(ListSelectionListener listener) { // when a user clicks a calendar from the list
        this.publicList.addListSelectionListener(listener);
    }

    public void setFamilyListListener(ListSelectionListener listener) {
        this.familyList.addListSelectionListener(listener);
    }

    public String getSelectedPublicCalendar() {
        return this.publicList.getSelectedValue();
    }

    public String getSelectedFamilyCalendar() {
        return this.familyList.getSelectedValue();
    }

    public void setBackBtnListener(ActionListener actionListener) {
        this.backBtn.addActionListener(actionListener);
    }

    // helper methods
    public void addPublicCalendar(String calendarName) {
        this.publicListModel.addElement(calendarName);
    }

    public void addFamilyCalendar(String calendarName) {
        this.familyListModel.addElement(calendarName);
    }

    public void removePublicCalendar(String calendarName) {
        this.publicListModel.removeElement(calendarName);
    }

    public void removeFamilyCalendar(String calendarName) {
        this.familyListModel.removeElement(calendarName);
    }

    public boolean showPublicCalendarConfirmation(String calendarName) { // pop up for adding public calendars
        int option = JOptionPane.showConfirmDialog(
            this.panel,
            "Are you sure you want to add the calendar: " + calendarName + "?",
            "Confirm Add Calendar",
            JOptionPane.YES_NO_OPTION
        );
        return option == JOptionPane.YES_OPTION;
    }

    public String showFamilyCalendarAccessCode(String calendarName) { // returns the access code entered by user
        String accessCode = JOptionPane.showInputDialog(
            this.panel,
            "Enter access code for calendar: " + calendarName,
            "Access Code Required",
            JOptionPane.PLAIN_MESSAGE
        );
        return accessCode; // null if cancelled
    }

    public void showAccessCodeError() { // pop up for incorrect access code
        JOptionPane.showMessageDialog(
            this.panel,
            "Incorrect access code",
            "Access Denied",
            JOptionPane.ERROR_MESSAGE
        );
    }

    public void clearPublicSelection() {
        this.publicList.clearSelection();
    }

    public void clearFamilySelection() {
        this.familyList.clearSelection();
    }

    public void clearAllPublicCalendars() {
        this.publicListModel.clear();
    }

    public void clearAllFamilyCalendars() {
        this.familyListModel.clear();
    }

    // getters
    public JPanel getPanel() {
        return this.panel;
    }
}