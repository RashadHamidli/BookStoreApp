package com.company.dao.inter;

import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;

import java.util.List;

public interface UserSkillDAOinter {

    public List<UserSkill> getAllSkillByUserİd(int userId);

    public Boolean insertUserSkill(UserSkill userSkill);

    public Boolean removeUserSkill(int id);

    public boolean updateUserSkill(UserSkill userSkill);
}
