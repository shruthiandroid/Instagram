package test.instagram.screens.utils;

public class InstagramConstants {

	public static final String APP_AUTH_URL = "https://api.instagram.com/oauth/authorize/";
	public static final String APP_TOKEN_URL = "https://api.instagram.com/oauth/access_token";
	public static final String APP_API_URL = "https://api.instagram.com/v1";
	public static final String APP_SELFIE_URL = "https://api.instagram.com/v1/tags/selfie/media/recent?access_token=";
	public static final String CLIENT_ID = "d68826c6193c430d8407070144bb8c1c";
	public static final String CLIENT_SECRET = "35360e1b71a0439b8e2edd2a5d35c2f0";
	public static String CALLBACKURL = "http://localhost";

	public static final String AUTH_URL_STRING = APP_AUTH_URL
			+ "?client_id="
			+ CLIENT_ID
			+ "&redirect_uri="
			+ CALLBACKURL
			+ "&response_type=code&display=touch&scope=likes+comments+relationships";

	public static final String KEY_ACCESS_TOKEN = "accesstoken";
	public static final String KEY_REQUEST_TOKEN = "requesttoken";

	public static final String MSG_NO_NETWORK = "Please Check Your Network ";
	public static final String MSG_WAIT = "Please Wait...";

	public static final int REQUEST_FETCH_TOKEN = 101;
	public static final int REQUEST_FETCH_SELIEF = 102;

	public static final int RESPONSE_SUCCESS = 103;
	public static final int RESPONSE_FAILURE = 104;
	public static final int RESPONSE_NO_NETWORK = 105;

}
