package com.company.dao.inter;

import com.company.entity.User;
import com.company.entity.UserSkill;

import java.sql.Date;
import java.util.List;

public interface UserDAOinter {
    public List<User> getAll();
    public List<User> getAll(String name, String surname, Integer nationalityId);
    public List<User> getAll(String name, String surname, String email, String phone, String address );

    public User getById(int id);

    public boolean addUser(User user);

    public boolean updateUser(User user);

    public boolean removeUser(int id);

}
