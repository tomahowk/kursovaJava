package com.andrusiak.userInterface.tables;

import com.andrusiak.domain.Item;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class ItemsTable extends AbstractTableModel {
    private final String[] columns = {"ID", "Товар", "Виробник",
            "Модель", "Ціна", "Характеристики", "На складі"};

    private List<Item> items;
    private List<Item> oldItems = new ArrayList<>();

    private static String oldSearch = "";

    public ItemsTable(List<Item> items) {
        this.items = items;
        this.oldItems.addAll(items);
        getSorted();
    }

    private void getSorted() {
        Comparator<Item> modelComparator = (o1, o2) -> o1.getId() - (o2.getId());
        this.items.sort(modelComparator);
        this.oldItems.sort(modelComparator);
    }

    public void search(String name) {
        this.items = oldSearch.length() > name.length() ? oldItems : this.items;
        if (name.equals("")) this.items = oldItems;
        else this.items = this.items.stream()
                .filter(s -> s.getGoodType()
                        .toLowerCase()
                        .startsWith(name.toLowerCase()))
                .collect(Collectors.toList());
        oldSearch = name;
        fireTableStructureChanged();
    }

    public void addItem(Item item) {
        items.add(item);
        oldItems.add(item);
        getSorted();
        fireTableDataChanged();
    }

    public void updateItem(Item item) {
        UnaryOperator<Item> modelUnaryOperator = s -> {
            if (s.getId() == item.getId()) return item;
            else return s;
        };
        items.replaceAll(modelUnaryOperator);
        oldItems.replaceAll(modelUnaryOperator);
        getSorted();
        fireTableDataChanged();
    }

    public void removeRow(int rowIndex) {
        oldItems.remove(items.get(rowIndex));
        items.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Item getItemFromRow(int rowIndex) {
        return items.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Item s = items.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return String.valueOf(s.getId());
            case 1:
                return s.getGoodType();
            case 2:
                return s.getManufacturer();
            case 3:
                return s.getItemName();
            case 4:
                return s.getPrice();
            case 5:
                return s.getSpec();
            case 6:
                return s.getStorage();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
}
