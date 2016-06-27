package bo.edu.ucbcba.hotel.view;

import bo.edu.ucbcba.hotel.controller.SalonReservationController;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Gabo on 27-Jun-16.
 */
public class NewSalonReservationForm extends JDialog {
    private JPanel newSalonreservationPanel;
    private JTextField ClienteNameField;
    private JTextField CantPersonasField;
    private JTextField CiClientField;
    private JComboBox DaysComboBox;
    private JComboBox MonthsComboBox;
    private JComboBox SalonComboBox;
    private JButton saveButton;
    private JButton exitButton;
    private JLabel ClientNameField;
    private JComboBox YearComboBox;
    private SalonReservationController salonReservationController;

    public NewSalonReservationForm(JDialog parent) {
        super(parent, "New Reservation", true);
        setContentPane(newSalonreservationPanel);
        setSize(600, 400);
        setBounds(480, 150, 500, 400);
        addComboBox();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void save() {
        if (ClienteNameField.getText().length() == 0 || CiClientField.getText().length() == 0 || CantPersonasField.getText().length()==0) {
            JOptionPane.showMessageDialog(this, "Fields can't be empty", "Format error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (ClienteNameField.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Client name can't be a number,please insert new name", "Format error", JOptionPane.INFORMATION_MESSAGE);
            ClienteNameField.setText("");
            return;
        }
        if (ClienteNameField.getText().length() > 30) {
            JOptionPane.showMessageDialog(this, "Client name is to big,please insert new one", "Format error", JOptionPane.INFORMATION_MESSAGE);
            ClienteNameField.setText("");
            return;
        }

        if (!CiClientField.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Client's ci field can only be a number,please insert one number", "Format error", JOptionPane.INFORMATION_MESSAGE);
            CiClientField.setText("");
            return;
        }
        if (CiClientField.getText().length() > 8) {
            JOptionPane.showMessageDialog(this, "client's ci is to big,please insert new one (max capacity:9999999)", "Format error", JOptionPane.INFORMATION_MESSAGE);
            CiClientField.setText("");
            return;
        }
        if (!CantPersonasField.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Amount of people field can only be a number,please insert one number", "Format error", JOptionPane.INFORMATION_MESSAGE);
            CantPersonasField.setText("");
            return;
        }
        if (CantPersonasField.getText().length() > 6) {
            JOptionPane.showMessageDialog(this, "Amount of people is to big,please insert new one (max capacity:99999)", "Format error", JOptionPane.INFORMATION_MESSAGE);
            CantPersonasField.setText("");
            return;
        }
        try {
            salonReservationController.create(ClientNameField.getText(), Integer.parseInt(CiClientField.getText()), Integer.parseInt(DaysComboBox.getSelectedItem().toString()), MonthsComboBox.getSelectedItem().toString(), Integer.parseInt(YearComboBox.getSelectedItem().toString()), Integer.parseInt(CantPersonasField.getText()), SalonComboBox.getSelectedItem().toString());
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
        }

        JOptionPane.showMessageDialog(this, "Reservation created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    private void addComboBox() {
        for (int c = 1; c <= 31; c++) {
            DaysComboBox.addItem(c);
        }
        for (int c = 2016; c <= 2030; c++) {
            YearComboBox.addItem(c);
        }
        MonthsComboBox.addItem("January");
        MonthsComboBox.addItem("February");
        MonthsComboBox.addItem("March");
        MonthsComboBox.addItem("April");
        MonthsComboBox.addItem("May");
        MonthsComboBox.addItem("June");
        MonthsComboBox.addItem("July");
        MonthsComboBox.addItem("August");
        MonthsComboBox.addItem("September");
        MonthsComboBox.addItem("October");
        MonthsComboBox.addItem("November");
        MonthsComboBox.addItem("December");


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
        newSalonreservationPanel = new JPanel();
        newSalonreservationPanel.setLayout(new GridLayoutManager(5, 9, new Insets(10, 10, 10, 10), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Nombre Cleinte");
        newSalonreservationPanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ClienteNameField = new JTextField();
        newSalonreservationPanel.add(ClienteNameField, new GridConstraints(0, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        CantPersonasField = new JTextField();
        newSalonreservationPanel.add(CantPersonasField, new GridConstraints(3, 1, 1, 6, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Cantdad Personas");
        newSalonreservationPanel.add(label2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Salon");
        newSalonreservationPanel.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Fecha Reserva");
        newSalonreservationPanel.add(label4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        DaysComboBox = new JComboBox();
        newSalonreservationPanel.add(DaysComboBox, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Month");
        newSalonreservationPanel.add(label5, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        MonthsComboBox = new JComboBox();
        newSalonreservationPanel.add(MonthsComboBox, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Year");
        newSalonreservationPanel.add(label6, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ClientNameField = new JLabel();
        ClientNameField.setText("Ci cliente");
        newSalonreservationPanel.add(ClientNameField, new GridConstraints(0, 5, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CiClientField = new JTextField();
        newSalonreservationPanel.add(CiClientField, new GridConstraints(0, 7, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        SalonComboBox = new JComboBox();
        newSalonreservationPanel.add(SalonComboBox, new GridConstraints(2, 1, 1, 6, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Day");
        newSalonreservationPanel.add(label7, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        YearComboBox = new JComboBox();
        newSalonreservationPanel.add(YearComboBox, new GridConstraints(1, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveButton = new JButton();
        saveButton.setText("Save");
        newSalonreservationPanel.add(saveButton, new GridConstraints(4, 4, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        exitButton = new JButton();
        exitButton.setText("Exit");
        newSalonreservationPanel.add(exitButton, new GridConstraints(4, 8, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return newSalonreservationPanel;
    }
}
