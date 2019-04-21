
package bll;

 import java.util.ArrayList;
 import java.util.List;
 import java.util.NoSuchElementException;
 import bll.validators.EmailValidator;
 import bll.validators.Validator;
 import dao.PurchaserDAO;
 import model.Purchaser;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */

public class PurchaserBLL {
    private List<Validator<Purchaser>> validators;
    private PurchaserDAO purchaserDAO;
    public PurchaserBLL(PurchaserDAO purchaserDAO) {
        this.purchaserDAO=purchaserDAO;
        validators = new ArrayList<Validator<Purchaser>>();
        validators.add(new EmailValidator());

    }

    
    public ArrayList<Purchaser> getAll() {
        ArrayList<Purchaser> purchasers = purchaserDAO.getAll();
        if (purchasers == null) {
            throw new NoSuchElementException("Couldn't get all purchasers\n");
        }
        return purchasers;
    }

    public int insertPurchaser(Purchaser purchaser){
        return purchaserDAO.insert(purchaser);
    }

    public void deletePurchaser(int idPurchaser)
    {
        purchaserDAO.delete(idPurchaser);
    }

    public void updateAll(int newId, String newPurchaser_name, String newPurchaser_email, String newPurchaser_phone, String newPurchaser_address, String newPurchaser_details) {
        purchaserDAO.update(newId, newPurchaser_name, newPurchaser_email, newPurchaser_phone, newPurchaser_address, newPurchaser_details);
    }
}
