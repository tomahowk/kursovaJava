package com.andrusiak.userInterface;

import com.andrusiak.dataAccess.CustomerDAO;
import com.andrusiak.domain.Customer;
import com.andrusiak.userInterface.tables.CustomersTable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class AddCustomer extends JDialog {
    private CustomerDAO dao = new CustomerDAO();
    private Customer customer;

    public AddCustomer(Dialog customer) {
        super(customer);
        initComponents();
    }

    public AddCustomer(Dialog dialog, Customer customer) {
        super(dialog);
        this.customer = customer;
        initComponents();
        initializeTextFields();
    }

    private void initializeTextFields() {

        textFieldName.setText(customer.getName());
        textFieldBough.setText(String.valueOf(customer.getBought()));
        textFieldDigits.setText(String.valueOf(customer.getDigits()));
        textFieldDeliver.setText(customer.getDelivery());
        textFieldAdress.setText(customer.getAdress());
    }

    private void getCustomer(Customer customer) {
        customer.setBought(Integer.parseInt(textFieldName.getText()));
        customer.setName(textFieldBough.getText());
        customer.setDigits(Integer.parseInt(textFieldDigits.getText()));
        customer.setDelivery((textFieldDeliver.getText()));
        customer.setAdress(textFieldAdress.getText());
    }

    private void buttonSaveActionPerformed(ActionEvent e) {
        if (this.customer != null) {
            getCustomer(this.customer);
            try {
                dao.updateCustomer(customer);
                CustomersTable mdl = (CustomersTable) CustomerView.customerTable.getModel();
                mdl.updateCustomer(customer);
                this.dispose();
            } catch (SQLException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(this, "Помилка");
            }
        } else {
            Customer customer = new Customer();
            getCustomer(customer);
            try {
                int newId = dao.insertCustomer(customer);
                customer.setId(newId);
                dao.insertCustomer(customer);
                CustomersTable mdl = (CustomersTable) CustomerView.customerTable.getModel();
                mdl.addCustomer(customer);
                this.dispose();
            } catch (SQLException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(this, "Помилка");
            }
        }
    }


    private void initComponents() {
        textFieldName = new JTextField();
        textFieldBough = new JTextField();
        textFieldDigits = new JTextField();
        textFieldDeliver = new JTextField();
        textFieldAdress = new JTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        buttonSave = new JButton();

        setTitle("Додати/Редагувати");
        setBackground(new Color(251, 136, 35));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setAlwaysOnTop(true);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(textFieldName);
        textFieldName.setBounds(155, 70, 135, 24);
        contentPane.add(textFieldBough);
        textFieldBough.setBounds(155, 50, 135, 24);
        contentPane.add(textFieldDigits);
        textFieldDigits.setBounds(155, 95, 135, 24);
        contentPane.add(textFieldDeliver);
        textFieldDeliver.setBounds(155, 120, 135, 24);
        contentPane.add(textFieldAdress);
        textFieldAdress.setBounds(155, 145, 135, 24);

        label1.setText("Придбано днів тому");
        contentPane.add(label1);
        label1.setBounds(10, 50, 135, 25);

        label2.setText("Замовник");
        contentPane.add(label2);
        label2.setBounds(10, 70, 135, 25);

        label3.setText("Телефон");
        contentPane.add(label3);
        label3.setBounds(10, 95, 135, 25);

        label4.setText("Доставка");
        contentPane.add(label4);
        label4.setBounds(10, 120, 135, 25);

        label5.setText("Адреса");
        contentPane.add(label5);
        label5.setBounds(10, 145, 135, 25);

        buttonSave.setText("Зберегти");
        buttonSave.setBackground(Color.white);
        buttonSave.setForeground(Color.black);
        buttonSave.addActionListener(e -> buttonSaveActionPerformed(e));
        contentPane.add(buttonSave);
        buttonSave.setBounds(5, 285, 100, 35);

        contentPane.setPreferredSize(new Dimension(315, 365));
        pack();
        setLocationRelativeTo(getOwner());
    }

    private JTextField textFieldName;
    private JTextField textFieldBough;
    private JTextField textFieldDigits;
    private JTextField textFieldDeliver;
    private JTextField textFieldAdress;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JButton buttonSave;
}
