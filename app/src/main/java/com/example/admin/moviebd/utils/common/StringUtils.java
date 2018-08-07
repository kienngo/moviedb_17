package com.example.admin.moviebd.utils.common;

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

    public static String formatStringMovieUrl(String apiLink, String contentApi, int pageDefault) {
        return String.format(Locale.getDefault(), apiLink, contentApi, pageDefault);
    }

    public static String formatStringSearchUrl(String apiLink, String contentApi, String query, int pageDefault) {
        return String.format(Locale.getDefault(), apiLink, contentApi, query, pageDefault);
    }

    public static String formatStringGenresUrl(String apiLink, String contentApi) {
        return String.format(Locale.getDefault(), apiLink, contentApi);
    }

    public static String formatStringSearchGenresUrl(String apiLink, String contentApi, String query, int pageDefault) {
        return String.format(Locale.getDefault(), apiLink, contentApi, query, pageDefault);
    }
}
