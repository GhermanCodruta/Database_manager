package dao;

import model.Purchaser;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PurchaserDAO extends AbstractDAO<Purchaser>{
    protected static final Logger LOGGER = Logger.getLogger(Purchaser.class.getName());
    private Connection connection;

    public PurchaserDAO (Connection connection){
        this.connection= connection;
    }

    public int insert(Purchaser purchaser){

        PreparedStatement insertStatement=null;
        int insertedID=-1;

        try {
            insertStatement=connection.prepareStatement(createInsertQuery(), Statement.RETURN_GENERATED_KEYS);

            insertStatement.setInt(1,purchaser.getIdPurchaser());
            insertStatement.setString(2,purchaser.getPurchaser_name());
            insertStatement.setString(3,purchaser.getPurchaser_email());
            insertStatement.setString(4,purchaser.getPurchaser_phone());
            insertStatement.setString(5,purchaser.getPurchaser_address());
            insertStatement.setString(6,purchaser.getPurchaser_details());
            System.out.println(insertStatement.execute());

            ResultSet rs = insertStatement.getGeneratedKeys();
            System.out.println(rs);
            if (rs.next()) {
                insertedID = rs.getInt(1);
            }
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"PurchaserDAO:insert "+e.getMessage());
        }
        return insertedID;
    }

    public void update(int newId, String newPurchaser_name, String newPurchaser_email, String newPurchaser_phone, String newPurchaser_address, String newPurchaser_details) {
        PreparedStatement findStatement=null;

        try {
            String statement= createUpdateQuery();
            findStatement=connection.prepareStatement(statement);
            findStatement.setInt(6, newId);
            findStatement.setString(1, newPurchaser_name);
            findStatement.setString(2,newPurchaser_email);
            findStatement.setString(3, newPurchaser_phone);
            findStatement.setString(4, newPurchaser_address);
            findStatement.setString(5, newPurchaser_details);
            findStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "PurchaserDAO:update all " + e.getMessage());
        }

    }

    public void delete(int idPurchaser){
        PreparedStatement findStatement=null;
        try {
            String deleteStatement=createDeleteQuery();
            findStatement=connection.prepareStatement(deleteStatement);
            findStatement.setInt(1,idPurchaser);
            System.out.println(findStatement);
            findStatement.execute();
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"PurchaserDAO: delete "+e.getMessage());
        }
    }

    public ArrayList<Purchaser> getAll(){
        ArrayList<Purchaser> purchasers=new ArrayList<Purchaser>();
        PreparedStatement findStatement=null;

        ResultSet rs=null;

        try {
            String statement=createSelectAllQuery();
            findStatement=connection.prepareStatement(statement);
            rs=findStatement.executeQuery();
            while(rs.next()){
                int idPurchaser=rs.getInt("idPurchaser");
                String purchaser_name=rs.getString("purchaser_name");
                String purchaser_email=rs.getString("purchaser_email");
                String purchaser_phone=rs.getString("purchaser_phone");
                String purchaser_address=rs.getString("purchaser_address");
                String purchaser_details=rs.getString("purchaser_details");

                purchasers.add(new Purchaser(idPurchaser,purchaser_name,purchaser_email,purchaser_phone,
                        purchaser_address,purchaser_details));
            }
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"PurchaserDAO:findById "+e.getMessage());
        }
        return purchasers;
    }
}
