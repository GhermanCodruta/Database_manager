package model;

public class Product {
    private int idProduct;
    private int idSupplier;
    private int idCategory;
    private int product_stock;
    private String product_name;
    private int product_price;
    private String product_details;

    public Product(int idProduct, int idSupplier, int idCategory, int product_stock, String product_name, int product_price, String product_details) {
        this.idProduct = idProduct;
        this.idSupplier = idSupplier;
        this.idCategory = idCategory;
        this.product_stock = product_stock;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_details = product_details;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public String getProduct_details() {
        return product_details;
    }

    public void setProduct_details(String product_details) {
        this.product_details = product_details;
    }

    public int getProduct_stock() {
        return product_stock;
    }

    public void setProduct_stock(int product_stock) {
        this.product_stock = product_stock;
    }
}
