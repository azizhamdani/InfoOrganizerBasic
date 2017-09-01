package io.tutorial.turntotech.infoOrganizerSample;

import java.util.ArrayList;

/**
 * Created by Webster on 8/31/2017.
 */

public class Company {

    private int id;
    private String company_name;
    private String logoURL;
    private String stock_ticker;
    private double stock_price;
    private ArrayList<Product> productArrayList;

    public Company(int id, String company_name, String logoURL, String stock_ticker, double stock_price) {
        this.id = id;
        this.company_name = company_name;
        this.logoURL = logoURL;
        this.stock_ticker = stock_ticker;
        this.stock_price = stock_price;
        productArrayList = new ArrayList<>();
    }



    public Company() {
        this.id = 0;
        this.company_name = "";
        this.logoURL = "";
        this.stock_ticker = "";
        this.stock_price = 0.0;
        productArrayList = new ArrayList<>();
    }

    public Company(String company_name, String logoURL,  double stock_price) {
        this.id = 0;
        this.company_name = company_name;
        this.logoURL = logoURL;
        this.stock_ticker = "";
        this.stock_price = stock_price;
        productArrayList = new ArrayList<>();
    }

    // -------------- add a product -----------------
    public void addProduct( Product product){
        productArrayList.add(product);
    }

    // -----------get product List of company ---------
    public ArrayList<Product> getProductList(){
        if(productArrayList.isEmpty()){
            return null;
        }
        else{
            return productArrayList;
        }

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

    public double getStock_price() {
        return stock_price;
    }

    public void setStock_price(double stock_price) {
        this.stock_price = stock_price;
    }
}
