package org.example.Database;

import org.example.models.Cart;

import java.sql.*;
import java.util.ArrayList;

public class CartDb {
    private static Connection con = DbConnection.getConection();

    public String INSERT_CARTITEM = "INSERT INTO cart (id,userId,name,count,price) VALUES (?,?,?,?,?)";

    private String SELECT_CARTITEM = "SELECT id, userId, name, count, price FROM cart WHERE userId=? and isCheckout=0";

    private String SELECT_CART_ID = "SELECT id FROM cart WHERE id=? and userId=? and isCheckout=0;";

    private final String UPDATE_CART = "UPDATE cart SET count = count+1 WHERE userId = ? and id = ? and isCheckout=0;";


    public void  insertCartItem(int id,int userId,String name,int count,double price){
        try {
            PreparedStatement ps = con.prepareStatement(INSERT_CARTITEM);
            ps.setInt(1, id);
            ps.setInt(2, userId);
            ps.setString(3,name);
            ps.setInt(4,count);
            ps.setInt(5,(int)price);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCount(int id,int userId){
        try {
            PreparedStatement ps = con.prepareStatement(UPDATE_CART);
            ps.setInt(1,userId);
            ps.setInt(2,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean findItem(int id,int userId){
        boolean exists = false;
        try {
            PreparedStatement ps = con.prepareStatement(SELECT_CART_ID);
            ps.setInt(1,id);
            ps.setInt(2,userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
    public ArrayList<Cart> getCart(int userId){
        ArrayList<Cart> cart = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(SELECT_CARTITEM);
            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Cart cartItem = new Cart(
                        rs.getInt("userId"),
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("count"),
                        rs.getInt("price")
                );
                cart.add(cartItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }
}
