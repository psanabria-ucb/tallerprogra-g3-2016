package bo.edu.ucbcba.hotel.view;

import bo.edu.ucbcba.hotel.controller.ClientController;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by CésarIvan on 01/06/2016.
 */
public class EditClientForm extends JDialog{
    private JPanel EditClientsPanel;
    private JTextField firstNameText;
    private JTextField lastNameText;
    private JTextField phoneText;
    private JButton saveButton;
    private JButton cancelButton;
    private JLabel CiField;
    ClientController clientController;
    int ciSearch;

    public EditClientForm(JDialog parent, String firstName, String lastName, int Ci, int Phone){
        super(parent, "Edit Client", true);
        pack();
        setContentPane(EditClientsPanel);
        setSize(600, 400);
        setBounds(400, 150, 600, 400);
        clientController = new ClientController();
        firstNameText.setText(firstName);
        lastNameText.setText(lastName);
        String ci;
        ci=Integer.toString(Ci);
        CiField.setText(ci);
        String phone;
        phone= Integer.toString(Phone);
        phoneText.setText(phone);
        ciSearch=Ci;

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveClient();
            }
        });

    }

    private void saveClient() {
        try {
            clientController.update(firstNameText.getText(),lastNameText.getText(),Integer.parseInt(CiField.getText()),Integer.parseInt(phoneText.getText()));
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
        }

        JOptionPane.showMessageDialog(this, "Client modificated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
