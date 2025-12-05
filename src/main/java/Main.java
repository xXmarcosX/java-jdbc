import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.dao.impl.DepartmentDaoJDBC;
import model.enitites.Department;
import model.enitites.Seller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        DepartmentDao depDao = DaoFactory.createDepartmentDao();
        Department dep = new Department(1, "Computers");

        SellerDao sellerDao = DaoFactory.createSellerDao();
        Seller seller = sellerDao.findById(11);

        List<Seller> sellers = sellerDao.findByDepartment(dep);
        List<Seller> sellers2 = sellerDao.findAll();

        //TEST FIND BY SELLER ID
        System.out.println("Test ID");
        System.out.println(seller);

        //TEST FIND SELLER BY DEPARTMENT
        System.out.println("\n\nTest find by department");
        sellers.forEach(System.out::println);

        //TEST FIND ALL SELLERS
        System.out.println("\n\nTest find all");
        sellers2.forEach(System.out::println);

        //TEST INSERT SELLER
        System.out.println("\n\nTest insert");
        Seller newSeller = new Seller(null, "Chris", "chris@gmail.com", new Date(), 4000.0, dep);

        sellerDao.insert(newSeller);

        System.out.println("New seller ID: " + newSeller.getId());

        //TEST UPDATE SELLER
        System.out.println("\n\nTest update");
        seller.setBaseSalary(6000.0);

        sellerDao.update(seller);

        System.out.println(seller.getBaseSalary());

        //TEST DELETE SELLER
        System.out.println("\n\nTest Delete");

        sellerDao.deleteById(newSeller.getId());

        System.out.println(sellerDao.findById(newSeller.getId()));

        System.out.println("\n");

        //TEST GET DEPARTMENT BY ID
        System.out.println(depDao.findById(1));
    }
}
