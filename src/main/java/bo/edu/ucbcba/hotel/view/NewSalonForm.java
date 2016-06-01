package bo.edu.ucbcba.hotel.view;

import bo.edu.ucbcba.hotel.controller.SalonController;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NewSalonForm extends JDialog {
    private JPanel NewSalonPanel;
    private JTextField SalonName;
    private JTextField SalonCapacity;
    private JButton saveButton;
    private JButton exitButton;
    private SalonController salonController;
    private boolean availibility = true;

    NewSalonForm(JDialog parent) {
        super(parent, "New Salon Form", true);
        pack();
        setContentPane(NewSalonPanel);
        setBounds(560, 200, 350, 150);

        salonController = new SalonController();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                create();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
    }

    private void create() {
        if (SalonName.getText().length() > 20) {
            JOptionPane.showMessageDialog(this, "Salon name is to big,please insert new one", "Format error", JOptionPane.INFORMATION_MESSAGE);
            SalonName.setText("");
            return;
        }
        if (SalonCapacity.getText().length() > 5) {
            JOptionPane.showMessageDialog(this, "Salon capacity is to big,please insert new one", "Format error", JOptionPane.INFORMATION_MESSAGE);
            SalonCapacity.setText("");
            return;
        }
        if (!SalonCapacity.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Salon capacity field can only be a number,please insert one number", "Format error", JOptionPane.INFORMATION_MESSAGE);
            SalonCapacity.setText("");
            return;
        }
        try {
            salonController.create(SalonName.getText(), availibility, Integer.parseInt(SalonCapacity.getText()));
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
        }
        JOptionPane.showMessageDialog(this, "Salon created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        exit();
    }

    private void exit() {
        dispose();
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
        NewSalonPanel = new JPanel();
        NewSalonPanel.setLayout(new GridLayoutManager(3, 4, new Insets(20, 20, 20, 20), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Salon Name");
        NewSalonPanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Salon Capacity");
        NewSalonPanel.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        SalonName = new JTextField();
        NewSalonPanel.add(SalonName, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        SalonCapacity = new JTextField();
        NewSalonPanel.add(SalonCapacity, new GridConstraints(1, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        saveButton = new JButton();
        saveButton.setText("Save");
        NewSalonPanel.add(saveButton, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        NewSalonPanel.add(spacer1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        exitButton = new JButton();
        exitButton.setText("Exit");
        NewSalonPanel.add(exitButton, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return NewSalonPanel;
    }
}
