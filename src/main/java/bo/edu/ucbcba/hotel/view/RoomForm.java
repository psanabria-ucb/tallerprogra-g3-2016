package bo.edu.ucbcba.hotel.view;

import bo.edu.ucbcba.hotel.controller.RoomController;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;
import bo.edu.ucbcba.hotel.model.Rooms;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Gabo on 15-May-16.
 */
public class RoomForm extends JDialog {
    private JPanel roomForm;
    private JTextField SearchField;
    private JTable RoomsTable;
    private JButton SearchButton;
    private JButton agregarHabitacionButton;
    private JButton verInventarioButton;
    private JButton eliminarHabitacionButton;
    private JButton salirButton;
    private JButton editButton;
    private RoomController roomController;

    RoomForm(JFrame parent) {
        super(parent, "Rooms", true);
        pack();
        setContentPane(roomForm);
        setSize(650, 400);
        setBounds(400, 150, 650, 400);
        setResizable(false);
        roomController = new RoomController();
        populateTable1();
        eliminarHabitacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRoom();
                populateTable();
            }
        });
        agregarHabitacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchregistrer();
            }
        });
        verInventarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewInventory();
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTable();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editRoom();
            }
        });


    }

    private void editRoom() {
        int n;
        if (RoomsTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Please select one room to edit", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            DefaultTableModel tm = (DefaultTableModel) RoomsTable.getModel();
            n = ((int) tm.getValueAt(RoomsTable.getSelectedRow(), 0));
            EditRoomForm editRoomForm = new EditRoomForm(this, n);
            editRoomForm.setVisible(true);
            populateTable();
        }
    }

    private void deleteRoom() {
        int n;
        if (RoomsTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Please select one room to delete", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            DefaultTableModel tm = (DefaultTableModel) RoomsTable.getModel();
            n = ((int) tm.getValueAt(RoomsTable.getSelectedRow(), 0));

            try {
                roomController.DeleteRoom(Integer.toString(n));
            } catch (ValidationException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(this, "Room deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void launchregistrer() {
        NewRoomForm newroom = new NewRoomForm(this);
        newroom.setVisible(true);
        populateTable();

    }

    private void viewInventory() {
        int n;
        if (RoomsTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Please select one room to view inventory", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            DefaultTableModel tm = (DefaultTableModel) RoomsTable.getModel();
            n = ((int) tm.getValueAt(RoomsTable.getSelectedRow(), 0));
            InventoryForm inventoryForm = new InventoryForm(this, n);
            inventoryForm.setVisible(true);
        }
    }

    private void populateTable1() {

        List<Rooms> roomsList = RoomController.searchRoom(SearchField.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Room Number");
        model.addColumn("Type");
        model.addColumn("Room View");
        model.addColumn("Availability");
        RoomsTable.setModel(model);

        for (Rooms s : roomsList) {
            Object[] row = new Object[4];

            row[0] = s.getRoomNumber();
            row[1] = s.getType();
            row[2] = s.getRoomView();
            if (!s.isAvailability())
                row[3] = "Available";
            else
                row[3] = "Not available";


            model.addRow(row);
        }
    }

    private void populateTable() {

        List<Rooms> roomsList = RoomController.searchRoom(SearchField.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Room Number");
        model.addColumn("Type");
        model.addColumn("Room View");
        model.addColumn("Availability");

        RoomsTable.setModel(model);
        if (SearchField.getText().length() > 15) {
            JOptionPane.showMessageDialog(this, "Search argument is to big,please insert another one", "Error", JOptionPane.INFORMATION_MESSAGE);
            SearchField.setText("");
            populateTable1();
            return;
        }
        if (roomsList.size() == 0) {
            JOptionPane.showMessageDialog(this, "No matches with rooms data base", "Error", JOptionPane.INFORMATION_MESSAGE);
            SearchField.setText("");
            populateTable1();
        } else {
            for (Rooms s : roomsList) {
                Object[] row = new Object[4];

                row[0] = s.getRoomNumber();
                row[1] = s.getType();
                row[2] = s.getRoomView();
                if (!s.isAvailability())
                    row[3] = "Available";
                else
                    row[3] = "Not available";


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
        roomForm = new JPanel();
        roomForm.setLayout(new GridLayoutManager(3, 5, new Insets(10, 10, 10, 10), -1, -1));
        SearchField = new JTextField();
        roomForm.add(SearchField, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        SearchButton = new JButton();
        SearchButton.setText("Search");
        roomForm.add(SearchButton, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        agregarHabitacionButton = new JButton();
        agregarHabitacionButton.setBackground(new Color(-15128255));
        agregarHabitacionButton.setForeground(new Color(-16777216));
        agregarHabitacionButton.setHideActionText(false);
        agregarHabitacionButton.setText("Add New Room");
        roomForm.add(agregarHabitacionButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        verInventarioButton = new JButton();
        verInventarioButton.setBackground(new Color(-15122154));
        verInventarioButton.setForeground(new Color(-16777216));
        verInventarioButton.setText("View Inventory");
        roomForm.add(verInventarioButton, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        eliminarHabitacionButton = new JButton();
        eliminarHabitacionButton.setBackground(new Color(-12514548));
        eliminarHabitacionButton.setForeground(new Color(-16777216));
        eliminarHabitacionButton.setText("Delete Room");
        roomForm.add(eliminarHabitacionButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        salirButton = new JButton();
        salirButton.setBackground(new Color(-12514289));
        salirButton.setForeground(new Color(-16777216));
        salirButton.setText("Exit");
        roomForm.add(salirButton, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editButton = new JButton();
        editButton.setBackground(new Color(-12500957));
        editButton.setForeground(new Color(-16777216));
        editButton.setText("Edit");
        roomForm.add(editButton, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setEnabled(true);
        scrollPane1.setFont(new Font(scrollPane1.getFont().getName(), scrollPane1.getFont().getStyle(), scrollPane1.getFont().getSize()));
        roomForm.add(scrollPane1, new GridConstraints(1, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        RoomsTable = new JTable();
        scrollPane1.setViewportView(RoomsTable);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return roomForm;
    }
}
