package com.andrusiak.dataAccess;

import com.andrusiak.domain.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static com.andrusiak.queries.CustomerQueries.*;

public class CustomerDAO {
    public int insertCustomer(Customer customer) throws SQLException {
        try (Connection connection = DataAccessUtil.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    getInsertQuery(), Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, customer.getBought());
            statement.setString(2, customer.getName());
            statement.setInt(3, customer.getDigits());
            statement.setString(4, customer.getDelivery());
            statement.setString(5, customer.getAdress());

            statement.executeUpdate();
            return DataAccessUtil.getNewRowKey(statement);
        }
    }

    public void updateCustomer(Customer customer) throws SQLException {
        try (Connection connection = DataAccessUtil.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(getUpdateQuery());

            statement.setInt(1, customer.getBought());
            statement.setString(2, customer.getName());
            statement.setInt(3, customer.getDigits());
            statement.setString(4, customer.getDelivery());
            statement.setString(5, customer.getAdress());
            statement.setInt(6, customer.getId());

            statement.executeUpdate();
        }

    }

    public void deleteCustomer(int id) throws SQLException {
        try (Connection connection = DataAccessUtil.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(getDeleteQuery());

            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public List<Customer> findAll() throws SQLException {
        try (Connection connection = DataAccessUtil.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(getSelectAllQuery());

            ResultSet rs = statement.executeQuery();
            List<Customer> result = new ArrayList<>();
            while (rs.next()) result.add(getCustomerFromRow(rs));
            return result;
        }

    }

    private Customer getCustomerFromRow(ResultSet rs) throws SQLException {
        Customer mdl = new Customer();
        mdl.setId(rs.getInt(1));
        mdl.setBought(rs.getInt(2));
        mdl.setName(rs.getString(3));
        mdl.setDigits(rs.getInt(4));
        mdl.setDelivery(rs.getString(5));
        mdl.setAdress(rs.getString(6));

        return mdl;
    }
}
