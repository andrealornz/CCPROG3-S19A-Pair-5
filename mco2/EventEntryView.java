import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

public class EventEntryView {
    private JPanel panel, datePanel, entryTitlePanel, startTimePanel, endTimePanel, venuePanel, organizerPanel, descriptionPanel, addPanel;
    private JLabel titleLbl, dateLbl, entryTitleLbl, startTimeLbl, endTimeLbl, venueLbl, organizerLbl, descriptionLbl, errorLbl;
    private JTextField entryTitleTf, venueTf, organizerTf;
    private JTextArea descriptionTa;
    private JScrollPane descriptionScrollPane;
    private JComboBox<Integer> dayBox, monthBox, yearBox, startTimeHourBox, startTimeMinuteBox, endTimeHourBox, endTimeMinuteBox;
    private JButton cancelBtn, addBtn;
    private JPanel dateTitlePanel, startEndTimePanel, venueOrganizerPanel;
    private JSeparator separator;

    public EventEntryView() {
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));

        // title label
        this.titleLbl = new JLabel("Add Event Entry");
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

        // colon label
        JLabel startTimeColonLbl = new JLabel(":", SwingConstants.CENTER);
        startTimeColonLbl.setPreferredSize(new Dimension(20, 50));
        startTimeColonLbl.setMaximumSize(new Dimension(20, 50));
        startTimeColonLbl.setMinimumSize(new Dimension(20, 50));
        startTimeColonLbl.setFont(new Font("Century Gothic", 0, 24));
        startTimeColonLbl.setForeground(new Color(51, 51, 51));

        // start time label
        this.startTimeLbl = new JLabel("Start Time:");
        this.startTimeLbl.setPreferredSize(new Dimension(140, 50));
        this.startTimeLbl.setMaximumSize(new Dimension(140, 50));
        this.startTimeLbl.setMinimumSize(new Dimension(140, 50));
        this.startTimeLbl.setFont(new Font("Century Gothic", 0, 24));
        this.startTimeLbl.setForeground(new Color(51, 51, 51));

        // start time hour & minute boxes
        this.startTimeHourBox = new JComboBox<>();
        this.startTimeHourBox.setPreferredSize(new Dimension(100, 50));
        this.startTimeHourBox.setMaximumSize(new Dimension(100, 50));
        this.startTimeHourBox.setMinimumSize(new Dimension(100, 50));
        this.startTimeHourBox.setFont(new Font("Century Gothic", 0, 20));
        this.startTimeHourBox.setForeground(new Color(51, 51, 51));
        this.startTimeHourBox.setBackground(new Color(242,242,242));
        for (int i = 0; i <= 23; i++) {
            startTimeHourBox.addItem(i);
        }

        this.startTimeMinuteBox = new JComboBox<>();
        this.startTimeMinuteBox.setPreferredSize(new Dimension(100, 50));
        this.startTimeMinuteBox.setMaximumSize(new Dimension(100, 50));
        this.startTimeMinuteBox.setMinimumSize(new Dimension(100, 50));
        this.startTimeMinuteBox.setFont(new Font("Century Gothic", 0, 20));
        this.startTimeMinuteBox.setForeground(new Color(51, 51, 51));
        this.startTimeMinuteBox.setBackground(new Color(242,242,242));
        for (int i = 0; i <= 59; i++) {
            startTimeMinuteBox.addItem(i);
        }

        // start time panel
        this.startTimePanel = new JPanel();
        this.startTimePanel.setLayout(new BoxLayout(this.startTimePanel, BoxLayout.X_AXIS));
        this.startTimePanel.setPreferredSize(new Dimension(400, 50));
        this.startTimePanel.setMaximumSize(new Dimension(400, 50));
        this.startTimePanel.setMinimumSize(new Dimension(400, 50));
        this.startTimePanel.add(startTimeLbl);
        this.startTimePanel.add(Box.createHorizontalStrut(10));
        this.startTimePanel.add(startTimeHourBox);
        this.startTimePanel.add(Box.createHorizontalStrut(10));
        this.startTimePanel.add(startTimeColonLbl);
        this.startTimePanel.add(Box.createHorizontalStrut(10));
        this.startTimePanel.add(startTimeMinuteBox);

        // colon label
        JLabel endTimeColonLbl = new JLabel(":", SwingConstants.CENTER);
        endTimeColonLbl.setPreferredSize(new Dimension(20, 50));
        endTimeColonLbl.setMaximumSize(new Dimension(20, 50));
        endTimeColonLbl.setMinimumSize(new Dimension(20, 50));
        endTimeColonLbl.setFont(new Font("Century Gothic", 0, 24));
        endTimeColonLbl.setForeground(new Color(51, 51, 51));

        // end time label
        this.endTimeLbl = new JLabel("End Time:");
        this.endTimeLbl.setPreferredSize(new Dimension(140, 50));
        this.endTimeLbl.setMaximumSize(new Dimension(140, 50));
        this.endTimeLbl.setMinimumSize(new Dimension(140, 50));
        this.endTimeLbl.setFont(new Font("Century Gothic", 0, 24));
        this.endTimeLbl.setForeground(new Color(51, 51, 51));

        // end time hour & minute boxes
        this.endTimeHourBox = new JComboBox<>();
        this.endTimeHourBox.setPreferredSize(new Dimension(100, 50));
        this.endTimeHourBox.setMaximumSize(new Dimension(100, 50));
        this.endTimeHourBox.setMinimumSize(new Dimension(100, 50));
        this.endTimeHourBox.setFont(new Font("Century Gothic", 0, 20));
        this.endTimeHourBox.setForeground(new Color(51, 51, 51));
        this.endTimeHourBox.setBackground(new Color(242,242,242));
        for (int i = 0; i <= 23; i++) {
            endTimeHourBox.addItem(i);
        }

        this.endTimeMinuteBox = new JComboBox<>();
        this.endTimeMinuteBox.setPreferredSize(new Dimension(100, 50));
        this.endTimeMinuteBox.setMaximumSize(new Dimension(100, 50));
        this.endTimeMinuteBox.setMinimumSize(new Dimension(100, 50));
        this.endTimeMinuteBox.setFont(new Font("Century Gothic", 0, 20));
        this.endTimeMinuteBox.setForeground(new Color(51, 51, 51));
        this.endTimeMinuteBox.setBackground(new Color(242,242,242));
        for (int i = 0; i <= 59; i++) {
            endTimeMinuteBox.addItem(i);
        }

        // end time panel
        this.endTimePanel = new JPanel();
        this.endTimePanel.setLayout(new BoxLayout(this.endTimePanel, BoxLayout.X_AXIS));
        this.endTimePanel.setPreferredSize(new Dimension(400, 50));
        this.endTimePanel.setMaximumSize(new Dimension(400, 50));
        this.endTimePanel.setMinimumSize(new Dimension(400, 50));
        this.endTimePanel.add(endTimeLbl);
        this.endTimePanel.add(Box.createHorizontalStrut(10));
        this.endTimePanel.add(endTimeHourBox);
        this.endTimePanel.add(Box.createHorizontalStrut(10));
        this.endTimePanel.add(endTimeColonLbl);
        this.endTimePanel.add(Box.createHorizontalStrut(10));
        this.endTimePanel.add(endTimeMinuteBox);

        // venue label
        this.venueLbl = new JLabel("Venue:");
        this.venueLbl.setPreferredSize(new Dimension(140, 50));
        this.venueLbl.setMaximumSize(new Dimension(140, 50));
        this.venueLbl.setMinimumSize(new Dimension(140, 50));
        this.venueLbl.setFont(new Font("Century Gothic", 0, 24));
        this.venueLbl.setForeground(new Color(51, 51, 51));

        // venue text field
        this.venueTf = new JTextField();
        this.venueTf.setPreferredSize(new Dimension(240, 50));
        this.venueTf.setMaximumSize(new Dimension(240, 50));
        this.venueTf.setMinimumSize(new Dimension(240, 50));
        this.venueTf.setFont(new Font("Century Gothic", 0, 18));
        this.venueTf.setForeground(new Color(51, 51, 51)); 
        this.venueTf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)),
                                    BorderFactory.createEmptyBorder(0, 10, 0, 10)));

        // venue panel
        this.venuePanel = new JPanel();
        this.venuePanel.setLayout(new BoxLayout(this.venuePanel, BoxLayout.X_AXIS));
        this.venuePanel.setPreferredSize(new Dimension(400, 50));
        this.venuePanel.setMaximumSize(new Dimension(400, 50));
        this.venuePanel.setMinimumSize(new Dimension(400, 50));
        this.venuePanel.add(venueLbl);
        this.venuePanel.add(Box.createHorizontalStrut(10));
        this.venuePanel.add(venueTf);

        // organizer label
        this.organizerLbl = new JLabel("Organizer:");
        this.organizerLbl.setPreferredSize(new Dimension(140, 50));
        this.organizerLbl.setMaximumSize(new Dimension(140, 50));
        this.organizerLbl.setMinimumSize(new Dimension(140, 50));
        this.organizerLbl.setFont(new Font("Century Gothic", 0, 24));
        this.organizerLbl.setForeground(new Color(51, 51, 51));

        // organizer text field
        this.organizerTf = new JTextField("<username>");
        this.organizerTf.setPreferredSize(new Dimension(240, 50));
        this.organizerTf.setMaximumSize(new Dimension(240, 50));
        this.organizerTf.setMinimumSize(new Dimension(240, 50));
        this.organizerTf.setFont(new Font("Century Gothic", 0, 18));
        this.organizerTf.setForeground(new Color(51, 51, 51)); 
        this.organizerTf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)),
                                    BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        this.organizerTf.setEditable(false); // because it is always the username of the account

        // organizer panel
        this.organizerPanel = new JPanel();
        this.organizerPanel.setLayout(new BoxLayout(this.organizerPanel, BoxLayout.X_AXIS));
        this.organizerPanel.setPreferredSize(new Dimension(400, 50));
        this.organizerPanel.setMaximumSize(new Dimension(400, 50));
        this.organizerPanel.setMinimumSize(new Dimension(400, 50));
        this.organizerPanel.add(organizerLbl);
        this.organizerPanel.add(Box.createHorizontalStrut(10));
        this.organizerPanel.add(organizerTf);

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
        this.descriptionScrollPane.setPreferredSize(new Dimension(660, 100));
        this.descriptionScrollPane.setMaximumSize(new Dimension(660, 100));
        this.descriptionScrollPane.setMinimumSize(new Dimension(660, 100));
        this.descriptionScrollPane.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.descriptionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.descriptionScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.descriptionScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        this.descriptionScrollPane.getVerticalScrollBar().setBackground(new Color(240, 240, 240));

        // description panel
        this.descriptionPanel = new JPanel();
        this.descriptionPanel.setLayout(new BoxLayout(this.descriptionPanel, BoxLayout.X_AXIS));
        this.descriptionPanel.setPreferredSize(new Dimension(810, 100)); 
        this.descriptionPanel.setMaximumSize(new Dimension(810, 100));
        this.descriptionPanel.setMinimumSize(new Dimension(810, 100));
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

        // start time & end time panel
        this.startEndTimePanel = new JPanel();
        this.startEndTimePanel.setLayout(new BoxLayout(this.startEndTimePanel, BoxLayout.X_AXIS));
        this.startEndTimePanel.setPreferredSize(new Dimension(810, 50));
        this.startEndTimePanel.setMaximumSize(new Dimension(810, 50));
        this.startEndTimePanel.setMinimumSize(new Dimension(810, 50));
        this.startEndTimePanel.add(Box.createHorizontalGlue());
        this.startEndTimePanel.add(startTimePanel);
        this.startEndTimePanel.add(Box.createHorizontalStrut(20));
        this.startEndTimePanel.add(endTimePanel);
        this.startEndTimePanel.add(Box.createHorizontalGlue());

        // venue & organizer panel
        this.venueOrganizerPanel = new JPanel();
        this.venueOrganizerPanel.setLayout(new BoxLayout(this.venueOrganizerPanel, BoxLayout.X_AXIS));
        this.venueOrganizerPanel.setPreferredSize(new Dimension(810, 50));
        this.venueOrganizerPanel.setMaximumSize(new Dimension(810, 50));
        this.venueOrganizerPanel.setMinimumSize(new Dimension(810, 50));
        this.venueOrganizerPanel.add(Box.createHorizontalGlue());
        this.venueOrganizerPanel.add(venuePanel);
        this.venueOrganizerPanel.add(Box.createHorizontalStrut(20));
        this.venueOrganizerPanel.add(organizerPanel);
        this.venueOrganizerPanel.add(Box.createHorizontalGlue());

        // center panel components
        this.titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.separator.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.dateTitlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.startEndTimePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.venueOrganizerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        this.panel.add(startEndTimePanel);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20))); 
        this.panel.add(venueOrganizerPanel);
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

    public int getSelectedStartTimeHour() {
        return (Integer) this.startTimeHourBox.getSelectedItem();
    }

    public int getSelectedStartTimeMinute() {
        return (Integer) this.startTimeMinuteBox.getSelectedItem();
    }

    public int getSelectedEndTimeHour() {
        return (Integer) this.endTimeHourBox.getSelectedItem();
    }

    public int getSelectedEndTimeMinute() {
        return (Integer) this.endTimeMinuteBox.getSelectedItem();
    }

    public String getVenueTfText() {
        return this.venueTf.getText();
    }
    
    public String getOrganizerTfText() {
        return this.organizerTf.getText();
    }

    public String getDescriptionTaText() {
        return this.descriptionTa.getText();
    }

    public void setCancelBtnListener(ActionListener actionListener) {
        this.cancelBtn.addActionListener(actionListener);
    }
    
    public void setAddBtnListener(ActionListener actionListener) {
        this.addBtn.addActionListener(actionListener);
    }

    // helper methods
    public void showError() { // if cannot create entry
        this.errorLbl.setVisible(true);
    }

    public void hideError() { 
        this.errorLbl.setVisible(false);
    }

    public void clearTextFields() {
        LocalDate today = LocalDate.now();
        this.monthBox.setSelectedItem(today.getMonthValue());
        this.dayBox.setSelectedItem(today.getDayOfMonth());
        this.yearBox.setSelectedItem(today.getYear());

        this.startTimeHourBox.setSelectedItem(0);
        this.startTimeMinuteBox.setSelectedItem(0);
        this.endTimeHourBox.setSelectedItem(0);
        this.endTimeMinuteBox.setSelectedItem(0);

		this.entryTitleTf.setText("");
		this.venueTf.setText("");
        this.organizerTf.setText("");
        this.descriptionTa.setText("");
	}

    public void setOrganizerTfText(String organizer) { // use to change the text field display to the username
        this.organizerTf.setText(organizer);
    }

    public void populateFields(Event entry) { // use for editing entries
        this.entryTitleTf.setText(entry.getTitle());
        this.venueTf.setText(entry.getVenue());
        this.organizerTf.setText(entry.getOrganizer());
        this.descriptionTa.setText(entry.getDetails());
        
        // date combo boxes
        LocalDate date = entry.getDate();
        this.monthBox.setSelectedItem(date.getMonthValue());
        this.dayBox.setSelectedItem(date.getDayOfMonth());
        this.yearBox.setSelectedItem(date.getYear());

        // start time & end time combo boxes
        LocalTime startTime = entry.getStartTime();
        this.startTimeHourBox.setSelectedItem(startTime.getHour());
        this.startTimeMinuteBox.setSelectedItem(startTime.getMinute());

        LocalTime endTime = entry.getEndTime();
        this.endTimeHourBox.setSelectedItem(endTime.getHour());
        this.endTimeMinuteBox.setSelectedItem(endTime.getMinute());
        
        // change label and button
        titleLbl.setText("Edit Event Entry");
        addBtn.setText("Edit");
    }

    // getters
    public JPanel getPanel() {
        return this.panel;
    }
}