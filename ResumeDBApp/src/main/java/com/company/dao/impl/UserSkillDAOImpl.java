package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserSkillDAOinter;
import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserSkillDAOImpl extends AbstractDAO implements UserSkillDAOinter {

    private UserSkill getUserSkill(ResultSet resultSet) throws Exception {
        int userSkillId = resultSet.getInt("userSkillId");
        int userId = resultSet.getInt("id");
        int skillId = resultSet.getInt("skill_id");
        String skillName = resultSet.getString("skill_name");
        int power = resultSet.getInt("power");

        return new UserSkill(userSkillId, new User(userId), new Skill(skillId, skillName), power);
    }

    @Override
    public List<UserSkill> getAllSkillByUserİd(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try (Connection conn = connect()) {

            PreparedStatement statement = conn.prepareStatement("SELECT\n " +
                    "\tus.id AS userSkillId,\n " +
                    "\tu.*,\n " +
                    "\tus.skill_id,\n " +
                    "\tus.power,\n " +
                    "\ts.NAME AS skill_name \n " +
                    "FROM\n " +
                    "\tuser_skill us\n " +
                    "\tLEFT JOIN USER u ON us.user_id = u.id\n " +
                    "\tLEFT JOIN skill s ON us.skill_id = s.id \n " +
                    "WHERE\n " +
                    "\tus.user_id =? ");
            statement.setInt(1, userId);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                UserSkill u = getUserSkill(resultSet);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Boolean insertUserSkill(UserSkill userSkill) {
        try (Connection conn = connect()) {
            PreparedStatement statement = conn.prepareStatement("insert into user_skill(user_id, skill_id, power) values(?,?,?) ");
            statement.setInt(1, userSkill.getUser().getId());
            statement.setInt(2, userSkill.getSkill().getId());
            statement.setInt(3, userSkill.getPower());
            return statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean removeUserSkill(int id) {
        try (Connection conn = connect()) {
            PreparedStatement statement = conn.prepareStatement("delete from user_skill where id=" + id);
            return statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUserSkill(UserSkill userSkill) {
        try (Connection conn = connect()) {
            PreparedStatement statement = conn.prepareStatement("update user_skil set user_id=?, skill_id=?, power=? where id=?");
            statement.setInt(1, userSkill.getUser().getId());
            statement.setInt(2, userSkill.getSkill().getId());
            statement.setInt(3, userSkill.getPower());
            return statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}


