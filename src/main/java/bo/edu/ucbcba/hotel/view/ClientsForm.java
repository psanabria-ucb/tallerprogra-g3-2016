package bo.edu.ucbcba.hotel.view;

import bo.edu.ucbcba.hotel.controller.ClientController;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;
import bo.edu.ucbcba.hotel.model.Clients;
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
 * Created by Cesar on 15-May-16.
 */
public class ClientsForm extends JDialog {
    private JPanel ClientsPanel;
    private JButton buscarClienteButton;
    private JTextField SearchTextField;
    private JTable ClientsTable;
    private JButton agregarClienteButton;
    private JButton editButton;
    private JButton eliminarClienteButton;
    private JButton salirButton;
    private ClientController clientController;


    public ClientsForm(JFrame parent) {
        super(parent,"Clientes",true);
        pack();
        setContentPane(ClientsPanel);
        setSize(1000, 600);
        setBounds(400, 150, 600, 400);
        clientController = new ClientController();
        populateTable();
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        agregarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createClient();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editClient();
                populateTable();
            }
        });

        buscarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTable();
            }
        });

        eliminarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteClient();
                populateTable();
            }
        });
        populateTable();

    }

    private void editClient() {
        int ci,phone;
        String firstName, lastName;
         if (ClientsTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Please select one client to edit", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            DefaultTableModel tm = (DefaultTableModel) ClientsTable.getModel();
             ci = (int) tm.getValueAt(ClientsTable.getSelectedRow(), 0);
             firstName=(String) tm.getValueAt(ClientsTable.getSelectedRow(),1);
             lastName=(String) tm.getValueAt(ClientsTable.getSelectedRow(),2);
             phone=(int) tm.getValueAt(ClientsTable.getSelectedRow(),3);
            EditClientForm editClientForm = new EditClientForm(this, firstName, lastName, ci, phone);
            editClientForm.setVisible(true);
            populateTable();
        }
    }

    private void deleteClient() {
        int n;
        if (ClientsTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Please select one Client to delete", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            DefaultTableModel tm = (DefaultTableModel) ClientsTable.getModel();
            n = (int) tm.getValueAt(ClientsTable.getSelectedRow(), 0);

            try {
                clientController.delete(Integer.toString(n));
            } catch (ValidationException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(this, "Client deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void createClient(){
        NewClientForm newClient = new NewClientForm(this);
        newClient.setVisible(true);
        populateTable();
    }

    private void populateTable() {
        List<Clients> clientsList = clientController.searchClients(SearchTextField.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Ci");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Phone");

        ClientsTable.setModel(model);
        if (SearchTextField.getText().length() > 15) {
            JOptionPane.showMessageDialog(this, "Search argument is to big,please insert another one", "Error", JOptionPane.INFORMATION_MESSAGE);
            SearchTextField.setText("");
            populateTable();
            return;
        }
        if (clientsList.size() == 0) {
            JOptionPane.showMessageDialog(this, "No matches with rooms data base", "Error", JOptionPane.INFORMATION_MESSAGE);
            SearchTextField.setText("");
            populateTable();
        }else{
            for (Clients s : clientsList) {
                Object[] row = new Object[4];

                row[0] = s.getClientCi();
                row[1] = s.getFirstName();
                row[2] = s.getLastName();
                row[3] = s.getPhone();


                model.addRow(row);

        }


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
        ClientsPanel = new JPanel();
        ClientsPanel.setLayout(new GridLayoutManager(3, 5, new Insets(0, 0, 0, 0), -1, -1));
        ClientsPanel.setBorder(BorderFactory.createTitledBorder(""));
        final Spacer spacer1 = new Spacer();
        ClientsPanel.add(spacer1, new GridConstraints(1, 4, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        SearchTextField = new JTextField();
        ClientsPanel.add(SearchTextField, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(204, 24), null, 0, false));
        ClientsTable = new JTable();
        ClientsPanel.add(ClientsTable, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        agregarClienteButton = new JButton();
        agregarClienteButton.setText("Agregar Cliente");
        ClientsPanel.add(agregarClienteButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(204, 32), null, 0, false));
        editButton = new JButton();
        editButton.setText("Actualizar");
        ClientsPanel.add(editButton, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        eliminarClienteButton = new JButton();
        eliminarClienteButton.setText("Eliminar Cliente");
        ClientsPanel.add(eliminarClienteButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        salirButton = new JButton();
        salirButton.setText("Salir");
        ClientsPanel.add(salirButton, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buscarClienteButton = new JButton();
        buscarClienteButton.setText("Buscar Cliente");
        ClientsPanel.add(buscarClienteButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return ClientsPanel;
    }
}
