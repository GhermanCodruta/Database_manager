package start;

import bll.*;
import dao.*;
import presentation.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    CategoryDAO categoryDAO;
    CategoryBLL categoryBLL;

    OrderDAO orderDAO;
    OrderBLL orderBLL;

    ProductDAO productDAO;
    ProductBLL productBLL;

    PurchaserDAO purchaserDAO;
    PurchaserBLL purchaserBLL;

    ReforderDAO reforderDAO;
    ReforderBLL reforderBLL;

    SupplierDAO supplierDAO;
    SupplierBLL supplierBLL;

    public Controller(CategoryDAO categoryDAO, CategoryBLL categoryBLL, OrderDAO orderDAO, OrderBLL orderBLL, ProductDAO productDAO, ProductBLL productBLL, PurchaserDAO purchaserDAO, PurchaserBLL purchaserBLL, ReforderDAO reforderDAO, ReforderBLL reforderBLL, SupplierDAO supplierDAO, SupplierBLL supplierBLL) {
        this.categoryDAO = categoryDAO;
        this.categoryBLL = categoryBLL;
        this.orderDAO = orderDAO;
        this.orderBLL = orderBLL;
        this.productDAO = productDAO;
        this.productBLL = productBLL;
        this.purchaserDAO = purchaserDAO;
        this.purchaserBLL = purchaserBLL;
        this.reforderDAO = reforderDAO;
        this.reforderBLL = reforderBLL;
        this.supplierDAO = supplierDAO;
        this.supplierBLL = supplierBLL;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "purchaser") {
            ViewPurchaser viewPurchaser = new ViewPurchaser(purchaserDAO, purchaserBLL);
        }
        if (e.getActionCommand() == "product") {
            ViewProduct viewProduct = new ViewProduct(productBLL, productDAO, categoryBLL, categoryDAO, supplierBLL, supplierDAO);
        }
        if (e.getActionCommand() == "category") {
            ViewCategory viewCategory = new ViewCategory(categoryDAO, categoryBLL);
        }
        if (e.getActionCommand() == "reforder") {
            ViewReforder viewReforder = new ViewReforder(reforderDAO, reforderBLL);
        }
        if (e.getActionCommand() == "order") {
            ViewOrder viewOrder = new ViewOrder(orderDAO, orderBLL);
        }
        if (e.getActionCommand() == "supplier") {
            ViewSupplier viewSupplier = new ViewSupplier(supplierDAO, supplierBLL);
        }
        if (e.getActionCommand() == "create") {
            ViewCreateOrder viewCreateOrder=new ViewCreateOrder(purchaserBLL,purchaserDAO,productBLL,productDAO,orderBLL,orderDAO,reforderBLL,reforderDAO);
        }
    }
}
