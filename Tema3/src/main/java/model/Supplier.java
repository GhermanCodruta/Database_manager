package model;

public class Supplier {
    private int idSupplier;
    private String supplier_email;
    private String supplier_name;
    private String supplier_phone;
    private String supplier_details;

    public Supplier(int idSupplier, String supplier_email, String supplier_name, String supplier_phone, String supplier_details) {
        this.idSupplier = idSupplier;
        this.supplier_email = supplier_email;
        this.supplier_name = supplier_name;
        this.supplier_phone = supplier_phone;
        this.supplier_details = supplier_details;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getSupplier_email() {
        return supplier_email;
    }

    public void setSupplier_email(String supplier_email) {
        this.supplier_email = supplier_email;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getSupplier_phone() {
        return supplier_phone;
    }

    public void setSupplier_phone(String supplier_phone) {
        this.supplier_phone = supplier_phone;
    }

    public String getSupplier_details() {
        return supplier_details;
    }

    public void setSupplier_details(String supplier_details) {
        this.supplier_details = supplier_details;
    }
}
