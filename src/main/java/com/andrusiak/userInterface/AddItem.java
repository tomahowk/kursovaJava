package com.andrusiak.userInterface;

import com.andrusiak.dataAccess.ItemDAO;
import com.andrusiak.domain.Item;
import com.andrusiak.userInterface.tables.ItemsTable;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

public class AddItem extends JDialog {
    private ItemDAO dao = new ItemDAO();
    private Item item;

    public AddItem(Dialog dialog) {
        initComponents();
    }

    public AddItem(Dialog dialog, Item item) {
        super(dialog);
        this.item = item;
        initComponents();
        initializeTextFields();
    }

    private void initializeTextFields() {
        textFieldGood.setText(item.getGoodType());
        textFieldManufacturer.setText(item.getManufacturer());
        textFieldName.setText(item.getItemName());
        textFieldPrice.setText(String.valueOf(item.getPrice()));
        textFieldSpec.setText(String.valueOf(item.getSpec()));
        textFieldStorage.setText(String.valueOf(item.getStorage()));
    }

    private void getItem(Item item) {
        item.setGoodType(textFieldGood.getText());
        item.setManufacturer(textFieldManufacturer.getText());
        item.setItemName(textFieldName.getText());
        item.setPrice(Integer.parseInt(textFieldPrice.getText()));
        item.setSpec(textFieldSpec.getText());
        item.setStorage(Integer.parseInt(textFieldStorage.getText()));
    }

    private void buttonSaveActionPerformed(ActionEvent e) {
        if (this.item != null) {
            getItem(this.item);
            try {
                dao.updateItem(item);
                ItemsTable mdl = (ItemsTable) ItemView.itemTable.getModel();
                mdl.updateItem(item);
                this.dispose();
            } catch (SQLException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(this, "Перевірте типи даних ");
            }
        } else {
            Item itm = new Item();
            getItem(itm);
            try {
                int newId = dao.insertItem(itm);
                itm.setId(newId);
                dao.insertItem(itm);
                ItemsTable mdl = (ItemsTable) ItemView.itemTable.getModel();
                mdl.addItem(itm);
                this.dispose();
            } catch (SQLException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(this, "Перевірте типи даних ");
            }
        }
    }


    private void initComponents() {
        textFieldGood = new JTextField();
        textFieldManufacturer = new JTextField();
        textFieldName = new JTextField();
        textFieldPrice = new JTextField();
        textFieldSpec = new JTextField();
        textFieldStorage = new JTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        buttonSave = new JButton();

        setTitle("Додати/Редагувати");
        setBackground(new Color(251, 136, 35));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setAlwaysOnTop(true);
        setType(Window.Type.POPUP);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(textFieldGood);
        textFieldGood.setBounds(155, 50, 135, textFieldGood.getPreferredSize().height);
        contentPane.add(textFieldManufacturer);
        textFieldManufacturer.setBounds(155, 70, 135, 24);
        contentPane.add(textFieldName);
        textFieldName.setBounds(155, 95, 135, 24);
        contentPane.add(textFieldPrice);
        textFieldPrice.setBounds(155, 120, 135, 24);
        contentPane.add(textFieldSpec);
        textFieldSpec.setBounds(155, 145, 135, 24);
        contentPane.add(textFieldStorage);
        textFieldStorage.setBounds(155, 170, 135, 25);

        label1.setText("Товар");
        contentPane.add(label1);
        label1.setBounds(10, 50, 135, 25);

        label2.setText("Виробник");
        contentPane.add(label2);
        label2.setBounds(10, 70, 135, 25);

        label3.setText("Модель");
        contentPane.add(label3);
        label3.setBounds(10, 95, 135, 25);

        label4.setText("Ціна");
        contentPane.add(label4);
        label4.setBounds(10, 120, 135, 25);

        label5.setText("Характеристика");
        contentPane.add(label5);
        label5.setBounds(10, 145, 135, 25);

        label6.setText("На складі");
        contentPane.add(label6);
        label6.setBounds(10, 170, 135, 25);

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

    private JTextField textFieldGood;
    private JTextField textFieldManufacturer;
    private JTextField textFieldName;
    private JTextField textFieldPrice;
    private JTextField textFieldSpec;
    private JTextField textFieldStorage;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JButton buttonSave;
}
