import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

public class JournalEntryView {
    private JPanel panel, datePanel, entryTitlePanel, descriptionPanel, addPanel;
    private JLabel titleLbl, dateLbl, entryTitleLbl, descriptionLbl, errorLbl;
    private JTextField entryTitleTf;
    private JTextArea descriptionTa;
    private JScrollPane descriptionScrollPane;
    private JComboBox<Integer> dayBox, monthBox, yearBox;
    private JButton cancelBtn, addBtn;
    private JPanel dateTitlePanel;
    private JSeparator separator;

    public JournalEntryView() {
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));

        // title label
        this.titleLbl = new JLabel("Add Journal Entry");
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

        // date label
        this.dateLbl = new JLabel("Date:");
        this.dateLbl.setPreferredSize(new Dimension(140, 50));
        this.dateLbl.setMaximumSize(new Dimension(140, 50));
        this.dateLbl.setMinimumSize(new Dimension(140, 50));
        this.dateLbl.setFont(new Font("Century Gothic", 0, 24));
        this.dateLbl.setForeground(new Color(51, 51, 51)); 

        // get current date
        LocalDate now = LocalDate.now();
        int currentMonth = now.getMonthValue();
        int currentYear = now.getYear();
        int currentDay = now.getDayOfMonth();

        // date combo boxes
        this.dayBox = new JComboBox<>();
        this.dayBox.setPreferredSize(new Dimension(70, 50));
        this.dayBox.setMaximumSize(new Dimension(70, 50));
        this.dayBox.setMinimumSize(new Dimension(70, 50));
        this.dayBox.setFont(new Font("Century Gothic", 0, 20));
        this.dayBox.setForeground(new Color(51, 51, 51));
        this.dayBox.setBackground(new Color(242,242,242));
        for (int i = 1; i <= 31; i++) {
            dayBox.addItem(i);
        }
        this.dayBox.setSelectedItem(currentDay);

        this.monthBox = new JComboBox<>();
        this.monthBox.setPreferredSize(new Dimension(70, 50));
        this.monthBox.setMaximumSize(new Dimension(70, 50));
        this.monthBox.setMinimumSize(new Dimension(70, 50));
        this.monthBox.setFont(new Font("Century Gothic", 0, 20));
        this.monthBox.setForeground(new Color(51, 51, 51));
        this.monthBox.setBackground(new Color(242,242,242));
        for (int i = 1; i <= 12; i++) {
            monthBox.addItem(i);
        }
        this.monthBox.setSelectedItem(currentMonth);

        this.yearBox = new JComboBox<>();
        this.yearBox.setPreferredSize(new Dimension(80, 50));
        this.yearBox.setMaximumSize(new Dimension(80, 50));
        this.yearBox.setMinimumSize(new Dimension(80, 50));
        this.yearBox.setFont(new Font("Century Gothic", 0, 20));
        this.yearBox.setForeground(new Color(51, 51, 51));
        this.yearBox.setBackground(new Color(242,242,242));
        for (int i = 1000; i <= 9999; i++) {
            yearBox.addItem(i);
        }
        this.yearBox.setSelectedItem(currentYear);

        // date panel
        this.datePanel = new JPanel();
        this.datePanel.setLayout(new BoxLayout(this.datePanel, BoxLayout.X_AXIS));
        this.datePanel.setPreferredSize(new Dimension(400, 50));
        this.datePanel.setMaximumSize(new Dimension(400, 50));
        this.datePanel.setMinimumSize(new Dimension(400, 50));
        this.datePanel.add(dateLbl);
        this.datePanel.add(Box.createHorizontalStrut(10));
        this.datePanel.add(monthBox);
        this.datePanel.add(Box.createHorizontalStrut(10));
        this.datePanel.add(dayBox);
        this.datePanel.add(Box.createHorizontalStrut(10));
        this.datePanel.add(yearBox);

        // entry title label
        this.entryTitleLbl = new JLabel("Title:");
        this.entryTitleLbl.setPreferredSize(new Dimension(140, 50));
        this.entryTitleLbl.setMaximumSize(new Dimension(140, 50));
        this.entryTitleLbl.setMinimumSize(new Dimension(140, 50));
        this.entryTitleLbl.setFont(new Font("Century Gothic", 0, 24));
        this.entryTitleLbl.setForeground(new Color(51, 51, 51));

        // entry title text field
        this.entryTitleTf = new JTextField();
        this.entryTitleTf.setPreferredSize(new Dimension(240, 50));
        this.entryTitleTf.setMaximumSize(new Dimension(240, 50));
        this.entryTitleTf.setMinimumSize(new Dimension(240, 50));
        this.entryTitleTf.setFont(new Font("Century Gothic", 0, 18));
        this.entryTitleTf.setForeground(new Color(51, 51, 51)); 
        this.entryTitleTf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)),
                                    BorderFactory.createEmptyBorder(0, 10, 0, 10)));

        // entry title panel
        this.entryTitlePanel = new JPanel();
        this.entryTitlePanel.setLayout(new BoxLayout(this.entryTitlePanel, BoxLayout.X_AXIS));
        this.entryTitlePanel.setPreferredSize(new Dimension(400, 50));
        this.entryTitlePanel.setMaximumSize(new Dimension(400, 50));
        this.entryTitlePanel.setMinimumSize(new Dimension(400, 50));
        this.entryTitlePanel.add(entryTitleLbl);
        this.entryTitlePanel.add(Box.createHorizontalStrut(10));
        this.entryTitlePanel.add(entryTitleTf);

        // description label
        this.descriptionLbl = new JLabel("Description:");
        this.descriptionLbl.setPreferredSize(new Dimension(140, 50));
        this.descriptionLbl.setMaximumSize(new Dimension(140, 50));
        this.descriptionLbl.setMinimumSize(new Dimension(140, 50));
        this.descriptionLbl.setFont(new Font("Century Gothic", 0, 24));
        this.descriptionLbl.setForeground(new Color(51, 51, 51));
        this.descriptionLbl.setAlignmentY(Component.CENTER_ALIGNMENT);

        // description text area
        this.descriptionTa = new JTextArea();
        this.descriptionTa.setFont(new Font("Century Gothic", 0, 18));
        this.descriptionTa.setForeground(new Color(51, 51, 51));
        this.descriptionTa.setBackground(new Color(255, 255, 255));
        this.descriptionTa.setLineWrap(true);
        this.descriptionTa.setWrapStyleWord(true);
        this.descriptionTa.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // description scroll pane
        this.descriptionScrollPane = new JScrollPane(this.descriptionTa);
        this.descriptionScrollPane.setPreferredSize(new Dimension(660, 240));
        this.descriptionScrollPane.setMaximumSize(new Dimension(660, 240));
        this.descriptionScrollPane.setMinimumSize(new Dimension(660, 240));
        this.descriptionScrollPane.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.descriptionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.descriptionScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.descriptionScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        this.descriptionScrollPane.getVerticalScrollBar().setBackground(new Color(240, 240, 240));

        // description panel
        this.descriptionPanel = new JPanel();
        this.descriptionPanel.setLayout(new BoxLayout(this.descriptionPanel, BoxLayout.X_AXIS));
        this.descriptionPanel.setPreferredSize(new Dimension(810, 240)); 
        this.descriptionPanel.setMaximumSize(new Dimension(810, 240));
        this.descriptionPanel.setMinimumSize(new Dimension(810, 240));
        this.descriptionPanel.add(descriptionLbl);
        this.descriptionPanel.add(Box.createHorizontalStrut(10));
        this.descriptionPanel.add(descriptionScrollPane);

        // cancel button (back to select entry type)
        this.cancelBtn = new JButton("Cancel");
        this.cancelBtn.setPreferredSize(new Dimension(240, 50));
        this.cancelBtn.setMaximumSize(new Dimension(240, 50));
        this.cancelBtn.setMinimumSize(new Dimension(240, 50));
        this.cancelBtn.setFont(new Font("Century Gothic", 0, 24));
        this.cancelBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.cancelBtn.setForeground(new Color(51, 51, 51));
        this.cancelBtn.setContentAreaFilled(false);

        // add button
        this.addBtn = new JButton("Add");
        this.addBtn.setPreferredSize(new Dimension(240, 50));
        this.addBtn.setMaximumSize(new Dimension(240, 50));
        this.addBtn.setMinimumSize(new Dimension(240, 50));
        this.addBtn.setFont(new Font("Century Gothic", 0, 24));
        this.addBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.addBtn.setForeground(new Color(51, 51, 51));
        this.addBtn.setContentAreaFilled(false);

        // add panel
        this.addPanel = new JPanel();
        this.addPanel.setLayout(new BoxLayout(this.addPanel, BoxLayout.X_AXIS));
        this.addPanel.setPreferredSize(new Dimension(810, 50));
        this.addPanel.setMaximumSize(new Dimension(810, 50));
        this.addPanel.setMinimumSize(new Dimension(810, 50));
        this.addPanel.add(Box.createHorizontalGlue());
        this.addPanel.add(cancelBtn);
        this.addPanel.add(Box.createHorizontalStrut(20));
        this.addPanel.add(addBtn);
        this.addPanel.add(Box.createHorizontalGlue());

        // error message label
        this.errorLbl = new JLabel("Cannot create entry");
        this.errorLbl.setFont(new Font("Century Gothic", 0, 18));
        this.errorLbl.setForeground(new Color(153, 153, 153));
        this.errorLbl.setVisible(false); // initially hidden

        // date & title panel
        this.dateTitlePanel = new JPanel();
        this.dateTitlePanel.setLayout(new BoxLayout(this.dateTitlePanel, BoxLayout.X_AXIS));
        this.dateTitlePanel.setPreferredSize(new Dimension(810, 50));
        this.dateTitlePanel.setMaximumSize(new Dimension(810, 50));
        this.dateTitlePanel.setMinimumSize(new Dimension(810, 50));
        this.dateTitlePanel.add(Box.createHorizontalGlue());
        this.dateTitlePanel.add(datePanel);
        this.dateTitlePanel.add(Box.createHorizontalStrut(20));
        this.dateTitlePanel.add(entryTitlePanel);
        this.dateTitlePanel.add(Box.createHorizontalGlue());

        // center panel components
        this.titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.separator.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.dateTitlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.descriptionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.addPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.errorLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add components to panel
        this.panel.add(Box.createVerticalGlue());
        this.panel.add(titleLbl);
        this.panel.add(Box.createRigidArea(new Dimension(0, 30)));
        this.panel.add(separator);
        this.panel.add(Box.createRigidArea(new Dimension(0, 40)));
        this.panel.add(dateTitlePanel);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20))); 
        this.panel.add(descriptionPanel);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20))); 
        this.panel.add(addPanel);
        this.panel.add(Box.createRigidArea(new Dimension(0, 40))); 
        this.panel.add(errorLbl);
        this.panel.add(Box.createVerticalGlue());
    }

     // listeners & text field/combo box getters
    public int getSelectedMonth() {
        return (Integer) this.monthBox.getSelectedItem();
    }

    public int getSelectedDay() {
        return (Integer) this.dayBox.getSelectedItem();
    }

    public int getSelectedYear() {
        return (Integer) this.yearBox.getSelectedItem();
    }

    public String getEntryTitleTfText() {
        return this.entryTitleTf.getText();
    }

    public String getDescriptionTaText() {
        return this.descriptionTa.getText();
    }

    // helper methods
    public void showError() { // if cannot create entry
        this.errorLbl.setVisible(true);
    }

    public void hideError() { 
        this.errorLbl.setVisible(false);
    }

    public void clearTextFields() {
		this.entryTitleTf.setText("");
        this.descriptionTa.setText("");
	}

    public void populateFields(Journal entry) { // use for editing entries
        this.entryTitleTf.setText(entry.getTitle());
        this.descriptionTa.setText(entry.getDetails());
        
        // date combo boxes
        LocalDate date = entry.getDate();
        this.monthBox.setSelectedItem(date.getMonthValue());
        this.dayBox.setSelectedItem(date.getDayOfMonth());
        this.yearBox.setSelectedItem(date.getYear());
        
        // change label and button
        titleLbl.setText("Edit Journal Entry");
        addBtn.setText("Update");
    }

    // getters
    public JPanel getPanel() {
        return this.panel;
    }
}