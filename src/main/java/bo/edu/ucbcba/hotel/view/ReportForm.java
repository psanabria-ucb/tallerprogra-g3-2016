package bo.edu.ucbcba.hotel.view;

import bo.edu.ucbcba.hotel.controller.ReportsController;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;
import bo.edu.ucbcba.hotel.model.Reports;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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

    ReportForm(JFrame parent) {
        super(parent, "Reports", true);
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
                setVisible(false);
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
            row[3] = s.getText();
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
            JOptionPane.showMessageDialog(this, "No matches with reports data base", "Error", JOptionPane.INFORMATION_MESSAGE);
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
        int reportId, day, month, year;
        String text, type, date;
        if (reportsTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Please select one report to edit", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            DefaultTableModel tm = (DefaultTableModel) reportsTable.getModel();
            reportId = (int) tm.getValueAt(reportsTable.getSelectedRow(), 0);
            text = (String) tm.getValueAt(reportsTable.getSelectedRow(), 1);
            type = (String) tm.getValueAt(reportsTable.getSelectedRow(), 2);
            date = (String) tm.getValueAt(reportsTable.getSelectedRow(), 3);
            List<Reports> reportsList = ReportsController.getReport((int) tm.getValueAt(reportsTable.getSelectedRow(), 0));

            for (Reports s : reportsList) {
                day = s.getDay();
                month = s.getMonth();
                year = s.getYear();


                EditReportForm editReportForm = new EditReportForm(this, reportId, text,type, date,day,month,year);
                editReportForm.setVisible(true);

            }


            populateTable();
        }
    }

    private void deleteReport() {
        int n;
        if (reportsTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Please select one Report to delete", "Error", JOptionPane.INFORMATION_MESSAGE);
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        reportForm = new JPanel();
        reportForm.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        searchField = new JTextField();
        reportForm.add(searchField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        searchButton = new JButton();
        searchButton.setText("Search");
        reportForm.add(searchButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 4, new Insets(0, 0, 0, 0), -1, -1));
        reportForm.add(panel1, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        reportsTable = new JTable();
        scrollPane1.setViewportView(reportsTable);
        addButton = new JButton();
        addButton.setText("Add new report");
        panel1.add(addButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        exitButton = new JButton();
        exitButton.setText("Exit");
        panel1.add(exitButton, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteReportButton = new JButton();
        deleteReportButton.setText("Delete report");
        panel1.add(deleteReportButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editButton = new JButton();
        editButton.setText("Edit");
        panel1.add(editButton, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return reportForm;
    }
}
