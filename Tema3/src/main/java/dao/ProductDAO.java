package dao;

import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO extends AbstractDAO<Product>{
    protected static final Logger LOGGER = Logger.getLogger(Product.class.getName());
    private Connection connection;

    public ProductDAO(Connection connection){
        this.connection= connection;
    }

    public int insert(Product product){

        PreparedStatement insertStatement=null;
        int insertedID=-1;

        try {
            insertStatement=connection.prepareStatement(createInsertQuery(), Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1,product.getIdProduct());
            insertStatement.setInt(2,product.getIdSupplier());
            insertStatement.setInt(3,product.getIdCategory());
            insertStatement.setInt(4,product.getProduct_stock());
            insertStatement.setString(5,product.getProduct_name());
            insertStatement.setInt(6,product.getProduct_price());
            insertStatement.setString(7,product.getProduct_details());
            System.out.println(insertStatement.toString());
            System.out.println(insertStatement.execute());

            ResultSet rs = insertStatement.getGeneratedKeys();
            System.out.println(rs);
            if (rs.next()) {
                insertedID = rs.getInt(1);
            }
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"ProductDAO:insert "+e.getMessage());
        }
        return insertedID;
    }

    public void update(int newQty, int newIdSupplier, int newIdCategory, int newProduct_price, String newProduct_name, String newProduct_details,int idProduct) {
        PreparedStatement findStatement=null;

        try {
            String statement= createUpdateQuery();
            findStatement=connection.prepareStatement(statement);
            findStatement.setInt(1, newIdSupplier);
            findStatement.setInt(2, newIdCategory);
            findStatement.setInt(3,newQty);
            findStatement.setString(4, newProduct_name);
            findStatement.setInt(5, newProduct_price);
            findStatement.setString(6, newProduct_details);
            findStatement.setInt(7,idProduct);
            findStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:update all " + e.getMessage());
        }

    }

    public void updateQuantity(int newQty, int idProduct){
        PreparedStatement findStatement=null;

        try {
            String statement="UPDATE product SET product_stock = "+newQty+ "WHERE idProduct= "+ idProduct;
            findStatement=connection.prepareStatement(statement);
            //findStatement.setInt(1,newQty);
            //findStatement.setInt(2,idProduct);
            findStatement.executeUpdate();
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"ProductDAO:update " + e.getMessage());
        }
    }

    public void delete(int idProduct){
        PreparedStatement findStatement=null;
         try {
            String deleteStatement=createDeleteQuery();
            findStatement=connection.prepareStatement(deleteStatement);
            findStatement.setInt(1,idProduct);
            System.out.println(findStatement);
            findStatement.execute();
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"ProductDAO: delete "+e.getMessage());
        }
    }

    public ArrayList<Product> getAll(){
        ArrayList<Product> products=new ArrayList<Product>();

         PreparedStatement findStatement=null;

        ResultSet rs=null;

        try {
            String statement=createSelectAllQuery();
            findStatement=connection.prepareStatement(statement);
            rs=findStatement.executeQuery();
            while(rs.next()){
                int idProduct=rs.getInt("idProduct");
                int idCategory=rs.getInt("idCategory");
                int idSupplier=rs.getInt("idSupplier");
                int product_stock=rs.getInt("product_stock");
                int product_price=rs.getInt("product_price");
                String product_name=rs.getString("product_name");
                String product_details=rs.getString("product_details");

                products.add(new Product(idProduct,idSupplier,idCategory,product_stock,product_name,product_price,product_details));
            }
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"ProductDAO:findById "+e.getMessage());
        }
        return products;
    }
}
