package org.example.view;

import org.example.Database.CartDb;
import org.example.models.Cart;
import org.example.models.CartProduct;
import org.example.util.FileUtil;
import org.example.util.LoadUtils;
import org.example.util.UserUtil;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CartPage {
    private CartDb cartDb = new CartDb();

    public void printCart(int id) {
        double total = 0;
//        for (CartProduct cartProduct : cartProducts) {
//            total += cartProduct.getCount() * cartProduct.getProduct().getPrice();
//            System.out.println(cartProduct.getProduct().getTitle() + " x " + cartProduct.getCount());
//        }
//        System.out.println("Total = "+total);
//        try {
//            Scanner scanner = new Scanner(FileUtil.getCartFile());
//            while (scanner.hasNext()) {
//                String value = scanner.next().trim();
//                if (!value.startsWith("user")) {
//                    String[] ordersArray = value.split(",");
//                    if(Integer.parseInt(ordersArray[0])==id){
//                        System.out.println(ordersArray[2] + "  " + ordersArray[3] + "  " + ordersArray[4]);
//                    }
//
//                }
//            }
//            scanner.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        ArrayList<Cart> Cart = cartDb.getCart(UserUtil.getLoggedUser().getId());

        if (Cart == null) {
            System.out.println("YOUR CART IS EMPTY");
        } else {
            for (Cart cartProd : Cart) {
                System.out.println(cartProd.getTitle() + " " + cartProd.getCount() + " " + cartProd.getPrice());
            }
        }


    }
}
