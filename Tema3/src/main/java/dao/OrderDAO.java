package dao;

import model.Orders;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO extends AbstractDAO<Orders> {
    protected static final Logger LOGGER = Logger.getLogger(Orders.class.getName());
    private Connection connection;

    public OrderDAO(Connection connection){
        this.connection= connection;
    }

    public int insert(Orders orders){

        PreparedStatement insertStatement=null;
        int insertedID=-1;

        try {
            insertStatement=connection.prepareStatement(createInsertQuery(),Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, orders.getIdOrder());
            insertStatement.setInt(2, orders.getIdPurchaser());
            insertStatement.setInt(3, orders.getIdRefOrder());
            insertStatement.setString(4, orders.getOrder_status());

            System.out.println(insertStatement.toString());
            insertStatement.execute();

            ResultSet rs = insertStatement.getGeneratedKeys();
            System.out.println(rs);
            if (rs.next()) {
                insertedID = rs.getInt(1);
            }
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"OrderDAO:insert "+e.getMessage());
        }
        return insertedID;
    }

    public void update(int newIdOrder, int newIdPurchaser, int newIdReforder, String newOrder_status) {
        PreparedStatement findStatement=null;

        try {
            String statement= createUpdateQuery();
            findStatement=connection.prepareStatement(statement);
            findStatement.setInt(4, newIdOrder);
            findStatement.setInt(1, newIdPurchaser);
            findStatement.setInt(2,newIdReforder);
            findStatement.setString(3, newOrder_status);
            System.out.println(findStatement);
            findStatement.execute();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:update all " + e.getMessage());
        }

    }


    public void delete(int idOrder){
        PreparedStatement findStatement=null;
        try {
            String deleteStatement=createDeleteQuery();
            findStatement=connection.prepareStatement(deleteStatement);
            findStatement.setInt(1,idOrder);
            System.out.println(findStatement);
            findStatement.execute();
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"OrderDAO: delete "+e.getMessage());
        }
    }


    public ArrayList<Orders> getAll(){
        ArrayList<Orders> orders=new ArrayList<Orders>();

        PreparedStatement findStatement=null;

        ResultSet rs=null;

        try {
            String statement=createSelectAllQuery();
            System.out.println(statement);
            findStatement=connection.prepareStatement(statement);
            rs=findStatement.executeQuery();
            while(rs.next()){
                int idOrder=rs.getInt("idOrder");
                int idPurchaser=rs.getInt("idPurchaser");
                int idRefOrder=rs.getInt("idRefOrder");
                String order_status=rs.getString("order_status");

                Orders ro=new Orders(idOrder,idPurchaser,idRefOrder,order_status);
                orders.add(ro);
            }
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"OrderDAO:findById "+e.getMessage());
        }
        return orders;
    }
}
