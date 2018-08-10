package com.example.admin.moviebd.data.model;

import org.json.JSONObject;

import java.io.Serializable;

public class Genres implements Serializable {
    private int mId;
    private String mName;

    public Genres(int id, String name) {
        mId = id;
        mName = name;
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

    public Genres(JSONObject jsonObject) {
        try {
            mId = jsonObject.optInt(Genres.NameParseUrl.ID, 0);
            mName = jsonObject.optString(Genres.NameParseUrl.NAME, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public @interface NameParseUrl {
        String GENRES = "genres";
        String ID = "id";
        String NAME = "name";
    }
}
