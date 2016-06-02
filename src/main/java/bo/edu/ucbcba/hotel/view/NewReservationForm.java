package bo.edu.ucbcba.hotel.view;

import bo.edu.ucbcba.hotel.controller.ClientController;
import bo.edu.ucbcba.hotel.controller.ReservationController;
import bo.edu.ucbcba.hotel.controller.RoomController;
import bo.edu.ucbcba.hotel.model.Clients;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by CésarIvan on 02/06/2016.
 */
public class NewReservationForm extends JDialog{
    private JComboBox dayComboBox;
    private JComboBox monthComboBox;
    private JComboBox yearComboBox;
    private JTextField daysText;
    private JComboBox clientComboBox;
    private JComboBox roomComboBox;
    private JButton saveButton;
    private JButton cancelButton;
    private JPanel NewReservationPanel;
    private ReservationController reservationController;
    private final ClientController clientController;
    private  final RoomController roomController;

    NewReservationForm(JDialog parent){
        super(parent,"New reservation form",true);
        pack();
        setContentPane(NewReservationPanel);
        setSize(600, 400);
        setBounds(400, 150, 600, 400);
        reservationController = new ReservationController();
        clientController = new ClientController();
        roomController = new RoomController();

        populateComboBox();

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //  saveReservation();
            }
        });
    }

    private void populateComboBox(){
        List<Clients> clients = clientController.getAllClients();
        for(Clients c : clients){
            clientComboBox.addItem(c);
        }
    }

  /*  private void saveReservation() {

        try {
            Fecha f;
            f.setFecha(Integer.parseInt(dayComboBox),Integer.parseInt(monthComboBox),Integer.parseInt(yearComboBox));
               reservationController.create(daysText.getText(), NewLastNameText.getText(), (Integer.parseInt(NewCiText.getText())), Integer.parseInt(NewPhoneText.getText()));
                JOptionPane.showMessageDialog(this, "Client created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                cancel();


        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
        }
        cancel();
    }*/

    private void cancel() {
        setVisible(false);
        dispose();
    }
}
