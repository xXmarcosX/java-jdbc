import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.enitites.Seller;

public class Main {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();
        Seller seller = sellerDao.findById(11);

        //TEST FIND BY ID
        System.out.println(seller);
    }
}
