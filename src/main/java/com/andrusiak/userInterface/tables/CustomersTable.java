package com.andrusiak.userInterface.tables;

import com.andrusiak.domain.Customer;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class CustomersTable extends AbstractTableModel {

    private final String[] columns = {"ID", "Замовлено днів тому", "Замовник",
            "Номер телефону", "Доставка за", "Адреса"};

    private List<Customer> customers;
    private List<Customer> oldCustomers = new ArrayList<>();

    private static String oldSearch = "";

    public CustomersTable(List<Customer> customers) {
        this.customers = customers;
        this.oldCustomers.addAll(customers);
        getSorted();

    }

    private void getSorted() {
        Comparator<Customer> modelComparator = (o1, o2) -> o1.getId() - (o2.getId());
        this.customers.sort(modelComparator);
        this.oldCustomers.sort(modelComparator);
    }

    public void search(String name) {
        this.customers = oldSearch.length() > name.length() ? oldCustomers : this.customers;
        if (name.equals("")) this.customers = oldCustomers;
        else this.customers = this.customers.stream()
                .filter(s -> s.getName().toLowerCase()
                        .startsWith(name.toLowerCase()))
                .collect(Collectors.toList());
        oldSearch = name;
        fireTableStructureChanged();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        oldCustomers.add(customer);
        getSorted();
        fireTableDataChanged();
    }

    public void updateCustomer(Customer customer) {
        UnaryOperator<Customer> modelUnaryOperator = s -> {
            if (s.getId() == customer.getId()) return customer;
            else return s;
        };
        customers.replaceAll(modelUnaryOperator);
        oldCustomers.replaceAll(modelUnaryOperator);
        getSorted();
        fireTableDataChanged();
    }

    public void removeRow(int rowIndex) {
        oldCustomers.remove(customers.get(rowIndex));
        customers.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Customer getCustomerFromRow(int rowIndex) {
        return customers.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return customers.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Customer s = customers.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return String.valueOf(s.getId());
            case 1:
                return s.getBought();
            case 2:
                return s.getName();
            case 3:
                return s.getDigits();
            case 4:
                return s.getDelivery();
            case 5:
                return s.getAdress();
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
