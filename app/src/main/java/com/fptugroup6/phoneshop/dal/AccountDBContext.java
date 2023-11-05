package com.fptugroup6.phoneshop.dal;

import android.util.Log;

import com.fptugroup6.phoneshop.model.Account;
import com.fptugroup6.phoneshop.model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDBContext extends DBContext{
    public Account getAccount(String username, String password){
        Log.e("Accountx","OK 3.5");
        String query = "SELECT a.Username,a.FullName, a.Email, o.OrderID,o.OrderDate\n" +
                "FROM Account a \n" +
                "JOIN [Order] o ON o.Username = a.Username\n" +
                "WHERE a.Username = ? AND a.[Password] = ?";
        Account account = new Account();
        try{
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1,username);
            stm.setString(2,password);
            ResultSet rs = stm.executeQuery();;
            while (rs.next()){
                if(account == null){
                    account.setUsername(rs.getString("Username"));
                    account.setEmail(rs.getString("Email"));
                    account.setFullName(rs.getString("FullName"));
                }
                Order order = new Order();
                order.setUsername(rs.getString("Username"));
                order.setOrderID(rs.getInt("OrderID"));
                order.setOrderDate(rs.getDate("OrderDate"));
                account.getOrders().add(order);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public void insert(Object model) {

    }

    @Override
    public void update(Object model) {

    }

    @Override
    public void delete(Object model) {

    }

    @Override
    public Object get(int id) {
        return null;
    }

    @Override
    public ArrayList list() {
        return null;
    }
}
