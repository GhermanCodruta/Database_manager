
/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package presentation;

import bll.CategoryBLL;
import bll.ProductBLL;
import bll.SupplierBLL;
import dao.CategoryDAO;
import dao.ProductDAO;
import dao.SupplierDAO;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;



public class ViewProduct {
    private static final long serialVersionUID = 1L;
    private static int width = 1200, height = 700;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateAllButton;
    private JButton showAllButton;
    private JTable table;
    private JScrollPane jsp;
    FlowLayout flowLayout = new FlowLayout();
    private JTextField idProd;
    private JComboBox idsup;
    private JComboBox cat;
    private JTextField prod_name;
    private JTextField prod_price;
    private JTextField prod_stock;
    private JTextField prod_details;
    private ProductBLL productBLL;
    private ProductDAO productDAO;
    private CategoryBLL categoryBLL;
    private CategoryDAO categoryDAO;
    private SupplierBLL supplierBLL;
    private SupplierDAO supplierDAO;

    public ViewProduct(ProductBLL productBLL, ProductDAO productDAO, CategoryBLL categoryBLL, CategoryDAO categoryDAO, SupplierBLL supplierBLL, SupplierDAO supplierDAO) {
        this.productBLL = productBLL;
        this.productDAO = productDAO;
        this.categoryBLL = categoryBLL;
        this.categoryDAO = categoryDAO;
        this.supplierBLL = supplierBLL;
        this.supplierDAO = supplierDAO;
        createAndShowGUI();
    }

    public JTable createTable(){

        Field[] fields = Product.class.getDeclaredFields();
        ArrayList<Product> products = productBLL.getAll();
        Object[][] rowData = new Object[products.size()][7];
        for (int i = 0; i < products.size(); i++) {
            rowData[i][0] = products.get(i).getIdProduct();
            rowData[i][1] = products.get(i).getIdSupplier();
            rowData[i][2] = products.get(i).getIdCategory();
            rowData[i][3] = products.get(i).getProduct_stock();
            rowData[i][4] = products.get(i).getProduct_name();
            rowData[i][5] = products.get(i).getProduct_price();
            rowData[i][6] = products.get(i).getProduct_details();
        }

        Object[] columnNames= new Object[fields.length];
        for (int i =0; i< fields.length; i++){
            columnNames[i]=fields[i].getName();
        }
        JTable table = new JTable(rowData, columnNames);
        return table;
    }
    
    private void updateView(){
        createAndShowGUI();
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    public void createAndShowGUI() {
        //Create and set up the window.
        final JFrame frame=new JFrame("Product");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        

        table=createTable();

        table.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent me) {
                int row = table.rowAtPoint(me.getPoint());
                System.out.println("You clicked at row " + row);
                idProd.setText(table.getValueAt(row, 0).toString());
                idsup.setSelectedIndex(row);
                cat.setSelectedIndex(row);
                prod_name.setText(table.getValueAt(row, 4).toString());
                prod_price.setText(table.getValueAt(row, 5).toString());
                prod_stock.setText(table.getValueAt(row, 3).toString());
                prod_details.setText(table.getValueAt(row, 6).toString());
            }
        });


        jsp = new JScrollPane(table);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setBounds(300,100,800,500);
        jPanel.add(jsp);
        insertButton = new JButton("Insert");
        insertButton.setBounds(400,20,100,30);
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Product p = new Product(Integer.valueOf(idProd.getText()), Integer.valueOf(idsup.getSelectedItem().toString()), Integer.valueOf(cat.getSelectedItem().toString()), Integer.valueOf(prod_stock.getText()), prod_name.getText(), Integer.valueOf(prod_price.getText()), prod_details.getText());

                int insert = productBLL.insertProduct(p);
                System.out.println(insert);
                frame.dispose();
                updateView();
            }

        });
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(600,20,100,30);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                productBLL.deleteProduct(Integer.valueOf(idProd.getText()));
                frame.dispose();
                updateView();
            }
        });

        updateAllButton = new JButton("Update");
        updateAllButton.setBounds(800,20,100,30);
        updateAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                productBLL.updateAll(Integer.valueOf(idProd.getText()), Integer.valueOf(idsup.getSelectedItem().toString()), Integer.valueOf(cat.getSelectedItem().toString()), Integer.valueOf(prod_stock.getText()), prod_name.getText(), Integer.valueOf(prod_price.getText()), prod_details.getText());
                frame.dispose();
                updateView();
            }
        });




        JLabel idProduct = new JLabel("Product id");
        idProduct.setBounds(20,20,100,20);

        JLabel idSupplier = new JLabel("Supplier id");
        idSupplier.setBounds(20,100,100,20);

        JLabel idCategory = new JLabel("Category id");
        idCategory.setBounds(20,180,100,20);

        JLabel stock = new JLabel("Stock");
        stock.setBounds(20,340,100,20);

        JLabel name = new JLabel("Product name");
        name.setBounds(20,260,100,20);

        JLabel price = new JLabel("Product price");
        price.setBounds(20,420,100,20);

        JLabel details = new JLabel("Details");
        details.setBounds(20,500,100,20);

        ArrayList<Product> products = productBLL.getAll();
        Random r = new Random();
        int idd = r.nextInt(1000) + 1;
        boolean ok = false;
        for (int i = 0; i < products.size(); i++) {
            if (idd == products.get(i).getIdProduct()) {
                ok = true;
            }
            if (ok) {
                idd = r.nextInt(1000) + 1;
            }
        }

        idProd = new JTextField(String.valueOf(idd), 20);
        idProd.setBounds(20,60,180,30);
        String[] suppliers = new String[products.size()];
        for (int i = 0; i < products.size(); i++) {
            int id = products.get(i).getIdSupplier();
            suppliers[i] = String.valueOf(id);
        }

        idsup = new JComboBox(suppliers);
        idsup.setBounds(20,140,180,30);
        String[] category = new String[products.size()];
        for (int i = 0; i < products.size(); i++) {
            int id = products.get(i).getIdCategory();
            category[i] = String.valueOf(id);
        }

        cat = new JComboBox(category);
        cat.setBounds(20,220,180,30);

        prod_name = new JTextField("name", 20);
        prod_name.setBounds(20,300,180,30);

        prod_stock = new JTextField("1", 10);
        prod_stock.setBounds(20,380,180,30);

        prod_price = new JTextField("1", 10);
        prod_price.setBounds(20,460,180,30);

        prod_details = new JTextField("-", 30);
        prod_details.setBounds(20,540,180,30);
        jPanel.add(deleteButton);
        jPanel.add(insertButton);

        jPanel.add(updateAllButton);


        jPanel.add(idProduct);
        jPanel.add(idProd);
        jPanel.add(idSupplier);
        jPanel.add(idsup);
        jPanel.add(idCategory);
        jPanel.add(cat);
        jPanel.add(name);
        jPanel.add(prod_name);
        jPanel.add(stock);
        jPanel.add(prod_stock);
        jPanel.add(price);
        jPanel.add(prod_price);
        jPanel.add(details);
        jPanel.add(prod_details);


        frame.add(jPanel);
        //Display the window.
        frame.setSize(width, height);
        //frame.pack();
        frame.setVisible(true);
    }
    
}
