package bo.edu.ucbcba.hotel.view;


import bo.edu.ucbcba.hotel.controller.ServiceController;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;
import bo.edu.ucbcba.hotel.model.Services;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


/**
 * Created by Alejandra on 17/05/2016.
 */
public class ServiceForm extends JDialog {
    private JPanel ServiceForm;
    private JPanel panel;
    private JTextField searchField;
    private JButton searchButton;
    private JTable serviceTable;
    private JButton salirButton1;
    private JButton eliminarServicioButton;
    private JButton AGregarServicioButton;
    private JButton editServiceButton;
    private ServiceController serviceController;


    public ServiceForm(JFrame parent) {
        super(parent, "Services", true);
        pack();
        setContentPane(panel);
        setSize(600, 400);
        setBounds(400, 150, 600, 400);
        serviceController = new ServiceController();
        populateTable1();

        salirButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        AGregarServicioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createService();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTable();
            }
        });

        eliminarServicioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteService();
                populateTable();
            }
        });

        editServiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editService();
                populateTable();

            }
        });
    }

    private void editService() {
        int number, cost;
        String name, description;
        DefaultTableModel tm = (DefaultTableModel) serviceTable.getModel();

        if (serviceTable.getSelectedRow() != -1) {
            number = (int) tm.getValueAt(serviceTable.getSelectedRow(), 0);
            cost = (int) tm.getValueAt(serviceTable.getSelectedRow(), 3);
            name = (String) tm.getValueAt(serviceTable.getSelectedRow(), 1);
            description = (String) tm.getValueAt(serviceTable.getSelectedRow(), 2);

            try {
                EditServiceForm ser = new EditServiceForm(this, cost, name, description, number);
                ser.setVisible(true);
            } catch (ValidationException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
            }
        } else
            JOptionPane.showMessageDialog(this, "Please select one service to edit it", "Error", JOptionPane.INFORMATION_MESSAGE);

    }

    private void deleteService() {

        int n;

        DefaultTableModel tm = (DefaultTableModel) serviceTable.getModel();
        if (serviceTable.getSelectedRow() != -1) {
            n = (int) tm.getValueAt(serviceTable.getSelectedRow(), 0);
            try {
                serviceController.delete(Integer.toString(n));
            } catch (ValidationException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(this, "Service deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else
            JOptionPane.showMessageDialog(this, "Please select one service to delete it", "Error", JOptionPane.INFORMATION_MESSAGE);

    }

    private void createService() {
        NewServiceForm newservice = new NewServiceForm(this);
        newservice.setVisible(true);
        populateTable();
    }

    private void populateTable1() {
        List<Services> servicesList = serviceController.searchService(searchField.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Description");
        model.addColumn("Cost");

        serviceTable.setModel(model);
        for (Services t : servicesList) {
            Object[] row = new Object[5];

            row[0] = t.getRoomNumber();
            row[1] = t.getName();
            row[2] = t.getDescription();
            row[3] = t.getCost();

            model.addRow(row);
        }
    }

    private void populateTable() {


        List<Services> servicesList = serviceController.searchService(searchField.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Description");
        model.addColumn("Cost");

        serviceTable.setModel(model);

        if (searchField.getText().length() > 20) {
            JOptionPane.showMessageDialog(this, "Search argument is too big, please insert another one", "Error", JOptionPane.INFORMATION_MESSAGE);
            searchField.setText("");
            populateTable();
            return;
        }
        if (servicesList.size() == 0) {
            JOptionPane.showMessageDialog(this, "No matches with services data base ", "Error", JOptionPane.INFORMATION_MESSAGE);
            searchField.setText("");
            populateTable1();
        }
        for (Services s : servicesList) {
            Object[] row = new Object[5];

            row[0] = s.getRoomNumber();
            row[1] = s.getName();
            row[2] = s.getDescription();
            row[3] = s.getCost();

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
        ServiceForm = new JPanel();
        ServiceForm.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(4, 10, new Insets(10, 10, 10, 10), -1, -1));
        ServiceForm.add(panel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        searchField = new JTextField();
        panel.add(searchField, new GridConstraints(0, 2, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        searchButton = new JButton();
        searchButton.setText("Search");
        panel.add(searchButton, new GridConstraints(0, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AGregarServicioButton = new JButton();
        AGregarServicioButton.setText("New service");
        panel.add(AGregarServicioButton, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel.add(spacer1, new GridConstraints(1, 9, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel.add(spacer3, new GridConstraints(1, 8, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel.add(spacer4, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        eliminarServicioButton = new JButton();
        eliminarServicioButton.setText("Delete service");
        panel.add(eliminarServicioButton, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editServiceButton = new JButton();
        editServiceButton.setText("Edit service");
        panel.add(editServiceButton, new GridConstraints(3, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel.add(scrollPane1, new GridConstraints(1, 2, 2, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        serviceTable = new JTable();
        scrollPane1.setViewportView(serviceTable);
        salirButton1 = new JButton();
        salirButton1.setText("Close");
        panel.add(salirButton1, new GridConstraints(3, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        ServiceForm.add(spacer5, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return ServiceForm;
    }
}
