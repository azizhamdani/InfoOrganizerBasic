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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_company, container, false);

        //AppCompatActivity activity = (AppCompatActivity) getActivity();
         name = (EditText) v.findViewById(R.id.company_name);
         logo = (EditText) v.findViewById(R.id.logo_url);
         stock = (EditText) v.findViewById(R.id.stock_tick);
        submit = (Button) v.findViewById(R.id.submit_btn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cName = name.getText().toString();
                String cLogo = logo.getText().toString();
                String cStock = stock.getText().toString();
                Toast.makeText(getContext(), cName + cLogo + cStock,Toast.LENGTH_LONG).show();
                boolean isUpdate = ((StartActivity) (getActivity())).getUpdateFlag();
                int curCompany = ((StartActivity) (getActivity())).getCurrentCompanyNo();
                if(isUpdate == false) {
                    // add new company
                    dao.getCompanies().add(new Company(cName, cLogo, cStock));
                }
                else{
                    // before updating grab old values
                    // if the values are not entered then leave the old values the same
                    //String old_cName = dao.getDAOCompany(curCompany).getCompany_name();
                    //String old_cLogo = dao.getDAOCompany(curCompany).getLogoURL();
                    //String old_cStock = dao.getDAOCompany(curCompany).getStock_ticker();
                    // update Company
                    if(cName.length() > 1)
                    dao.getDAOCompany(curCompany).setCompany_name(cName);
                    if(cLogo.length() > 1)
                    dao.getDAOCompany(curCompany).setLogoURL(cLogo);
                    if(cStock.length() > 1)
                    dao.getDAOCompany(curCompany).setStock_ticker(cStock);
                    isUpdate = false;
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

        return v;
    }



//        Toast.makeText(getApplicationContext(), "clicked,", Toast.LENGTH_SHORT).show();

}
