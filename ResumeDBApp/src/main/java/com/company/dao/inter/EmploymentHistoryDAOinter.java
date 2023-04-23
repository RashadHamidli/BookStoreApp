package com.company.dao.inter;

import com.company.entity.Country;
import com.company.entity.EmploymentHistory;
import com.company.entity.User;
import com.company.entity.UserSkill;

import java.util.List;

public interface EmploymentHistoryDAOinter {
    public List<EmploymentHistory> getAllEmploymentHistoryByUserİd(int userId);

    public Boolean addEmploymentHistory(EmploymentHistory emp);

    public Boolean removeEmploymentHistory(int id);

    public boolean updateEmploymentHistory(EmploymentHistory emp);
}
