package test.instagram.screens;

import test.instagram.screens.utils.InstagramConstants;
import android.app.Activity;
import android.widget.Toast;

public class BaseActivity extends Activity {

	

	protected void noNetwork() {
		Toast.makeText(getApplicationContext(), InstagramConstants.MSG_NO_NETWORK,Toast.LENGTH_SHORT).show();
	}

}
