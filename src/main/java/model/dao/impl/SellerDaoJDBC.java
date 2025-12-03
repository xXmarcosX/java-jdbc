package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.enitites.Department;
import model.enitites.Seller;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller seller) {
        PreparedStatement st = null;

        try {
            st = this.conn.prepareStatement(
                    "INSERT INTO seller " +
                            "(Name, Email, BirthDate, BaseSalary, DepartmentId) " +
                            "VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

            st.setString(1, seller.getName());
            st.setString(2, seller.getEmail());
            st.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
            st.setDouble(4, seller.getBaseSalary());
            st.setInt(5, seller.getDepartment().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt(1);

                    seller.setId(id);
                }
            }
            else {
                throw new DbException("Erro inesperado! Nenhuma linha afetada.");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
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
                Department dep = instantiateDepartment(rs);
                Seller seller = instantiateSeller(rs, dep);

                return seller;
            }

            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findAll() {
        Statement st = null;
        ResultSet rs = null;

        List<Seller> sellers = new ArrayList<>();

        try {
            st = conn.createStatement();

            rs = st.executeQuery("SELECT seller.*, department.name as depName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.departmentId = department.Id " +
                    "ORDER BY Name");

            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {
                Department dp = map.get(rs.getInt("DepartmentId"));

                if (dp == null) {
                    dp = instantiateDepartment(rs);

                    map.put(rs.getInt("DepartmentId"), dp);
                }

                Seller seller = instantiateSeller(rs, dp);
                sellers.add(seller);
            }

            return sellers;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Seller> sellers = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                    "SELECT seller.*, department.name as depName " +
                            "FROM seller INNER JOIN department " +
                            "ON seller.departmentId = department.Id " +
                            "WHERE seller.departmentId = ?",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setInt(1, department.getId());

            rs = st.executeQuery();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {
                Department dp = map.get(rs.getInt("DepartmentId"));

                if (dp == null) {
                    dp = instantiateDepartment(rs);

                    map.put(rs.getInt("DepartmentId"), dp);
                }

                Seller seller = instantiateSeller(rs, dp);
                sellers.add(seller);
            }

            return sellers;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller seller = new Seller();

        seller.setId(rs.getInt("Id"));
        seller.setName(rs.getString("Name"));
        seller.setEmail(rs.getString("Email"));
        seller.setBirthDate(rs.getDate("BirthDate"));
        seller.setBaseSalary(rs.getDouble("BaseSalary"));
        seller.setDepartment(dep);

        return seller;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();

        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));

        return dep;
    }
}
