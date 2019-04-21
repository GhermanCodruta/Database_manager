package presentation;

import bll.ReforderBLL;
import dao.ReforderDAO;
import model.Reforder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.lang.reflect.Field;
import java.util.ArrayList;


public class ViewReforder {
    private ReforderDAO reforderDAO;
    private ReforderBLL reforderBLL;

    private static int width = 1200, height = 700;

    private JButton updateAllButton;
    private JTable table;
    private JScrollPane jsp;


    public ViewReforder(ReforderDAO reforderDAO, ReforderBLL reforderBLL) {
        this.reforderBLL = reforderBLL;
        this.reforderDAO = reforderDAO;
        createAndShowGUI();
    }

    public JTable createTable() {

        Field[] fields = Reforder.class.getDeclaredFields();
        ArrayList<Reforder> reforders = reforderBLL.getAll();
        Object[][] rowData = new Object[reforders.size()][6];
        for (int i = 0; i < reforders.size(); i++) {
            rowData[i][0] = reforders.get(i).getIdRefOrder();
            rowData[i][1] = reforders.get(i).getIdProduct();
            rowData[i][2] = reforders.get(i).getQuantity();
            rowData[i][3] = reforders.get(i).getTotal_price();
            rowData[i][4] = reforders.get(i).getProduct_price();
            rowData[i][5] = reforders.get(i).getOrder_details();
        }

        Object[] columnNames = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            columnNames[i] = fields[i].getName();
        }
        JTable table = new JTable(rowData, columnNames);
        return table;
    }

    private void updateView() {
        createAndShowGUI();
    }

    public void createAndShowGUI() {
        final JFrame frame = new JFrame("Reforder");
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);


        updateAllButton = new JButton("Update");
        updateAllButton.setBounds(800, 20, 100, 30);
        updateAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // reforderBLL.updateAll(Integer.valueOf(ref_id.getText()),p_name.getText(),p_email.getText(),p_phone.getText(),p_address.getText(),ref_details.getText());
                frame.dispose();
                updateView();
            }
        });


        jPanel.add(updateAllButton);

        table = createTable();
        jsp = new JScrollPane(table);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setBounds(100, 100, 800, 500);

        jPanel.add(jsp);
        frame.add(jPanel);
        frame.setSize(width, height);
        frame.setVisible(true);
    }
}
