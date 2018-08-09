package com.example.admin.moviebd.data.model;

import org.json.JSONObject;

public class Trailer {
    private String mId;
    private String mName;

    public Trailer(String id, String name) {
        mId = id;
        mName = name;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Trailer(JSONObject jsonObject) {
        try {
            mId = jsonObject.optString(NameParseUrl.ID, null);
            mName = jsonObject.optString(NameParseUrl.NAME, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public @interface NameParseUrl {
        String RESULT = "results";
        String ID = "key";
        String NAME = "name";
    }
}
