package com.example.kk;

import android.os.Build;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.text.SpannableStringBuilder;

//public class MainActivity extends AppCompatActivity {
public class MainActivity extends Activity implements OnClickListener{

    //private final int FP = ViewGroup.LayoutParams.FILL_PARENT;
    //private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;

    boolean checkOnPageStartedCalled = false;
    private EditText textUrl;
    private Button buttonGo;
    private WebView webview;

    public static int reloaded = 0;
    public final int reloadmax = 4;

    //if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB){
    //    reloadmax = 0;
    //} else {
    //    reloadmax = 5;
    //}
    @Override protected void onCreate(Bundle icicle) {
        android.util.Log.d("MYDEBUG", "" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        super.onCreate(icicle);

        setContentView(R.layout.activity_main);

        //WebView webview = (WebView) findViewById(R.id.webView1);
        webview = (WebView) findViewById(R.id.webView1);



        //if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB){
        //    reloadmax = 0;
        //} else {
        //    reloadmax = 5;
        //}
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);

        String pdfUrl = "https://www.data.jma.go.jp/fcd/yoho/data/jishin/kaisetsu_tanki_latest.pdf";
        String url = "http://docs.google.com/gview?embedded=true&url=" + pdfUrl;

        //webview.invalidate();
        webview.loadUrl(url);

        webview.setWebViewClient(new WebViewClient() {
            boolean checkOnPageStartedCalled = false;

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                checkOnPageStartedCalled = true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //SystemClock.sleep(1000);
                if (reloaded < reloadmax) {
                    if (checkOnPageStartedCalled == true) {
                        android.util.Log.d("MYDEBUG", "" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        //SystemClock.sleep(1000);
                        webview.reload();
                        reloaded++;
                        android.util.Log.d("MYDEBUG", "" + Thread.currentThread().getStackTrace()[2].getLineNumber()
                                + "   " + reloaded);
                    } else {
                        android.util.Log.d("MYDEBUG", "" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        showPdfFile(url);
                    }
                }
            }

        });

    }

    private LinearLayout.LayoutParams createParam(int w, int h){
        return new LinearLayout.LayoutParams(w, h);
    }
    public void onClick(View v) {
        android.util.Log.d("MYDEBUG", "" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        SpannableStringBuilder url = (SpannableStringBuilder)textUrl.getText();
        webview.loadUrl(url.toString());
    }

    public void onResume() {
        android.util.Log.d("MYDEBUG", "" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        super.onResume();
        webview.reload();
    }
    private void showPdfFile(final String urlString) {
        //showProgress();
        //webview.invalidate();
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            webview.getSettings().setJavaScriptEnabled(true);
        }
        //webview.getSettings().setSupportZoom(true);
        //webview.getSettings().setDisplayZoomControls(false);
        webview.loadUrl(urlString);
        webview.setWebViewClient(new WebViewClient() {
            boolean checkOnPageStartedCalled = false;

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                checkOnPageStartedCalled = true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                android.util.Log.d("MYDEBUG", "" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                //SystemClock.sleep(1000);
                if (reloaded <= 0) {
                    if (checkOnPageStartedCalled == true) {
                        android.util.Log.d("MYDEBUG", "" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        //SystemClock.sleep(1000);
                        webview.reload();
                        reloaded++;
                        android.util.Log.d("MYDEBUG", "" + Thread.currentThread().getStackTrace()[2].getLineNumber()
                                + "   " + reloaded);
                    } else {
                        android.util.Log.d("MYDEBUG", "" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        showPdfFile(url);
                    }
                }
            }

        });
    }
}