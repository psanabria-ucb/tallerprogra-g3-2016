package bo.edu.ucbcba.hotel.view;

import bo.edu.ucbcba.hotel.controller.ReportsController;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;
import bo.edu.ucbcba.hotel.model.Reports;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by CésarIvan on 27/06/2016.
 */
public class ReportForm extends JDialog {
    private JPanel reportForm;
    private JTextField searchField;
    private JButton searchButton;
    private JTable reportsTable;
    private JButton addButton;
    private JButton exitButton;
    private JButton deleteReportButton;
    private JButton editButton;
    private ReportsController reportsController;

    ReportForm(JFrame parent){
        super(parent,"Reports",true);
        pack();
        setContentPane(reportForm);
        setSize(650, 400);
        setBounds(400, 150, 650, 400);
        reportsController = new ReportsController();
        populateTable1();
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        deleteReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteReport();
                populateTable();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createReport();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editReport();
                populateTable();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTable();
            }
        });
    }

    private void populateTable1() {

        List<Reports> reportList = ReportsController.searchReport(searchField.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Report Id");
        model.addColumn("Type");
        model.addColumn("Date");
        model.addColumn("Text");
        reportsTable.setModel(model);

        for (Reports s : reportList) {
            Object[] row = new Object[4];

            row[0] = s.getReportId();
            row[1] = s.getType();
            row[2] = s.getDate();
            model.addRow(row);
        }
    }
    private void populateTable() {

        List<Reports> reportsList = ReportsController.searchReport(searchField.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Report Id");
        model.addColumn("Type");
        model.addColumn("Date");
        model.addColumn("Text");

        reportsTable.setModel(model);
        if (searchField.getText().length() > 15) {
            JOptionPane.showMessageDialog(this, "Search argument is to big,please insert another one", "Error", JOptionPane.INFORMATION_MESSAGE);
            searchField.setText("");
            populateTable1();
            return;
        }
        if (reportsList.size() == 0) {
            JOptionPane.showMessageDialog(this, "No matches with rooms data base", "Error", JOptionPane.INFORMATION_MESSAGE);
            searchField.setText("");
            populateTable1();
        } else {
            for (Reports s : reportsList) {
                Object[] row = new Object[4];

                row[0] = s.getReportId();
                row[1] = s.getType();
                row[2] = s.getDate();
                model.addRow(row);
            }
        }
    }

    private void editReport() {
        int reportId,day,month,year;
        String text,type,date;
        if (reportsTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Please select one client to edit", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            DefaultTableModel tm = (DefaultTableModel) reportsTable.getModel();
            reportId = (int) tm.getValueAt(reportsTable.getSelectedRow(), 0);
            text = (String) tm.getValueAt(reportsTable.getSelectedRow(), 1);
            type = (String) tm.getValueAt(reportsTable.getSelectedRow(), 2);
            date = (String) tm.getValueAt(reportsTable.getSelectedRow(), 3);
            List<Reports> reportsList = ReportsController.getReport((int) tm.getValueAt(reportsTable.getSelectedRow(), 0));

            for (Reports s: reportsList){
                day=s.getDay();
                month=s.getMonth();
                year=s.getYear();

                //EditReportForm editReportForm = new EditReportForm(this, reportId, text,type, date,day,month,year);
               // editClientForm.setVisible(true);
            }


            populateTable();
        }
    }

    private void deleteReport() {
        int n;
        if (reportsTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Please select one Client to delete", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            DefaultTableModel tm = (DefaultTableModel) reportsTable.getModel();
            n = (int) tm.getValueAt(reportsTable.getSelectedRow(), 0);

            try {
                reportsController.DeleteReport(Integer.toString(n));
            } catch (ValidationException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(this, "Report deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void createReport() {
        NewReportForm newReport = new NewReportForm(this);
        newReport.setVisible(true);
        populateTable();
    }
}
