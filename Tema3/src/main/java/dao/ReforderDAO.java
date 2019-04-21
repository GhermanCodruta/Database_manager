package dao;

import model.Reforder;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReforderDAO extends AbstractDAO<Reforder>{
    protected static final Logger LOGGER = Logger.getLogger(Reforder.class.getName());
    private Connection connection;

    public ReforderDAO(Connection connection){
        this.connection= connection;
    }

    public int insert(Reforder reforder){

        PreparedStatement insertStatement=null;
        int insertedID=-1;

        try {
            insertStatement=connection.prepareStatement(createInsertQuery(), Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1,reforder.getIdRefOrder());
            insertStatement.setInt(2,reforder.getIdProduct());
            insertStatement.setInt(3,reforder.getQuantity());
            insertStatement.setInt(4,reforder.getTotal_price());
            insertStatement.setInt(5,reforder.getProduct_price());
            insertStatement.setString(6,reforder.getOrder_details());

            System.out.println(insertStatement.toString());
            insertStatement.execute();

            ResultSet rs = insertStatement.getGeneratedKeys();
            System.out.println(rs);
            if (rs.next()) {
                insertedID = rs.getInt(1);
            }
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"ReforderDAO:insert "+e.getMessage());
        }
        return insertedID;
    }

    public void update(int newIdReforder, int newIdProduct, int newQuantity, int newTotal_price, int newProduct_price,String newReforder_details) {
        PreparedStatement findStatement=null;

        try {
            String statement= createUpdateQuery();
            findStatement=connection.prepareStatement(statement);
            findStatement.setInt(1, newIdReforder);
            findStatement.setInt(2, newIdProduct);
            findStatement.setInt(3, newQuantity);
            findStatement.setInt(4, newTotal_price);
            findStatement.setInt(5, newProduct_price);
            findStatement.setString(6, newReforder_details);
            findStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ReforderDAO:update all " + e.getMessage());
        }

    }

    public void delete(int idReforder){
        PreparedStatement findStatement=null;
        try {
            String deleteStatement=createDeleteQuery();
            findStatement=connection.prepareStatement(deleteStatement);
            findStatement.setInt(1,idReforder);
            System.out.println(findStatement);
            findStatement.execute();
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"ReforderDAO: delete "+e.getMessage());
        }
    }

    public ArrayList<Reforder> getAll(){
        ArrayList<Reforder> reforders=new ArrayList<Reforder>();

        PreparedStatement findStatement=null;

        ResultSet rs=null;

        try {
            String statement=createSelectAllQuery();
            System.out.println(statement);
            findStatement=connection.prepareStatement(statement);
            rs=findStatement.executeQuery();
            while(rs.next()){
                int idReforder=rs.getInt("idReforder");
                int idProduct=rs.getInt("idProduct");
                int quantity=rs.getInt("quantity");
                int total_price=rs.getInt("total_price");
                int product_price=rs.getInt("product_price");
                String reforder_details=rs.getString("order_details");

                Reforder ro=new Reforder(idReforder,idProduct,quantity,total_price,product_price,reforder_details);
                reforders.add(ro);
            }
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"ReforderDAO:findById "+e.getMessage());
        }
        return reforders;
    }
}
