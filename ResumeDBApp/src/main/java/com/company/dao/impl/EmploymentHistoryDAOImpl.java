package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.EmploymentHistoryDAOinter;
import com.company.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmploymentHistoryDAOImpl extends AbstractDAO implements EmploymentHistoryDAOinter {
    private EmploymentHistory getEmploymentHistory(ResultSet resultSet) throws Exception {
        String header = resultSet.getString("header");
        String jobDescription = resultSet.getString("job_description");
        Date beginDate = resultSet.getDate("begin_date");
        Date endDate = resultSet.getDate("end_date");
        int userId = resultSet.getInt("user_id");
        EmploymentHistory emp = new EmploymentHistory(null, header, jobDescription, beginDate, endDate, new User(userId));
        return emp;
    }

    @Override
    public List<EmploymentHistory> getAllEmploymentHistoryByUserİd(int userId) {
        List<EmploymentHistory> result = new ArrayList<>();
        try (Connection conn = connect()) {

            PreparedStatement statement = conn.prepareStatement("select * from employment_history where user_id=? ");
            statement.setInt(1, userId);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();


            while (resultSet.next()) {
                EmploymentHistory emp = getEmploymentHistory(resultSet);
                result.add(emp);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Boolean addEmploymentHistory(EmploymentHistory emp) {
        try (Connection conn = connect()) {
            PreparedStatement statement = conn.prepareStatement("insert into employment_history(header, begin_date, end_date, job_description, user_id) values(?,?,?,?,?)");
            statement.setString(1, emp.getHeader());
            statement.setDate(2, emp.getBeginDate());
            statement.setDate(3, emp.getEndDate());
            statement.setString(4, emp.getJobDescription());
            statement.setInt(5, emp.getId());
            return statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean removeEmploymentHistory(int id) {
        try (Connection conn = connect()) {
            PreparedStatement statement = conn.prepareStatement("delete from employment_history where id=" + id);
            return statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateEmploymentHistory(EmploymentHistory emp) {
        try (Connection conn = connect()) {
            PreparedStatement statement = conn.prepareStatement("update user set header=?, begin_date=?, end_date=?, job_description=?, user_id=?  where id=?");
            statement.setString(1, emp.getHeader());
            statement.setDate(2, emp.getBeginDate());
            statement.setDate(3, emp.getEndDate());
            statement.setString(4, emp.getJobDescription());
            statement.setInt(5, emp.getId());
            return statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
