package io.tutorial.turntotech.infoOrganizerSample;

import android.content.Context;
import android.database.SQLException;
import android.widget.Toast;

import com.j256.ormlite.stmt.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abdulaziz on 8/31/2017.
 */

public class DAO {

    private ArrayList<Company> companies;
    private static DAO instance = null;
    private static DBHelper database;

    // --------- class instantiated here -------------
    // to allow this to be called once
    public static DAO getInstance(Context context){
        // I made a mistake here
        if (instance == null){
            instance =  new DAO(context); // I did not assign DAO to isntance.
            // this problem caused DAO to be created again and again.
            // fixed it this way or call DAO once in the StartActivity
            return instance;
        }
        else
            return instance;
    }
    // ------------- Constructors ---------------------
    // it is private coz the class is a singleton
    private DAO (Context context) {

        // 1- Ticker is what entered and price is what should be shown.
        //  1- instantiate the DBHelper class
        try {
            database = new DBHelper(context);
            if(getCompanies().size() == 0)
                createCompaniesAndProducts();

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }


        // retreive from database --> if null create insert


        /*
        companies = new ArrayList<Company>();
        companies.add(new Company("Apple",
                "http://pngimg.com/uploads/apple_logo/apple_logo_PNG19664.png?i=1",
                "AAPL"));
        companies.add(new Company("Samsung",
                "http://pngimg.com/uploads/samsung_logo/samsung_logo_PNG5.png",
                "Samsungcompany"));
        companies.add(new Company("Motorola",
                "https://seeklogo.com/images/M/Motorola-logo-C3DDCBA822-seeklogo.com.png",
                "Motorolacompany"));
        companies.add(new Company("Microsoft",
                "http://www.pngmart.com/files/4/Microsoft-Logo-PNG-HD.png",
                "MSFT"));
        // added companies for assignment 3
        companies.add(new Company("Nokia",
                "https://www.seeklogo.net/wp-content/uploads/2015/03/nokia-logo.png",
                "Nokiacompany"));
        companies.add(new Company("Blackberry",
                "https://www.strategicmanagementinsight.com/img/company-logos/blackberry.png",
                "Blackberrycompany"));

        // apple Products
        companies.get(0).addProduct(new Product("Iphone", "https://www.apple.com/iphone-7/",
                "http://pngimg.com/uploads/iphone/iphone_PNG5735.png?i=1"));
        companies.get(0).addProduct(new Product("IPad", "https://www.apple.com/ipad/",
                "http://www.pngmart.com/files/4/iPad-PNG-Transparent-Image.png"));
        companies.get(0).addProduct(new Product("IPod", "https://www.apple.com/ipod-touch/",
                "https://upload.wikimedia.org/wikipedia/commons/5/51/IPod_classic.png"));

        // Samsung Products
        companies.get(1).addProduct(new Product("Galaxy s",
                "http://www.samsung.com/us/mobile/phones/galaxy-s/s/_/n-10+11+hv1rp+zq1xa/",
                "https://upload.wikimedia.org/wikipedia/commons/d/d6/Samsung_Galaxy_S_III.png"));
        companies.get(1).addProduct(new Product("Galaxy Note", "http://www.samsung.com/us/galaxy/note8/",
                "https://upload.wikimedia.org/wikipedia/commons/9/98/Samsung_Galaxy_Note_5.png"));
        companies.get(1).addProduct(new Product("J7",
                "http://www.samsung.com/us/mobile/phones/all-other-phones/samsung-galaxy-j7-16gb-t-mobile-white-sm-j700tzwatmb/",
                "https://drphonefix.com/wp-content/uploads/2017/02/samsung-galaxy-j7-repair-dr-phone-fix.png"));

        // Moto Products
        companies.get(2).addProduct(new Product("Moto E",
                "https://www.motorola.com/us/products/moto-e-gen-4",
                "https://www.oneclickroot.com/wp-content/uploads/2015/04/Motorola-Moto-E.png"));
        companies.get(2).addProduct(new Product("Moto G",
                "https://www.motorola.ca/products/moto-g-plus",
                "http://www.androidpolice.com/wp-content/uploads/2014/09/nexus2cee_332.png"));
        companies.get(2).addProduct(new Product("Moto X",
                "https://www.android.com/phones/moto-x/",
                "https://www.androidheadlines.com/wp-content/uploads/2014/09/moto-G-leak-3.png"));

        // Microsoft Products
        companies.get(3).addProduct(new Product("Lumia 540",
                "https://www.microsoft.com/en/mobile/phone/lumia540-dual-sim/",
                "http://www.bdmobilephone.com/images/gallery/microsoft-lumia-540-dual-sim_10165.png"));
        companies.get(3).addProduct(new Product("Lumia 640",
                "https://www.microsoft.com/en-us/mobile/phone/lumia640/",
                "https://www.phoneworld.com.pk/wp-content/uploads/2015/03/Microsoft-Lumia-640-XL.png"));
        companies.get(3).addProduct(new Product("Lumia 925",
                "https://support.t-mobile.com/docs/DOC-13685",
                "http://www.cheshirefixers.com/image/cache/catalog/Designs/Product-Images/Battery-Repairs" +
                        "-Iconized/Nokia-Microsoft-Lumia-925-Front-500x500.png"));

        // Microsoft Products
        companies.get(4).addProduct(new Product("Nokia 3310",
                "https://www.nokia.com/en_int/phones/nokia-3310",
                "http://www.atlasict.co.uk/wp-content/uploads/2017/02/nokia-3310.png"));
        companies.get(4).addProduct(new Product("Nokia 1280",
                "http://www.gsmarena.com/nokia_1280-3008.php",
                "http://www.mega.pk/items_images/22061280_black_front_604x604.png"));
        companies.get(4).addProduct(new Product("Nokia 6210",
                "http://www.gsmarena.com/nokia_6210-12.php",
                "http://esmobil.org/nokia/img/nokia-6210.jpg"));

        // Microsoft Products
        companies.get(5).addProduct(new Product("Blackberry Z10",
                "https://crackberry.com/blackberry-z10",
                "http://s3.reglobe.in/details/54c9bc94842ef-Blackberry_Z10_12290.PNG"));
        companies.get(5).addProduct(new Product("Blackberry Q10",
                "https://crackberry.com/blackberry-q10",
                "https://appbus.files.wordpress.com/2013/05/ods-peek-q10.png"));
        companies.get(5).addProduct(new Product("Blackberry Z30",
                "https://crackberry.com/blackberry-z30",
                "http://www.telus.com/common/images/devices/large/blackberry-z30.png"));

*/

    }

