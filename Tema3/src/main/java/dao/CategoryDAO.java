package dao;

import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CategoryDAO extends AbstractDAO<Category> {

    protected static final Logger LOGGER = Logger.getLogger(Category.class.getName());
    private Connection connection;

    public CategoryDAO(Connection connection){
        this.connection= connection;
    }

    public int insert(Category category){

        PreparedStatement insertStatement=null;
        int insertedID=-1;

        try {
            insertStatement=connection.prepareStatement(createInsertQuery(), Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1,category.getIdCategory());
            insertStatement.setString(2,category.getCategory_name());
            insertStatement.setString(3,category.getCategory_details());
            System.out.println(insertStatement.execute());

            ResultSet rs = insertStatement.getGeneratedKeys();
            System.out.println(rs);
            if (rs.next()) {
                insertedID = rs.getInt(1);
            }
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"CategoryDAO:insert "+e.getMessage());
        }
        return insertedID;
    }

    public void update(int newIdCategory, String newCategory_name, String newCategory_details) {
        PreparedStatement findStatement=null;

        try {
            String statement= createUpdateQuery();
            findStatement=connection.prepareStatement(statement);
            findStatement.setInt(3, newIdCategory);
            findStatement.setString(1, newCategory_name);
            findStatement.setString(2, newCategory_details);
            findStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "CategoryDAO:update all " + e.getMessage());
        }

    }
    
    public void delete(int idCategory){
        PreparedStatement findStatement=null;
        try {
            String deleteStatement=createDeleteQuery();
            findStatement=connection.prepareStatement(deleteStatement);
            findStatement.setInt(1,idCategory);
            System.out.println(findStatement);
            findStatement.execute();
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"CategoryDAO: delete "+e.getMessage());
        }
    }

    public ArrayList<Category> getAll(){
        ArrayList<Category> categorys=new ArrayList<Category>();

        PreparedStatement findStatement=null;

        ResultSet rs=null;

        try {
            String statement=createSelectAllQuery();
            findStatement=connection.prepareStatement(statement);
            rs=findStatement.executeQuery();
            while(rs.next()){
                int idCategory=rs.getInt("idCategory");
                String category_name=rs.getString("category_name");
                String category_details=rs.getString("category_details");

                categorys.add(new Category(idCategory,category_name,category_details));
            }
        }catch (SQLException e){
            LOGGER.log(Level.WARNING,"CategoryDAO:findById "+e.getMessage());
        }
        return categorys;
    }
}
