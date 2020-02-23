package com.example.swproject.Activitiies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.example.swproject.R;

public class GoSiteActivity extends AppCompatActivity {

    ImageButton closebutton;
    private WebView webView;
    private WebSettings webSettings;
    private String hompagetext = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gosite);

        closebutton = findViewById(R.id.closebutton);
        closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoSiteActivity.super.onBackPressed();
            }
        });

        Intent intent = getIntent();
        hompagetext = intent.getStringExtra("hompage");
        Log.d("hompage", intent.getStringExtra("hompage"));


        webView = findViewById(R.id.webview_go_site);
        webView.setWebViewClient(new WebViewClient()); //클릭시 새창 안뜨도록
        webSettings = webView.getSettings();//세부 세팅 등록
        webSettings.setJavaScriptEnabled(true);//웹페이지 자바스쿨비트 허용 여부
        webSettings.setSupportMultipleWindows(false);//새창 띄우기 허용 여부
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false); //자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        webSettings.setLoadWithOverviewMode(true); // 매타태그 허용 여부
        webSettings.setUseWideViewPort(true); //화면 사이즈 맞추기 허용 여부
        webSettings.setSupportZoom(true);//화면 줌 허용 여부
        webSettings.setBuiltInZoomControls(true); //화면 확대 축소 허용 여부
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //컨텐츠 사이즈 맞추기
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); //브라우저 캐시 허용 여부
        webSettings.setDomStorageEnabled(true);//로컬 저장소 허용 여부
        webView.loadUrl(hompagetext); //웹 뷰 시작


    }
}
