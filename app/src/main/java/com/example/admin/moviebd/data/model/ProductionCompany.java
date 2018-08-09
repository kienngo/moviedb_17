package com.example.admin.moviebd.data.model;

import org.json.JSONObject;

public class ProductionCompany {
    private int mId;
    private String mName, mLogoPath;

    public ProductionCompany(int id, String name, String logoPath) {
        mId = id;
        mName = name;
        mLogoPath = logoPath;
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

    public String getLogoPath() {
        return mLogoPath;
    }

    public void setLogoPath(String logoPath) {
        mLogoPath = logoPath;
    }

    public ProductionCompany(JSONObject jsonObject) {
        try {
            mId = jsonObject.optInt(NameParseUrl.COMPANIES, 0);
            mName = jsonObject.optString(NameParseUrl.NAME, null);
            mLogoPath = jsonObject.optString(NameParseUrl.LOGO_PATH, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public @interface NameParseUrl {
        String COMPANIES = "production_companies";
        String ID = "id";
        String NAME = "name";
        String LOGO_PATH = "logo_path";
    }
}
