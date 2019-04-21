package bll;

import dao.ReforderDAO;
import model.Reforder;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ReforderBLL {
    private ReforderDAO reforderDAO;
    public ReforderBLL(ReforderDAO reforderDAO) {
        this.reforderDAO = reforderDAO;
    }

    public ArrayList<Reforder> getAll() {
        ArrayList<Reforder> reforders = reforderDAO.getAll();
        if (reforders == null) {
            throw new NoSuchElementException("Couldn't get all Reforders\n");
        }
        return reforders;
    }

    public int insertReforder(Reforder reforder){
        return reforderDAO.insert(reforder);
    }

    public void deleteReforder(int idReforder)
    {
        reforderDAO.delete(idReforder);
    }


    public void updateAll(int newIdReforder, int newIdProduct, int newQuantity, int newTotal_price, int newProduct_price,String newReforder_details){
        reforderDAO.update(newIdReforder, newIdProduct,newQuantity,newTotal_price,newProduct_price,  newReforder_details);
    }
}
