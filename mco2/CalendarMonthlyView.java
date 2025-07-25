import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class CalendarMonthlyView {
    private JPanel panel, headerPanel, calendarPanel;
    private JLabel monthYearLbl;
    private JLabel[][] dayNumberLbl;
    private JPanel[][] dayPanel, entryPanel;
    private JButton prevBtn, nextBtn, deleteBtn, addEntryBtn, backBtn, goBtn;
    private JComboBox<Integer> monthBox;
    private JComboBox<Integer> yearBox;

    public CalendarMonthlyView() {
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));

        // get current date
        LocalDate now = LocalDate.now();
        int currentMonth = now.getMonthValue();
        int currentYear = now.getYear();

        String currentMonthName = now.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String currentYearString = String.valueOf(now.getYear());

        // calendarNameLbL
        this.monthYearLbl = new JLabel(currentMonthName + " " + currentYearString);
        this.monthYearLbl.setPreferredSize(new Dimension(300, 50));
        this.monthYearLbl.setMaximumSize(new Dimension(300, 50));
        this.monthYearLbl.setMinimumSize(new Dimension(300, 50));
        this.monthYearLbl.setFont(new Font("Century Gothic", 1, 36));
        this.monthYearLbl.setForeground(new Color(51, 51, 51));
        this.monthYearLbl.setHorizontalAlignment(SwingConstants.LEFT);

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
        this.monthBox.setSelectedItem(currentMonth);

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
        this.yearBox.setSelectedItem(currentYear);

        // align components
        this.monthYearLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.prevBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.nextBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.backBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.addEntryBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.deleteBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.goBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.monthBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.yearBox.setAlignmentX(Component.RIGHT_ALIGNMENT);

        // add components to header panel
        this.headerPanel = new JPanel();
        this.headerPanel.setLayout(new BoxLayout(this.headerPanel, BoxLayout.X_AXIS));
        this.headerPanel.setPreferredSize(new Dimension(980, 55));
        this.headerPanel.setMaximumSize(new Dimension(980, 55));
        this.headerPanel.setMinimumSize(new Dimension(980, 55));
        this.headerPanel.add(monthYearLbl);

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
        this.headerPanel.add(yearBox);

        // add components to panel
        this.panel.add(headerPanel);
        this.panel.add(Box.createHorizontalStrut(10));
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

    public void setDayPanelListener(ActionListener actionListener) { // makes the panels clickable
        if (this.dayPanel != null) {
            for (int week = 0; week < 6; week++) {
                for (int day = 0; day < 7; day++) {
                    if (this.dayPanel[week][day] != null) {
                        final JPanel panel = this.dayPanel[week][day];
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
    }

    public int getSelectedYear() {
        return (Integer) this.yearBox.getSelectedItem();
    }

    // helper methods
    public void initializeCalendar() {
        LocalDate now = LocalDate.now();
        generateCalendar(now.getMonthValue(), now.getYear());
    }

    public void generateCalendar(int month, int year) {
        // remove calendar panel if it exists
        if (this.calendarPanel != null) {
            this.panel.remove(this.calendarPanel);
        }

        // create the calendar panel
        this.calendarPanel = new JPanel(new GridLayout(7, 7, 0, 0));
        this.calendarPanel.setPreferredSize(new Dimension(980, 560));
        this.calendarPanel.setMaximumSize(new Dimension(980, 560));
        this.calendarPanel.setMinimumSize(new Dimension(980, 560));

        // add day headers
        String[] dayHeaders = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : dayHeaders) {
            JLabel dayLbl = new JLabel(day, SwingConstants.CENTER);
            dayLbl.setFont(new Font("Century Gothic", 1, 16));
            dayLbl.setForeground(new Color(51, 51, 51));
            dayLbl.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
            this.calendarPanel.add(dayLbl);
        }

        // get calendar dates
        LocalDate firstDay = LocalDate.of(year, month, 1);
        int startDayOfWeek = firstDay.getDayOfWeek().getValue() % 7;
        int daysInMonth = firstDay.lengthOfMonth();

        // initialize panel arrays
        this.dayPanel = new JPanel[6][7];
        this.dayNumberLbl = new JLabel[6][7];
        this.entryPanel = new JPanel[6][7];

        // fill calendar grid
        int currentDate = 1;
        LocalDate prevMonth = firstDay.minusMonths(1);
        int prevMonthDays = prevMonth.lengthOfMonth();
        int nextMonthDate = 1;

        for (int week = 0; week < 6; week++) {
            for (int day = 0; day < 7; day++) {
                // main panel for the day
                this.dayPanel[week][day] = new JPanel();
                this.dayPanel[week][day].setLayout(new BorderLayout());
                this.dayPanel[week][day].setPreferredSize(new Dimension(90, 80));
                this.dayPanel[week][day].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(51, 51, 51)));
                
                // day number label
                this.dayNumberLbl[week][day] = new JLabel();
                this.dayNumberLbl[week][day].setFont(new Font("Century Gothic", 0, 14));
                this.dayNumberLbl[week][day].setHorizontalAlignment(SwingConstants.CENTER);
                this.dayNumberLbl[week][day].setBorder(BorderFactory.createEmptyBorder(8, 5, 0, 0));
                
                // entry panel
                this.entryPanel[week][day] = new JPanel();
                this.entryPanel[week][day].setLayout(new BoxLayout(this.entryPanel[week][day], BoxLayout.Y_AXIS));
                this.entryPanel[week][day].setOpaque(false);
                this.entryPanel[week][day].setBorder(BorderFactory.createEmptyBorder(0, 2, 2, 2));

                int position = week * 7 + day;
                LocalDate panelDate;

                if (position < startDayOfWeek) {
                    // previous month dates
                    int prevDate = prevMonthDays - startDayOfWeek + position + 1;
                    this.dayNumberLbl[week][day].setText(String.valueOf(prevDate));
                    this.dayNumberLbl[week][day].setForeground(new Color(153, 153, 153));
                    panelDate = LocalDate.of(prevMonth.getYear(), prevMonth.getMonth(), prevDate);

                } else if (currentDate <= daysInMonth) {
                    // current month dates
                    this.dayNumberLbl[week][day].setText(String.valueOf(currentDate));
                    this.dayNumberLbl[week][day].setForeground(new Color(51, 51, 51));
                    panelDate = LocalDate.of(year, month, currentDate);

                    // highlight date today
                    LocalDate today = LocalDate.now();
                    if (currentDate == today.getDayOfMonth() && 
                        month == today.getMonthValue() && 
                        year == today.getYear()) {
                            this.dayNumberLbl[week][day].setForeground(new Color(255, 102, 102));
                        }
                    currentDate++;

                } else {
                    // next month dates
                    this.dayNumberLbl[week][day].setText(String.valueOf(nextMonthDate));
                    this.dayNumberLbl[week][day].setForeground(new Color(153, 153, 153));
                    LocalDate nextMonth = firstDay.plusMonths(1);
                    panelDate = LocalDate.of(nextMonth.getYear(), nextMonth.getMonth(), nextMonthDate);
                    nextMonthDate++;
                }

                // store the date in the panel for click handling
                this.dayPanel[week][day].putClientProperty("date", panelDate);
                
                // add components to day panel
                this.dayPanel[week][day].add(this.dayNumberLbl[week][day], BorderLayout.NORTH);
                this.dayPanel[week][day].add(this.entryPanel[week][day], BorderLayout.CENTER);

                // add day panel to calendar
                this.calendarPanel.add(this.dayPanel[week][day]);
            }
        }

        // add calendar panel to main panel
        this.panel.add(this.calendarPanel);
        this.panel.revalidate();
        this.panel.repaint();
    }

    public void updateCalendar(int month, int year) {
        // update month & year label
        LocalDate date = LocalDate.of(year, month, 1);
        String monthName = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        this.monthYearLbl.setText(monthName + " " + year);

        // update calendar panel
        generateCalendar(month, year);

        // update combo boxes
        this.monthBox.setSelectedItem(month);
        this.yearBox.setSelectedItem(year);
    }

    // getters
    public JPanel getPanel() {
        return this.panel;
    }
}