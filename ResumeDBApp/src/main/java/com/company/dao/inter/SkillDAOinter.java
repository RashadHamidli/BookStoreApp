package com.company.dao.inter;

import com.company.entity.Country;
import com.company.entity.Skill;

import java.util.List;

public interface SkillDAOinter {
    public List<Skill> getAll();

    public List<Skill> getSkillById(int userId);

    public Boolean insertSkill(Skill skill);

    public Boolean removeSkill(int id);

    public boolean updateSkill(Skill skill);
}
