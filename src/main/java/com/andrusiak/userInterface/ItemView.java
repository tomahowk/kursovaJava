package com.andrusiak.userInterface;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import com.andrusiak.dataAccess.ItemDAO;
import com.andrusiak.domain.Item;
import com.andrusiak.userInterface.tables.ItemsTable;

public class ItemView extends JDialog {
    public ItemView() {
        initComponents();
    }

    private void buttonAddActionPerformed(ActionEvent e) {
        AddItem addItem = new AddItem(this);
        addItem.setVisible(true);
    }

    private void buttonEditActionPerformed(ActionEvent e) {
        int selectedRow = itemTable.getSelectedRow();
        Item item = itemsTab.getItemFromRow(selectedRow);
        AddItem newItem = new AddItem(this, item);
        newItem.setVisible(true);
    }

    private void buttonDelActionPerformed(ActionEvent e) {
        int confirmDialog = JOptionPane.showConfirmDialog(this,
                "Ви дійсно бажаєте видалити магазин?", "Увага!",
                JOptionPane.YES_NO_OPTION);
        if (confirmDialog == JOptionPane.YES_OPTION) try {
            int selectedRow = itemTable.getSelectedRow();
            Item shop = itemsTab.getItemFromRow(selectedRow);
            new ItemDAO().deleteItem(shop.getId());
            itemsTab.removeRow(selectedRow);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private void textFieldSearchKeyPressed(KeyEvent e) {
        if ( e.getKeyChar() != KeyEvent.VK_BACK_SPACE ) {
            String name = textFieldSearch.getText() + e.getKeyChar();
            itemsTab.search( name );
        } else {
            String name = textFieldSearch.getText();
            itemsTab.search( name );
        }
    }

    private void buttonChangePerformed(ActionEvent e) {
        CustomerView customerView = new CustomerView();
        customerView.setVisible(true);
        this.setVisible(false);
    }

    private ItemsTable getItemsTable() {
        try {
            ItemDAO dao = new ItemDAO();
            final List<Item> items = dao.findAll();
            return new ItemsTable(items);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Не вдалося ініціалізувати таблицю виробників: " + e.getMessage());
        }
        return new ItemsTable(new ArrayList<>());
    }


    private void initComponents() {
        scrollPane1 = new JScrollPane();
        itemTable = new JTable(itemsTab);
        toolBar1 = new JToolBar();
        buttonAdd = new JButton();
        buttonEdit = new JButton();
        buttonDel = new JButton();
        buttonChange = new JButton();

        setBackground(Color.white);
        setTitle("Продукція");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setForeground(Color.white);
        setFont(new Font("Segoe UI", Font.PLAIN, 12));
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        {
            scrollPane1.setBackground(Color.white);
            scrollPane1.setForeground(Color.black);

            itemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            itemTable.setBackground(new Color(254, 253, 255));
            itemTable.setForeground(Color.black);
            itemTable.setGridColor(Color.black);
            itemTable.setSelectionBackground(new Color(102, 102, 102));
            itemTable.getColumnModel().getColumn(0).setPreferredWidth(25);
            itemTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
            itemTable.getColumnModel().getColumn(5).setPreferredWidth(125);
            itemTable.getColumnModel().getColumn(6).setPreferredWidth(35);
            itemTable.getColumnModel().getColumn(4).setPreferredWidth(35);
            ;
            scrollPane1.setViewportView(itemTable);
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
            buttonChange.setText("Поміняти таблицю");
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
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private JScrollPane scrollPane1;
    public static JTable itemTable;
    private JToolBar toolBar1;
    private JButton buttonAdd;
    private JButton buttonEdit;
    private JButton buttonDel;
    private JLabel label1;
    private JTextField textFieldSearch;
    private JButton buttonChange;
    private ItemsTable itemsTab = getItemsTable();
}
