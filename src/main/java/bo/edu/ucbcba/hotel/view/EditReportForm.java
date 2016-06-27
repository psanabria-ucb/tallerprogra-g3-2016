package bo.edu.ucbcba.hotel.view;

import bo.edu.ucbcba.hotel.controller.ReportsController;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by CésarIvan on 27/06/2016.
 */
public class EditReportForm extends JDialog {
    private JPanel EditFormPanel;
    private JComboBox dayComboBox;
    private JComboBox monthComboBox;
    private JComboBox yearComboBox;
    private JComboBox typeComboBox;
    private JTextArea textArea;
    private JButton saveButton;
    private JButton cancelButton;
    private ReportsController reportsController;

    public EditReportForm(JDialog parent,int reportId, String text,String type,String date,int day,int month,int year){
        super(parent, "Edit Report", true);
        pack();
        setContentPane(EditFormPanel);
        setSize(600, 400);
        setBounds(400, 150, 600, 400);

        reportsController = new ReportsController();
        dayComboBox.setSelectedItem(day);
        monthComboBox.setSelectedItem(month);
        yearComboBox.setSelectedItem(year);
        typeComboBox.setSelectedItem(type);
        textArea.setText(text);
        populateComboBox();

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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
            Object type=typeComboBox.getSelectedItem();
            String t = String.valueOf(type);
            Object day= dayComboBox.getSelectedItem();
            String da= String.valueOf(day);
            int d=Integer.parseInt(da);
            Object month= monthComboBox.getSelectedItem();
            String mon= String.valueOf(month);
            int m=Integer.parseInt(mon);
            Object year= yearComboBox.getSelectedItem();
            String yea= String.valueOf(year);
            int y=Integer.parseInt(yea);
            if (reportsController.exemptions(textArea.getText(), t)) {
                reportsController.create(textArea.getText(), d, m, y,t);
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
