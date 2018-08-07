package com.example.admin.moviebd.data.model;

import org.json.JSONObject;

public class Genre {
    private int mId;
    private String mName;
    private boolean mStateGenres;

    public Genre(JSONObject jsonObject) {
        try {
            mId = jsonObject.optInt(Genre.NameParseUrl.ID, 0);
            mName = jsonObject.optString(Genre.NameParseUrl.NAME, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public boolean isStateGenres() {
        return mStateGenres;
    }

    public void setStateGenres(boolean stateGenres) {
        mStateGenres = stateGenres;
    }

    public @interface NameParseUrl {
        String GENRES = "genres";
        String ID = "id";
        String NAME = "name";
    }
}
