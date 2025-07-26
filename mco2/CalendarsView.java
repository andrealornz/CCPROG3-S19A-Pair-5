import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class CalendarsView {
    private JPanel panel, panel2, ownedPanel, addedPanel;
    private JLabel titleLbl, ownedLbl, addedLbl;
    private JList<String> ownedList, addedList;
    private DefaultListModel<String> ownedListModel, addedListModel;
    private JScrollPane ownedScrollPane, addedScrollPane;
    private JButton addBtn, backBtn;

    private JSeparator separator;
    
    public CalendarsView() {
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));

        // title label
        this.titleLbl = new JLabel("Your Calendars");
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

        // owned & added labels
        this.ownedLbl = new JLabel("Owned");
        this.ownedLbl.setPreferredSize(new Dimension(100, 50));
        this.ownedLbl.setMaximumSize(new Dimension(100, 50));
        this.ownedLbl.setMinimumSize(new Dimension(100, 50));
        this.ownedLbl.setHorizontalAlignment(SwingConstants.CENTER);
        this.ownedLbl.setFont(new Font("Century Gothic", 0, 24));
        this.ownedLbl.setForeground(new Color(51, 51, 51)); 
        
        this.addedLbl = new JLabel("Added");
        this.addedLbl.setPreferredSize(new Dimension(100, 50));
        this.addedLbl.setMaximumSize(new Dimension(100, 50));
        this.addedLbl.setMinimumSize(new Dimension(100, 50));
        this.addedLbl.setHorizontalAlignment(SwingConstants.CENTER);
        this.addedLbl.setFont(new Font("Century Gothic", 0, 24));
        this.addedLbl.setForeground(new Color(51, 51, 51));

        // owned & added list models
        this.ownedListModel = new DefaultListModel<>();
        this.ownedList = new JList<>(ownedListModel);
        this.ownedList.setFont(new Font("Century Gothic", 0, 18));
        this.ownedList.setForeground(new Color(51, 51, 51));
        this.ownedList.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.addedListModel = new DefaultListModel<>();
        this.addedList = new JList<>(addedListModel);
        this.addedList.setFont(new Font("Century Gothic", 0, 18));
        this.addedList.setForeground(new Color(51, 51, 51));
        this.addedList.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // scroll panes for the lists
        this.ownedScrollPane = new JScrollPane(ownedList);
        this.ownedScrollPane.setPreferredSize(new Dimension(400, 280));
        this.ownedScrollPane.setMaximumSize(new Dimension(400, 280));
        this.ownedScrollPane.setMinimumSize(new Dimension(400, 280));
        this.ownedScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.ownedScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.ownedScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        this.ownedScrollPane.getVerticalScrollBar().setBackground(new Color(240, 240, 240));
        this.ownedScrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));
        this.ownedScrollPane.getHorizontalScrollBar().setBackground(new Color(240, 240, 240));

        this.addedScrollPane = new JScrollPane(addedList);
        this.addedScrollPane.setPreferredSize(new Dimension(400, 280));
        this.addedScrollPane.setMaximumSize(new Dimension(400, 280));
        this.addedScrollPane.setMinimumSize(new Dimension(400, 280));
        this.addedScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.addedScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.addedScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        this.addedScrollPane.getVerticalScrollBar().setBackground(new Color(240, 240, 240));
        this.addedScrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));
        this.addedScrollPane.getHorizontalScrollBar().setBackground(new Color(240, 240, 240));

        // add & back buttons
        this.addBtn = new JButton("Add New");
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
        this.ownedLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.addedLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // owned & added panels (with back & add buttons)
        this.ownedPanel = new JPanel();
        this.ownedPanel.setLayout(new BoxLayout(this.ownedPanel, BoxLayout.Y_AXIS));
        this.ownedPanel.setPreferredSize(new Dimension(450, 440));
        this.ownedPanel.setMaximumSize(new Dimension(450, 440));
        this.ownedPanel.setMinimumSize(new Dimension(450, 440));
        this.ownedPanel.add(ownedLbl);
        this.ownedPanel.add(Box.createVerticalStrut(10));
        this.ownedPanel.add(ownedScrollPane);
        this.ownedPanel.add(Box.createVerticalStrut(30));
        this.ownedPanel.add(backBtn);
        
        this.addedPanel = new JPanel();
        this.addedPanel.setLayout(new BoxLayout(this.addedPanel, BoxLayout.Y_AXIS));
        this.addedPanel.setPreferredSize(new Dimension(450, 440));
        this.addedPanel.setMaximumSize(new Dimension(450, 440));
        this.addedPanel.setMinimumSize(new Dimension(450, 440));
        this.addedPanel.add(addedLbl);
        this.addedPanel.add(Box.createVerticalStrut(10));
        this.addedPanel.add(addedScrollPane);
        this.addedPanel.add(Box.createVerticalStrut(30));
        this.addedPanel.add(addBtn);

        // add both panels to another panel
        this.panel2 = new JPanel();
        this.panel2.setLayout(new BoxLayout(this.panel2, BoxLayout.X_AXIS));
        this.panel2.add(ownedPanel);
        this.panel2.add(addedPanel);

        // center panel components
        this.titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.separator.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.panel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add components to panel
        this.panel.add(Box.createVerticalGlue());
        this.panel.add(titleLbl);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(separator);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        this.panel.add(panel2);
        this.panel.add(Box.createVerticalGlue());
    }

    // listeners
    public void setOwnedListListener(ListSelectionListener listener) { // when a user clicks a calendar from the list
        this.ownedList.addListSelectionListener(listener);
    }

    public void setAddedListListener(ListSelectionListener listener) {
        this.addedList.addListSelectionListener(listener);
    }

    public String getSelectedOwnedCalendar() {
        return this.ownedList.getSelectedValue();
    }

    public String getSelectedAddedCalendar() {
        return this.addedList.getSelectedValue();
    }

    public void setBackBtnListener(ActionListener actionListener) {
        this.backBtn.addActionListener(actionListener);
    }

    public void setAddBtnListener(ActionListener actionListener) {
        this.addBtn.addActionListener(actionListener);
    }

    // helper methods
    public void addOwnedCalendar(String calendarName) {
        this.ownedListModel.addElement(calendarName);
    }

    public void addAddedCalendar(String calendarName) {
        this.addedListModel.addElement(calendarName);
    }

    public void removeOwnedCalendar(String calendarName) {
        this.ownedListModel.removeElement(calendarName);
    }

    public void removeAddedCalendar(String calendarName) {
        this.addedListModel.removeElement(calendarName);
    }

    // getters
    public JPanel getPanel() {
        return this.panel;
    }
}