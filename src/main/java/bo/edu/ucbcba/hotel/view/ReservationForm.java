package bo.edu.ucbcba.hotel.view;

import bo.edu.ucbcba.hotel.controller.ReservationController;
import bo.edu.ucbcba.hotel.model.Reservations;
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
 * Created by CésarIvan on 02/06/2016.
 */
public class ReservationForm extends JDialog {
    private JTextField searchText;
    private JButton searchButton;
    private JTable reservationTable;
    private JButton deleteButton;
    private JButton editButton;
    private JButton exitButton;
    private JButton addButton;
    private JComboBox dayComboBox;
    private JComboBox monthComboBox;
    private JComboBox yearComboBox;
    private JPanel ReservationPanel;

    private ReservationController reservationController;

    public ReservationForm(JFrame parent) {
        super(parent, "Reservation", true);
        pack();
        setContentPane(ReservationPanel);
        setSize(1000, 600);
        setBounds(400, 150, 600, 400);
        reservationController = new ReservationController();
        populateComboBox();
        populateTable1();
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createReservation();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // editReservation();
                //  populateTable();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // populateTable();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //deleteReservation();
                //  populateTable();
            }
        });
        //   populateTable();

    }

    private void createReservation() {
        NewReservationForm newReservationForm = new NewReservationForm(this);
        newReservationForm.setVisible(true);
        populateTable();
    }

    private void populateTable1() {

        List<Reservations> reservationsList = ReservationController.searchReservation(searchText.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Room Number");
        model.addColumn("Type");
        model.addColumn("Room View");
        model.addColumn("Availability");
        reservationTable.setModel(model);

        for (Reservations s : reservationsList) {
            Object[] row = new Object[5];

            row[0] = s.getRerserveNumber();
            row[1] = s.getRoom();
            row[2] = s.getCantDays();
            row[3] = s.getFechaRes();
            row[4] = s.getClient();

            model.addRow(row);
        }
    }

    private void populateTable() {

        List<Reservations> reservationList = reservationController.searchReservation(searchText.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Number");
        model.addColumn("Room");
        model.addColumn("Cant days");
        model.addColumn("Date");
        model.addColumn("Client");

        reservationTable.setModel(model);
       /* if (searchText.getText().length() > 15) {
            JOptionPane.showMessageDialog(this, "Search argument is to big,please insert another one", "Error", JOptionPane.INFORMATION_MESSAGE);
            searchText.setText("");
            populateTable();
            return;
        }
        if (reservationList.size() == 0) {
            JOptionPane.showMessageDialog(this, "No matches with Reservations data base", "Error", JOptionPane.INFORMATION_MESSAGE);
            searchText.setText("");
            populateTable();
        } else {*/
        for (Reservations s : reservationList) {
            Object[] row = new Object[5];

            row[0] = s.getRerserveNumber();
            row[1] = s.getRoom();
            row[2] = s.getCantDays();
            row[3] = s.getFechaRes();
            row[4] = s.getClient();


            model.addRow(row);

        }
        // }

    }
    private void populateComboBox(){
        for (int day = 1; day < 32; day = day + 1) {
            dayComboBox.addItem(day);
        }
        for (int month = 1; month < 13; month = month + 1) {
            monthComboBox.addItem(month);
        }
        for (int year = 2016; year < 2018; year = year + 1) {
            yearComboBox.addItem(year);
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
        ReservationPanel = new JPanel();
        ReservationPanel.setLayout(new GridLayoutManager(8, 7, new Insets(10, 10, 10, 10), -1, -1));
        searchText = new JTextField();
        ReservationPanel.add(searchText, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 24), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        ReservationPanel.add(scrollPane1, new GridConstraints(4, 1, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        reservationTable = new JTable();
        scrollPane1.setViewportView(reservationTable);
        deleteButton = new JButton();
        deleteButton.setText("Delete");
        ReservationPanel.add(deleteButton, new GridConstraints(6, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(65, 32), null, 0, false));
        addButton = new JButton();
        addButton.setText("Add");
        ReservationPanel.add(addButton, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 32), null, 0, false));
        final Spacer spacer1 = new Spacer();
        ReservationPanel.add(spacer1, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        ReservationPanel.add(spacer2, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(200, 14), null, 0, false));
        final Spacer spacer3 = new Spacer();
        ReservationPanel.add(spacer3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        ReservationPanel.add(spacer4, new GridConstraints(4, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Client");
        ReservationPanel.add(label1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 16), null, 0, false));
        dayComboBox = new JComboBox();
        ReservationPanel.add(dayComboBox, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        monthComboBox = new JComboBox();
        ReservationPanel.add(monthComboBox, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Day");
        ReservationPanel.add(label2, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Month");
        ReservationPanel.add(label3, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchButton = new JButton();
        searchButton.setText("Search");
        ReservationPanel.add(searchButton, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(65, 32), null, 0, false));
        yearComboBox = new JComboBox();
        ReservationPanel.add(yearComboBox, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, 26), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Year");
        ReservationPanel.add(label4, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        ReservationPanel.add(spacer5, new GridConstraints(3, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        exitButton = new JButton();
        exitButton.setText("Exit");
        ReservationPanel.add(exitButton, new GridConstraints(6, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editButton = new JButton();
        editButton.setText("Edit");
        ReservationPanel.add(editButton, new GridConstraints(6, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        ReservationPanel.add(spacer6, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        ReservationPanel.add(spacer7, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return ReservationPanel;
    }
}
