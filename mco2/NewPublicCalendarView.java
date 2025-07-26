import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NewPublicCalendarView {
    private JPanel panel, namePanel, createPanel;
    private JLabel titleLbl, nameLbl, errorLbl;
    private JTextField nameTf;
    private JButton createBtn, cancelBtn;
    private JSeparator separator;

    public NewPublicCalendarView() {
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));

        // title label
        this.titleLbl = new JLabel("New Public Calendar");
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

        // name label
        this.nameLbl = new JLabel("Name:");
        this.nameLbl.setPreferredSize(new Dimension(140, 50));
        this.nameLbl.setMaximumSize(new Dimension(140, 50));
        this.nameLbl.setMinimumSize(new Dimension(140, 50));
        this.nameLbl.setFont(new Font("Century Gothic", 0, 24));
        this.nameLbl.setForeground(new Color(51, 51, 51)); 

        // name text field
        this.nameTf = new JTextField();
        this.nameTf.setPreferredSize(new Dimension(240, 50));
        this.nameTf.setMaximumSize(new Dimension(240, 50));
        this.nameTf.setMinimumSize(new Dimension(240, 50));
        this.nameTf.setFont(new Font("Century Gothic", 0, 18));
        this.nameTf.setForeground(new Color(51, 51, 51));
        this.nameTf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)),
                                    BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        
        // name panel
        this.namePanel = new JPanel();
        this.namePanel.setLayout(new BoxLayout(this.namePanel, BoxLayout.X_AXIS));
        this.namePanel.setPreferredSize(new Dimension(400, 50));
        this.namePanel.setMaximumSize(new Dimension(400, 50));
        this.namePanel.setMinimumSize(new Dimension(400, 50));
        this.namePanel.add(nameLbl);
        this.namePanel.add(Box.createHorizontalStrut(10));
        this.namePanel.add(nameTf);

        // cancel button (back to calendar type options)
        this.cancelBtn = new JButton("Cancel");
        this.cancelBtn.setPreferredSize(new Dimension(140, 50));
        this.cancelBtn.setMaximumSize(new Dimension(140, 50));
        this.cancelBtn.setMinimumSize(new Dimension(140, 50));
        this.cancelBtn.setFont(new Font("Century Gothic", 0, 24));
        this.cancelBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.cancelBtn.setForeground(new Color(51, 51, 51));
        this.cancelBtn.setContentAreaFilled(false);

        // create button
        this.createBtn = new JButton("Create");
        this.createBtn.setPreferredSize(new Dimension(240, 50));
        this.createBtn.setMaximumSize(new Dimension(240, 50));
        this.createBtn.setMinimumSize(new Dimension(240, 50));
        this.createBtn.setFont(new Font("Century Gothic", 0, 24));
        this.createBtn.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
        this.createBtn.setForeground(new Color(51, 51, 51));
        this.createBtn.setContentAreaFilled(false);

        // create panel
        this.createPanel = new JPanel();
        this.createPanel.setLayout(new BoxLayout(this.createPanel, BoxLayout.X_AXIS));
        this.createPanel.setPreferredSize(new Dimension(400, 50));
        this.createPanel.setMaximumSize(new Dimension(400, 50));
        this.createPanel.setMinimumSize(new Dimension(400, 50));
        this.createPanel.add(cancelBtn);
        this.createPanel.add(Box.createHorizontalStrut(10));
        this.createPanel.add(createBtn);

        // error message label
        this.errorLbl = new JLabel("Cannot create calendar (select a different name)");
        this.errorLbl.setFont(new Font("Century Gothic", 0, 18));
        this.errorLbl.setForeground(new Color(153, 153, 153));
        this.errorLbl.setVisible(false); // initially hidden

        // center panel components
        this.titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.createPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.errorLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add components to panel
        this.panel.add(Box.createVerticalGlue());
        this.panel.add(titleLbl);
        this.panel.add(Box.createRigidArea(new Dimension(0, 30)));
        this.panel.add(separator);
        this.panel.add(Box.createRigidArea(new Dimension(0, 40)));
        this.panel.add(namePanel);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20))); 
        this.panel.add(createPanel);
        this.panel.add(Box.createRigidArea(new Dimension(0, 40))); 
        this.panel.add(errorLbl);
        this.panel.add(Box.createVerticalGlue());
    }

    // listeners & text field getters
    public String getNameTfText() {
        return this.nameTf.getText();
    }

    public void setCreateBtnListener(ActionListener actionListener) {
        this.createBtn.addActionListener(actionListener);
    }

    public void setCancelBtnListener(ActionListener actionListener) {
        this.cancelBtn.addActionListener(actionListener);
    }

    // helper methods
    public void showError() { // if cannot create calendar
        this.errorLbl.setVisible(true);
    }

    public void hideError() {
        this.errorLbl.setVisible(false);
    }

    public void clearTextFields() {
		this.nameTf.setText("");
	}

    // getters
    public JPanel getPanel() {
        return this.panel;
    }
}