package model;

public class Purchaser {
    private int idPurchaser;
    private String purchaser_name;
    private String purchaser_email;
    private String purchaser_phone;
    private String purchaser_address;
    private String purchaser_details;


    public Purchaser(int idPurchaser, String purchaser_name, String purchaser_email, String purchaser_phone, String purchaser_address, String purchaser_details) {
        this.idPurchaser = idPurchaser;
        this.purchaser_name = purchaser_name;
        this.purchaser_email = purchaser_email;
        this.purchaser_phone = purchaser_phone;
        this.purchaser_address = purchaser_address;
        this.purchaser_details = purchaser_details;
    }

    public int getIdPurchaser() {
        return idPurchaser;
    }

    public void setIdPurchaser(int idPurchaser) {
        this.idPurchaser = idPurchaser;
    }

    public String getPurchaser_name() {
        return purchaser_name;
    }

    public void setPurchaser_name(String purchaser_name) {
        this.purchaser_name = purchaser_name;
    }

    public String getPurchaser_email() {
        return purchaser_email;
    }

    public void setPurchaser_email(String purchaser_email) {
        this.purchaser_email = purchaser_email;
    }

    public String getPurchaser_phone() {
        return purchaser_phone;
    }

    public void setPurchaser_phone(String purchaser_phone) {
        this.purchaser_phone = purchaser_phone;
    }

    public String getPurchaser_address() {
        return purchaser_address;
    }

    public void setPurchaser_address(String purchaser_address) {
        this.purchaser_address = purchaser_address;
    }

    public String getPurchaser_details() {
        return purchaser_details;
    }

    public void setPurchaser_details(String purchaser_details) {
        this.purchaser_details = purchaser_details;
    }
}
