import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.enitites.Department;
import model.enitites.Seller;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Department dep = new Department(1, "Computers");
        SellerDao sellerDao = DaoFactory.createSellerDao();
        Seller seller = sellerDao.findById(11);

        List<Seller> sellers = sellerDao.findByDepartment(dep);
        List<Seller> sellers2 = sellerDao.findAll();

        //TEST FIND BY ID
//        System.out.println(seller);

        //TEST FIND BY DEPARTMENT
//        sellers.forEach(System.out::println);

        //TEST FIND ALL
        sellers2.forEach(System.out::println);
    }
}
