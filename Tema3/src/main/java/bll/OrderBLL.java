package bll;

import dao.OrderDAO;
import model.Orders;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class OrderBLL {
    private OrderDAO orderDAO;

    public OrderBLL(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public ArrayList<Orders> getAll() {
        ArrayList<Orders> orders = orderDAO.getAll();
        if (orders == null) {
            throw new NoSuchElementException("Couldn't get all Orders\n");
        }
        return orders;
    }

    public int insertOrder(Orders orders){
        return orderDAO.insert(orders);
    }

    public void deleteOrder(int idOrder)
    {
        orderDAO.delete(idOrder);
    }


    public void updateAll(int newIdOrder, int newIdPurchaser, int newIdReforder, String newOrder_status){
        orderDAO.update(newIdOrder, newIdPurchaser, newIdReforder, newOrder_status);
    }
}
