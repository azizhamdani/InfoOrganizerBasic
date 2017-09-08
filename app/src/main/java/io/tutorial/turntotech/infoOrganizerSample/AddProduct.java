package io.tutorial.turntotech.infoOrganizerSample;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import static io.tutorial.turntotech.infoOrganizerSample.StartActivity.dao;


public class AddProduct extends Fragment {
    private EditText name, logo, webPage;
    private Button submit;
    private ImageButton addButton, backButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_add_product, container, false);

        //AppCompatActivity activity = (AppCompatActivity) getActivity();
        name = (EditText) v.findViewById(R.id.product_name);
        logo = (EditText) v.findViewById(R.id.product_logo_url);
        webPage = (EditText) v.findViewById(R.id.product_url);
        submit = (Button) v.findViewById(R.id.product_submit_btn);

        final boolean isUpdate = ((StartActivity) (getActivity())).getUpdateFlag();
        final int curCompany = ((StartActivity) (getActivity())).getCurrentCompanyNo();
        final int curPro = ((StartActivity) getActivity()).getCurrentProductNo();
        Product temp_pro = dao.getDAOProduct(curCompany, curPro);
        if(isUpdate == true) {
            name.setText((CharSequence) temp_pro.getProduct_name());
            logo.setText((CharSequence) temp_pro.getLogo_URL());
            webPage.setText((CharSequence) temp_pro.getProduct_URL());
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pName = name.getText().toString();
                String pLogo = logo.getText().toString();
                String pStock = webPage.getText().toString();
                int companyNo = ((StartActivity) getActivity()).getCurrentCompanyNo();
                //boolean isUpdate = ((StartActivity) (getActivity())).getUpdateFlag();
                //Toast.makeText(getContext(), pName + pLogo + pStock,Toast.LENGTH_LONG).show();
                if(isUpdate == false) {
                    if (pName.length() <= 0 || pName == null)
                        pName = "No Name";
                    if (pLogo.length() <= 0 || pLogo == null)
                        pLogo = "No_url";
                    if (pStock.length() <= 0 || pStock == null)
                        pStock = "Nothing";
                    Company c = dao.getDAOCompany(companyNo);
                    Product pro = new Product(pName, pLogo, pStock, c);
                    dao.addProduct(pro);
                }
                else{
                    int productNo = ((StartActivity) getActivity()).getCurrentProductNo();
                    //------------------- update over here -------------------- ??????????????????????????????????
                    // before updating grab old values
                    // if the values are not entered then leave the old values the same
                    Product temp_product = dao.getDAOProduct(companyNo, productNo);
                    // update Company
                    if(pName.length() > 1)
                        temp_product.setProduct_name(pName);
                    //dao.getDAOCompany(curCompany).setCompany_name(cName);
                    if(pLogo.length() > 1)
                        temp_product.setLogo_URL(pLogo);
                    //dao.getDAOCompany(curCompany).setLogoURL(cLogo);
                    if(pStock.length() > 1)
                        temp_product.setProduct_URL(pStock);
                    //dao.getDAOCompany(curCompany).setStock_ticker(cStock);
                    dao.updateProduct(temp_product);
                    ((StartActivity) (getActivity())).setUpdate(false);
                }
                ///////////////dao.getCompanies().get(companyNo).addProduct(pro);

                Fragment product = new ProductFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainLayout, product);
                fragmentTransaction.addToBackStack(null);
                // Commit the transaction
                fragmentTransaction.commit();
                // Go to Child not Found Screen
            }
        });

        // ActionBar SetUp
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.toolbar);
        addButton = (ImageButton)activity.findViewById(R.id.imageButton2);
        addButton.setVisibility(View.INVISIBLE);
        backButton = (ImageButton)activity.findViewById(R.id.imageButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = ((StartActivity) (getActivity())).getUpdateFlag();
                if(flag == true)
                    ((StartActivity) (getActivity())).setUpdate(false);
                //Toast.makeText(getContext(),"Back",Toast.LENGTH_LONG).show();
                Fragment pro = new ProductFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainLayout, pro);
                fragmentTransaction.addToBackStack(null);
                // Commit the transaction
                fragmentTransaction.commit();
            }
        });

        return v;
    }

}
