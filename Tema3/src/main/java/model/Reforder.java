package model;

public class Reforder {
    private int idRefOrder;
    private int idProduct;
    private int quantity;
    private int total_price;
    private int product_price;
    private String order_details;

    public Reforder(int idRefOrder, int idProduct, int quantity, int total_price, int product_price, String order_details) {
        this.idRefOrder = idRefOrder;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.total_price = total_price;
        this.product_price = product_price;
        this.order_details = order_details;
    }

    public int getIdRefOrder() {
        return idRefOrder;
    }

    public void setIdRefOrder(int idRefOrder) {
        this.idRefOrder = idRefOrder;
    }

    public String getOrder_details() {
        return order_details;
    }

    public void setOrder_details(String order_details) {
        this.order_details = order_details;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }
}
