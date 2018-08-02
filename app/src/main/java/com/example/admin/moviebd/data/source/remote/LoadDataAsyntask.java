package com.example.admin.moviebd.data.source.remote;

import android.os.AsyncTask;

import com.example.admin.moviebd.data.source.MovieDataSource;
import com.example.admin.moviebd.utils.Constants;
import com.example.admin.moviebd.utils.common.StringUtils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadDataAsyntask extends AsyncTask<String, String, String> {
    private Exception mException;
    private MovieDataSource.Callback mCallback;

    public LoadDataAsyntask(MovieDataSource.Callback callback) {
        this.mCallback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mCallback.onStartLoading();
    }


    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(Constants.BaseApiUrl.METHOD_REQUEST_API);
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = StringUtils.convertStreamToString(in);
        } catch (Exception e) {
            mException = e;
        }
        return response;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (mCallback == null){
            return;
        }

        if (mException != null) {
            mCallback.onGetFailure(mException);
        } else {
            mCallback.onGetSuccess(s);
            mCallback.onComplete();
        }
    }
}
