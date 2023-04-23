package com.company.dao.inter;

import com.company.entity.User;
import com.company.entity.UserSkill;

import java.util.List;

public interface UserSkillDAOinter {

    public List<UserSkill> getAllSkillByUserİd(int userId);
}
