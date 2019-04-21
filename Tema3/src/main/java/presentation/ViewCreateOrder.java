package presentation;

import bll.OrderBLL;
import bll.ProductBLL;
import bll.PurchaserBLL;
import bll.ReforderBLL;
import dao.OrderDAO;
import dao.ProductDAO;
import dao.PurchaserDAO;
import dao.ReforderDAO;
import model.Orders;
import model.Product;
import model.Purchaser;
import model.Reforder;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class ViewCreateOrder {
    private static int width = 900, height = 700;
    private PurchaserBLL purchaserBLL;
    private PurchaserDAO purchaserDAO;
    private ProductBLL productBLL;
    private ProductDAO productDAO;
    private OrderBLL orderBLL;
    private OrderDAO orderDAO;
    private ReforderBLL reforderBLL;
    private ReforderDAO reforderDAO;
    private JTextField idOrder;
    private JComboBox idPurchaser;
    private JComboBox idProduct;
    private JTextField productQuantity;
    private JTextField totalPrice;
    private JTextField orderDetails;
    private JButton orderButton;
    private JButton calculateTotal;
    private JButton verifyStock;
    private JTextField productname;

    public ViewCreateOrder(PurchaserBLL purchaserBLL, PurchaserDAO purchaserDAO, ProductBLL productBLL, ProductDAO productDAO, OrderBLL orderBLL, OrderDAO orderDAO, ReforderBLL reforderBLL, ReforderDAO reforderDAO) {
        this.purchaserBLL = purchaserBLL;
        this.purchaserDAO = purchaserDAO;
        this.productBLL = productBLL;
        this.productDAO = productDAO;
        this.orderBLL = orderBLL;
        this.orderDAO = orderDAO;
        this.reforderBLL = reforderBLL;
        this.reforderDAO = reforderDAO;
        createAndShowGUI();
    }
    public void createAndShowGUI(){
        final JFrame frame=new JFrame("Place order");
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);

        orderButton=new JButton("ORDER!");
        orderButton.setBounds(500,700,100,30);
        //orderButton.addActionListener();
        jPanel.add(orderButton);
        orderButton.setEnabled(false);
        orderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int pr_id=idProduct.getSelectedIndex();
                int pur_id=idPurchaser.getSelectedIndex();
                Purchaser pur=purchaserBLL.getAll().get(pur_id);

                System.out.println(pr_id);
                Product p= productBLL.getAll().get(pr_id);
                ArrayList<Reforder> reforders = reforderBLL.getAll();
                Random r = new Random();
                int idd = r.nextInt(1000) + 1;
                boolean ok = false;
                for (int i = 0; i < reforders.size(); i++) {
                    if (idd == reforders.get(i).getIdRefOrder()) {
                        ok = true;
                    }
                    if (ok) {
                        idd = r.nextInt(1000) + 1;
                    }
                }
                Reforder reforder=new Reforder(idd,
                        p.getIdProduct(),
                        Integer.valueOf( productQuantity.getText()),
                        Integer.valueOf( totalPrice.getText()),
                        p.getProduct_price(),
                        String.valueOf( orderDetails.getText()));
                Orders orders =new Orders(Integer.valueOf( idOrder.getText()),
                        pur.getIdPurchaser(),
                        idd,
                        "ordered");
                System.out.println("orders: "+ orders.getIdOrder()+" "+ orders.getIdPurchaser()+" "+ orders.getIdRefOrder()+ " "+ orders.getOrder_status());
                System.out.println("reforder "+reforder.getIdRefOrder()+" "+reforder.getIdProduct()+" "+reforder.getQuantity()+" "+reforder.getTotal_price()+" "+reforder.getOrder_details()+ " "+reforder.getProduct_price());
                System.out.println( reforderBLL.insertReforder(reforder));
                System.out.println( orderBLL.insertOrder(orders));
                productBLL.updateAll(p.getIdProduct(),p.getIdSupplier(),p.getIdCategory(), p.getProduct_stock()-Integer.valueOf( productQuantity.getText()),p.getProduct_name(),p.getProduct_price(),p.getProduct_details());
                String data="Order with number "+idOrder.getText()+" has been made for the client: \r\n    Client id: "+pur.getIdPurchaser()+"\r\n    Client name: "+
                        pur.getPurchaser_name()+"\r\n    Client email: "+pur.getPurchaser_email()+"\r\n    Client address: "+pur.getPurchaser_address()+"\r\n    Clent phone: "
                        +pur.getPurchaser_phone()+"\r\n-------------------------------------------------------------------------------\r\nProduct: "+p.getProduct_name()+"\r\nProduct price: "+p.getProduct_price()+".............Product Quantity: "
                        +productQuantity.getText()+".............Total price: " +totalPrice.getText()+"\r\n-------------------------------------------------------------------------------\r\nDate: "+(new Date()).toString()+"\r\n\r\n\r\nSeller Signature         " +
                        "                                            Client Signature";
                FileOutputStream out=null;
                File f=new File("D:\\testfile.txt");
                try {
                    out = new FileOutputStream(f);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                try {
                    out.write(data.getBytes());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        verifyStock=new JButton("Verify Stock");
        verifyStock.setBounds(20, 380,200,30);
        jPanel.add(verifyStock);
        verifyStock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int pr_id=idProduct.getSelectedIndex();
                System.out.println(pr_id);
                Product p= productBLL.getAll().get(pr_id);
                System.out.println(p.getProduct_name());
                productname.setText(p.getProduct_name());
                if(Integer.valueOf(productQuantity.getText())<1){
                    JOptionPane.showMessageDialog(frame,
                            "The minimum quantity for the selected product is 1",
                            "Stock error",
                            JOptionPane.ERROR_MESSAGE);
                    productQuantity.setText("1");
                    calculateTotal.setEnabled(false);
                    orderButton.setEnabled(false);
                }
                if (p.getProduct_stock()<Integer.valueOf(productQuantity.getText())){
                    JOptionPane.showMessageDialog(frame,
                            "The maximum quantity for the selected product is "+p.getProduct_stock(),
                            "Stock error",
                            JOptionPane.ERROR_MESSAGE);
                    calculateTotal.setEnabled(false);
                    orderButton.setEnabled(false);
                }else{
                    JOptionPane.showMessageDialog(frame,
                            "There are enough products.");
                    calculateTotal.setEnabled(true);
                    orderButton.setEnabled(false);
                }
            }
        });

        calculateTotal=new JButton("Calculate total");
        calculateTotal.setBounds(20,500,200,30);
        jPanel.add(calculateTotal);
        calculateTotal.setEnabled(false);
        calculateTotal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int pr_id=idProduct.getSelectedIndex();
                System.out.println(pr_id);
                Product p= productBLL.getAll().get(pr_id);
                System.out.println(p.getProduct_name());
                totalPrice.setText(String.valueOf(p.getProduct_price()*  Integer.valueOf( productQuantity.getText())));
                orderButton.setEnabled(true);
            }

        });

        JLabel title=new JLabel("Create order form");
        title.setBounds(280,0,200,60);
        title.setFont (new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 20));
        jPanel.add(title);

        ArrayList<Orders> orders = orderBLL.getAll();
        Random r = new Random();
        int idd = r.nextInt(1000) + 1;
        boolean ok = false;
        for (int i = 0; i < orders.size(); i++) {
            if (idd == orders.get(i).getIdOrder()) {
                ok = true;
            }
            if (ok) {
                idd = r.nextInt(1000) + 1;
            }
        }

        JLabel idord=new JLabel("Orders id");
        idord.setBounds(20,20,100,20);

        idOrder=new JTextField(String.valueOf(idd));
        idOrder.setBounds(20,50,100,30);
        idOrder.setEnabled(false);

        productname=new JTextField("Product name");


        JLabel idpur=new JLabel("Purchaser id");
        idpur.setBounds(20,100,100,20);

        String[]purchasers=new String[purchaserBLL.getAll().size()];
        for(int i=0; i<purchaserBLL.getAll().size(); i++){
            int id=purchaserBLL.getAll().get(i).getIdPurchaser();
            purchasers[i]=String.valueOf(id);
        }
        idPurchaser=new JComboBox(purchasers);
        idPurchaser.setBounds(20,130,100,30);


        JLabel prodname=new JLabel("Product id");
        prodname.setBounds(20,180,100,20);


        String[]products=new String[productBLL.getAll().size()];
        for(int i=0; i<productBLL.getAll().size(); i++){
            int id=productBLL.getAll().get(i).getIdProduct();
            products[i]=String.valueOf(id);
        }
        idProduct=new JComboBox(products);
        idProduct.setBounds(20,210,100,30);
        productname.setBounds(150,210,100,30);

        JLabel prodquantity=new JLabel("Choose quantity");
        prodquantity.setBounds(20,290,100,20);

        productQuantity=new JTextField();
        productQuantity.setBounds(20,320, 100,30);


        JLabel totalprice=new JLabel("Total to pay:");
        totalprice.setBounds(20,560,100,20);

        totalPrice=new JTextField();
        totalPrice.setBounds(130, 560, 70, 30);


        JLabel orderdetails=new JLabel("Orders details");
        orderdetails.setBounds(20, 600 , 100,20);

        orderDetails=new JTextField();
        orderDetails.setBounds(130,600, 100, 30);




        jPanel.add(idord);
        jPanel.add(idpur);

        jPanel.add(prodname);


        jPanel.add(prodquantity);
        jPanel.add(totalprice);
        jPanel.add(orderdetails);

        jPanel.add(idOrder);
        jPanel.add(idPurchaser);
        jPanel.add(productname);
        jPanel.add(idProduct);


        jPanel.add(productQuantity);
        jPanel.add(totalPrice);
        jPanel.add(orderDetails);

        frame.add(jPanel);
        //Display the window.
        frame.setSize(height,width);
        //frame.pack();
        frame.setVisible(true);
    }
}

