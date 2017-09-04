package io.tutorial.turntotech.infoOrganizerSample;
/*
started 08/30/2017
new line added
 */
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



public class StartActivity extends AppCompatActivity {

    static int companyNo;
    // added to keep track of the clicked product
    static int productNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        companyNo = 0;
        productNo = 0;
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainLayout, new CompanyFragment()).commit();
    }

    public int getCurrentCompanyNo()
    {
        return companyNo;
    }

    public void setCurrentCompanyNo(int pos)
    {
        companyNo = pos;
    }
    public int getCurrentProductNo()
    {
        return productNo;
    }

    public void setCurrentProductNo(int pos)
    {
        productNo = pos;
    }
}
