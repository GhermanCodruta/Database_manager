package bll;


import dao.ProductDAO;
import model.Product;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ProductBLL {
    private ProductDAO productDAO;
    public ProductBLL(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public ArrayList<Product> getAll() {
        ArrayList<Product> products = productDAO.getAll();
        if (products == null) {
            throw new NoSuchElementException("Couldn't get all Products\n");
        }
        return products;
    }

    public int insertProduct(Product product){
        return productDAO.insert(product);
    }

    public void deleteProduct(int idProduct)
    {
        productDAO.delete(idProduct);
    }

    public void updateProductQuantity(int newQty, int idProduct){
        productDAO.updateQuantity(newQty, idProduct);
    }

    public void updateAll(int idProduct,int newIdSupplier, int newIdCategory, int newQty, String newProduct_name, int newProduct_price, String newProduct_details){
        productDAO.update(newQty, newIdSupplier,  newIdCategory,  newProduct_price,  newProduct_name,  newProduct_details, idProduct);
    }
}
