package com.company.dao.inter;

import com.company.entity.Country;
import com.company.entity.Skill;
import com.company.entity.User;

import java.util.List;

public interface CountryDAOinter {
    public List<Country> getAll();
    public List<Country> getClountryById(int userId);
    public Boolean addCountry(Country country);
    public Boolean removeCountry(int id);
    public boolean updateCountr(Country country);

}
