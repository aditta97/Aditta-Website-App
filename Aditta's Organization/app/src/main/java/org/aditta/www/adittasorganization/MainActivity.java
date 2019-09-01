package org.aditta.www.adittasorganization;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView mWebView;

    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            public void onRefresh(){
                LoadWeb();
            }
        });

        LoadWeb();

    }

    public void LoadWeb()
    {
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.loadUrl("https://www.aditta.org/");
        swipe.setRefreshing(true);
        mWebView.setWebViewClient(new WebViewClient() {

            public void onReveivedError(WebView view, int errorCode, String description, String failingUrl){
                mWebView.loadUrl("file://android_asset/error.html");
            }

            public void onPageFinished(WebView view, String url)
            {
                //hide the swipe refreshlayout
                swipe.setRefreshing(false);
            }

        });

    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

