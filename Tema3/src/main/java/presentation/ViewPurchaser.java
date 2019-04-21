package presentation;

import bll.PurchaserBLL;
import dao.PurchaserDAO;
import model.Purchaser;
import model.Purchaser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class ViewPurchaser {
    private PurchaserDAO purchaserDAO;
    private PurchaserBLL purchaserBLL;

    private static int width = 1200, height = 700;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateAllButton;

    private JTextField p_name;
    private JTextField p_email;
    private JTextField p_phone;
    private JTextField p_address;
    private JTextField p_details;
    private JTextField p_id;
    private JTable table;
    private JScrollPane jsp;


    public ViewPurchaser(PurchaserDAO purchaserDAO, PurchaserBLL purchaserBLL){
        this.purchaserBLL=purchaserBLL;
        this.purchaserDAO=purchaserDAO;
        createAndShowGUI();
    }

    public JTable createTable(){

        Field[] fields = Purchaser.class.getDeclaredFields();
        ArrayList<Purchaser> purchasers = purchaserBLL.getAll();
        Object[][] rowData = new Object[purchasers.size()][6];
        for (int i = 0; i < purchasers.size(); i++) {
            rowData[i][0] = purchasers.get(i).getIdPurchaser();
            rowData[i][1] = purchasers.get(i).getPurchaser_name();
            rowData[i][2] = purchasers.get(i).getPurchaser_email();
            rowData[i][3] = purchasers.get(i).getPurchaser_phone();
            rowData[i][4] = purchasers.get(i).getPurchaser_address();
            rowData[i][5] = purchasers.get(i).getPurchaser_details();
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
        final JFrame frame = new JFrame("Purchaser");
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        insertButton= new JButton("Insert");
        insertButton.setBounds(400,20,100,30);
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Purchaser p = new Purchaser(Integer.valueOf(p_id.getText()),p_name.getText(),p_email.getText(),p_phone.getText(),p_address.getText(),p_details.getText() );
                int insert = purchaserBLL.insertPurchaser(p);
                System.out.println(insert);
                frame.dispose();
                updateView();
            }

        });

        deleteButton=new JButton("Delete");
        deleteButton.setBounds(600,20,100,30);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                purchaserBLL.deletePurchaser(Integer.valueOf(p_id.getText()));
                frame.dispose();
                updateView();

            }
        });

        updateAllButton=new JButton("Update");
        updateAllButton.setBounds(800,20,100,30);
        updateAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                purchaserBLL.updateAll(Integer.valueOf(p_id.getText()),p_name.getText(),p_email.getText(),p_phone.getText(),p_address.getText(),p_details.getText());
                frame.dispose();
                updateView();
            }
        });


        jPanel.add(insertButton);
        jPanel.add(deleteButton);
        jPanel.add(updateAllButton);


        JLabel idPurchaser = new JLabel("Purchaser id");
        idPurchaser.setBounds(20,20,100,20);

        JLabel pname = new JLabel("Purchaser name");
        pname.setBounds(20,100,100,20);

        JLabel pemail = new JLabel("Purchaser email");
        pemail.setBounds(20,180,100,20);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(20,260,100,20);

        JLabel address = new JLabel("Address");
        address.setBounds(20,340,100,20);

        JLabel details = new JLabel("Details");
        details.setBounds(20,420,100,20);

        ArrayList<Purchaser> purchasers = purchaserBLL.getAll();
        Random r = new Random();
        int idd = r.nextInt(1000) + 1;
        boolean ok = false;
        for (Purchaser purchaser : purchasers) {
            if (idd == purchaser.getIdPurchaser()) {
                ok = true;
            }
            if (ok) {
                idd = r.nextInt(1000) + 1;
            }
        }

        p_id = new JTextField(String.valueOf(idd), 20);
        p_id.setBounds(20,60,180,30);

        p_name = new JTextField("name", 20);
        p_name.setBounds(20,140,180,30);

        p_email = new JTextField("email@example.com",20);
        p_email.setBounds(20,220,180,30);

        p_phone = new JTextField("1234567890", 10);
        p_phone.setBounds(20,300,180,30);

        p_address= new JTextField("Mem Street");
        p_address.setBounds(20,380,180,30);

        p_details = new JTextField("-", 30);
        p_details.setBounds(20,460,180,30);



        jPanel.add(idPurchaser);
        jPanel.add(p_id);
        jPanel.add(pname);
        jPanel.add(p_name);
        jPanel.add(pemail);
        jPanel.add(p_email);
        jPanel.add(phone);
        jPanel.add(p_phone);
        jPanel.add(address);
        jPanel.add(p_address);
        jPanel.add(details);
        jPanel.add(p_details);




        table=createTable();
        table.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent me) {
                int row = table.rowAtPoint(me.getPoint());
                System.out.println("You clicked at row " + row);
                p_id.setText(table.getValueAt(row, 0).toString());
                p_name.setText(table.getValueAt(row,1).toString());
                p_email.setText(table.getValueAt(row,2).toString());
                p_phone.setText(table.getValueAt(row,3).toString());
                p_address.setText(table.getValueAt(row,4).toString());
                p_details.setText(table.getValueAt(row, 5).toString());
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
