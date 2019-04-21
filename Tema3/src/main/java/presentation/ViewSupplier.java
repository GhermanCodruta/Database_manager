package presentation;

import bll.SupplierBLL;
import dao.SupplierDAO;
import model.Supplier;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class ViewSupplier {
    private SupplierDAO supplierDAO;
    private SupplierBLL supplierBLL;

    private static int width = 1200, height = 700;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateAllButton;

    private JTextField s_name;
    private JTextField s_email;
    private JTextField s_phone;
    private JTextField s_details;
    private JTextField s_id;
    private JTable table;
    private JScrollPane jsp;


    public ViewSupplier(SupplierDAO supplierDAO, SupplierBLL supplierBLL){
        this.supplierBLL=supplierBLL;
        this.supplierDAO=supplierDAO;
        createAndShowGUI();
    }

    public JTable createTable(){

        Field[] fields = Supplier.class.getDeclaredFields();
        ArrayList<Supplier> suppliers = supplierBLL.getAll();
        Object[][] rowData = new Object[suppliers.size()][5];
        for (int i = 0; i < suppliers.size(); i++) {
            rowData[i][0] = suppliers.get(i).getIdSupplier();
            rowData[i][1] = suppliers.get(i).getSupplier_name();
            rowData[i][2] = suppliers.get(i).getSupplier_email();
            rowData[i][3] = suppliers.get(i).getSupplier_phone();
            rowData[i][4] = suppliers.get(i).getSupplier_details();
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

    public void createAndShowGUI(){
        final JFrame frame = new JFrame("Supplier");
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        insertButton= new JButton("Insert");
        insertButton.setBounds(400,20,100,30);
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Supplier p = new Supplier(Integer.valueOf(s_id.getText()),s_name.getText(),s_email.getText(),s_phone.getText(),s_details.getText() );
                int insert = supplierBLL.insertSupplier(p);
                System.out.println(insert);
                frame.dispose();
                updateView();
            }

        });

        deleteButton=new JButton("Delete");
        deleteButton.setBounds(600,20,100,30);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                supplierBLL.deleteSupplier(Integer.valueOf(s_id.getText()));
                frame.dispose();
                updateView();
            }
        });

        updateAllButton=new JButton("Update");
        updateAllButton.setBounds(800,20,100,30);
        updateAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                supplierBLL.updateAll(Integer.valueOf( s_id.getText()),s_email.getText(), s_name.getText(), s_phone.getText(),s_details.getText());
                frame.dispose();
                updateView();
            }
        });


        jPanel.add(insertButton);
        jPanel.add(deleteButton);
        jPanel.add(updateAllButton);


        JLabel idSupplier = new JLabel("Supplier id");
        idSupplier.setBounds(20,20,100,20);

        JLabel pname = new JLabel("Supplier name");
        pname.setBounds(20,100,100,20);

        JLabel pemail = new JLabel("Supplier email");
        pemail.setBounds(20,180,100,20);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(20,260,100,20);



        JLabel details = new JLabel("Details");
        details.setBounds(20,420,100,20);

        ArrayList<Supplier> suppliers = supplierBLL.getAll();
        Random r = new Random();
        int idd = r.nextInt(1000) + 1;
        boolean ok = false;
        for (Supplier supplier : suppliers) {
            if (idd == supplier.getIdSupplier()) {
                ok = true;
            }
            if (ok) {
                idd = r.nextInt(1000) + 1;
            }
        }

        s_id = new JTextField(String.valueOf(idd), 20);
        s_id.setBounds(20,60,180,30);

        s_name = new JTextField("name", 20);
        s_name.setBounds(20,140,180,30);

        s_email = new JTextField("email@example.com",20);
        s_email.setBounds(20,220,180,30);

        s_phone = new JTextField("1234567890", 10);
        s_phone.setBounds(20,300,180,30);


        s_details = new JTextField("-", 30);
        s_details.setBounds(20,460,180,30);



        jPanel.add(idSupplier);
        jPanel.add(s_id);
        jPanel.add(pname);
        jPanel.add(s_name);
        jPanel.add(pemail);
        jPanel.add(s_email);
        jPanel.add(phone);
        jPanel.add(s_phone);

        jPanel.add(details);
        jPanel.add(s_details);




        table=createTable();
        table.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent me) {
                int row = table.rowAtPoint(me.getPoint());
                System.out.println("You clicked at row " + row);
                s_id.setText(table.getValueAt(row, 0).toString());
                s_name.setText(table.getValueAt(row,1).toString());
                s_email.setText(table.getValueAt(row,2).toString());
                s_phone.setText(table.getValueAt(row,3).toString());
                s_details.setText(table.getValueAt(row, 4).toString());
            }
        });

        jsp = new JScrollPane(table);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setBounds(300,100,800,500);

        jPanel.add(jsp);



        frame.add(jPanel);
        frame.setSize(width, height);
        //frame.pack();
        frame.setVisible(true);
    }
}
