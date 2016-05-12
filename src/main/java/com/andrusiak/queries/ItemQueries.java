package com.andrusiak.queries;


public class ItemQueries {
    private static final String INSERT_QUERY = "INSERT INTO items " +
            "(goodType, manufacturer, itemName, price, spec, storage) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE items " +
            "SET goodType = ?, manufacturer = ?, itemName = ?, price = ?," +
            " spec = ?, storage = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM items WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM items";

    public static String getInsertQuery() {
        return INSERT_QUERY;
    }

    public static String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    public static String getDeleteQuery() {
        return DELETE_QUERY;
    }

    public static String getSelectAllQuery() {
        return SELECT_ALL_QUERY;
    }


}
