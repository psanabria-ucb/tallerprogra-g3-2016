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


  }
