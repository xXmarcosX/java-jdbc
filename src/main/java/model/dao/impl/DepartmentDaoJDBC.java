package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.enitites.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {this.conn = conn;}

    @Override
    public void insert(Department department) {

    }

    @Override
    public void update(Department department) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM department WHERE Id = ?",
                    Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()) {
                Department dp = new Department();

                dp.setName(rs.getString("name"));
                dp.setId(rs.getInt("Id"));

                return dp;
            }

            return null;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        Statement st = null;
        ResultSet rs = null;

        List<Department> departments = new ArrayList<>();

        try {
            st = conn.createStatement();

            rs = st.executeQuery("SELECT * FROM Department " +
                    "ORDER BY NAME");

            while(rs.next()) {
                Department dep = new Department();

                dep.setId(rs.getInt("Id"));
                dep.setName(rs.getString("Name"));

                departments.add(dep);
            }

            return departments;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
