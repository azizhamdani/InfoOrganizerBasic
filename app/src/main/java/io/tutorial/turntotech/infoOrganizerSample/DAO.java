package io.tutorial.turntotech.infoOrganizerSample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Webster on 8/31/2017.
 */

public class DAO {

    private ArrayList<Company> companies;
    private static DAO instance = null;



    public static DAO getInstance(){
        if (instance == null){
            return new DAO();
        }
        else
            return instance;
    }

    private DAO () {
        companies = new ArrayList<Company>();
        companies.add(new Company("Apple", "apple company", 1000));
        companies.add(new Company("Samsung", "Samsung company", 1000));
        companies.add(new Company("Motorola", "Motorola company", 1000));
        companies.add(new Company("Microsoft", "Microsoft company", 1000));

        // apple Products
        companies.get(0).addProduct(new Product("Iphone", "product_URL", "LOGO_URL"));
        companies.get(0).addProduct(new Product("IPad", "product_URL", "LOGO_URL"));
        companies.get(0).addProduct(new Product("IPod", "product_URL", "LOGO_URL"));

        // Samsung Products
        companies.get(1).addProduct(new Product("Galaxy s", "product_URL", "LOGO_URL"));
        companies.get(1).addProduct(new Product("Galaxy Note", "product_URL", "LOGO_URL"));
        companies.get(1).addProduct(new Product("J7", "product_URL", "LOGO_URL"));

        // Moto Products
        companies.get(2).addProduct(new Product("Moto E", "product_URL", "LOGO_URL"));
        companies.get(2).addProduct(new Product("Moto G", "product_URL", "LOGO_URL"));
        companies.get(2).addProduct(new Product("Moto X", "product_URL", "LOGO_URL"));

        // Microsoft Products
        companies.get(3).addProduct(new Product("Lumia 540", "product_URL", "LOGO_URL"));
        companies.get(3).addProduct(new Product("Lumia 640", "product_URL", "LOGO_URL"));
        companies.get(3).addProduct(new Product("Lumia 925", "product_URL", "LOGO_URL"));
    }

    //(((((((((((((((((( Product class  ))))))))))))))))))))
    public ArrayList<Product> getDAOProductList(int index){
        if(companies.isEmpty()){
            return null;
        }
        else {
            return companies.get(index).getProductList();
        }
    }


    // ((((((((((((((((( company class ))))))))))))))))))))
    // ----------------get companies ---------------------
    public ArrayList<Company> getCompanies(){
        // should be throwing an error message over here.
        if(companies.isEmpty()){
            return null;
        }
        return companies;
    }
    public Company getDAOCompany(int index){
        if(companies.isEmpty())
            return null;  // should throw an error too
        else
            return companies.get(index);
    }
    // ------------  add a company -----------------
    public void addCompany(Company company){
        companies.add(company);
    }

    // ----------- get index of company ------------------
    public int getCompanyIndex(String company_name){
        if(companies.isEmpty()){
            return -1;
        }
        int index = -2;
        for(int i = 0; i < companies.size(); i++){
            if(companies.get(i).getCompany_name().equals(company_name)){
                index = i;
            }
        }
        return index;

    }
    // ----------- delete company by companyname ------------------
    public boolean deleteCompany(String company_name){
        if(companies.isEmpty()){
            return false;
        }
        for(int i = 0; i < companies.size(); i++){
            if(companies.get(i).getCompany_name().equals(company_name)){
                companies.remove(i);
            }
        }
        return true;

    }






}
