package test.instagram.screens.utils;

import android.content.Context;
import android.os.AsyncTask;

public class AsyncRequest {

	private ResponseListener listener;
	private Context mCtx;
	private int REQ_ID = -1;

	private String mRequestToken;

	public AsyncRequest(ResponseListener listener, Context ctx, int reqId,
			String reqToken) {
		this.listener = listener;
		mCtx = ctx;
		REQ_ID = reqId;
		mRequestToken = reqToken;
	}

	public synchronized void startResult() {
		if (InstaGramUtils.isNetworkAvaliable(mCtx))
			new GetResult().execute();
		else
			listener.onNetworkError(1);
	}

	class GetResult extends AsyncTask<Integer, Void, EntityResponse> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			listener.onStarted();
		}

		@Override
		protected EntityResponse doInBackground(Integer... params) {
			EntityResponse res = new EntityResponse();
			if (REQ_ID == InstagramConstants.REQUEST_FETCH_TOKEN) {
				res = InstaGramUtils.setAccessToken(mCtx, mRequestToken);
			} else if (REQ_ID == InstagramConstants.REQUEST_FETCH_SELIEF) {
				res = InstaGramUtils.getSelfieData(mCtx);
			}
			return res;
		}

		@Override
		protected void onPostExecute(EntityResponse result) {
			super.onPostExecute(result);
			if (result.responseId == InstagramConstants.RESPONSE_SUCCESS) {
				listener.onSuccess(result);
			} else {
				listener.onFailure(result);
			}
		}
	}

}
