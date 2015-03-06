package test.instagram.screens.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class InstgramPreferences {

	public static  void setAccessToken(Context ctx, String accToken, String requestToken) {
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(ctx);
		Editor edit = pref.edit();
		edit.putString(InstagramConstants.KEY_ACCESS_TOKEN, accToken);
		edit.putString(InstagramConstants.KEY_REQUEST_TOKEN, requestToken);
		edit.commit();
	}

	public static String requestToken(Context ctx) {
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(ctx);
		String accToken = pref.getString(InstagramConstants.KEY_ACCESS_TOKEN, "");
		return accToken;
	}

}
