package com.company.main;

import com.company.dao.inter.*;
import com.company.entity.Country;
import com.company.entity.EmploymentHistory;
import com.company.entity.User;

import java.sql.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        UserDAOinter userDAO = Context.instanceUserDao();
        User u = new User(3, "Rashad", "Hamidli", "asdasd@gmail.com", "+994552557794", "Java App", "Baku", new Country(1, null, null), new Country(1, null, null), new Date(199 - 10 - 10));
        userDAO.updateUser(u);

    }
}
