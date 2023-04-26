package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserDAOinter;
import com.company.entity.Country;
import com.company.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends AbstractDAO implements UserDAOinter {
    public static User getUser(ResultSet resultSet) throws Exception {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        String profileDescription = resultSet.getString("profileDescription");
        String address = resultSet.getString("address");
        Date birthdate = resultSet.getDate("birthdate");
        int nationalityId = resultSet.getInt("nationality_id");
        int birthplaceId = resultSet.getInt("birthplace_id");
        String nationalityStr = resultSet.getString("nationality");
        String birthplaceStr = resultSet.getString("birthplace");
        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthplace = new Country(birthplaceId, birthplaceStr, null);
        return new User(id, name, surname, email, phone, profileDescription, address, nationality, birthplace, birthdate);
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Connection conn = connect()) {

            Statement statement = conn.createStatement();
            statement.execute("SELECT\n" +
                    "\tu.*,\n" +
                    "\tn.nationality,\n" +
                    "\tc.name as birthplace \n" +
                    "FROM\n" +
                    "\tUSER u\n" +
                    "\tLEFT JOIN country n ON u.nationality_id = n.id\n" +
                    "\tLEFT JOIN country c ON u.birthplace_id = c.id");
            ResultSet resultSet = statement.getResultSet();


            while (resultSet.next()) {
                User u = getUser(resultSet);
                result.add(u);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User getById(int getId) {
        User result = null;
        try (Connection conn = connect()) {
            Statement statement = conn.createStatement();
            statement.execute("SELECT\n " +
                    "\tu.*, \n" +
                    "\tn.nationality,\n " +
                    "\tc.name as birthplace \n " +
                    "FROM\n " +
                    "\tUSER u\n " +
                    "\tLEFT JOIN country n ON u.nationality_id = n.id\n " +
                    "\tLEFT JOIN country c ON u.birthplace_id = c.id where u.id=" + getId);
            ResultSet resultSet = statement.getResultSet();


            while (resultSet.next()) {
                result = getUser(resultSet);//yoxlamaq lazimdir

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addUser(User user) {
        try (Connection conn = connect()) {
            PreparedStatement statement = conn.prepareStatement("insert into user(name, surname, email, phone,profileDescription, address, birthdate) values(?,?,?,?,?,?,?)");
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getProfileDescription());
            statement.setString(6, user.getAddress());
            statement.setDate(7, user.getBirthDate());
            return statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(User u) {
        try (Connection conn = connect()) {
            PreparedStatement statement = conn.prepareStatement("update user set name=?, surname=?, email=?, phone=?, profileDescription=?, address=?, birthdate=?, birthplace_id=?, nationality_id=?   where id=? ");
            statement.setString(1, u.getName());
            statement.setString(2, u.getSurname());
            statement.setString(3, u.getEmail());
            statement.setString(4, u.getPhone());
            statement.setString(5, u.getProfileDescription());
            statement.setString(6, u.getAddress());
            statement.setDate(7, u.getBirthDate());
           statement.setInt(8, u.getBirthplace().getId());
           statement.setInt(9, u.getNationality().getId());
            statement.setInt(10, u.getId());

            return statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        try (Connection conn = connect()) {
            Statement statement = conn.createStatement();
            return statement.execute("delete from user where id=" + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


}
