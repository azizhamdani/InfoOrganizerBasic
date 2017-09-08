package io.tutorial.turntotech.infoOrganizerSample;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

/**
 * Created by Abdulaziz on 8/31/2017.
 */
@DatabaseTable(tableName = "Company")
public class Company {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String company_name;
    @DatabaseField
    private String logoURL;
    @DatabaseField
    private String stock_ticker;
    @DatabaseField
    private String stock_price;
    @ForeignCollectionField(columnName = "products", eager = true)
    private ForeignCollection<Product> products;

    // ------------- Constructors -----------------------
    public Company() {
    }

    public Company(int id, String company_name, String logoURL, String stock_ticker, String stock_price) {
       // this.id = id;
        this.company_name = company_name;
        this.logoURL = logoURL;
        this.stock_ticker = stock_ticker;
        this.stock_price = stock_price;
    }

    public Company(String company_name, String logoURL, String stock_ticker) {
        //this.id = 0;
        this.company_name = company_name;
        this.logoURL = logoURL;
        this.stock_ticker = stock_ticker;
        this.stock_price = "N/A";
    }

    public Company(String company_name, String logoURL, String stock_ticker, String stock_price) {
        //this.id = 0;
        this.company_name = company_name;
        this.logoURL = logoURL;
        this.stock_ticker = stock_ticker;
        this.stock_price = stock_price;
    }

    // -----------get product List of company ---------
    public ForeignCollection<Product> getProductList(){
            return products;
    }
    public void setProductList(ForeignCollection<Product> products){
        this.products = products;
    }

    //------------ get ID -----------------------
    public int getId() {
        return id;
    }
    // ----------- set ID -----------------------
    public void setId(int id) {
        this.id = id;
    }

    // -------------- get Company Name ------------
    public String getCompany_name() {
        return company_name;
    }

    // --------------- set Company name -------------
    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public String getStock_ticker() {
        return stock_ticker;
    }

    public void setStock_ticker(String stock_ticker) {
        this.stock_ticker = stock_ticker;
    }

    public String getStock_price() {
        return stock_price;
    }

    public void setStock_price(String stock_price) {
        this.stock_price = stock_price;
    }
}
