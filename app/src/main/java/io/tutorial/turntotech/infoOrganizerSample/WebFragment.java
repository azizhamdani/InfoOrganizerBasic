package io.tutorial.turntotech.infoOrganizerSample;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WebFragment extends Fragment {
    private WebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View v = inflater.inflate(R.layout.fragment_web, container, false);

        int companyNo = ((StartActivity) getActivity()).getCurrentCompanyNo();
        int productNo = ((StartActivity) getActivity()).getCurrentProductNo();
        String url = DAO.getInstance().getDAOProductList(companyNo).get(productNo).getProduct_URL();

        mWebView = (WebView) v.findViewById(R.id.webview);
        mWebView.loadUrl(url);

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        return v;
    }
}
