package com.company.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class AbstractDAO {
    public static Connection connect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/resumesecond";
        String userName = "root";
        String password = "123456";

        Connection conn = DriverManager.getConnection(url, userName, password);
        return conn;
    }
}
