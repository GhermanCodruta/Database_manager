package bll;

import dao.CategoryDAO;
import model.Category;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class CategoryBLL {
    private CategoryDAO categoryDAO;
    public CategoryBLL(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public ArrayList<Category> getAll() {
        ArrayList<Category> categorys = categoryDAO.getAll();
        if (categorys == null) {
            throw new NoSuchElementException("Couldn't get all Categorys\n");
        }
        return categorys;
    }

    public int insertCategory(Category category){
        return categoryDAO.insert(category);
    }

    public void deleteCategory(int idCategory)
    {
        categoryDAO.delete(idCategory);
    }


    public void updateAll(int newIdCategory, String newCategory_name, String newCategory_details){
        categoryDAO.update(newIdCategory, newCategory_name,  newCategory_details);
    }

}
