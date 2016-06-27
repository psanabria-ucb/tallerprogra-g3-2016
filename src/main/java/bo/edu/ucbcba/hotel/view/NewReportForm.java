package bo.edu.ucbcba.hotel.view;

import bo.edu.ucbcba.hotel.controller.ReportsController;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by CésarIvan on 27/06/2016.
 */
public class NewReportForm extends JDialog{
    private JComboBox dayComboBox;
    private JComboBox monthComboBox;
    private JComboBox yearComboBox;
    private JComboBox typeComboBox;
    private JTextArea textArea;
    private JButton saveButton;
    private JButton cancelButton;
    private JPanel newReportPanel;
    private ReportsController reportsController;

    public NewReportForm(JDialog parent){
        super(parent, "New Report", true);
        pack();
        setContentPane(newReportPanel);
        setSize(600, 400);
        setBounds(400, 150, 600, 400);
        reportsController = new ReportsController();

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveReport();
            }
        });
    }

    private void cancel() {
        setVisible(false);
        dispose();
    }

    private void saveReport() {

        try {
            if (reportsController.exemptions(textArea.getText(),  typeComboBox.getActionCommand())) {
                reportsController.create(textArea.getText(), dayComboBox.getSelectedIndex(), monthComboBox.getSelectedIndex(), yearComboBox.getSelectedIndex(),typeComboBox.getActionCommand());
                JOptionPane.showMessageDialog(this, "Report created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                cancel();
            }

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void populateComboBox() {
        for (int day = 1; day < 32; day = day + 1) {
            dayComboBox.addItem(day);
        }
        for (int month = 1; month < 13; month = month + 1) {
            monthComboBox.addItem(month);
        }
        for (int year = 2016; year < 2018; year = year + 1) {
            yearComboBox.addItem(year);
        }

        String[] type={"Client","Employer","Service","Room","Salon"};

        for (String t: type){
            typeComboBox.addItem(t);
        }


    }
}
