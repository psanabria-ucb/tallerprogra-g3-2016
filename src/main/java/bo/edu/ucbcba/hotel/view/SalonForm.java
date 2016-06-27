package bo.edu.ucbcba.hotel.view;

import bo.edu.ucbcba.hotel.controller.SalonController;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;
import bo.edu.ucbcba.hotel.model.Salons;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Gabo on 31-May-16.
 */
public class SalonForm extends JDialog {
    private JPanel salonPanel;
    private JTextField searchField;
    private JButton searchButton;
    private JTable SalonTable;
    private JButton newSalonButton;
    private JButton exitButton;
    private JButton deleteSalonButton;
    private JButton editSalonButton;
    private SalonController salonController;

    SalonForm(JFrame parent) {
        super(parent, "Salon Form", true);

        pack();
        setContentPane(salonPanel);
        setBounds(400, 150, 650, 400);
        salonController = new SalonController();
        newSalonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newSalon();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
        deleteSalonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSalon();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTable();
            }
        });
        editSalonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editSalon();
            }
        });
        populateTable1();
    }

    private void newSalon() {
        NewSalonForm newSalonForm = new NewSalonForm(this);
        newSalonForm.setVisible(true);
        populateTable();

    }

    private void exit() {
        dispose();
    }

    private void deleteSalon() {
        int n;
        if (SalonTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Please select one salon to delete", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            DefaultTableModel tm = (DefaultTableModel) SalonTable.getModel();
            n = ((int) tm.getValueAt(SalonTable.getSelectedRow(), 0));

            try {
                salonController.DeleteSalon(Integer.toString(n));
            } catch (ValidationException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(this, "Salon deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        populateTable();
    }

    private void populateTable1() {

        List<Salons> roomsList = SalonController.searchSalon(searchField.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Salon Id");
        model.addColumn("Name");
        model.addColumn("Capacity");
        model.addColumn("Availability");
        SalonTable.setModel(model);

        for (Salons s : roomsList) {
            Object[] row = new Object[4];

            row[0] = s.getId();
            row[1] = s.getName();
            row[2] = s.getCapacity();
            if (s.isAvailability())
                row[3] = "Available";
            else
                row[3] = "Not available";


            model.addRow(row);
        }
    }

    private void editSalon() {
        int n;
        if (SalonTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Please select one salon to edit", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            DefaultTableModel tm = (DefaultTableModel) SalonTable.getModel();
            n = ((int) tm.getValueAt(SalonTable.getSelectedRow(), 0));
            EditSalonForm editSalonForm = new EditSalonForm(this, n);
            editSalonForm.setVisible(true);
            populateTable();
        }
    }

    private void populateTable() {

        List<Salons> roomsList = SalonController.searchSalon(searchField.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Salon Id");
        model.addColumn("Name");
        model.addColumn("Capacity");
        model.addColumn("Availability");
        SalonTable.setModel(model);

        if (searchField.getText().matches("[0-9]+")) {
            if (searchField.getText().length() > 999) {
                JOptionPane.showMessageDialog(this, "Search argument number is to big,please insert a new one", "Error", JOptionPane.INFORMATION_MESSAGE);
                searchField.setText("");
                populateTable1();
                return;
            }

        }

        if (searchField.getText().length() > 20) {
            JOptionPane.showMessageDialog(this, "Search argument is to big,please insert a new one", "Error", JOptionPane.INFORMATION_MESSAGE);
            searchField.setText("");
            populateTable1();
            return;
        }
        if (roomsList.size() == 0) {
            JOptionPane.showMessageDialog(this, "No matches with salons data base", "Error", JOptionPane.INFORMATION_MESSAGE);
            searchField.setText("");
            populateTable1();
        }
        for (Salons s : roomsList) {
            Object[] row = new Object[4];

            row[0] = s.getId();
            row[1] = s.getName();
            row[2] = s.getCapacity();
            if (s.isAvailability())
                row[3] = "Available";
            else
                row[3] = "Not available";


            model.addRow(row);
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
        salonPanel = new JPanel();
        salonPanel.setLayout(new GridLayoutManager(3, 4, new Insets(20, 20, 20, 20), -1, -1));
        searchField = new JTextField();
        salonPanel.add(searchField, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        searchButton = new JButton();
        searchButton.setText("Search");
        salonPanel.add(searchButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        salonPanel.add(scrollPane1, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        SalonTable = new JTable();
        scrollPane1.setViewportView(SalonTable);
        newSalonButton = new JButton();
        newSalonButton.setText("New Salon");
        salonPanel.add(newSalonButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        exitButton = new JButton();
        exitButton.setText("Exit");
        salonPanel.add(exitButton, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteSalonButton = new JButton();
        deleteSalonButton.setText("Delete Salon");
        salonPanel.add(deleteSalonButton, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editSalonButton = new JButton();
        editSalonButton.setText("Edit Salon");
        salonPanel.add(editSalonButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return salonPanel;
    }
}
