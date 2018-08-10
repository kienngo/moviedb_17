package com.example.admin.moviebd.utils.common;

import android.content.Context;
import android.widget.Toast;

import com.example.admin.moviebd.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

public class StringUtils {
    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void checkInsertMovie(Context context, boolean isState) {
        if (isState) {
            Toast.makeText(context, R.string.add_movie_success, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, R.string.exits_movie, Toast.LENGTH_SHORT).show();
        }
    }

    public static void checkDeleteMovie(Context context, boolean isState) {
        if (isState) {
            Toast.makeText(context, R.string.delete_movie, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, R.string.not_delete_movie_success, Toast.LENGTH_SHORT).show();
        }
    }

    public static String formatStringMovieUrl(String apiLink, String contentApi, int pageDefault) {
        return String.format(Locale.getDefault(), apiLink, contentApi, pageDefault);
    }

    public static String formatStringSearchUrl(String apiLink,
                                               String contentApi, String query, int pageDefault) {
        return String.format(Locale.getDefault(), apiLink, contentApi, query, pageDefault);
    }

    public static String formatStringGenresUrl(String apiLink, String contentApi) {
        return String.format(Locale.getDefault(), apiLink, contentApi);
    }

    public static String formatStringSearchGenresUrl(String apiLink, String contentApi,
                                                     String query, int pageDefault) {
        return String.format(Locale.getDefault(), apiLink, contentApi, query, pageDefault);
    }
}
