package presentation;

import bll.OrderBLL;
import dao.OrderDAO;
import model.Orders;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;


public class ViewOrder {
    private OrderDAO orderDAO;
    private OrderBLL orderBLL;

    private static int width = 1200, height = 700;

    private JButton updateAllButton;
    private JTable table;
    private JTextField orderstats;
    private JScrollPane jsp;


    public ViewOrder(OrderDAO orderDAO, OrderBLL orderBLL) {
        this.orderBLL = orderBLL;
        this.orderDAO = orderDAO;
        createAndShowGUI();
    }

    public JTable createTable() {

        Field[] fields = Orders.class.getDeclaredFields();
        ArrayList<Orders> orders = orderBLL.getAll();
        Object[][] rowData = new Object[orders.size()][4];
        for (int i = 0; i < orders.size(); i++) {
            rowData[i][0] = orders.get(i).getIdOrder();
            rowData[i][1] = orders.get(i).getIdPurchaser();
            rowData[i][2] = orders.get(i).getIdRefOrder();
            rowData[i][3] = orders.get(i).getOrder_status();
        }

        Object[] columnNames = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            columnNames[i] = fields[i].getName();
            System.out.println(columnNames[i]);
        }
        JTable table1 = new JTable(rowData, columnNames);
        return table1;
    }

    private void updateView() {
        createAndShowGUI();
    }

    public void createAndShowGUI() {
        final JFrame frame = new JFrame("Orders");
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);


        updateAllButton = new JButton("Update");
        updateAllButton.setBounds(800, 20, 100, 30);
        updateAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                orderBLL.updateAll(Integer.valueOf(table.getValueAt(table.getSelectedRow(),0).toString()),
                        Integer.valueOf(table.getValueAt(table.getSelectedRow(),1).toString()),
                        Integer.valueOf(table.getValueAt(table.getSelectedRow(),2).toString()),
                        orderstats.getText());
                System.out.println(Integer.valueOf(table.getValueAt(table.getSelectedRow(),0).toString()));
                System.out.println(Integer.valueOf(table.getValueAt(table.getSelectedRow(),1).toString()));
                System.out.println(Integer.valueOf(table.getValueAt(table.getSelectedRow(),2).toString()));
                System.out.println(String.valueOf(table.getValueAt(table.getSelectedRow(),3).toString()));

                frame.dispose();
                updateView();
            }
        });

        JLabel stats=new JLabel("New Status");
        stats.setBounds(930, 20, 100, 20);
        orderstats=new JTextField();
        orderstats.setBounds(930, 60, 100, 30);

        jPanel.add(stats);
        jPanel.add(orderstats);
        jPanel.add(updateAllButton);

        table = createTable();
        table.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent me) {
                int row = table.rowAtPoint(me.getPoint());
                System.out.println("You clicked at row " + row);
                orderstats.setText(table.getValueAt(row, 3).toString());

            }
        });
        jsp = new JScrollPane(table);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setBounds(100, 100, 800, 500);

        jPanel.add(jsp);
        frame.add(jPanel);
        frame.setSize(width, height);
        frame.setVisible(true);
    }
}
