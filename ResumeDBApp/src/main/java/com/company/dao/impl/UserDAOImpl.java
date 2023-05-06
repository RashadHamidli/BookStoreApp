package com.company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
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
        String profileDescription = resultSet.getString("profile_description");
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

    private User getUserSimple(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String profileDesc = rs.getString("profile_description");
        String address = rs.getString("address");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        Date birthdate = rs.getDate("birthdate");


        User user = new User(id, name, surname, phone, email, profileDesc, address, null, null, null);
        user.setPassword(rs.getString("password"));

        return user;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from user where email=? and password=?");
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = getUserSimple(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public User findByEmail(String email) {
        User result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from user where email=?");
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = getUserSimple(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
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
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        List<User> result = new ArrayList<>();
        try (Connection c = connect()) {

            String sql = "select "
                    + "  u.*,  "
                    + "  n.nationality, "
                    + "  c.name as birthplace  "
                    + "from user u "
                    + "left join country n on u.nationality_id = n.id "
                    + "left join country c on u.birthplace_id = c.id where 1=1 ";

            if (name != null && !name.trim().isEmpty()) {
                sql += " and u.name=? ";
            }

            if (surname != null && !surname.trim().isEmpty()) {
                sql += " and u.surname=? ";
            }

            if (nationalityId != null) {
                sql += " and u.nationality_id=? ";
            }


            PreparedStatement stmt = c.prepareStatement(sql);

            int i = 1;
            if (name != null && !name.trim().isEmpty()) {
                stmt.setString(i, name);
                i++;//2
            }

            if (surname != null && !surname.trim().isEmpty()) {
                stmt.setString(i, surname);
                i++;//3
            }

            if (nationalityId != null) {
                stmt.setInt(i, nationalityId);
            }


            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                User u = getUser(rs);

                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    //int id, String name, String surname, String email, String phone, String profileDescription, String address, Country country, Country birthplace, Date birthDate
    @Override
    public List<User> getAll(String name, String surname, String email, String phone, String address) {
        List<User> result = new ArrayList<>();
        try (Connection c = connect()) {

            String sql = "select "
                    + "  u.*,  "
                    + "  n.nationality, "
                    + "  c.name as birthplace  "
                    + "from user u "
                    + "left join country n on u.nationality_id = n.id "
                    + "left join country c on u.birthplace_id = c.id where 1=1 ";

            if (name != null && !name.trim().isEmpty()) {
                sql += " and u.name=? ";
            }

            if (surname != null && !surname.trim().isEmpty()) {
                sql += " and u.surname=? ";
            }

            if (email != null && !email.trim().isEmpty()) {
                sql += " and u.email=? ";
            }
            if (phone != null && !phone.trim().isEmpty()) {
                sql += " and u.phone=? ";
            }
            if (address != null && !address.trim().isEmpty()) {
                sql += " and u.address=? ";
            }


            PreparedStatement stmt = c.prepareStatement(sql);

            int i = 1;
            if (name != null && !name.trim().isEmpty()) {
                stmt.setString(i, name);
                i++;
            }

            if (surname != null && !surname.trim().isEmpty()) {
                stmt.setString(i, surname);
                i++;
            }
            if (email != null && !email.trim().isEmpty()) {
                stmt.setString(i, email);
                i++;
            }
            if (phone != null && !phone.trim().isEmpty()) {
                stmt.setString(i, phone);
                i++;
            }
            if (address != null && !address.trim().isEmpty()) {
                stmt.setString(i, address);
            }


            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                User u = getUser(rs);

                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


    //    @Override
//    public User getById(int getId) {
//        User result = null;
//        try (Connection conn = connect()) {
//            Statement statement = conn.createStatement();
//            statement.execute("SELECT\n " +
//                    "\tu.*, \n" +
//                    "\tn.nationality,\n " +
//                    "\tc.name as birthplace \n " +
//                    "FROM\n " +
//                    "\tUSER u\n " +
//                    "\tLEFT JOIN country n ON u.nationality_id = n.id\n " +
//                    "\tLEFT JOIN country c ON u.birthplace_id = c.id where u.id=" + getId);
//            ResultSet resultSet = statement.getResultSet();
//
//
//            while (resultSet.next()) {
//                result = getUser(resultSet);//yoxlamaq lazimdir
//
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return result;
//    }
    @Override
    public User getById(int userId) {
        User result = null;
        try (Connection c = connect()) {

            Statement stmt = c.createStatement();
            stmt.execute("select "
                    + "  u.*,  "
                    + "  n.nationality, "
                    + "  c.name as birthplace  "
                    + "from user u "
                    + "left join country n on u.nationality_id = n.id "
                    + "left join country c on u.birthplace_id = c.id where u.id=" + userId);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                result = getUser(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUser(User u) {
        try (Connection conn = connect()) {
            PreparedStatement statement = conn.prepareStatement("update user set name=?, surname=?, email=?, phone=?, profile_description=?, address=?, birthdate=?, birthplace_id=?, nationality_id=?   where id=? ");
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

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean addUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into user(name,surname,phone,email,password,profile_description) values(?,?,?,?,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, crypt.hashToString(4, u.getPassword().toCharArray()));
            stmt.setString(6, u.getProfileDescription());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) {
        User u = new User(0, "test","test","test","test",null,null,null,null,null);
        u.setPassword("12345");
        new UserDAOImpl().addUser(u);

        System.out.println(crypt.hashToString(4, "12345".toCharArray()));
    }



}
