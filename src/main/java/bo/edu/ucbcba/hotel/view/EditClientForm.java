package bo.edu.ucbcba.hotel.view;

import bo.edu.ucbcba.hotel.controller.ClientController;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by CésarIvan on 01/06/2016.
 */
public class EditClientForm extends JDialog {
    private JPanel EditClientsPanel;
    private JTextField firstNameText;
    private JTextField lastNameText;
    private JTextField phoneText;
    private JButton saveButton;
    private JButton cancelButton;
    private JLabel CiField;
    ClientController clientController;


    public EditClientForm(JDialog parent, String firstName, String lastName, int Ci, int Phone) {
        super(parent, "Edit Client", true);
        pack();
        setContentPane(EditClientsPanel);
        setSize(600, 400);
        setBounds(400, 150, 600, 400);
        clientController = new ClientController();
        firstNameText.setText(firstName);
        lastNameText.setText(lastName);
        String ci;
        ci = Integer.toString(Ci);
        CiField.setText(ci);
        String phone;
        phone = Integer.toString(Phone);
        phoneText.setText(phone);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveClient();
            }
        });

    }

    private void saveClient() {
        try {
            clientController.update(firstNameText.getText(), lastNameText.getText(), Integer.parseInt(CiField.getText()), Integer.parseInt(phoneText.getText()));
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
        }

        JOptionPane.showMessageDialog(this, "Client modificated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
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
        EditClientsPanel = new JPanel();
        EditClientsPanel.setLayout(new GridLayoutManager(5, 8, new Insets(10, 10, 10, 10), -1, -1));
        EditClientsPanel.setBackground(new Color(-14034741));
        final JLabel label1 = new JLabel();
        label1.setText("First name:");
        EditClientsPanel.add(label1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        firstNameText = new JTextField();
        firstNameText.setBackground(new Color(-260));
        EditClientsPanel.add(firstNameText, new GridConstraints(1, 2, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Last name:");
        EditClientsPanel.add(label2, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lastNameText = new JTextField();
        lastNameText.setBackground(new Color(-260));
        EditClientsPanel.add(lastNameText, new GridConstraints(2, 2, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Phone:");
        EditClientsPanel.add(label3, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        phoneText = new JTextField();
        phoneText.setBackground(new Color(-260));
        EditClientsPanel.add(phoneText, new GridConstraints(3, 2, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        saveButton = new JButton();
        saveButton.setBackground(new Color(-15945937));
        saveButton.setText("Save");
        EditClientsPanel.add(saveButton, new GridConstraints(4, 2, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setBackground(new Color(-4450033));
        cancelButton.setText("Cancel");
        EditClientsPanel.add(cancelButton, new GridConstraints(4, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        EditClientsPanel.add(spacer1, new GridConstraints(0, 0, 5, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Client CI:");
        EditClientsPanel.add(label4, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        EditClientsPanel.add(spacer2, new GridConstraints(3, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        CiField = new JLabel();
        CiField.setText("");
        EditClientsPanel.add(CiField, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        EditClientsPanel.add(spacer3, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        EditClientsPanel.add(spacer4, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return EditClientsPanel;
    }
}
