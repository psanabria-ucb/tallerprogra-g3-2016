package bo.edu.ucbcba.hotel.view;

import bo.edu.ucbcba.hotel.controller.ServiceController;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alejandra on 17/05/2016.
 */
public class NewServiceForm extends JDialog {
    private JPanel NewServicePanel;
    private JButton cancelButton;
    private JButton saveButton;
    private JTextField serviceNametextField;
    private JTextField serviceDescriptiontextField;
    private JTextField serviceCosttextField;
    private ServiceController serviceController;


    public NewServiceForm(JDialog parent) {
        super(parent, "New service", true);
        setContentPane(NewServicePanel);
        setSize(600, 400);
        setBounds(400, 150, 600, 400);
        serviceController = new ServiceController();
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveService();
            }
        });


    }

    private void saveService() {

        try {
            if (serviceController.exemptions(serviceNametextField.getText(), serviceDescriptiontextField.getText(), serviceCosttextField.getText())) {
                serviceController.create(serviceNametextField.getText(), serviceDescriptiontextField.getText(), serviceCosttextField.getText());
                JOptionPane.showMessageDialog(this, "Service created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                cancel();
            }
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void cancel() {
        setVisible(false);
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
        NewServicePanel = new JPanel();
        NewServicePanel.setLayout(new GridLayoutManager(4, 5, new Insets(10, 10, 10, 10), -1, -1));
        final Spacer spacer1 = new Spacer();
        NewServicePanel.add(spacer1, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        NewServicePanel.add(spacer2, new GridConstraints(0, 2, 3, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        saveButton = new JButton();
        saveButton.setBackground(new Color(-13793741));
        saveButton.setForeground(new Color(-2631721));
        saveButton.setIcon(new ImageIcon(getClass().getResource("/com/sun/deploy/resources/image/installapp24-d.png")));
        saveButton.setText("Save");
        NewServicePanel.add(saveButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setBackground(new Color(-5621447));
        cancelButton.setForeground(new Color(-2631721));
        cancelButton.setIcon(new ImageIcon(getClass().getResource("/com/sun/deploy/resources/image/delete24-d.png")));
        cancelButton.setText("Cancel");
        NewServicePanel.add(cancelButton, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        NewServicePanel.add(spacer3, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        NewServicePanel.add(spacer4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Service Name:");
        NewServicePanel.add(label1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Service description: ");
        NewServicePanel.add(label2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Service cost:");
        NewServicePanel.add(label3, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        serviceNametextField = new JTextField();
        NewServicePanel.add(serviceNametextField, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        serviceDescriptiontextField = new JTextField();
        NewServicePanel.add(serviceDescriptiontextField, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        serviceCosttextField = new JTextField();
        NewServicePanel.add(serviceCosttextField, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return NewServicePanel;
    }
}
