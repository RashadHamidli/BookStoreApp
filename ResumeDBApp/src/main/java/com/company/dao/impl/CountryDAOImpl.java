package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.CountryDAOinter;
import com.company.entity.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CountryDAOImpl extends AbstractDAO implements CountryDAOinter {
    private Country getCountry(ResultSet resultSet) throws Exception {
        String name = resultSet.getString("name");
        String nationality = resultSet.getString("nationality");
        int id = resultSet.getInt("id");
        Country emp = new Country(id, name, nationality);
        return emp;
    }


    public List<Country> getSkillId(int userId) {
        List<Country> result = new ArrayList<>();
        try (Connection conn = connect()) {

            PreparedStatement statement = conn.prepareStatement("select * from country where id=? ");
            statement.setInt(1, userId);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();


            while (resultSet.next()) {
                Country c = getCountry(resultSet);
                result.add(c);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Country> getClountryById(int userId) {
        List<Country> result = new ArrayList<>();
        try (Connection conn = connect()) {

            PreparedStatement statement = conn.prepareStatement("select * from country where id=? ");
            statement.setInt(1, userId);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();


            while (resultSet.next()) {
                Country c = getCountry(resultSet);
                result.add(c);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Boolean addCountry(Country country) {

        try (Connection conn = connect()) {
            PreparedStatement statement = conn.prepareStatement("insert into country(name, nationality) values(?,?)");
            statement.setString(1, country.getName());
            statement.setString(2, country.getNationality());

            return statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean removeCountry(int id) {

        try (Connection conn = connect()) {
            PreparedStatement statement = conn.prepareStatement("delete from country where id="+id);
            return statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateCountr(Country country) {
        try (Connection conn = connect()) {
            PreparedStatement statement = conn.prepareStatement("update user set name=?, nationality=? where id=?");
            statement.setString(1, country.getName());
            statement.setString(2, country.getNationality());
            return statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Country> getAll() {
        List<Country> result = new ArrayList<>();
        try (Connection conn = connect()) {

            PreparedStatement statement = conn.prepareStatement("select * from country");
            statement.execute();
            ResultSet resultSet = statement.getResultSet();


            while (resultSet.next()) {
                Country c = getCountry(resultSet);
                result.add(c);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}

