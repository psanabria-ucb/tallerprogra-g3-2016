package bo.edu.ucbcba.hotel.view;

import bo.edu.ucbcba.hotel.controller.ReportsController;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by CésarIvan on 27/06/2016.
 */
public class NewReportForm extends JDialog {
    private JComboBox dayComboBox;
    private JComboBox monthComboBox;
    private JComboBox yearComboBox;
    private JComboBox typeComboBox;
    private JTextArea textArea;
    private JButton saveButton;
    private JButton cancelButton;
    private JPanel newReportPanel;
    private ReportsController reportsController;

    public NewReportForm(JDialog parent) {
        super(parent, "New Report", true);
        pack();
        setContentPane(newReportPanel);
        setSize(600, 400);
        setBounds(400, 150, 600, 400);
        reportsController = new ReportsController();
        populateComboBox();

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

        String[] type = {"Client", "Employer", "Service", "Room", "Salon"};

        for (String t : type) {
            typeComboBox.addItem(t);
        }


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
        newReportPanel = new JPanel();
        newReportPanel.setLayout(new GridLayoutManager(9, 8, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("New");
        newReportPanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        newReportPanel.add(spacer1, new GridConstraints(0, 1, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Date:");
        newReportPanel.add(label2, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dayComboBox = new JComboBox();
        newReportPanel.add(dayComboBox, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(33, 24), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Day:");
        newReportPanel.add(label3, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(23, 24), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Month");
        newReportPanel.add(label4, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(36, 24), null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Year");
        newReportPanel.add(label5, new GridConstraints(2, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(23, 24), null, 0, false));
        yearComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        yearComboBox.setModel(defaultComboBoxModel1);
        newReportPanel.add(yearComboBox, new GridConstraints(2, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        monthComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        monthComboBox.setModel(defaultComboBoxModel2);
        newReportPanel.add(monthComboBox, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        typeComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        typeComboBox.setModel(defaultComboBoxModel3);
        newReportPanel.add(typeComboBox, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Type:");
        newReportPanel.add(label6, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        newReportPanel.add(spacer2, new GridConstraints(3, 0, 1, 7, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        newReportPanel.add(spacer3, new GridConstraints(5, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        textArea = new JTextArea();
        newReportPanel.add(textArea, new GridConstraints(6, 2, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Comment:");
        newReportPanel.add(label7, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        newReportPanel.add(spacer4, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        newReportPanel.add(spacer5, new GridConstraints(0, 7, 9, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        newReportPanel.add(spacer6, new GridConstraints(7, 0, 1, 7, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        saveButton = new JButton();
        saveButton.setText("Save");
        newReportPanel.add(saveButton, new GridConstraints(8, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        newReportPanel.add(cancelButton, new GridConstraints(8, 4, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return newReportPanel;
    }
}
