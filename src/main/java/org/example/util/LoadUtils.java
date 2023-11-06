package org.example.util;

import org.example.Database.CartDb;
import org.example.Database.ProductDb;
import org.example.models.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadUtils {
    private static ArrayList<Category> categories = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();

    private static ArrayList<Cart> cart = new ArrayList<>();

    public static void setCart(ArrayList<Cart> cart) {
        LoadUtils.cart = cart;
    }

    public static ArrayList<Cart> getCart() {
        return cart;
    }

    public static  ProductDb productDb = new ProductDb();

    public static CartDb cartDb = new CartDb();

    //    private static  ArrayList<Order> Orders = new ArrayList<>();
    public static void load(){
        ResultSet categoriesRS = productDb.getCategories();
        try {
            while (categoriesRS.next()) {
                categories.add(new Category(categoriesRS.getInt("id"), categoriesRS.getString("categoryName")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ResultSet productsRs = productDb.getProducts();

        try {
            while(productsRs.next()) {
                for(Category cat:categories) {
                    if (productsRs.getInt("category_id") == cat.getId()) {
                        products.add(new Product(productsRs.getInt("id"),productsRs.getString("name"),productsRs.getInt("price"),cat));
                    }
                }

            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

//        ResultSet cartRS = cartDb.getCart(UserUtil.getLoggedUser().getId());
//        try {
//            while (categoriesRS.next()) {
//                categories.add(new Category(categoriesRS.getInt("id"), categoriesRS.getString("categoryName")));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            Scanner scanner = new Scanner(FileUtil.getCartFile());
//            while (scanner.hasNext()) {
//                String value = scanner.next().trim();
//                if (!value.startsWith("user")) {
//                    String[] cartArray = value.split(",");
//                    cart.add(new Cart(Integer.parseInt(cartArray[0]),Integer.parseInt(cartArray[1]),cartArray[2],Integer.parseInt(cartArray[3]),Double.parseDouble(cartArray[4])));
//                }
//            }
//            scanner.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

    }

//    public static void logoutCartLoad(ArrayList<Cart> cart){
//        try{
//            FileWriter csv = new FileWriter(FileUtil.getCartFile(),false);
//            for(Cart cartProd: cart){
//                csv.append("\n");
//                csv.append(cartProd.getUserid()+","+cartProd.getId()+","+cartProd.getTitle()+","+cartProd.getCount()+","+cartProd.getPrice());
//            }
//            csv.flush();
//            csv.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//
//    }

    public static ArrayList<Category> getCategories() {
        return categories;
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }
}
