package start;

import bll.*;
import connection.ConnectionFactory;
import dao.*;


import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.SQLException;


public class Start {


    public Start(Connection connection,CategoryDAO categoryDAO, CategoryBLL categoryBLL,
                 OrderDAO orderDAO, OrderBLL orderBLL,
                 ProductDAO productDAO, ProductBLL productBLL,
                 PurchaserDAO purchaserDAO, PurchaserBLL purchaserBLL,
                 ReforderDAO reforderDAO, ReforderBLL reforderBLL,
                 SupplierDAO supplierDAO, SupplierBLL supplierBLL){

        JButton categoryButton=new JButton("Category");
        JButton orderButton= new JButton("Orders");
        JButton productButton= new JButton("Product");
        JButton purchaserButton= new JButton("Purchaser");
        JButton reforderButton=new JButton("Ref Orders");
        JButton supplierButton = new JButton("Supplier");
        JButton createOrderButton=new JButton("Create Orders");

        final Connection c1=connection;
        categoryButton.setActionCommand("category");
        orderButton.setActionCommand("order");
        productButton.setActionCommand("product");
        purchaserButton.setActionCommand("purchaser");
        reforderButton.setActionCommand("reforder");
        supplierButton.setActionCommand("supplier");
        createOrderButton.setActionCommand("create");

        Controller controller = new Controller(categoryDAO, categoryBLL, orderDAO, orderBLL, productDAO, productBLL, purchaserDAO, purchaserBLL, reforderDAO, reforderBLL, supplierDAO, supplierBLL);

        categoryButton.addActionListener(controller);
        orderButton.addActionListener(controller);
        productButton.addActionListener(controller);
        purchaserButton.addActionListener(controller);
        reforderButton.addActionListener(controller);
        supplierButton.addActionListener(controller);
        createOrderButton.addActionListener(controller);

        JPanel jPanel = new JPanel();
        jPanel.add(categoryButton);
        jPanel.add(orderButton);
        jPanel.add(productButton);
        jPanel.add(purchaserButton);
        jPanel.add(reforderButton);
        jPanel.add(supplierButton);
        jPanel.add(createOrderButton);

        final JFrame frame = new JFrame("Warehouse management");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.add(jPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void createAndShowGUI(Connection connection, CategoryDAO categoryDAO, CategoryBLL categoryBLL,
                                        OrderDAO orderDAO, OrderBLL orderBLL,
                                        ProductDAO productDAO, ProductBLL productBLL,
                                        PurchaserDAO purchaserDAO, PurchaserBLL purchaserBLL,
                                        ReforderDAO reforderDAO, ReforderBLL reforderBLL,
                                        SupplierDAO supplierDAO, SupplierBLL supplierBLL) throws Exception {
        new Start(connection , categoryDAO, categoryBLL, orderDAO, orderBLL, productDAO, productBLL, purchaserDAO, purchaserBLL, reforderDAO, reforderBLL, supplierDAO, supplierBLL);
    }
    public static void main(String[] args) {

        final Connection connection = ConnectionFactory.getConnection();

        final CategoryDAO categoryDAO = new CategoryDAO(connection);
        final CategoryBLL categoryBLL = new CategoryBLL(categoryDAO);

        final OrderDAO orderDAO = new OrderDAO(connection);
        final OrderBLL orderBLL = new OrderBLL(orderDAO);

        final ProductDAO productDAO = new ProductDAO(connection);
        final ProductBLL productBLL = new ProductBLL(productDAO);

        final PurchaserDAO purchaserDAO = new PurchaserDAO(connection);
        final PurchaserBLL purchaserBLL = new PurchaserBLL(purchaserDAO);

        final ReforderDAO reforderDAO = new ReforderDAO(connection);
        final ReforderBLL reforderBLL = new ReforderBLL(reforderDAO);

        final SupplierDAO supplierDAO = new SupplierDAO(connection);
        final SupplierBLL supplierBLL = new SupplierBLL(supplierDAO);


        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI(connection,categoryDAO, categoryBLL, orderDAO, orderBLL, productDAO, productBLL, purchaserDAO, purchaserBLL, reforderDAO, reforderBLL, supplierDAO, supplierBLL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });





    }

}
