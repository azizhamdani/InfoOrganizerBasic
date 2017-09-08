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


public class AddCompany extends Fragment {

    static EditText name, logo, stock;
    private Button submit;
    private ImageButton backButton, addButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_company, container, false);

        //AppCompatActivity activity = (AppCompatActivity) getActivity();
         name = (EditText) v.findViewById(R.id.company_name);
         logo = (EditText) v.findViewById(R.id.logo_url);
         stock = (EditText) v.findViewById(R.id.stock_tick);

        final boolean isUpdate = ((StartActivity) (getActivity())).getUpdateFlag();
        final int curCompany = ((StartActivity) (getActivity())).getCurrentCompanyNo();

        if(isUpdate == true) {
            Company temp_company = dao.getDAOCompany(curCompany);
            name.setText((CharSequence) temp_company.getCompany_name());
            logo.setText((CharSequence) temp_company.getLogoURL());
            stock.setText((CharSequence) temp_company.getStock_ticker());
        }

        submit = (Button) v.findViewById(R.id.submit_btn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cName = name.getText().toString();
                String cLogo = logo.getText().toString();
                String cStock = stock.getText().toString();
                //Toast.makeText(getContext(), cName + cLogo + cStock,Toast.LENGTH_LONG).show();
                if(isUpdate == false) {
                    if (cName.length() <= 0 || cName == null)
                        cName = "No Name";
                    if (cLogo.length() <= 0 || cLogo == null)
                        cLogo = "No_url";
                    if (cStock.length() <= 0 || cStock == null)
                        cStock = "Nothing";
                    // add new company
                    //dao.getCompanies().add(new Company(cName, cLogo, cStock));
                    dao.addCompany(new Company(cName, cLogo, cStock));
                }
                else{
                    //------------------- update over here -------------------- ??????????????????????????????????
                    // before updating grab old values
                    // if the values are not entered then leave the old values the same
                    Company temp_company = dao.getDAOCompany(curCompany);
                    // update Company
                    if(cName.length() > 1)
                        temp_company.setCompany_name(cName);
                    //dao.getDAOCompany(curCompany).setCompany_name(cName);
                    if(cLogo.length() > 1)
                        temp_company.setLogoURL(cLogo);
                    //dao.getDAOCompany(curCompany).setLogoURL(cLogo);
                    if(cStock.length() > 1)
                        temp_company.setStock_ticker(cStock);
                    //dao.getDAOCompany(curCompany).setStock_ticker(cStock);
                    dao.updateCompany(temp_company);
                    ((StartActivity) (getActivity())).setUpdate(false);
                }

                Fragment company = new CompanyFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainLayout, company);
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
                Fragment company = new CompanyFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainLayout, company);
                fragmentTransaction.addToBackStack(null);
                // Commit the transaction
                fragmentTransaction.commit();
            }
        });

        return v;
    }


}
