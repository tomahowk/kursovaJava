package com.andrusiak.queries;


public class CustomerQueries {
    private static final String INSERT_QUERY = "INSERT INTO customers " +
            "(bought, name, digits, delivery, adress) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE customers " +
            "SET  bought = ?, name = ?, digits = ?, delivery = ?," +
            " adress = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM customers WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM customers";

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
