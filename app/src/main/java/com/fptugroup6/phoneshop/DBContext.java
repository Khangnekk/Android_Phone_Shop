package com.fptugroup6.phoneshop;

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
            String username = "Khangnek";
            String password = "123";
            String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=PhoneStore";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection =  DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public abstract void insert(T model);
    public abstract void update(T model);
    public abstract void delete(T model);
    public abstract T get(int id);
    public abstract ArrayList<T> list();
    
}
