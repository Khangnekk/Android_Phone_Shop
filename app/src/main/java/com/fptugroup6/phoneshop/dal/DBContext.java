package com.fptugroup6.phoneshop.dal;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DBContext<T> {
    protected Connection connection;

    public DBContext() {
        try {
            Log.e("Accountx","OK 3.1");
            String username = "Khangnek";
            String password = "123";
            String url = "jdbc:sqlserver://MSI:1433;databaseName=PhoneStore";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection =  DriverManager.getConnection(url,username,password);
            Log.e("Accountx","OK 3.3");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            Log.e("Accountx","!OK 3.3");
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            Log.e("Accountx","!OK 3.4: "+ex);
            ex.printStackTrace();
        }
        
    }
    
    public abstract void insert(T model);
    public abstract void update(T model);
    public abstract void delete(T model);
    public abstract T get(int id);
    public abstract ArrayList<T> list();
    
}
