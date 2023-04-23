package com.company.main;

import com.company.dao.inter.*;
import com.company.entity.Country;
import com.company.entity.EmploymentHistory;
import com.company.entity.User;

import java.sql.Date;
import java.util.List;


public class Main {

    public static void main(String[] args) throws Exception {
        UserSkillDAOinter userDAO = Context.instanceUserSkillDao();
        System.out.println(userDAO.getAllSkillByUserİd(1));

    }

}
