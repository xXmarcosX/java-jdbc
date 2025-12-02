package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.enitites.Department;
import model.enitites.Seller;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller seller) {

    }

    @Override
    public void update(Seller seller) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT seller.*, department.name as depName " +
                            "FROM seller INNER JOIN department " +
                            "ON seller.departmentId = department.Id " +
                            "WHERE seller.Id = ?",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()) {
                Department dep = new Department();
                Seller seller = new Seller();

                dep.setId(rs.getInt("DepartmentId"));
                dep.setName(rs.getString("DepName"));

                seller.setId(rs.getInt("Id"));
                seller.setName(rs.getString("Name"));
                seller.setEmail(rs.getString("Email"));
                seller.setBirthDate(rs.getDate("BirthDate").toLocalDate());
                seller.setBaseSalary(rs.getDouble("BaseSalary"));
                seller.setDepartment(dep);

                return seller;
            }

            return null;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }
}
