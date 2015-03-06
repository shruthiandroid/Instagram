package test.instagram.screens;

import test.instagram.screens.app.AppController;
import test.instagram.screens.utils.AppKeys;
import android.os.Bundle;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class ViewLargeImage extends BaseActivity {

	private NetworkImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_max_layout);

		iv = (NetworkImageView) findViewById(R.id.ivZoom);
		String url = getIntent().getStringExtra(AppKeys.KEY_URL);
		ImageLoader imageLoader = AppController.getInstance().getImageLoader();
		iv.setImageUrl(url, imageLoader);

	}

}
