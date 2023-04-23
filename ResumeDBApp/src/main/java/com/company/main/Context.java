package com.company.main;

import com.company.dao.impl.*;
import com.company.dao.inter.*;

public class Context {
    public static UserDAOinter instanceUserDao() {
        return new UserDAOImpl();
    }

    public static UserSkillDAOinter instanceUserSkillDao() {
        return new UserSkillDAOImpl();
    }

    public static EmploymentHistoryDAOinter instanceEmploymentHistoryDao() {
        return new EmploymentHistoryDAOImpl();
    }
    public static CountryDAOinter instanceCountryDao() {
        return new CountryDAOImpl();
    }
    public static SkillDAOinter instanceSkillDao() {
        return new SkillDAOImpl();
    }
}
