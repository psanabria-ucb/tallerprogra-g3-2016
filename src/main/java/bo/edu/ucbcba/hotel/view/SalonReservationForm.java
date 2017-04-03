package bo.edu.ucbcba.hotel.view;

import bo.edu.ucbcba.hotel.controller.SalonController;
import bo.edu.ucbcba.hotel.controller.SalonReservationController;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;
import bo.edu.ucbcba.hotel.model.SalonReservation;
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
 * Created by Gabo on 26-Jun-16.
 */
public class SalonReservationForm extends JDialog {
    private JPanel SalonReservationForm;
    private JTextField SearchField;
    private JButton searchButton;
    private JButton editReservationButton;
    private JButton exitButton;
    private JButton newReservationButton;
    private JButton deleteReservationButton;
    private JTable SalonsTable;
    private SalonController salonController;
    private SalonReservationController salonReservationController;

    SalonReservationForm(JDialog parent) {
        super(parent, "Salon Reservation", true);
        pack();
        setContentPane(SalonReservationForm);
        setSize(650, 400);
        setBounds(400, 150, 650, 400);
        salonController = new SalonController();
        salonReservationController = new SalonReservationController();
        newReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newReservation();
            }
        });
        editReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editReservatio();
                populateTable();
            }
        });
        deleteReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteReservation();
                populateTable();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTable();
            }
        });
        populateTable1();
    }

    private void newReservation() {
        NewSalonReservationForm newSalonReservationForm = new NewSalonReservationForm(this);
        newSalonReservationForm.setVisible(true);
        populateTable1();
    }

    private void editReservatio() {

    }

    private void deleteReservation() {
        int n;
        if (SalonsTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Please select one room to delete", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            DefaultTableModel tm = (DefaultTableModel) SalonsTable.getModel();
            n = ((int) tm.getValueAt(SalonsTable.getSelectedRow(), 0));

            try {
                salonReservationController.DeleteRoom(Integer.toString(n));
            } catch (ValidationException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(this, "Room deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void exit() {
        dispose();
    }

    private void populateTable1() {

        List<SalonReservation> salonList = SalonReservationController.searchSalon(SearchField.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Salon id");
        model.addColumn("Client name");
        model.addColumn("Client ci");
        model.addColumn("Date");
        model.addColumn("Salon Name");
        model.addColumn("Amount of people");

        SalonsTable.setModel(model);


        for (SalonReservation s : salonList) {
            Object[] row = new Object[6];

            row[0] = s.getId();
            row[1] = s.getClientName();
            row[2] = s.getClientCi();
            row[3] = s.getDay() + "-" + s.getMonth() + "-" + s.getAnio();
            row[4] = s.getSalonName();
            row[5] = s.getCantPersonas();


            model.addRow(row);
        }
    }

    private void populateTable() {

        List<SalonReservation> salonList = SalonReservationController.searchSalon(SearchField.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Salon id");
        model.addColumn("Client name");
        model.addColumn("Client ci");
        model.addColumn("Date");
        model.addColumn("Salon Name");
        model.addColumn("Amount of people");

        SalonsTable.setModel(model);
        if (SearchField.getText().length() > 15) {
            JOptionPane.showMessageDialog(this, "Search argument is to big,please insert another one", "Error", JOptionPane.INFORMATION_MESSAGE);
            SearchField.setText("");
            populateTable1();
            return;
        }
        if (isEmpty(salonList)) {
            JOptionPane.showMessageDialog(this, "No matches with salons reservation data base", "Error", JOptionPane.INFORMATION_MESSAGE);
            SearchField.setText("");
            populateTable1();
        } else {
            for (SalonReservation s : salonList) {
                Object[] row = new Object[6];

                row[0] = s.getId();
                row[1] = s.getClientName();
                row[2] = s.getClientCi();
                row[3] = s.getDay() + "-" + s.getMonth() + "-" + s.getAnio();
                row[4] = s.getSalonName();
                row[5] = s.getCantPersonas();


                model.addRow(row);
            }
        }
    }

	private boolean isEmpty(List<SalonReservation> salonList) {
		return salonList.isEmpty();
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
        SalonReservationForm = new JPanel();
        SalonReservationForm.setLayout(new GridLayoutManager(3, 4, new Insets(10, 10, 10, 10), -1, -1));
        SearchField = new JTextField();
        SalonReservationForm.add(SearchField, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        SalonReservationForm.add(scrollPane1, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        SalonsTable = new JTable();
        scrollPane1.setViewportView(SalonsTable);
        editReservationButton = new JButton();
        editReservationButton.setText("Edit Reservation");
        SalonReservationForm.add(editReservationButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        newReservationButton = new JButton();
        newReservationButton.setText("New Reservation");
        SalonReservationForm.add(newReservationButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchButton = new JButton();
        searchButton.setText("Search");
        SalonReservationForm.add(searchButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteReservationButton = new JButton();
        deleteReservationButton.setText("Delete Reservation");
        SalonReservationForm.add(deleteReservationButton, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        exitButton = new JButton();
        exitButton.setText("Exit");
        SalonReservationForm.add(exitButton, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return SalonReservationForm;
    }
}
