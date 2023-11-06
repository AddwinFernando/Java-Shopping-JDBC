package org.example.controller;

import org.example.Database.UserDb;
import org.example.controller.impl.IAuthController;
import org.example.models.Role;
import org.example.models.User;
import org.example.util.*;
import org.example.view.AuthPage;
import org.example.view.LoginPage;
import org.example.view.RegisterPage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.example.util.AppInput.enterInt;

public class AuthController implements IAuthController {
    private final HomeController homeController;
    private final LoginPage loginPage;
    private final RegisterPage registerPage;

    private final UserDb userDb;

    private final AuthPage authPage;
    public AuthController() {
        this.userDb = new UserDb();
        this.loginPage = new LoginPage();
        this.registerPage = new RegisterPage();
        this.homeController = new HomeController(this);
        this.authPage = new AuthPage();
    }

    public void authMenu() throws AppException {
        authPage.printAuthMenu();
        int choice;

        try {
            choice = enterInt(StringUtil.ENTER_CHOICE);
            if (choice ==1){
                login();
            } else if (choice == 2) {
                register();
            } else {
                invalidChoice(new AppException(StringUtil.INVALID_CHOICE));
            }
        } catch (AppException appException) {
            invalidChoice(appException);
        }
    }

    public void login() throws AppException {
        String email,password;
        email = AppInput.enterString(StringUtil.ENTER_EMAIL);
        password = AppInput.enterString(StringUtil.ENTER_PASSWORD);
        User user = userDb.loginUser(email,password);
        if (user != null){
            UserUtil.setLoggedUser(user);
            homeController.printMenu();
            LoadUtils.load();
        } else {
            loginPage.printInvalidCredentials();
            authMenu();
        }
    }


    public void register() throws AppException {
        String email,password,name,c_password;
        name = AppInput.enterString(StringUtil.ENTER_NAME);
        email =  AppInput.enterString(StringUtil.ENTER_EMAIL);
        password =  AppInput.enterString(StringUtil.ENTER_PASSWORD);
        c_password =  AppInput.enterString(StringUtil.ENTER_PASSWORD_AGAIN);
        int id = (int) (Math.random()*100);

        if(password.equals(c_password)){
            userDb.registerUser(id,name,email,password);
        }else {
            RegisterPage.passwordMissMatch();
        }
        authMenu();
    }

    @Override
    public void logout() throws AppException {
        UserUtil.setLoggedUser(null);
        this.authMenu();
    }

    public void invalidChoice(AppException e) throws AppException {
        System.out.println(e.getMessage());
        authMenu();
    }
}
