package com.andrusiak.userInterface;

import com.andrusiak.dataAccess.CustomerDAO;
import com.andrusiak.domain.Customer;
import com.andrusiak.userInterface.tables.CustomersTable;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerView extends JDialog {

    public CustomerView() {
        initComponents();
    }

    private void buttonAddActionPerformed(ActionEvent e) {
        AddCustomer addCustomer = new AddCustomer(this);
        addCustomer.setVisible(true);
    }

    private void buttonEditActionPerformed(ActionEvent e) {
        int selectedRow = customerTable.getSelectedRow();
        Customer mdl = customerTab.getCustomerFromRow(selectedRow);
        AddCustomer newCustomer = new AddCustomer(this, mdl);
        newCustomer.setVisible(true);
    }

    private void buttonDelActionPerformed(ActionEvent e) {
        int confirmDialog = JOptionPane.showConfirmDialog(this,
                "Ви дійсно бажаєте видалити магазин?", "Увага!",
                JOptionPane.YES_NO_OPTION);
        if (confirmDialog == JOptionPane.YES_OPTION) try {
            int selectedRow = customerTable.getSelectedRow();
            Customer shop = customerTab.getCustomerFromRow(selectedRow);
            new CustomerDAO().deleteCustomer(shop.getId());
            customerTab.removeRow(selectedRow);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private void buttonChangePerformed(ActionEvent e) {
        ItemView itemiew = new ItemView();
        itemiew.setVisible(true);
        this.setVisible(false);

    }

    private CustomersTable getCustomersTable() {
        try {
            CustomerDAO dao = new CustomerDAO();
            final List<Customer> customers = dao.findAll();
            return new CustomersTable(customers);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Не вдалося ініціалізувати таблицю моделей: " + e.getMessage());
        }
        return new CustomersTable(new ArrayList<>());
    }


    private void initComponents() {
        scrollPane1 = new JScrollPane();
        customerTable = new JTable(customerTab);
        toolBar1 = new JToolBar();
        buttonAdd = new JButton();
        buttonEdit = new JButton();
        buttonDel = new JButton();
        buttonChange = new JButton();

        setBackground(Color.white);
        setTitle(("Замовники"));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setForeground(Color.white);
        setFont(new Font("Segoe UI", Font.PLAIN, 12));
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        {
            scrollPane1.setBackground(Color.white);
            scrollPane1.setForeground(Color.black);
            customerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            customerTable.setBackground(new Color(255, 255, 255));
            customerTable.setForeground(Color.black);
            customerTable.setGridColor(Color.black);
            customerTable.setSelectionBackground(new Color(102, 102, 102));
            customerTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            customerTable.getColumnModel().getColumn(1).setPreferredWidth(60);
            customerTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
            scrollPane1.setViewportView(customerTable);
        }

        contentPane.add(scrollPane1);
        scrollPane1.setBounds(130, 0, 650, 440);

        {
            toolBar1.setFloatable(false);
            toolBar1.setBackground(Color.white);
            toolBar1.setBorderPainted(false);
            toolBar1.setOrientation(SwingConstants.VERTICAL);
            toolBar1.setForeground(new Color(0, 162, 152));
            toolBar1.setEnabled(false);

            buttonAdd.setBorderPainted(false);
            buttonAdd.setBackground(Color.white);
            buttonAdd.setMaximumSize(new Dimension(136, 76));
            buttonAdd.setText("Додати");
            buttonAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            buttonAdd.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            buttonAdd.setMargin(new Insets(2, 16, 2, 0));
            buttonAdd.addActionListener(e -> buttonAddActionPerformed(e));
            toolBar1.add(buttonAdd);

            buttonEdit.setBackground(Color.white);
            buttonEdit.setMaximumSize(new Dimension(136, 76));
            buttonEdit.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
            buttonEdit.setBorderPainted(false);
            buttonEdit.setText("Редагувати");
            buttonEdit.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            buttonEdit.setMargin(new Insets(2, 0, 2, 0));
            buttonEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            buttonEdit.addActionListener(e -> buttonEditActionPerformed(e));
            toolBar1.add(buttonEdit);

            buttonDel.setBackground(Color.white);
            buttonDel.setMaximumSize(new Dimension(136, 76));
            buttonDel.setBorder(LineBorder.createBlackLineBorder());
            buttonDel.setHorizontalTextPosition(SwingConstants.LEFT);
            buttonDel.setContentAreaFilled(false);
            buttonDel.setText("Видалити");
            buttonDel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            buttonDel.setBorderPainted(false);
            buttonDel.setMargin(new Insets(2, 10, 2, 6));
            buttonDel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            buttonDel.setForeground(Color.black);
            buttonDel.addActionListener(e -> buttonDelActionPerformed(e));
            toolBar1.add(buttonDel);


            buttonChange.setIcon(null);
            buttonChange.setBackground(Color.white);
            buttonChange.setMaximumSize(new Dimension(136, 76));
            buttonChange.setBorder(LineBorder.createBlackLineBorder());
            buttonChange.setHorizontalTextPosition(SwingConstants.LEFT);
            buttonChange.setContentAreaFilled(false);
            buttonChange.setText("Перейти на таблицю");
            buttonChange.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            buttonChange.setBorderPainted(false);
            buttonChange.setMargin(new Insets(2, 10, 2, 6));
            buttonChange.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            buttonChange.setForeground(Color.black);
            buttonChange.setFont(buttonChange.getFont().deriveFont(buttonChange.getFont().getStyle() | Font.BOLD));
            buttonChange.addActionListener(e -> buttonChangePerformed(e));
            toolBar1.add(buttonChange);
        }
        contentPane.add(toolBar1);
        toolBar1.setBounds(0, 0, 130, 445);

        {
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private JScrollPane scrollPane1;
    public static JTable customerTable;
    private JToolBar toolBar1;
    private JButton buttonAdd;
    private JButton buttonEdit;
    private JButton buttonDel;
    private JButton buttonChange;
    private CustomersTable customerTab = getCustomersTable();

}
