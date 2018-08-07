package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by 17000719 on 7/8/2018.
 */

public class WebPage extends AppCompatActivity{

    WebView wvRPPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        wvRPPage = findViewById(R.id.webViewRPPage);
        wvRPPage.setWebViewClient(new WebViewClient());



        Intent intentReceived = getIntent();
        String strURL = intentReceived.getStringExtra("URL");
        wvRPPage.loadUrl(strURL);

    }
}
