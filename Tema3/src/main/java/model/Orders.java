package model;

public class Orders {

    private int idOrder;
    private int idPurchaser;
    private int idRefOrder;
    private String order_status;


    @Override
    public String toString() {
        return "Orders{" +
                "idOrder=" + idOrder +
                ", idPurchaser=" + idPurchaser +
                ", idRefOrder=" + idRefOrder +
                ", order_status='" + order_status + '\'' +
                '}';
    }

    public Orders(int idOrder, int idPurchaser, int idRefOrder, String order_status){
        this.idOrder=idOrder;
        this.idPurchaser=idPurchaser;
        this.idRefOrder=idRefOrder;
        this.order_status=order_status;

    }
    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdPurchaser() {
        return idPurchaser;
    }

    public void setIdPurchaser(int idPurchaser) {
        this.idPurchaser = idPurchaser;
    }

    public int getIdRefOrder() {
        return idRefOrder;
    }

    public void setIdRefOrder(int idRefOrder) {
        this.idRefOrder = idRefOrder;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}
