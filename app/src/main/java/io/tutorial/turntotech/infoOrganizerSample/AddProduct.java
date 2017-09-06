package io.tutorial.turntotech.infoOrganizerSample;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static io.tutorial.turntotech.infoOrganizerSample.StartActivity.dao;


public class AddProduct extends Fragment {
    private EditText name, logo, webPage;
    private Button submit;

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
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pName = name.getText().toString();
                String pLogo = logo.getText().toString();
                String pStock = webPage.getText().toString();
                int companyNo = ((StartActivity) getActivity()).getCurrentCompanyNo();
                Toast.makeText(getContext(), pName + pLogo + pStock,Toast.LENGTH_LONG).show();

                Product pro = new Product(pName, pLogo, pStock);
                dao.getCompanies().get(companyNo).addProduct(pro);

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

        return v;
    }

}
