package com.andrusiak.dataAccess;

import com.andrusiak.domain.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.andrusiak.queries.ItemQueries.*;

public class ItemDAO {
    public int insertItem(Item item) throws SQLException {
        try (Connection connection = DataAccessUtil.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    getInsertQuery(), Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getGoodType());
            statement.setString(2, item.getManufacturer());
            statement.setString(3, item.getItemName());
            statement.setInt(4, item.getPrice());
            statement.setString(5, item.getSpec());
            statement.setInt(6, item.getStorage());

            statement.executeUpdate();
            return DataAccessUtil.getNewRowKey(statement);
        }
    }

    public void updateItem(Item item) throws SQLException {
        try (Connection connection = DataAccessUtil.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(getUpdateQuery());


            statement.setString(1, item.getGoodType());
            statement.setString(2, item.getManufacturer());
            statement.setString(3, item.getItemName());
            statement.setInt(4, item.getPrice());
            statement.setString(5, item.getSpec());
            statement.setInt(6, item.getStorage());
            statement.setInt(7, item.getId());

            statement.executeUpdate();
        }

    }

    public void deleteItem(int id) throws SQLException {
        try (Connection connection = DataAccessUtil.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(getDeleteQuery());

            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public List<Item> findAll() throws SQLException {
        try (Connection connection = DataAccessUtil.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(getSelectAllQuery());

            ResultSet rs = statement.executeQuery();
            List<Item> result = new ArrayList<>();
            while (rs.next()) result.add(getItemFromRow(rs));
            return result;
        }

    }

    private Item getItemFromRow(ResultSet rs) throws SQLException {
        Item dv = new Item();
        dv.setId(rs.getInt(1));
        dv.setGoodType(rs.getString(2));
        dv.setManufacturer(rs.getString(3));
        dv.setItemName(rs.getString(4));
        dv.setPrice(rs.getInt(5));
        dv.setSpec(rs.getString(6));
        dv.setStorage(rs.getInt(7));

        return dv;
    }
}
