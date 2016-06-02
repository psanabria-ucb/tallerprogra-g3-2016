package bo.edu.ucbcba.hotel.view;

import bo.edu.ucbcba.hotel.controller.ReservationController;

import javax.swing.*;

/**
 * Created by CésarIvan on 02/06/2016.
 */
public class ReservationForm extends JDialog{
    private JTextField SearchByClientText;
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
    /*    populateTable();
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // createReservation();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // editReservation();
                populateTable();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTable();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //deleteReservation();
                populateTable();
            }
        });
        populateTable();*/

    }

  /*  private void populateTable() {

        //if(dayComboBox.equals(""))
        List<Reservations> reservationList = reservationController.searchReservation(SearchByClientText.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Number");
        model.addColumn("Room");
        model.addColumn("Cant days");
        model.addColumn("Date");
        model.addColumn("Client");

        reservationTable.setModel(model);
        if (SearchByClientText.getText().length() > 15) {
            JOptionPane.showMessageDialog(this, "Search argument is to big,please insert another one", "Error", JOptionPane.INFORMATION_MESSAGE);
            SearchByClientText.setText("");
            populateTable();
            return;
        }
        if (reservationList.size() == 0) {
            JOptionPane.showMessageDialog(this, "No matches with rooms data base", "Error", JOptionPane.INFORMATION_MESSAGE);
            SearchByClientText.setText("");
            populateTable();
        } else {
            for (Reservations s : reservationList ) {
                Object[] row = new Object[4];

                row[0] = s.getRerserveNumber();
                row[1] = s.getRoom();
                row[2] = s.getCantDays();
                row[3] = s.getFechaRes();
                row[4] = s.getClient();


                model.addRow(row);

            }


        }

    }*/
}
