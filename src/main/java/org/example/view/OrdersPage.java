package org.example.view;

import org.example.Database.OrdersDb;
import org.example.models.Category;
import org.example.models.User;
import org.example.util.FileUtil;
import org.example.util.UserUtil;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class OrdersPage {
    private final OrdersDb ordersDb = new OrdersDb();
    public void printOrders() {
        ResultSet ordersRs = ordersDb.getOrders(UserUtil.getLoggedUser().getId());
        try {
            while (ordersRs.next()){
                System.out.println(ordersRs.getInt("id")+" "+ordersRs.getString("name")+" "+ordersRs.getInt("count")+" "+ordersRs.getString("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        try {
//            Scanner scanner = new Scanner(FileUtil.getOrdersFile());
//            while (scanner.hasNext()) {
//                String value = scanner.next().trim();
//                if (!value.startsWith("id")) {
//                    String[] ordersArray = value.split(",");
//                    if (Integer.parseInt(ordersArray[4]) == UserUtil.getLoggedUser().getId()){
//                        System.out.println(ordersArray[1]+"  "+ordersArray[2]+"  "+ordersArray[3]);
//                    }
//
//                }
//            }
//            scanner.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
