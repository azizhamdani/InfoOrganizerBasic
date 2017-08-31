package io.tutorial.turntotech.infoOrganizerSample;

/**
 * Created by Webster on 8/31/2017.
 */

public class Product {

    private int id;
    private int company_id;
    private String product_name;
    private String product_URL;
    private String logo_URL;

    public Product(int id, int company_id, String product_name, String product_URL, String logo_URL) {
        this.id = id;
        this.company_id = company_id;
        this.product_name = product_name;
        this.product_URL = product_URL;
        this.logo_URL = logo_URL;
    }

    public Product(){
        this.id = 0;
        this.company_id = 0;
        this.product_name = "";
        this.product_URL = "";
        this.logo_URL = "";
    }
    public Product( String product_name, String product_URL, String logo_URL) {
        this.id = 0;
        this.company_id = 0;
        this.product_name = product_name;
        this.product_URL = product_URL;
        this.logo_URL = logo_URL;
    }

    public Product( String product_name, String logo_URL) {
        this.id = 0;
        this.company_id = 0;
        this.product_name = product_name;
        this.product_URL = "";
        this.logo_URL = logo_URL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_URL() {
        return product_URL;
    }

    public void setProduct_URL(String product_URL) {
        this.product_URL = product_URL;
    }

    public String getLogo_URL() {
        return logo_URL;
    }

    public void setLogo_URL(String logo_URL) {
        this.logo_URL = logo_URL;
    }
}
