package io.tutorial.turntotech.infoOrganizerSample;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Abdulaziz on 8/31/2017.
 */
@DatabaseTable(tableName = "Product")
public class Product {

    @DatabaseField(generatedId = true)
    private int id;
    //@DatabaseField // this has to specify the foriegn column name
    //private int company_id;
    @DatabaseField
    private String product_name;
    @DatabaseField
    private String product_URL;
    @DatabaseField
    private String logo_URL;
    @DatabaseField(columnName = "company", foreign = true, foreignAutoRefresh = true)
    private Company company;

    // ------------------- Constructors ----------------
    public Product(){

    }
    public Product(int id, String product_name, String product_URL, String logo_URL, Company company) {
        //this.id = id;
        //this.company_id = company_id;
        this.product_name = product_name;
        this.product_URL = product_URL;
        this.logo_URL = logo_URL;
        this.company = company;
    }

    public Product( String product_name, String product_URL, String logo_URL, Company company) {
        //this.id = 0;
        //this.company_id = 0;
        this.product_name = product_name;
        this.product_URL = product_URL;
        this.logo_URL = logo_URL;
        this.company = company;
    }

    public Product( String product_name, String logo_URL, Company company) {
       // this.id = 0;
        //this.company_id = 0;
        this.product_name = product_name;
        this.product_URL = "";
        this.logo_URL = logo_URL;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCompany(Company c){
        company = c;
    }

    public Company getCompany(){
        return company;
    }

//    public int getCompany_id() {
//        return company_id;
//    }

//    public void setCompany_id(int company_id) {
//        this.company_id = company_id;
//    }

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
