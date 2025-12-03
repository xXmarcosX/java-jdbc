import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.enitites.Department;
import model.enitites.Seller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Department dep = new Department(1, "Computers");
        SellerDao sellerDao = DaoFactory.createSellerDao();
        Seller seller = sellerDao.findById(11);

        List<Seller> sellers = sellerDao.findByDepartment(dep);
        List<Seller> sellers2 = sellerDao.findAll();

        //TEST FIND BY ID
        System.out.println("Test ID");
        System.out.println(seller);

        //TEST FIND BY DEPARTMENT
        System.out.println("\n\nTest find by department");
        sellers.forEach(System.out::println);

        //TEST FIND ALL
        System.out.println("\n\nTest find all");
        sellers2.forEach(System.out::println);

        //TEST INSERT
        System.out.println("\n\nTest find insert");
        Seller newSeller = new Seller(null, "Chris", "chris@gmail.com", new Date(), 4000.0, dep);

        sellerDao.insert(newSeller);

        System.out.println("New seller ID: " + newSeller.getId());
    }
}
