package com.redhead.y14.routes.FragmentsAuto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.fragment.app.Fragment;
import com.redhead.y14.routes.C0546R;

public class BlankFragment14A extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C0546R.layout.fragment_blank_fragment14_a, container, false);
        WebView webView = (WebView) v.findViewById(C0546R.C0548id.webView14A);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/indexA25.html");
        return v;
    }
}
