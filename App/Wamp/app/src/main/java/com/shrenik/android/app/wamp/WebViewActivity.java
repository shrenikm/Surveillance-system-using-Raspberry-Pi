package com.shrenik.android.app.wamp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WebViewActivity extends ActionBarActivity {

    private WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle("  "+"Video Stream");


        mWebView = (WebView)findViewById(R.id.web_view);

        // Initially, webview will be zoomed out by the below code
        mWebView.getSettings().setLoadWithOverviewMode(true);

        //Normal viewport like a desktop browser. When false, its viewport is constrained to its own dimensions
        mWebView.getSettings().setUseWideViewPort(true);


        mWebView.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url);
                return true;
            }


        });

        mWebView.getSettings().setJavaScriptEnabled(true);

        String url = "http://watchmeprintagain.webege.com";

        mWebView.loadUrl(url);

    }

}
