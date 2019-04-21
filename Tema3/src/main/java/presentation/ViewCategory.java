package presentation;

import bll.CategoryBLL;
import dao.CategoryDAO;
import model.Category;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class ViewCategory {
   
        private CategoryDAO categoryDAO;
        private CategoryBLL categoryBLL;

        private static int width = 1200, height = 700;
        private JButton insertButton;
        private JButton deleteButton;
        private JButton updateAllButton;

        private JTextField c_name;
        private JTextField c_details;
        private JTextField c_id;
        private JTable table;
        private JScrollPane jsp;


        public ViewCategory(CategoryDAO categoryDAO, CategoryBLL categoryBLL){
            this.categoryBLL=categoryBLL;
            this.categoryDAO=categoryDAO;
            createAndShowGUI();
        }

        public JTable createTable(){

            Field[] fields = Category.class.getDeclaredFields();
            ArrayList<Category> categorys = categoryBLL.getAll();
            Object[][] rowData = new Object[categorys.size()][3];
            for (int i = 0; i < categorys.size(); i++) {
                rowData[i][0] = categorys.get(i).getIdCategory();
                rowData[i][1] = categorys.get(i).getCategory_name();
                rowData[i][2] = categorys.get(i).getCategory_details();
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
            final JFrame frame = new JFrame("Category");
            JPanel jPanel = new JPanel();
            jPanel.setLayout(null);
            insertButton= new JButton("Insert");
            insertButton.setBounds(400,20,100,30);
            insertButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Category p = new Category(Integer.valueOf(c_id.getText()),c_name.getText(),c_details.getText() );
                    int insert = categoryBLL.insertCategory(p);
                    System.out.println(insert);
                    frame.dispose();
                    updateView();
                }

            });

            deleteButton=new JButton("Delete");
            deleteButton.setBounds(600,20,100,30);
            deleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    categoryBLL.deleteCategory(Integer.valueOf(c_id.getText()));
                    frame.dispose();
                    updateView();
                }
            });

            updateAllButton=new JButton("Update");
            updateAllButton.setBounds(800,20,100,30);
            updateAllButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    categoryBLL.updateAll(Integer.valueOf(c_id.getText()),c_name.getText(),c_details.getText());
                    frame.dispose();
                    updateView();
                }
            });


            jPanel.add(insertButton);
            jPanel.add(deleteButton);
            jPanel.add(updateAllButton);


            JLabel idCategory = new JLabel("Category id");
            idCategory.setBounds(20,20,100,20);

            JLabel cname = new JLabel("Category name");
            cname.setBounds(20,100,100,20);

            JLabel details = new JLabel("Details");
            details.setBounds(20,420,100,20);

            ArrayList<Category> categorys = categoryBLL.getAll();
            Random r = new Random();
            int idd = r.nextInt(1000) + 1;
            boolean ok = false;
            for (Category category : categorys) {
                if (idd == category.getIdCategory()) {
                    ok = true;
                }
                if (ok) {
                    idd = r.nextInt(1000) + 1;
                }
            }

            c_id = new JTextField(String.valueOf(idd), 20);
            c_id.setBounds(20,60,180,30);

            c_name = new JTextField("name", 20);
            c_name.setBounds(20,140,180,30);

            c_details = new JTextField("-", 30);
            c_details.setBounds(20,460,180,30);



            jPanel.add(idCategory);
            jPanel.add(c_id);
            jPanel.add(cname);
            jPanel.add(c_name);
            jPanel.add(details);
            jPanel.add(c_details);




            table=createTable();
            table.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent me) {
                    int row = table.rowAtPoint(me.getPoint());
                    System.out.println("You clicked at row " + row);
                    c_id.setText(table.getValueAt(row, 0).toString());
                    c_name.setText(table.getValueAt(row,1).toString());
                    c_details.setText(table.getValueAt(row, 2).toString());
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
