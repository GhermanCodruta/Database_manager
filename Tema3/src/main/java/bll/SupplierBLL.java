package bll;

import bll.validators.EmailSupplier;
import bll.validators.EmailValidator;
import bll.validators.Validator;
import dao.SupplierDAO;
import model.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class SupplierBLL {
    private List<Validator<Supplier>> validators;
    private SupplierDAO supplierDAO;
    public SupplierBLL(SupplierDAO supplierDAO) {
        this.supplierDAO=supplierDAO;
        validators = new ArrayList<Validator<Supplier>>();
        validators.add(new EmailSupplier());

    }


    public ArrayList<Supplier> getAll() {
        ArrayList<Supplier> suppliers = supplierDAO.getAll();
        if (suppliers == null) {
            throw new NoSuchElementException("Couldn't get all suppliers\n");
        }
        return suppliers;
    }

    public int insertSupplier(Supplier supplier){
        return supplierDAO.insert(supplier);
    }

    public void deleteSupplier(int idSupplier)
    {
        supplierDAO.delete(idSupplier);
    }

    public void updateAll(int newId, String newSupplier_email, String newSupplier_name, String newSupplier_phone,  String newSupplier_details) {
        supplierDAO.update(newId, newSupplier_email, newSupplier_name,  newSupplier_phone, newSupplier_details);
    }
}
