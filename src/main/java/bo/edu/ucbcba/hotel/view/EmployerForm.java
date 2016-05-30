package bo.edu.ucbcba.hotel.view;

import bo.edu.ucbcba.hotel.controller.EmployerController;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;
import bo.edu.ucbcba.hotel.model.Employers;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by Alejandra on 18/05/2016.
 */
public class EmployerForm extends JDialog {
    private JPanel EmployerPanel;
    private JButton salirButton;
    private JTextField textField1;
    private JButton searchButton;
    private JTable employertable;
    private JButton newEmployerButton;
    private JButton deleteEmployerButton;
    private EmployerController e;

    public EmployerForm(JFrame parent) {
        super(parent, "Employers", true);
        pack();
        setContentPane(EmployerPanel);
        setSize(600, 400);
        setBounds(400, 150, 600, 400);
        e = new EmployerController();
        populateTable();
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
        newEmployerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createEmployer();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTable();
            }
        });

        deleteEmployerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEmployer();
                populateTable();
            }
        });
    }

    private void populateTable() {
        List<Employers> servicesList = e.searchEmployers(textField1.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Description");
        model.addColumn("Cost");

        employertable.setModel(model);


        for (Employers s : servicesList) {
            Object[] row = new Object[5];

            row[0] = s.getCi();
            row[1] = s.getName();
            row[2] = s.getLastName();
            row[3] = s.getPhone();

            model.addRow(row);
        }
    }

    private void createEmployer() {
        NewEmployerForm em = new NewEmployerForm(this);
        em.setVisible(true);
        populateTable();
    }

    private void deleteEmployer() {
        int n;
        DefaultTableModel tm = (DefaultTableModel) employertable.getModel();
        n = (int) tm.getValueAt(employertable.getSelectedRow(), 0);

        try {
            e.delete(Integer.toString(n));
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
        }
        JOptionPane.showMessageDialog(this, "Service deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

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
        EmployerPanel = new JPanel();
        EmployerPanel.setLayout(new GridLayoutManager(5, 8, new Insets(0, 0, 0, 0), -1, -1));
        searchButton = new JButton();
        searchButton.setText("Search");
        EmployerPanel.add(searchButton, new GridConstraints(1, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        EmployerPanel.add(textField1, new GridConstraints(1, 1, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        employertable = new JTable();
        EmployerPanel.add(employertable, new GridConstraints(2, 1, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        newEmployerButton = new JButton();
        newEmployerButton.setText("New employer");
        EmployerPanel.add(newEmployerButton, new GridConstraints(3, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteEmployerButton = new JButton();
        deleteEmployerButton.setText("Delete employer");
        EmployerPanel.add(deleteEmployerButton, new GridConstraints(3, 4, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        EmployerPanel.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        EmployerPanel.add(spacer2, new GridConstraints(1, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        salirButton = new JButton();
        salirButton.setText("Close");
        EmployerPanel.add(salirButton, new GridConstraints(3, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        EmployerPanel.add(spacer3, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        EmployerPanel.add(spacer4, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return EmployerPanel;
    }
}
