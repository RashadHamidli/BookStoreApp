package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.SkillDAOinter;
import com.company.entity.Country;
import com.company.entity.Skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SkillDAOImpl extends AbstractDAO implements SkillDAOinter {

    private Skill getSkill(ResultSet resultSet) throws Exception {
        String name = resultSet.getString("name");
        int id = resultSet.getInt("id");
        Skill skill = new Skill(id, name);
        return skill;
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> result = new ArrayList<>();
        try (Connection conn = connect()) {

            PreparedStatement statement = conn.prepareStatement("select * from skill ");
            statement.execute();
            ResultSet resultSet = statement.getResultSet();


            while (resultSet.next()) {
                Skill s = getSkill(resultSet);
                result.add(s);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Skill> getSkillById(int userId) {
        List<Skill> result = new ArrayList<>();
        try (Connection conn = connect()) {

            PreparedStatement statement = conn.prepareStatement("select * from skill where id=? ");
            statement.setInt(1, userId);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();


            while (resultSet.next()) {
                Skill s = getSkill(resultSet);
                result.add(s);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Boolean insertSkill(Skill skill) {
        try (Connection conn = connect()) {
            PreparedStatement statement = conn.prepareStatement("insert into skill(name) values(?) ", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, skill.getName());

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                skill.setId(generatedKeys.getInt(1));
            }
            return statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean insertSkillaaa(Skill skl) {
        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("insert skill (name) VALUES (?);", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, skl.getName());
            b = stmt.execute();

            ResultSet generatedKeys = stmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                skl.setId(generatedKeys.getInt(1));
            }

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    @Override
    public Boolean removeSkill(int id) {
        try (Connection conn = connect()) {
            PreparedStatement statement = conn.prepareStatement("delete from skill where id=" + id);
            return statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateSkill(Skill skill) {
        try (Connection conn = connect()) {
            PreparedStatement statement = conn.prepareStatement("update user set name=? where id=?");
            statement.setString(1, skill.getName());
            return statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

