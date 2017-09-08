package io.tutorial.turntotech.infoOrganizerSample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Rajat on 4/19/17.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {


    private static final String DATABASE_NAME    = "companyDB.db";
    private static final int    DATABASE_VERSION = 5;

    private static Dao<Company, Integer> companyDAO = null;
    private static Dao<Product, Integer> productDAO = null;



    public DBHelper(Context context) throws SQLException {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    /* Company */
    public  Dao<Company, Integer> getCompanyDAO() throws SQLException {
        if(companyDAO == null)
            companyDAO = getDao(Company.class);

        return companyDAO;
    }

    /* Product */
    public  Dao<Product, Integer> getProductDAO() throws SQLException {
        if(productDAO == null)
            productDAO = getDao(Product.class);

        return productDAO;
    }




    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Company.class);
            TableUtils.createTable(connectionSource, Product.class);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Company.class, true);
            TableUtils.dropTable(connectionSource, Product.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void close(){
        companyDAO = null;
        productDAO = null;
        super .close();
    }
}
