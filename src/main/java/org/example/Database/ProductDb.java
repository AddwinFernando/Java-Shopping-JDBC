package org.example.Database;

import org.example.models.Role;
import org.example.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDb {
    private final Connection con = DbConnection.getConection();

    private final String SELECT_PRODUCTS = "SELECT id, name, price, category_id FROM products;";

    private final String SELECT_CATEGORY = "SELECT id ,categoryName FROM category;";

    public ResultSet getCategories(){
        ResultSet rs = null;
        try {
            PreparedStatement ps = con.prepareStatement(SELECT_CATEGORY);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public  ResultSet getProducts(){
        ResultSet rs = null;
        try {
            PreparedStatement ps = con.prepareStatement(SELECT_PRODUCTS);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