    public void createCompaniesAndProducts(){
        try {
            Company apple = new Company("Apple",
                    "http://pngimg.com/uploads/apple_logo/apple_logo_PNG19664.png?i=1",
                    "AAPL");
            Company samsung = new Company("Samsung",
                    "http://pngimg.com/uploads/samsung_logo/samsung_logo_PNG5.png",
                    "Samsungcompany");
            Company motorola = new Company("Motorola",
                    "https://seeklogo.com/images/M/Motorola-logo-C3DDCBA822-seeklogo.com.png",
                    "Motorolacompany");
            Company microsoft = new Company("Microsoft",
                    "http://www.pngmart.com/files/4/Microsoft-Logo-PNG-HD.png",
                    "MSFT");
            Company nokia = new Company("Nokia",
                    "https://www.seeklogo.net/wp-content/uploads/2015/03/nokia-logo.png",
                    "Nokiacompany");
            Company blackberry = new Company("Blackberry",
                    "https://www.strategicmanagementinsight.com/img/company-logos/blackberry.png",
                    "Blackberrycompany");
            Product iphone = new Product("Iphone", "https://www.apple.com/iphone-7/",
                    "http://pngimg.com/uploads/iphone/iphone_PNG5735.png?i=1", apple);
            Product ipad = new Product("IPad", "https://www.apple.com/ipad/",
                    "http://www.pngmart.com/files/4/iPad-PNG-Transparent-Image.png", apple);
            Product ipod = new Product("IPod", "https://www.apple.com/ipod-touch/",
                    "https://upload.wikimedia.org/wikipedia/commons/5/51/IPod_classic.png", apple);
            Product galaxy_s = new Product("Galaxy s",
                    "http://www.samsung.com/us/mobile/phones/galaxy-s/s/_/n-10+11+hv1rp+zq1xa/",
                    "https://upload.wikimedia.org/wikipedia/commons/d/d6/Samsung_Galaxy_S_III.png", samsung);
            Product galaxy_note = new Product("Galaxy Note", "http://www.samsung.com/us/galaxy/note8/",
                    "https://upload.wikimedia.org/wikipedia/commons/9/98/Samsung_Galaxy_Note_5.png", samsung);
            Product j7 = new Product("J7",
                    "http://www.samsung.com/us/mobile/phones/all-other-phones/samsung-galaxy-j7-16gb-t-mobile-white-sm-j700tzwatmb/",
                    "https://drphonefix.com/wp-content/uploads/2017/02/samsung-galaxy-j7-repair-dr-phone-fix.png", samsung);
            Product motoE = new Product("Moto E",
                    "https://www.motorola.com/us/products/moto-e-gen-4",
                    "https://www.oneclickroot.com/wp-content/uploads/2015/04/Motorola-Moto-E.png", motorola);
            Product motoG = new Product("Moto G",
                    "https://www.motorola.ca/products/moto-g-plus",
                    "http://www.androidpolice.com/wp-content/uploads/2014/09/nexus2cee_332.png", motorola);
            Product motoX = new Product("Moto X",
                    "https://www.android.com/phones/moto-x/",
                    "https://www.androidheadlines.com/wp-content/uploads/2014/09/moto-G-leak-3.png", motorola);
            Product lumia540 = new Product("Lumia 540",
                    "https://www.microsoft.com/en/mobile/phone/lumia540-dual-sim/",
                    "http://www.bdmobilephone.com/images/gallery/microsoft-lumia-540-dual-sim_10165.png", microsoft);
            Product lumia640 = new Product("Lumia 640",
                    "https://www.microsoft.com/en-us/mobile/phone/lumia640/",
                    "https://www.phoneworld.com.pk/wp-content/uploads/2015/03/Microsoft-Lumia-640-XL.png", microsoft);
            Product lumia925 = new Product("Lumia 925",
                    "https://support.t-mobile.com/docs/DOC-13685",
                    "http://www.cheshirefixers.com/image/cache/catalog/Designs/Product-Images/Battery-Repairs" +
                            "-Iconized/Nokia-Microsoft-Lumia-925-Front-500x500.png", microsoft);
            Product n3310 = new Product("Nokia 3310",
                    "https://www.nokia.com/en_int/phones/nokia-3310",
                    "http://www.atlasict.co.uk/wp-content/uploads/2017/02/nokia-3310.png", nokia);
            Product n1280 = new Product("Nokia 1280",
                    "http://www.gsmarena.com/nokia_1280-3008.php",
                    "http://www.mega.pk/items_images/22061280_black_front_604x604.png", nokia);
            Product n6210 = new Product("Nokia 6210",
                    "http://www.gsmarena.com/nokia_6210-12.php",
                    "http://esmobil.org/nokia/img/nokia-6210.jpg", nokia);
            Product bbZ10 = new Product("Blackberry Z10",
                    "https://crackberry.com/blackberry-z10",
                    "http://s3.reglobe.in/details/54c9bc94842ef-Blackberry_Z10_12290.PNG", blackberry);
            Product bbQ10 = new Product("Blackberry Q10",
                    "https://crackberry.com/blackberry-q10",
                    "https://appbus.files.wordpress.com/2013/05/ods-peek-q10.png", blackberry);
            Product bbZ30 = new Product("Blackberry Z30",
                    "https://crackberry.com/blackberry-z30",
                    "http://www.telus.com/common/images/devices/large/blackberry-z30.png", blackberry);


            // database.getCompanyDAO().create add a row in the table
            database.getCompanyDAO().create(apple);
            database.getCompanyDAO().create(samsung);
            database.getCompanyDAO().create(motorola);
            database.getCompanyDAO().create(microsoft);
            database.getCompanyDAO().create(nokia);
            database.getCompanyDAO().create(blackberry);
            database.getProductDAO().create(iphone);
            database.getProductDAO().create(ipad);
            database.getProductDAO().create(ipod);
            database.getProductDAO().create(galaxy_s);
            database.getProductDAO().create(galaxy_note);
            database.getProductDAO().create(j7);
            database.getProductDAO().create(motoE);
            database.getProductDAO().create(motoG);
            database.getProductDAO().create(motoX);
            database.getProductDAO().create(lumia540);
            database.getProductDAO().create(lumia640);
            database.getProductDAO().create(lumia925);
            database.getProductDAO().create(n3310);
            database.getProductDAO().create(n1280);
            database.getProductDAO().create(n6210);
            database.getProductDAO().create(bbZ10);
            database.getProductDAO().create(bbQ10);
            database.getProductDAO().create(bbZ30);


        }
        catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    //(((((((((((((((((( Product methods  ))))))))))))))))))))
    public ArrayList<Product> getDAOProductList(int index){
        ArrayList<Product> productList = new ArrayList<>();
        ArrayList<Company> tempList = getCompanies();
           // ArrayList<Product> tempCompany = (ArrayList<Product>) database.getProductDAO().queryForAll();
        productList = new ArrayList<>( tempList.get(index).getProductList());
        //productList = (ArrayList<Product>) (tempList.get(index).getProductList());

        return productList;
    }

    // ---------------- getProduct ------------------------
    public Product getDAOProduct(int cIndex, int pIndex){
        Company tempCompany = new Company();
        Product tempProduct = new Product();
        try {
            ArrayList<Company> tempCompanyList = (ArrayList<Company>) database.getCompanyDAO().queryForAll();
            tempCompany =  tempCompanyList.get(cIndex);
            ArrayList<Product> tempProductList = new ArrayList<>( tempCompany.getProductList());
            tempProduct = tempProductList.get(pIndex);

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return tempProduct;
    }

    // ------------  add a product -----------------
    public void addProduct(Product product){
        //companies.add(company);
        try {
            database.getProductDAO().create(product);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    // --------- update a company -------------
    public void updateProduct(Product product){
        try {
            database.getProductDAO().update(product);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    // ----------- delete a company -------------------
    public boolean removeProduct(Product p){
        try {
            database.getProductDAO().delete(p);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    // ((((((((((((((((( company class ))))))))))))))))))))
    // ----------------get companies ---------------------
    public ArrayList<Company> getCompanies(){
        ArrayList<Company> tempCompany = new ArrayList<>();
        try {
             tempCompany = (ArrayList<Company>) database.getCompanyDAO().queryForAll();

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return tempCompany;
    }

    // -------- get a company at an index ---------------------
    public Company getDAOCompany(int index){
        Company tempCompany = new Company();
        try {
            ArrayList<Company> tempCompanyList = (ArrayList<Company>) database.getCompanyDAO().queryForAll();
            tempCompany =  tempCompanyList.get(index);

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return tempCompany;
    }


    // ------------  add a company -----------------
    public void addCompany(Company company){
        //companies.add(company);
        try {

            database.getCompanyDAO().create(company);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    // --------- update a company -------------
    public void updateCompany(Company company){
        try {
            database.getCompanyDAO().update(company);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    // ----------- delete a company -------------------
    public boolean removeCompany(Company c){
//        if(companies.isEmpty())
//            return false;
//        companies.remove(index);
//        return true;
        try {
            database.getCompanyDAO().delete(c);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    // ----------- get index of company ------------------
//    public int getCompanyIndex(String company_name){
//        if(companies.isEmpty()){
//            return -1;
//        }
//        int index = -2;
//        for(int i = 0; i < companies.size(); i++){
//            if(companies.get(i).getCompany_name().equals(company_name)){
//                index = i;
//            }
//        }
//        return index;
//
//    }
    // ----------- delete company by companyname ------------------
//    public boolean deleteCompany(String company_name){
//        if(companies.isEmpty()){
//            return false;
//        }
//        for(int i = 0; i < companies.size(); i++){
//            if(companies.get(i).getCompany_name().equals(company_name)){
//                companies.remove(i);
//            }
//        }
//        return true;
//
//    }


}
