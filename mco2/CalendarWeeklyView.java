import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;

public class CalendarWeeklyView {
    private String currentCalendarName;
    private CalendarModel calendarModel;
    private Calendar currentCalendar;

    private JPanel panel, headerPanel, calendarPanel;
    private JLabel weekRangeLbl;
    private JLabel[] dayNumberLbl;
    private JPanel[] dayPanel, entryPanel;
    private JButton prevBtn, nextBtn, deleteBtn, addEntryBtn, backBtn, goBtn;
    private JComboBox<Integer> monthBox, dayBox, yearBox;
    private LocalDate currentWeekStart;
    private ActionListener titleClickListener;

    public CalendarWeeklyView() {
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));

        // get current date and week
        LocalDate now = LocalDate.now();
        this.currentWeekStart = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

        // week range label
        String weekRange = getWeekRangeString(this.currentWeekStart);
        this.weekRangeLbl = new JLabel(weekRange);
        this.weekRangeLbl.setPreferredSize(new Dimension(400, 40));
        this.weekRangeLbl.setMaximumSize(new Dimension(400, 40));
        this.weekRangeLbl.setMinimumSize(new Dimension(400, 40));
        this.weekRangeLbl.setFont(new Font("Century Gothic", 1, 30));
        this.weekRangeLbl.setForeground(new Color(51, 51, 51));
        this.weekRangeLbl.setHorizontalAlignment(SwingConstants.LEFT);

        // option buttons
        this.backBtn = new JButton("Back");
        this.backBtn.setPreferredSize(new Dimension(70, 30));
        this.backBtn.setMaximumSize(new Dimension(70, 30));
        this.backBtn.setMinimumSize(new Dimension(70, 30));
        this.backBtn.setFont(new Font("Century Gothic", 0, 14));
        this.backBtn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.backBtn.setForeground(new Color(51, 51, 51));
        this.backBtn.setContentAreaFilled(false);

        this.addEntryBtn = new JButton("Add Entry");
        this.addEntryBtn.setPreferredSize(new Dimension(100, 30));
        this.addEntryBtn.setMaximumSize(new Dimension(100, 30));
        this.addEntryBtn.setMinimumSize(new Dimension(100, 30));
        this.addEntryBtn.setFont(new Font("Century Gothic", 0, 14));
        this.addEntryBtn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.addEntryBtn.setForeground(new Color(51, 51, 51));
        this.addEntryBtn.setContentAreaFilled(false);

        this.deleteBtn = new JButton("Delete");
        this.deleteBtn.setPreferredSize(new Dimension(75, 30));
        this.deleteBtn.setMaximumSize(new Dimension(75, 30));
        this.deleteBtn.setMinimumSize(new Dimension(75, 30));
        this.deleteBtn.setFont(new Font("Century Gothic", 0, 14));
        this.deleteBtn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.deleteBtn.setForeground(new Color(51, 51, 51));
        this.deleteBtn.setContentAreaFilled(false);

        this.goBtn = new JButton("Go");
        this.goBtn.setPreferredSize(new Dimension(45, 30));
        this.goBtn.setMaximumSize(new Dimension(45, 30));
        this.goBtn.setMinimumSize(new Dimension(45, 30));
        this.goBtn.setFont(new Font("Century Gothic", 0, 14));
        this.goBtn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.goBtn.setForeground(new Color(51, 51, 51));
        this.goBtn.setContentAreaFilled(false);

        this.prevBtn = new JButton("<");
        this.prevBtn.setPreferredSize(new Dimension(30, 30));
        this.prevBtn.setMaximumSize(new Dimension(30, 30));
        this.prevBtn.setMinimumSize(new Dimension(30, 30));
        this.prevBtn.setFont(new Font("Century Gothic", 0, 14));
        this.prevBtn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.prevBtn.setForeground(new Color(51, 51, 51));
        this.prevBtn.setContentAreaFilled(false);

        this.nextBtn = new JButton(">");
        this.nextBtn.setPreferredSize(new Dimension(30, 30));
        this.nextBtn.setMaximumSize(new Dimension(30, 30));
        this.nextBtn.setMinimumSize(new Dimension(30, 30));
        this.nextBtn.setFont(new Font("Century Gothic", 0, 14));
        this.nextBtn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.nextBtn.setForeground(new Color(51, 51, 51));
        this.nextBtn.setContentAreaFilled(false);

        // month & year combo boxes
        this.monthBox = new JComboBox<>();
        this.monthBox.setPreferredSize(new Dimension(45, 30));
        this.monthBox.setMaximumSize(new Dimension(45, 30));
        this.monthBox.setMinimumSize(new Dimension(45, 30));
        this.monthBox.setFont(new Font("Century Gothic", 0, 14));
        this.monthBox.setForeground(new Color(51, 51, 51));
        this.monthBox.setBackground(new Color(242,242,242));
        for (int i = 1; i <= 12; i++) {
            monthBox.addItem(i);
        }
        this.monthBox.setSelectedItem(now.getMonthValue());

        this.dayBox = new JComboBox<>();
        this.dayBox.setPreferredSize(new Dimension(45, 30));
        this.dayBox.setMaximumSize(new Dimension(45, 30));
        this.dayBox.setMinimumSize(new Dimension(45, 30));
        this.dayBox.setFont(new Font("Century Gothic", 0, 14));
        this.dayBox.setForeground(new Color(51, 51, 51));
        this.dayBox.setBackground(new Color(242,242,242));
        for (int i = 1; i <= 31; i++) {
            dayBox.addItem(i);
        }
        this.dayBox.setSelectedItem(now.getDayOfMonth());

        this.yearBox = new JComboBox<>();
        this.yearBox.setPreferredSize(new Dimension(60, 30));
        this.yearBox.setMaximumSize(new Dimension(60, 30));
        this.yearBox.setMinimumSize(new Dimension(60, 30));
        this.yearBox.setFont(new Font("Century Gothic", 0, 14));
        this.yearBox.setForeground(new Color(51, 51, 51));
        this.yearBox.setBackground(new Color(242,242,242));
        for (int i = 1000; i <= 9999; i++) {
            yearBox.addItem(i);
        }
        this.yearBox.setSelectedItem(now.getYear());

        // align components
        this.weekRangeLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.prevBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.nextBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.backBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.addEntryBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.deleteBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.goBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.monthBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.dayBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.yearBox.setAlignmentX(Component.RIGHT_ALIGNMENT);

        // add components to header panel
        this.headerPanel = new JPanel();
        this.headerPanel.setLayout(new BoxLayout(this.headerPanel, BoxLayout.X_AXIS));
        this.headerPanel.setPreferredSize(new Dimension(980, 75));
        this.headerPanel.setMaximumSize(new Dimension(980, 75));
        this.headerPanel.setMinimumSize(new Dimension(980, 75));
        this.headerPanel.add(weekRangeLbl);

        this.headerPanel.add(Box.createHorizontalGlue());
        this.headerPanel.add(prevBtn);
        this.headerPanel.add(Box.createHorizontalStrut(10));
        this.headerPanel.add(nextBtn);
        this.headerPanel.add(Box.createHorizontalStrut(10));
        this.headerPanel.add(backBtn);
        this.headerPanel.add(Box.createHorizontalStrut(10));
        this.headerPanel.add(addEntryBtn);
        this.headerPanel.add(Box.createHorizontalStrut(10));
        this.headerPanel.add(deleteBtn);
        this.headerPanel.add(Box.createHorizontalStrut(10));
        this.headerPanel.add(goBtn);
        this.headerPanel.add(Box.createHorizontalStrut(10));
        this.headerPanel.add(monthBox);
        this.headerPanel.add(Box.createHorizontalStrut(10));
        this.headerPanel.add(dayBox);
        this.headerPanel.add(Box.createHorizontalStrut(10));
        this.headerPanel.add(yearBox);

        // add components to panel
        this.panel.add(headerPanel);
        initializeCalendar();
    }

    // listeners & combo box getters
    public void setPrevBtnListener(ActionListener actionListener) {
        this.prevBtn.addActionListener(actionListener);
    }

    public void setNextBtnListener(ActionListener actionListener) {
        this.nextBtn.addActionListener(actionListener);
    }

    public void setBackBtnListener(ActionListener actionListener) {
        this.backBtn.addActionListener(actionListener);
    }

    public void setAddEntryBtnListener(ActionListener actionListener) {
        this.addEntryBtn.addActionListener(actionListener);
    }

    public void setDeleteBtnListener(ActionListener actionListener) {
        this.deleteBtn.addActionListener(actionListener);
    }

    public void setGoBtnListener(ActionListener actionListener) {
        this.goBtn.addActionListener(actionListener);
    }

    public int getSelectedMonth() {
        return (Integer) this.monthBox.getSelectedItem();
    }

    public int getSelectedDay() {
        return (Integer) this.dayBox.getSelectedItem();
    }
    
    public int getSelectedYear() {
        return (Integer) this.yearBox.getSelectedItem();
    }

    public void setDayPanelListener(ActionListener actionListener) {
        if (this.dayPanel != null) {
            for (int day = 0; day < 7; day++) {
                if (this.dayPanel[day] != null) {
                    final JPanel panel = this.dayPanel[day];
                    panel.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent mouseEvent) {
                            LocalDate clickedDate = (LocalDate) panel.getClientProperty("date");
                            ActionEvent actionEvent = new ActionEvent(panel, 0, clickedDate.toString());
                            actionListener.actionPerformed(actionEvent);
                        }
                    });
                }
            }
        }
    }

    // helper methods
    private String getWeekRangeString(LocalDate weekStart) {
        LocalDate weekEnd = weekStart.plusDays(6);
        String result;
        
        if (weekStart.getMonth() == weekEnd.getMonth()) {
            // if the week is within the same month
            result = weekStart.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + 
                     weekStart.getYear();
        } else {
            // if the week is between 2 differnt months
            String startMonth = weekStart.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            String endMonth = weekEnd.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            
            if (weekStart.getYear() == weekEnd.getYear()) {
                result = startMonth + " - " + endMonth + " " + weekStart.getYear();
            } else {
                result = startMonth + " " + weekStart.getYear() + " - " + endMonth + " " + weekEnd.getYear();
            }
        }
        
        return result;
    }

    public void initializeCalendar() {
        LocalDate now = LocalDate.now();
        LocalDate weekStart = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        generateWeeklyCalendar(weekStart);
    }

    public void generateWeeklyCalendar(LocalDate weekStart) {
        this.currentWeekStart = weekStart;
        
        // remove calendar panel if it exists
        if (this.calendarPanel != null) {
            this.panel.remove(this.calendarPanel);
        }

        // create the calendar panel
        this.calendarPanel = new JPanel(new BorderLayout());
        this.calendarPanel.setPreferredSize(new Dimension(980, 595));
        this.calendarPanel.setMaximumSize(new Dimension(980, 595));
        this.calendarPanel.setMinimumSize(new Dimension(980, 595));

        // add day headers
        JPanel dayHeaderPanel = new JPanel(new GridLayout(1, 7));
        dayHeaderPanel.setPreferredSize(new Dimension(980, 50));
        dayHeaderPanel.setMaximumSize(new Dimension(980, 50));
        dayHeaderPanel.setMinimumSize(new Dimension(980, 50));
        String[] dayHeaders = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : dayHeaders) {
            JLabel dayLbl = new JLabel(day, SwingConstants.CENTER);
            dayLbl.setFont(new Font("Century Gothic", 1, 14));
            dayLbl.setForeground(new Color(51, 51, 51));
            dayLbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            dayHeaderPanel.add(dayLbl);
        }

        // week header with day numbers
        JPanel weekHeaderPanel = new JPanel(new GridLayout(1, 7));
        weekHeaderPanel.setPreferredSize(new Dimension(980, 80));
        weekHeaderPanel.setMaximumSize(new Dimension(980, 80));
        weekHeaderPanel.setMinimumSize(new Dimension(980, 80));
        weekHeaderPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(51, 51, 51)));
        
        // initialize arrays
        this.dayPanel = new JPanel[7];
        this.dayNumberLbl = new JLabel[7];
        
        for (int i = 0; i < 7; i++) {
            LocalDate currentDay = weekStart.plusDays(i);
            
            this.dayPanel[i] = new JPanel();
            this.dayPanel[i].setLayout(new BorderLayout());
            this.dayPanel[i].setPreferredSize(new Dimension(140, 80));
            this.dayPanel[i].setMaximumSize(new Dimension(140, 80));
            this.dayPanel[i].setMinimumSize(new Dimension(140, 80));
            this.dayPanel[i].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(51, 51, 51)));
            
            this.dayNumberLbl[i] = new JLabel(String.valueOf(currentDay.getDayOfMonth()));
            this.dayNumberLbl[i].setFont(new Font("Century Gothic", 0, 14));
            this.dayNumberLbl[i].setHorizontalAlignment(SwingConstants.CENTER);

            // highlight today
            LocalDate today = LocalDate.now();
            if (currentDay.equals(today)) {
                this.dayNumberLbl[i].setForeground(new Color(255, 102, 102));
            } else {
                this.dayNumberLbl[i].setForeground(new Color(51, 51, 51));
            }
            
            // store the date in the panel for click
            this.dayPanel[i].putClientProperty("date", currentDay);
            
            this.dayPanel[i].add(this.dayNumberLbl[i], BorderLayout.CENTER);
            weekHeaderPanel.add(this.dayPanel[i]);
        }

        // entries section
        JPanel entriesPanel = new JPanel();
        entriesPanel.setLayout(new BoxLayout(entriesPanel, BoxLayout.Y_AXIS));
        entriesPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // initialize entry panel for week
        this.entryPanel = new JPanel[7];
        for (int i = 0; i < 7; i++) {
            this.entryPanel[i] = new JPanel();
            this.entryPanel[i].setLayout(new BoxLayout(this.entryPanel[i], BoxLayout.Y_AXIS));
            this.entryPanel[i].setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        }
        
        // scrollable list for entries
        JScrollPane entriesScrollPane = new JScrollPane(entriesPanel);
        entriesScrollPane.setBorder(BorderFactory.createEmptyBorder());
        entriesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        entriesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        // combine headears
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(dayHeaderPanel, BorderLayout.NORTH);
        topPanel.add(weekHeaderPanel, BorderLayout.CENTER);

        this.calendarPanel.add(topPanel, BorderLayout.NORTH);
        this.calendarPanel.add(entriesScrollPane, BorderLayout.CENTER);

        // add calendar panel to main panel
        this.panel.add(this.calendarPanel);
        this.panel.revalidate();
        this.panel.repaint();
        
        // update the week range label
        this.weekRangeLbl.setText(getWeekRangeString(weekStart));
    }

    public void setTitleClickListener(ActionListener actionListener) {
        this.titleClickListener = actionListener;
    }

    public void displayEntriesForDay(int dayIndex, ArrayList<Entry> entries, LocalDate date) {
        if (this.entryPanel[dayIndex] != null) {
            this.entryPanel[dayIndex].removeAll();
            
            if (entries != null && !entries.isEmpty()) {
                // date headers
                String dayName = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
                JLabel dateHeaderLbl = new JLabel(dayName + " - " + date.getDayOfMonth());
                dateHeaderLbl.setFont(new Font("Century Gothic", 1, 24));
                dateHeaderLbl.setForeground(new Color(51, 51, 51));
                dateHeaderLbl.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
                this.entryPanel[dayIndex].add(dateHeaderLbl);
                
                // entries with details
                for (Entry entry : entries) {
                    JPanel entryContainer = new JPanel();
                    entryContainer.setLayout(new BoxLayout(entryContainer, BoxLayout.Y_AXIS));
                    entryContainer.setBorder(BorderFactory.createEmptyBorder(8, 18, 5, 0));
                    
                    // entry title
                    JLabel titleLbl = new JLabel(entry.getTitle());
                    titleLbl.setFont(new Font("Century Gothic", 1, 16));
                    titleLbl.setForeground(new Color(51, 51, 51));
                    titleLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    titleLbl.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (titleClickListener != null) {
                                ActionEvent actionEvent = new ActionEvent(titleLbl, 0, entry.getTitle());
                                titleClickListener.actionPerformed(actionEvent);
                            }
                        }
                    });
                    entryContainer.add(titleLbl);
                    
                    // entry details based on type
                    if (entry instanceof Task) {
                        Task task = (Task) entry;
                        
                        // description
                        JLabel descLbl = new JLabel("Description: " + task.getDetails());
                        descLbl.setFont(new Font("Century Gothic", 0, 14));
                        descLbl.setForeground(new Color(102, 102, 102));
                        descLbl.setBorder(BorderFactory.createEmptyBorder(2, 25, 0, 0));
                        entryContainer.add(descLbl);
                        
                        // priority
                        JLabel priorityLbl = new JLabel("Priority: " + task.getPriority());
                        priorityLbl.setFont(new Font("Century Gothic", 0, 14));
                        priorityLbl.setForeground(new Color(102, 102, 102));
                        priorityLbl.setBorder(BorderFactory.createEmptyBorder(2, 25, 0, 0));
                        entryContainer.add(priorityLbl);
                        
                        // status
                        JLabel statusLbl = new JLabel("Status: " + task.getStatus());
                        statusLbl.setFont(new Font("Century Gothic", 0, 14));
                        statusLbl.setForeground(new Color(102, 102, 102));
                        statusLbl.setBorder(BorderFactory.createEmptyBorder(2, 25, 0, 0));
                        entryContainer.add(statusLbl);
                        
                        // created by
                        JLabel createdByLbl = new JLabel("Created By: " + task.getCreatedBy());
                        createdByLbl.setFont(new Font("Century Gothic", 0, 14));
                        createdByLbl.setForeground(new Color(102, 102, 102));
                        createdByLbl.setBorder(BorderFactory.createEmptyBorder(2, 25, 0, 0));
                        entryContainer.add(createdByLbl);
                        
                        // finished by
                        JLabel finishedByLbl = new JLabel("Finished By: " + task.getFinishedBy());
                        finishedByLbl.setFont(new Font("Century Gothic", 0, 14));
                        finishedByLbl.setForeground(new Color(102, 102, 102));
                        finishedByLbl.setBorder(BorderFactory.createEmptyBorder(2, 25, 0, 0));
                        entryContainer.add(finishedByLbl);
                        
                    } else if (entry instanceof Event) {
                        Event event = (Event) entry;
                        
                        // description
                        JLabel descLbl = new JLabel("Description: " + event.getDetails());
                        descLbl.setFont(new Font("Century Gothic", 0, 14));
                        descLbl.setForeground(new Color(102, 102, 102));
                        descLbl.setBorder(BorderFactory.createEmptyBorder(2, 25, 0, 0));
                        entryContainer.add(descLbl);
                        
                        // venue
                        JLabel venueLbl = new JLabel("Venue: " + event.getVenue());
                        venueLbl.setFont(new Font("Century Gothic", 0, 14));
                        venueLbl.setForeground(new Color(102, 102, 102));
                        venueLbl.setBorder(BorderFactory.createEmptyBorder(2, 25, 0, 0));
                        entryContainer.add(venueLbl);
                        
                        // organizer
                        JLabel organizerLbl = new JLabel("organizer: " + event.getOrganizer());
                        organizerLbl.setFont(new Font("Century Gothic", 0, 14));
                        organizerLbl.setForeground(new Color(102, 102, 102));
                        organizerLbl.setBorder(BorderFactory.createEmptyBorder(2, 25, 0, 0));
                        entryContainer.add(organizerLbl);
                        
                        // time
                        JLabel timeLbl = new JLabel("Time: " + event.getStartTime() + " - " + event.getEndTime());
                        timeLbl.setFont(new Font("Century Gothic", 0, 14));
                        timeLbl.setForeground(new Color(102, 102, 102));
                        timeLbl.setBorder(BorderFactory.createEmptyBorder(2, 25, 0, 0));
                        entryContainer.add(timeLbl);
                        
                    } else if (entry instanceof Meeting) {
                        Meeting meeting = (Meeting) entry;
                        
                        // description
                        JLabel descLbl = new JLabel("Description: " + meeting.getDetails());
                        descLbl.setFont(new Font("Century Gothic", 0, 14));
                        descLbl.setForeground(new Color(102, 102, 102));
                        descLbl.setBorder(BorderFactory.createEmptyBorder(2, 25, 0, 0));
                        entryContainer.add(descLbl);
                        
                        // modality
                        JLabel modalityLbl = new JLabel("Modality: " + meeting.getModality());
                        modalityLbl.setFont(new Font("Century Gothic", 0, 14));
                        modalityLbl.setForeground(new Color(102, 102, 102));
                        modalityLbl.setBorder(BorderFactory.createEmptyBorder(2, 25, 0, 0));
                        entryContainer.add(modalityLbl);
                        
                        // venue
                        JLabel venueLbl = new JLabel("Venue: " + meeting.getVenue());
                        venueLbl.setFont(new Font("Century Gothic", 0, 14));
                        venueLbl.setForeground(new Color(102, 102, 102));
                        venueLbl.setBorder(BorderFactory.createEmptyBorder(2, 25, 0, 0));
                        entryContainer.add(venueLbl);
                        
                        // link
                        JLabel linkLbl = new JLabel("Link: " + meeting.getLink());
                        linkLbl.setFont(new Font("Century Gothic", 0, 14));
                        linkLbl.setForeground(new Color(102, 102, 102));
                        linkLbl.setBorder(BorderFactory.createEmptyBorder(2, 25, 0, 0));
                        entryContainer.add(linkLbl);
                        
                        // Time
                        JLabel timeLbl = new JLabel("Time: " + meeting.getStartTime() + " - " + meeting.getEndTime());
                        timeLbl.setFont(new Font("Century Gothic", 0, 14));
                        timeLbl.setForeground(new Color(102, 102, 102));
                        timeLbl.setBorder(BorderFactory.createEmptyBorder(2, 25, 0, 0));
                        entryContainer.add(timeLbl);
                        
                    } else if (entry instanceof Journal) {
                        Journal journal = (Journal) entry;
                        
                        // Description/Content
                        JLabel descLbl = new JLabel("Description: " + journal.getDetails());
                        descLbl.setFont(new Font("Century Gothic", 0, 14));
                        descLbl.setForeground(new Color(102, 102, 102));
                        descLbl.setBorder(BorderFactory.createEmptyBorder(2, 20, 0, 0));
                        entryContainer.add(descLbl);
                    }
                    
                    this.entryPanel[dayIndex].add(entryContainer);
                }
            }
            
            this.entryPanel[dayIndex].revalidate();
            this.entryPanel[dayIndex].repaint();
        }
    }

    public void populateWeeklyEntries(CalendarModel model, Calendar calendar) {
        // clear entries
        if (this.calendarPanel != null) {
            Component[] components = this.calendarPanel.getComponents();
            for (Component comp : components) {
                if (comp instanceof JScrollPane) {
                    JScrollPane scrollPane = (JScrollPane) comp;
                    JViewport viewport = scrollPane.getViewport();
                    if (viewport.getView() instanceof JPanel) {
                        JPanel entriesPanel = (JPanel) viewport.getView();
                        entriesPanel.removeAll();
                        
                        // add entries for each day
                        for (int i = 0; i < 7; i++) {
                            LocalDate currentDay = this.currentWeekStart.plusDays(i);
                            ArrayList<Entry> dayEntries = model.getEntriesForDate(calendar, currentDay);
                            
                            if (dayEntries != null && !dayEntries.isEmpty()) {
                                displayEntriesForDay(i, dayEntries, currentDay);
                                entriesPanel.add(this.entryPanel[i]);
                            }
                        }
                        
                        entriesPanel.revalidate();
                        entriesPanel.repaint();
                    }
                    break;
                }
            }
        }
    }

    public void updateWeekly(LocalDate selectedDate) {
        LocalDate weekStart = selectedDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        
        this.currentWeekStart = weekStart;
        this.weekRangeLbl.setText(getWeekRangeString(weekStart));
        
        this.monthBox.setSelectedItem(selectedDate.getMonthValue());
        this.yearBox.setSelectedItem(selectedDate.getYear());
        
        generateWeeklyCalendar(weekStart);
    }

    public void setCurrentCalendar(CalendarModel model, Calendar calendar) {
        this.calendarModel = model;
        this.currentCalendar = calendar;
        this.currentCalendarName = calendar.getName();
    }

    public LocalDate getCurrentWeekStart() {
        return this.currentWeekStart;
    }

    public JPanel[] getDayPanel() {
        return this.dayPanel;
    }

    public String getCurrentCalendarName() {
        return currentCalendarName;
    }

    public boolean showDeleteConfirmation() { // pop up for deleting the calendar
        int option = JOptionPane.showConfirmDialog(
            this.panel,
            "Are you sure you want to delete this calendar?",
            "Confirm Add Calendar",
            JOptionPane.YES_NO_OPTION
        );
        return option == JOptionPane.YES_OPTION;
    }

    public void showDefaultCalendarError() {
        JOptionPane.showMessageDialog(
            this.panel,
            "Default calendar cannot be deleted.",
            "Cannot Delete Calendar",
            JOptionPane.ERROR_MESSAGE
        );
    }

    // getters
    public JPanel getPanel() {
        return this.panel;
    }
}