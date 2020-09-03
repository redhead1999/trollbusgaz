package com.redhead.y14.routes.FragmentsAuto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.fragment.app.Fragment;
import com.redhead.y14.routes.C0546R;

public class BlankFragment3A extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C0546R.layout.fragment_blank_fragment3_auto, container, false);
        WebView webView = (WebView) v.findViewById(C0546R.C0548id.webView3A);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/indexA4.html");
        return v;
    }
}
