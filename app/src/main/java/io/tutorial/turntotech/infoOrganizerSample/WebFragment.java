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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.Toast;

import static io.tutorial.turntotech.infoOrganizerSample.StartActivity.dao;


public class WebFragment extends Fragment {
    private WebView mWebView;
    ImageButton backButton;
    ImageButton addButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_web, container, false);

        int companyNo = ((StartActivity) getActivity()).getCurrentCompanyNo();
        int productNo = ((StartActivity) getActivity()).getCurrentProductNo();
        String url = dao.getDAOProductList(companyNo).get(productNo).getProduct_URL();

        mWebView = (WebView) v.findViewById(R.id.webview);
        mWebView.loadUrl(url);

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

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
                //Toast.makeText(getContext(),"Back",Toast.LENGTH_LONG).show();
                Fragment product = new ProductFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainLayout, product);
                fragmentTransaction.addToBackStack(null);
                // Commit the transaction
                fragmentTransaction.commit();
            }
        });

        return v;
    }
}
