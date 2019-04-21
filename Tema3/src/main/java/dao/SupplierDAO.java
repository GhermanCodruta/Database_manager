package dao;

import model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SupplierDAO extends AbstractDAO<Supplier>{
    protected static final Logger LOGGER = Logger.getLogger(Supplier.class.getName());
    private Connection connection;

    public SupplierDAO (Connection connection){
        this.connection= connection;
    }

    public int insert(Supplier supplier){

        PreparedStatement insertStatement=null;
        int insertedID=-1;

        try {
            insertStatement=connection.prepareStatement(createInsertQuery(), Statement.RETURN_GENERATED_KEYS);

            insertStatement.setInt(1,supplier.getIdSupplier());
            insertStatement.setString(2,supplier.getSupplier_email());
            insertStatement.setString(3,supplier.getSupplier_name());
            insertStatement.setString(4,supplier.getSupplier_phone());
            insertStatement.setString(5,supplier.getSupplier_details());
            System.out.println(insertStatement.execute());

            ResultSet rs = insertStatement.getGeneratedKeys();
            System.out.println(rs);
            if (rs.next()) {
                insertedID = rs.getInt(1);
            }
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"SupplierDAO:insert "+e.getMessage());
        }
        return insertedID;
    }

    public void update(int newId, String newSupplier_name, String newSupplier_email, String newSupplier_phone, String newSupplier_details) {
        PreparedStatement findStatement=null;

        try {
            String statement= createUpdateQuery();
            findStatement=connection.prepareStatement(statement);
            findStatement.setInt(5, newId);
            findStatement.setString(1,newSupplier_email);
            findStatement.setString(2, newSupplier_name);
            findStatement.setString(3, newSupplier_phone);
            findStatement.setString(4, newSupplier_details);
            findStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "SupplierDAO:update all " + e.getMessage());
        }

    }

    public void delete(int idSupplier){
        PreparedStatement findStatement=null;
        try {
            String deleteStatement=createDeleteQuery();
            findStatement=connection.prepareStatement(deleteStatement);
            findStatement.setInt(1,idSupplier);
            System.out.println(findStatement);
            findStatement.execute();
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"SupplierDAO: delete "+e.getMessage());
        }
    }

    public ArrayList<Supplier> getAll(){
        ArrayList<Supplier> suppliers=new ArrayList<Supplier>();
        PreparedStatement findStatement=null;

        ResultSet rs=null;

        try {
            String statement=createSelectAllQuery();
            findStatement=connection.prepareStatement(statement);
            rs=findStatement.executeQuery();
            while(rs.next()){
                int idSupplier=rs.getInt("idSupplier");
                String supplier_name=rs.getString("supplier_name");
                String supplier_email=rs.getString("supplier_email");
                String supplier_phone=rs.getString("supplier_phone");
                String supplier_details=rs.getString("supplier_details");

                suppliers.add(new Supplier(idSupplier,supplier_email,supplier_name,supplier_phone,supplier_details));
            }
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"SupplierDAO:findById "+e.getMessage());
        }
        return suppliers;
    }

}
