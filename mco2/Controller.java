import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Controller {
    private MainView mainView;
    private AccountModel accountModel;
    private CalendarModel calendarModel;

    public Controller(MainView mainView, AccountModel accountModel, CalendarModel calendarModel) {
        this.mainView = mainView;
        this.accountModel = accountModel;
        this.calendarModel = calendarModel;

        // sign-in
        this.mainView.getSignInView().setLogInBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("LOGIN");
            }
        });

        this.mainView.getSignInView().setSignUpBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("SIGNUP");
            }
        });

        this.mainView.getSignInView().setExitBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainView.getSignInView().showConfirmDialog()) {
                    mainView.getFrame().dispose();
                }
            }
        });

        // log-in
        this.mainView.getLogInView().setLogInBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = mainView.getLogInView().getUsernameTfText();
                String password = mainView.getLogInView().getPasswordTfText();

                boolean success = accountModel.loginAccount(username, password);

                if (success) {
                    mainView.setUsernameLabel(accountModel.getCurrentAccount().getUsername());
                    mainView.showCard("MAIN");
                    mainView.getLogInView().clearTextFields();
                    mainView.getLogInView().showSuccess();
                    mainView.getLogInView().hideError();
                } else {
                    mainView.getLogInView().showError();
                }
            }
        });

        this.mainView.getLogInView().setCancelBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("SIGNIN");
                mainView.getLogInView().clearTextFields();
                mainView.getLogInView().hideError();
            }
        });

        // sign-up
        this.mainView.getSignUpView().setSignUpBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = mainView.getSignUpView().getUsernameTfText();
                String password = mainView.getSignUpView().getPasswordTfText();

                boolean success = accountModel.createAccount(username, password);

                if (success) {
                    mainView.showCard("SIGNIN");
                    mainView.getSignUpView().clearTextFields();
                    mainView.getSignUpView().showSuccess();
                    mainView.getSignUpView().hideError();
                } else {
                    mainView.getSignUpView().showError();
                }
            }
        });

        this.mainView.getSignUpView().setCancelBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("SIGNIN");
                mainView.getSignUpView().clearTextFields();
                mainView.getSignUpView().hideError();
            }
        });

        // main menu
        this.mainView.getMainMenuView().setExitBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainView.getMainMenuView().showConfirmDialog()) {
                    mainView.getFrame().dispose();
                }
            }
        });

        this.mainView.getMainMenuView().setSettingsBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("SETTINGS");
            }
        });

        this.mainView.getMainMenuView().setCalendarsBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadCalendarsIntoView();
                mainView.showCard("VIEW_CALENDARS");
            }
        });

        // account settings
        this.mainView.getAccountSettingsView().setBackBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("MAIN");
            }
        });

        this.mainView.getAccountSettingsView().setLogOutBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainView.getAccountSettingsView().showConfirmDialog()) {
                    mainView.clearUsernameLabel();
                    mainView.showCard("SIGNIN");
                    accountModel.logoutAccount();
                } 
            }
        });

        this.mainView.getAccountSettingsView().setDeleteBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainView.getAccountSettingsView().showConfirmDialog()) {
                    mainView.clearUsernameLabel();
                    mainView.showCard("SIGNIN");
                    accountModel.deleteAccount();
                } 
            }
        });

        // view calendars
        this.mainView.getCalendarsView().setBackBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("MAIN");
            }
        });

        this.mainView.getCalendarsView().setAddBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("NEW_CALENDAR");
            }
        });

        this.mainView.getCalendarsView().setOwnedListListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedCalendar = mainView.getCalendarsView().getSelectedOwnedCalendar();
                    if (selectedCalendar != null) { 
                        Calendar calendar = accountModel.findCalendarByName(selectedCalendar);
                        mainView.getCalendarMonthlyView().setCurrentCalendar(calendarModel, calendar);
                        mainView.getCalendarMonthlyView().initializeCalendar();
                        mainView.getCalendarMonthlyView().refreshEntries();
                        mainView.getCalendarMonthlyView().setDayPanelListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                LocalDate clickedDate = LocalDate.parse(e.getActionCommand());
                                Calendar currentCalendar = getCurrentCalendar();
                                calendarModel.sortEntries(currentCalendar);
                                mainView.getCalendarWeeklyView().setCurrentCalendar(calendarModel, currentCalendar);
                                mainView.getCalendarWeeklyView().updateWeekly(clickedDate);
                                mainView.getCalendarWeeklyView().populateWeeklyEntries(calendarModel, currentCalendar);
                                mainView.showCard("WEEKLY");
                            }
                        });
                        mainView.setFrameTitle(selectedCalendar);
                        mainView.showCard("MONTHLY");
                    }
                }
            }
        });

        this.mainView.getCalendarsView().setAddedListListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedCalendar = mainView.getCalendarsView().getSelectedAddedCalendar();
                    if (selectedCalendar != null) { 
                        Calendar calendar = accountModel.findCalendarByName(selectedCalendar);
                        mainView.getCalendarMonthlyView().setCurrentCalendar(calendarModel, calendar);
                        mainView.getCalendarMonthlyView().initializeCalendar();
                        mainView.getCalendarMonthlyView().refreshEntries();
                        mainView.getCalendarMonthlyView().setDayPanelListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                LocalDate clickedDate = LocalDate.parse(e.getActionCommand());
                                Calendar currentCalendar = getCurrentCalendar();
                                calendarModel.sortEntries(currentCalendar);
                                mainView.getCalendarWeeklyView().setCurrentCalendar(calendarModel, currentCalendar);
                                mainView.getCalendarWeeklyView().updateWeekly(clickedDate);
                                mainView.getCalendarWeeklyView().populateWeeklyEntries(calendarModel, currentCalendar);
                                mainView.showCard("WEEKLY");
                            }
                        });
                        mainView.setFrameTitle(selectedCalendar);
                        mainView.showCard("MONTHLY");
                    }
                }
            }
        });

        // new calendars
        this.mainView.getNewCalendarsView().setBackBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("VIEW_CALENDARS");
            }
        });

        this.mainView.getNewCalendarsView().setAddBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAvailableCalendarsIntoView();
                mainView.showCard("ADD_EXISTING");
            }
        });

        this.mainView.getNewCalendarsView().setCreateBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("CREATE_NEW");
            }
        });

        // create new calendar
        this.mainView.getCreateCalendarView().setBackBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("NEW_CALENDAR");
            }
        });

        this.mainView.getCreateCalendarView().setPersonalBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("PERSONAL");
            }
        });

        this.mainView.getCreateCalendarView().setFamilyBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("FAMILY");
            }
        });

        this.mainView.getCreateCalendarView().setPublicBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("PUBLIC");
            }
        });

        // new personal calendar
        this.mainView.getNewPersonalCalendarView().setCancelBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("CREATE_NEW");
                mainView.getNewPersonalCalendarView().clearTextFields();
                mainView.getNewPersonalCalendarView().hideError();
            }
        });

        this.mainView.getNewPersonalCalendarView().setCreateBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String calendarName = mainView.getNewPersonalCalendarView().getNameTfText();

                boolean success = accountModel.createCalendar(calendarName, "Personal", "");

                if (success) {
                    loadCalendarsIntoView();
                    mainView.showCard("CREATE_NEW");
                    mainView.getNewPersonalCalendarView().showSuccess(calendarName);
                    mainView.getNewPersonalCalendarView().clearTextFields();
                    mainView.getNewPersonalCalendarView().hideError();
                } else {
                    mainView.getNewPersonalCalendarView().showError();
                }
            }
        });

        // new family calendar
        this.mainView.getNewFamilyCalendarView().setCancelBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("CREATE_NEW");
                mainView.getNewFamilyCalendarView().clearTextFields();
                mainView.getNewFamilyCalendarView().hideError();
            }
        });

        this.mainView.getNewFamilyCalendarView().setCreateBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String calendarName = mainView.getNewFamilyCalendarView().getNameTfText();
                String accessCode = mainView.getNewFamilyCalendarView().getCodeTfText();

                boolean success = accountModel.createCalendar(calendarName, "Family", accessCode);

                if (success) {
                    loadCalendarsIntoView();
                    mainView.showCard("CREATE_NEW");
                    mainView.getNewFamilyCalendarView().showSuccess(calendarName);
                    mainView.getNewFamilyCalendarView().clearTextFields();
                    mainView.getNewFamilyCalendarView().hideError();
                } else {
                    mainView.getNewFamilyCalendarView().showError();
                }
            }
        });

        // new public calendar
        this.mainView.getNewPublicCalendarView().setCancelBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("CREATE_NEW");
                mainView.getNewPublicCalendarView().clearTextFields();
                mainView.getNewPublicCalendarView().hideError();
            }
        });

        this.mainView.getNewPublicCalendarView().setCreateBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String calendarName = mainView.getNewPublicCalendarView().getNameTfText();

                boolean success = accountModel.createCalendar(calendarName, "Public", "");

                if (success) {
                    loadCalendarsIntoView();
                    mainView.showCard("CREATE_NEW");
                    mainView.getNewPublicCalendarView().showSuccess(calendarName);
                    mainView.getNewPublicCalendarView().clearTextFields();
                    mainView.getNewPublicCalendarView().hideError();
                } else {
                    mainView.getNewPublicCalendarView().showError();
                }
            }
        });

        // add existing calendar
        this.mainView.getAddCalendarView().setBackBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("NEW_CALENDAR");
            }
        });

        this.mainView.getAddCalendarView().setPublicListListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedCalendar = mainView.getAddCalendarView().getSelectedPublicCalendar();
                    if (selectedCalendar != null) {
                        if (mainView.getAddCalendarView().showPublicCalendarConfirmation(selectedCalendar)) {
                            Calendar calendar = accountModel.findCalendarByNameFromAllAccounts(selectedCalendar);
                            if (calendar != null) {
                                boolean success = accountModel.acceptCalendar(calendar);
                                if (success) {
                                    loadCalendarsIntoView();
                                    loadAvailableCalendarsIntoView();
                                }
                            }
                        }
                        mainView.getAddCalendarView().clearPublicSelection();
                    }
                }
            }
        });

        this.mainView.getAddCalendarView().setFamilyListListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedCalendar = mainView.getAddCalendarView().getSelectedFamilyCalendar();
                    if (selectedCalendar != null) {
                        Calendar calendar = accountModel.findCalendarByNameFromAllAccounts(selectedCalendar);
                        if (calendar instanceof Family) {
                            boolean success = false;
                            boolean cancelled = false;
                            while (!success && !cancelled) {
                                String accessCode = mainView.getAddCalendarView().showFamilyCalendarAccessCode(selectedCalendar);
                                if (accessCode == null) { // user cancelled
                                    mainView.getAddCalendarView().clearFamilySelection();
                                    cancelled = true;
                                } else {
                                    if (accountModel.acceptCalendar((Family) calendar, accessCode)) {
                                        loadCalendarsIntoView();
                                        loadAvailableCalendarsIntoView();
                                        success = true;
                                    } else {
                                        mainView.getAddCalendarView().showAccessCodeError();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });


        // monthly view
        this.mainView.getCalendarMonthlyView().setBackBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.setFrameTitle("The Digital Calendar");
                mainView.getCalendarsView().clearOwnedSelection();
                mainView.getCalendarsView().clearAddedSelection();
                mainView.showCard("VIEW_CALENDARS");
            }
        });

        this.mainView.getCalendarMonthlyView().setPrevBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentMonth = mainView.getCalendarMonthlyView().getSelectedMonth();
                int currentYear = mainView.getCalendarMonthlyView().getSelectedYear();
                
                if (currentMonth == 1) {
                    mainView.getCalendarMonthlyView().updateCalendar(12, currentYear - 1);
                } else {
                    mainView.getCalendarMonthlyView().updateCalendar(currentMonth - 1, currentYear);
                }
                mainView.getCalendarMonthlyView().refreshEntries();
                mainView.getCalendarMonthlyView().setDayPanelListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        LocalDate clickedDate = LocalDate.parse(e.getActionCommand());
                        Calendar currentCalendar = getCurrentCalendar();
                        calendarModel.sortEntries(currentCalendar);
                        mainView.getCalendarWeeklyView().setCurrentCalendar(calendarModel, currentCalendar);
                        mainView.getCalendarWeeklyView().updateWeekly(clickedDate);
                        mainView.getCalendarWeeklyView().populateWeeklyEntries(calendarModel, currentCalendar);
                        mainView.showCard("WEEKLY");
                    }
                });
            }
        });

        this.mainView.getCalendarMonthlyView().setNextBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentMonth = mainView.getCalendarMonthlyView().getSelectedMonth();
                int currentYear = mainView.getCalendarMonthlyView().getSelectedYear();
                
                if (currentMonth == 12) {
                    mainView.getCalendarMonthlyView().updateCalendar(1, currentYear + 1);
                } else {
                    mainView.getCalendarMonthlyView().updateCalendar(currentMonth + 1, currentYear);
                }
                mainView.getCalendarMonthlyView().refreshEntries();
                mainView.getCalendarMonthlyView().setDayPanelListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        LocalDate clickedDate = LocalDate.parse(e.getActionCommand());
                        Calendar currentCalendar = getCurrentCalendar();
                        calendarModel.sortEntries(currentCalendar);
                        mainView.getCalendarWeeklyView().setCurrentCalendar(calendarModel, currentCalendar);
                        mainView.getCalendarWeeklyView().updateWeekly(clickedDate);
                        mainView.getCalendarWeeklyView().populateWeeklyEntries(calendarModel, currentCalendar);
                        mainView.showCard("WEEKLY");
                    }
                });
            }
        });

        this.mainView.getCalendarMonthlyView().setGoBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedMonth = mainView.getCalendarMonthlyView().getSelectedMonth();
                int selectedYear = mainView.getCalendarMonthlyView().getSelectedYear();
                mainView.getCalendarMonthlyView().updateCalendar(selectedMonth, selectedYear);
                mainView.getCalendarMonthlyView().refreshEntries();
                mainView.getCalendarMonthlyView().setDayPanelListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        LocalDate clickedDate = LocalDate.parse(e.getActionCommand());
                        Calendar currentCalendar = getCurrentCalendar();
                        calendarModel.sortEntries(currentCalendar);
                        mainView.getCalendarWeeklyView().setCurrentCalendar(calendarModel, currentCalendar);
                        mainView.getCalendarWeeklyView().updateWeekly(clickedDate);
                        mainView.getCalendarWeeklyView().populateWeeklyEntries(calendarModel, currentCalendar);
                        mainView.showCard("WEEKLY");
                    }
                });
            }
        });

        this.mainView.getCalendarMonthlyView().setDeleteBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainView.getCalendarMonthlyView().showDeleteConfirmation()) {
                    String currentCalendarName = mainView.getCalendarMonthlyView().getCurrentCalendarName();
                    if (currentCalendarName != null) {
                        Calendar calendar = accountModel.findCalendarByName(currentCalendarName);
                        if (calendar != null) {
                            // check for default calendar
                            if (calendar.getName().equals(accountModel.getCurrentAccount().getUsername())) {
                                mainView.getCalendarMonthlyView().showDefaultCalendarError();
                            } else {
                                boolean success = false;
                                // check if current account owns the calendar
                                if (calendar.getOwner().equals(accountModel.getCurrentAccount().getUsername())) {
                                    success = accountModel.deleteCalendar(calendar);
                                } else {
                                    // remove from list if account does not own the calendar
                                    success = accountModel.removeCalendar(calendar);
                                }
                                
                                if (success) {
                                    loadCalendarsIntoView();
                                    loadAvailableCalendarsIntoView(); // refresh available calendars list
                                    mainView.setFrameTitle("The Digital Calendar");
                                    mainView.showCard("VIEW_CALENDARS");
                                }
                            }
                        }
                    }
                }
            }
        });

        // add entries
        this.mainView.getCalendarMonthlyView().setAddEntryBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.getTaskEntryView().clearTextFields();
                mainView.getEventEntryView().clearTextFields();
                mainView.getMeetingEntryView().clearTextFields();
                mainView.getJournalEntryView().clearTextFields();
                
                if (isPersonalCalendar()) {
                    mainView.getAddEntryView().showJournalBtn();
                } else {
                    mainView.getAddEntryView().hideJournalBtn();
                }
                mainView.showCard("ADD_ENTRY");
            }
        });

        this.mainView.getAddEntryView().setBackBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("MONTHLY");
            }
        });

        // add task entry
        this.mainView.getAddEntryView().setTaskBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("TASK");
            }
        });

        this.mainView.getTaskEntryView().setAddBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = mainView.getTaskEntryView().getEntryTitleTfText().trim();
                String details = mainView.getTaskEntryView().getDescriptionTaText().trim();
                String priority = mainView.getTaskEntryView().getSelectedPriority();
                String status = mainView.getTaskEntryView().getSelectedStatus();
                String createdBy = mainView.getTaskEntryView().getCreatedByTfText().trim();
                String finishedBy = mainView.getTaskEntryView().getFinishedByTfText().trim(); // optional
                
                int day = mainView.getTaskEntryView().getSelectedDay();
                int month = mainView.getTaskEntryView().getSelectedMonth();
                int year = mainView.getTaskEntryView().getSelectedYear();
                LocalDate date = LocalDate.of(year, month, day);
                
                // check to see if editing mode
                Entry currentEntry = mainView.getTaskEntryView().getCurrentEntry();
                boolean isEditing = (currentEntry != null);
                
                // if editing, remove current entry first
                if (isEditing) {
                    getCurrentCalendar().getEntries().remove(currentEntry);
                }
                
                if (title.isEmpty() || createdBy.isEmpty() || entryTitleExists(title)) {
                    mainView.getTaskEntryView().showError();
                } else {
                    Calendar currentCalendar = getCurrentCalendar();
                    Entry newTask = calendarModel.createEntry(currentCalendar, date, title, details, priority, status, createdBy, finishedBy);
                    currentCalendar.getEntries().add(newTask);
                    
                    mainView.getCalendarMonthlyView().refreshEntries();
                    
                    // cheks if in edit mode
                    if (isEditing) {
                        mainView.getCalendarWeeklyView().populateWeeklyEntries(calendarModel, currentCalendar);
                        mainView.showCard("WEEKLY");
                    } else {
                        mainView.showCard("MONTHLY");
                    }
                    
                    mainView.getTaskEntryView().clearTextFields();
                    mainView.getTaskEntryView().hideError();
                    mainView.getTaskEntryView().hideDeleteButton();
                    mainView.getTaskEntryView().setCurrentEntry(null);
                }
            }
        });

        this.mainView.getTaskEntryView().setDeleteBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainView.getTaskEntryView().showDeleteConfirmation()) {
                    Entry entryToDelete = mainView.getTaskEntryView().getCurrentEntry();
                    Calendar currentCalendar = getCurrentCalendar();
                    
                    currentCalendar.getEntries().remove(entryToDelete);
                    
                    mainView.getCalendarMonthlyView().refreshEntries();
                    mainView.getCalendarWeeklyView().populateWeeklyEntries(calendarModel, currentCalendar);
                    mainView.getTaskEntryView().hideDeleteButton();
                    mainView.getTaskEntryView().clearTextFields();
                    mainView.getTaskEntryView().setCurrentEntry(null);
                    mainView.showCard("WEEKLY");
                }
            }
        });

        this.mainView.getTaskEntryView().setCancelBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("ADD_ENTRY");
                mainView.getTaskEntryView().clearTextFields();
                mainView.getTaskEntryView().hideError();
                mainView.getTaskEntryView().hideDeleteButton();
                mainView.getTaskEntryView().setCurrentEntry(null);
            }
        });

        // add event entry
        this.mainView.getAddEntryView().setEventBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.getEventEntryView().setOrganizerTfText(accountModel.getCurrentAccount().getUsername());
                mainView.showCard("EVENT");
            }
        });

        this.mainView.getEventEntryView().setAddBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = mainView.getEventEntryView().getEntryTitleTfText().trim();
                String details = mainView.getEventEntryView().getDescriptionTaText().trim();
                String venue = mainView.getEventEntryView().getVenueTfText().trim();
                String organizer = accountModel.getCurrentAccount().getUsername();
                
                int day = mainView.getEventEntryView().getSelectedDay();
                int month = mainView.getEventEntryView().getSelectedMonth();
                int year = mainView.getEventEntryView().getSelectedYear();
                LocalDate date = LocalDate.of(year, month, day);
                
                int startHour = mainView.getEventEntryView().getSelectedStartTimeHour();
                int startMinute = mainView.getEventEntryView().getSelectedStartTimeMinute();
                LocalTime startTime = LocalTime.of(startHour, startMinute);
                
                int endHour = mainView.getEventEntryView().getSelectedEndTimeHour();
                int endMinute = mainView.getEventEntryView().getSelectedEndTimeMinute();
                LocalTime endTime = LocalTime.of(endHour, endMinute);

                if (title.isEmpty() || venue.isEmpty() || 
                    entryTitleExists(title) || endTime.isBefore(startTime)) {
                    mainView.getEventEntryView().showError();
                } else {
                    Calendar currentCalendar = getCurrentCalendar();
                    Entry newEvent = new Event(date, title, details, venue, organizer, startTime, endTime);
                    currentCalendar.getEntries().add(newEvent);
                    
                    mainView.getCalendarMonthlyView().refreshEntries();
                    mainView.showCard("MONTHLY");
                    mainView.getEventEntryView().clearTextFields();
                    mainView.getEventEntryView().hideError();
                }
            }
        });

        this.mainView.getEventEntryView().setCancelBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("ADD_ENTRY");
                mainView.getEventEntryView().clearTextFields();
                mainView.getEventEntryView().hideError();
            }
        });

        // add meeting entry
        this.mainView.getAddEntryView().setMeetingBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("MEETING");
            }
        });

        this.mainView.getMeetingEntryView().setAddBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = mainView.getMeetingEntryView().getEntryTitleTfText().trim();
                String details = mainView.getMeetingEntryView().getDescriptionTaText().trim();
                String modality = mainView.getMeetingEntryView().getSelectedModality();
                String venue = mainView.getMeetingEntryView().getVenueTfText().trim(); // optional
                String link = mainView.getMeetingEntryView().getLinkTfText().trim(); // optional
                
                int day = mainView.getMeetingEntryView().getSelectedDay();
                int month = mainView.getMeetingEntryView().getSelectedMonth();
                int year = mainView.getMeetingEntryView().getSelectedYear();
                LocalDate date = LocalDate.of(year, month, day);
                
                int startHour = mainView.getMeetingEntryView().getSelectedStartTimeHour();
                int startMinute = mainView.getMeetingEntryView().getSelectedStartTimeMinute();
                LocalTime startTime = LocalTime.of(startHour, startMinute);
                
                int endHour = mainView.getMeetingEntryView().getSelectedEndTimeHour();
                int endMinute = mainView.getMeetingEntryView().getSelectedEndTimeMinute();
                LocalTime endTime = LocalTime.of(endHour, endMinute);
                
                if (title.isEmpty()|| entryTitleExists(title) || endTime.isBefore(startTime)) {
                    mainView.getMeetingEntryView().showError();
                } else {
                    Calendar currentCalendar = getCurrentCalendar();
                    Entry newMeeting = calendarModel.createEntry(currentCalendar, date, title, details, modality, venue, link, startTime, endTime);
                    currentCalendar.getEntries().add(newMeeting);
                    
                    mainView.getCalendarMonthlyView().refreshEntries();
                    mainView.showCard("MONTHLY");
                    mainView.getMeetingEntryView().clearTextFields();
                    mainView.getMeetingEntryView().hideError();
                }
            }
        });


        this.mainView.getMeetingEntryView().setCancelBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("ADD_ENTRY");
                mainView.getMeetingEntryView().clearTextFields();
                mainView.getMeetingEntryView().hideError();
            }
        });

        // add journal entry
        this.mainView.getAddEntryView().setJournalBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("JOURNAL");
            }
        });

        this.mainView.getJournalEntryView().setAddBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = mainView.getJournalEntryView().getEntryTitleTfText().trim();
                String details = mainView.getJournalEntryView().getDescriptionTaText().trim();
                
                int day = mainView.getJournalEntryView().getSelectedDay();
                int month = mainView.getJournalEntryView().getSelectedMonth();
                int year = mainView.getJournalEntryView().getSelectedYear();
                LocalDate date = LocalDate.of(year, month, day);
                
                // Check required fields and journal date uniqueness
                if (title.isEmpty() || details.isEmpty() || 
                    entryTitleExists(title) || journalExistsForDate(date)) {
                    mainView.getJournalEntryView().showError();
                } else {
                    Calendar currentCalendar = getCurrentCalendar();
                    Entry newJournal = calendarModel.createEntry(currentCalendar, date, title, details);
                    currentCalendar.getEntries().add(newJournal);
                    
                    mainView.getCalendarMonthlyView().refreshEntries();
                    mainView.showCard("MONTHLY");
                    mainView.getJournalEntryView().clearTextFields();
                    mainView.getJournalEntryView().hideError();
                }
            }
        });

        this.mainView.getJournalEntryView().setCancelBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("ADD_ENTRY");
                mainView.getJournalEntryView().clearTextFields();
                mainView.getJournalEntryView().hideError();
            }
        });

        // weekly view
        this.mainView.getCalendarMonthlyView().setDayPanelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate clickedDate = LocalDate.parse(e.getActionCommand());
                Calendar currentCalendar = getCurrentCalendar();
                calendarModel.sortEntries(currentCalendar);
                mainView.getCalendarWeeklyView().setCurrentCalendar(calendarModel, currentCalendar);
                mainView.getCalendarWeeklyView().updateWeekly(clickedDate);
                mainView.getCalendarWeeklyView().populateWeeklyEntries(calendarModel, currentCalendar);
                mainView.showCard("WEEKLY");
            }
        });

        this.mainView.getCalendarWeeklyView().setPrevBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate currentWeekStart = mainView.getCalendarWeeklyView().getCurrentWeekStart();
                LocalDate prevWeekStart = currentWeekStart.minusWeeks(1);
                Calendar currentCalendar = getCurrentCalendar();
                calendarModel.sortEntries(currentCalendar);
                mainView.getCalendarWeeklyView().updateWeekly(prevWeekStart);
                mainView.getCalendarWeeklyView().populateWeeklyEntries(calendarModel, currentCalendar);
            }
        });

        this.mainView.getCalendarWeeklyView().setNextBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate currentWeekStart = mainView.getCalendarWeeklyView().getCurrentWeekStart();
                LocalDate nextWeekStart = currentWeekStart.plusWeeks(1);
                Calendar currentCalendar = getCurrentCalendar();
                calendarModel.sortEntries(currentCalendar);
                mainView.getCalendarWeeklyView().updateWeekly(nextWeekStart);
                mainView.getCalendarWeeklyView().populateWeeklyEntries(calendarModel, currentCalendar);
            }
        });

        this.mainView.getCalendarWeeklyView().setGoBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedMonth = mainView.getCalendarWeeklyView().getSelectedMonth();
                int selectedDay = mainView.getCalendarWeeklyView().getSelectedDay();
                int selectedYear = mainView.getCalendarWeeklyView().getSelectedYear();
                
                LocalDate selectedDate = LocalDate.of(selectedYear, selectedMonth, selectedDay);
                Calendar currentCalendar = getCurrentCalendar();
                calendarModel.sortEntries(currentCalendar);
                
                mainView.getCalendarWeeklyView().updateWeekly(selectedDate);
                mainView.getCalendarWeeklyView().populateWeeklyEntries(calendarModel, currentCalendar);
            }
        });

        this.mainView.getCalendarWeeklyView().setAddEntryBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPersonalCalendar()) {
                    mainView.getAddEntryView().showJournalBtn();
                } else {
                    mainView.getAddEntryView().hideJournalBtn();
                }
                mainView.showCard("ADD_ENTRY");
            }
        });

        this.mainView.getCalendarWeeklyView().setDeleteBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainView.getCalendarWeeklyView().showDeleteConfirmation()) {
                    String currentCalendarName = mainView.getCalendarWeeklyView().getCurrentCalendarName();
                    if (currentCalendarName != null) {
                        Calendar calendar = accountModel.findCalendarByName(currentCalendarName);
                        if (calendar != null) {
                            // check for default calendar
                            if (calendar.getName().equals(accountModel.getCurrentAccount().getUsername())) {
                                mainView.getCalendarWeeklyView().showDefaultCalendarError();
                            } else {
                                boolean success = false;
                                // check if current account owns the calendar
                                if (calendar.getOwner().equals(accountModel.getCurrentAccount().getUsername())) {
                                    success = accountModel.deleteCalendar(calendar);
                                } else {
                                    // remove from list if account does not own the calendar
                                    success = accountModel.removeCalendar(calendar);
                                }
                                
                                if (success) {
                                    loadCalendarsIntoView();
                                    loadAvailableCalendarsIntoView(); // refresh available calendars list
                                    mainView.setFrameTitle("The Digital Calendar");
                                    mainView.showCard("VIEW_CALENDARS");
                                }
                            }
                        }
                    }
                }
            }
        });

        this.mainView.getCalendarWeeklyView().setBackBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.showCard("MONTHLY");
            }
        });

        mainView.getCalendarWeeklyView().setTitleClickListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entryTitle = e.getActionCommand();
                Entry entry = findEntryByTitle(entryTitle);
                
                if (entry instanceof Task) {
                    mainView.getTaskEntryView().populateFields((Task) entry);
                    mainView.getTaskEntryView().showDeleteButton();
                    mainView.getTaskEntryView().setCurrentEntry(entry);
                    mainView.showCard("TASK");
                } 
            }
        });
    }

    private void loadCalendarsIntoView() {
        mainView.getCalendarsView().clearAllCalendars();
        
        ArrayList<String> ownedCalendarNames = accountModel.getOwnedCalendarNames();
        ArrayList<String> addedCalendarNames = accountModel.getAddedCalendarNames();
        
        // add owned calendars
        for (String calendarName : ownedCalendarNames) {
            mainView.getCalendarsView().addOwnedCalendar(calendarName);
        }
        
        // add added calendars
        for (String calendarName : addedCalendarNames) {
            mainView.getCalendarsView().addAddedCalendar(calendarName);
        }
    }

    private void loadAvailableCalendarsIntoView() {
        // clear existing lists properly
        mainView.getAddCalendarView().clearPublicSelection();
        mainView.getAddCalendarView().clearFamilySelection();
        mainView.getAddCalendarView().clearAllPublicCalendars();
        mainView.getAddCalendarView().clearAllFamilyCalendars();
        
        // public calendars
        ArrayList<String> availablePublicCalendars = accountModel.getAllPublicCalendarNames();
        for (String calendarName : availablePublicCalendars) {
            mainView.getAddCalendarView().addPublicCalendar(calendarName);
        }
        
        // family calendars
        ArrayList<String> availableFamilyCalendars = accountModel.getAllFamilyCalendarNames();
        for (String calendarName : availableFamilyCalendars) {
            mainView.getAddCalendarView().addFamilyCalendar(calendarName);
        }
    }

    private Calendar getCurrentCalendar() {
        String calendarName = mainView.getCalendarMonthlyView().getCurrentCalendarName();
        return accountModel.findCalendarByName(calendarName);
    }

    private boolean entryTitleExists(String title) {
        Calendar currentCalendar = getCurrentCalendar();
        boolean exists = false;
        for (Entry entry : currentCalendar.getEntries()) {
            if (entry.getTitle().equals(title)) {
                exists = true;
            }
        }
        return exists;
    }

    private Entry findEntryByTitle(String title) {
        Calendar currentCalendar = getCurrentCalendar();
        for (Entry entry : currentCalendar.getEntries()) {
            if (entry.getTitle().equals(title)) {
                return entry;
            }
        }
        return null;
    }

    private boolean journalExistsForDate(LocalDate date) {
        Calendar currentCalendar = getCurrentCalendar();
        boolean exists = false;
        for (Entry entry : currentCalendar.getEntries()) {
            if (entry instanceof Journal && entry.getDate().equals(date)) {
                exists = true;
            }
        }
        return exists;
    }

    private boolean isPersonalCalendar() {
        Calendar currentCalendar = getCurrentCalendar();
        return currentCalendar instanceof Personal;
    }
}