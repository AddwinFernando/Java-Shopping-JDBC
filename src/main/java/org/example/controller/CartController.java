package org.example.controller;

import org.example.App;
import org.example.Database.CartDb;
import org.example.models.*;
import org.example.util.*;
import org.example.view.CartPage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CartController {
    private final HomeController homeController;
    private final CartPage cartPage;
    private final OrderController orderController;

    private final CartDb cartDb;

    public CartController(HomeController homeController) {
        this.homeController = homeController;
        this.cartPage = new CartPage();
        this.orderController = new OrderController(homeController);
        this.cartDb = new CartDb();
    }

    public void printCart() {
        User loggedInUser = UserUtil.getLoggedUser();

        cartPage.printCart(loggedInUser.getId());

        try {
            int choice = AppInput.enterInt(StringUtil.ENTER_CHOICE);
            if (choice == 99) {
                homeController.printMenu();
            } else if (choice == 88) {
                checkout();
                homeController.printMenu();
            } else {
                invalidChoice(new AppException(StringUtil.INVALID_CHOICE));
            }
        } catch (AppException e) {
            invalidChoice(e);
        }

    }

    private void invalidChoice(AppException appException) {
        System.out.println(appException.getMessage());
        homeController.printMenu();
    }

    public void addToCart(int validId) {
        ArrayList<Product> products = LoadUtils.getProducts();
        Product userProduct = null;
        int userId = UserUtil.getLoggedUser().getId();
        for (Product product : products) {
            if (product.getId() == validId) {
                userProduct = product;
                break;
            }
        }
        System.out.println(cartDb.findItem(validId,userId));
        if (cartDb.findItem(validId,userId)){
            cartDb.updateCount(validId,userId);
        } else {
            cartDb.insertCartItem(userProduct.getId(),userId,userProduct.getTitle(),1,userProduct.getPrice());
        }

    }

    private void checkout() {
        orderController.checkout();
    }
}
