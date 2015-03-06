package test.instagram.screens;

import test.instagram.screens.R;
import test.instagram.screens.utils.AsyncRequest;
import test.instagram.screens.utils.EntityResponse;
import test.instagram.screens.utils.InstaGramUtils;
import test.instagram.screens.utils.InstagramConstants;
import test.instagram.screens.utils.ResponseListener;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

@SuppressLint("HandlerLeak")
public class LoginActivity extends BaseActivity implements ResponseListener {

	private WebView mWebView;
	private ProgressBar mProgressBarBar;
	private Handler mHandler;

	private static boolean isStart = false;
	private String mToken = "";

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		mProgressBarBar = (ProgressBar) findViewById(R.id.progressbar);
		mProgressBarBar.setVisibility(ProgressBar.GONE);
		mWebView = (WebView) findViewById(R.id.webview);
		mWebView.setVerticalScrollBarEnabled(false);
		mWebView.setHorizontalScrollBarEnabled(false);

		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setBuiltInZoomControls(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setUseWideViewPort(true);
		mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

		if (!isStart) {
			mWebView.setWebViewClient(new AuthWebViewClient());
			webSettings.setJavaScriptEnabled(true);
			mWebView.loadUrl(InstagramConstants.AUTH_URL_STRING);
		}
		mProgressBarBar.setVisibility(ProgressBar.GONE);
		isStart = true;
		mHandler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				if (isStart) {
					isStart = false;

					getAccessToken();
				}
			}
		};
	}

	public class AuthWebViewClient extends WebViewClient {

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			mProgressBarBar.setVisibility(ProgressBar.VISIBLE);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			mProgressBarBar.setVisibility(ProgressBar.GONE);
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			if (url.startsWith(InstagramConstants.CALLBACKURL)) {
				mToken = InstaGramUtils.getRequestToken(url);
				mHandler.sendEmptyMessage(0);
				return true;
			}
			return false;
		}
	}

	private void getAccessToken() {
		AsyncRequest req = new AsyncRequest(this, getApplicationContext(),
				InstagramConstants.REQUEST_FETCH_TOKEN, mToken);
		req.startResult();

	}

	public void showImagesList() {
		Intent intent = new Intent(LoginActivity.this, SelfieImagesListActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void onStarted() {
		mProgressBarBar.setVisibility(ProgressBar.VISIBLE);

	}

	@Override
	public void onNetworkError(int errorType) {
		
		
		noNetwork();
	}

	@Override
	public void onFailure(EntityResponse erroMsg) {

	}

	@Override
	public void onSuccess(EntityResponse obj) {
		mProgressBarBar.setVisibility(ProgressBar.GONE);
		showImagesList();
	}

}
