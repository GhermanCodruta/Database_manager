package model;

public class Category {
    private int idCategory;
    private String category_name;
    private String category_details;


    public Category(int idCategory, String category_name, String category_details){

        this.idCategory=idCategory;
        this.category_name=category_name;
        this.category_details=category_details;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public String getCategory_details() {
        return category_details;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_details(String category_details) {
        this.category_details = category_details;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
}
