package com.example.admin.moviebd.data.model;

import org.json.JSONObject;

import java.io.Serializable;

public class MovieRecommendation implements Serializable {
    private int mId;
    private String mName;
    private String mDate;
    private String mPathImage;
    private double mVote;

    public MovieRecommendation(int id, String name, String date, String pathImage, double vote) {
        mId = id;
        mName = name;
        mDate = date;
        mPathImage = pathImage;
        mVote = vote;
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

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getPathImage() {
        return mPathImage;
    }

    public void setPathImage(String pathImage) {
        mPathImage = pathImage;
    }

    public double getVote() {
        return mVote;
    }

    public void setVote(double vote) {
        mVote = vote;
    }

    public MovieRecommendation(JSONObject jsonObject) {
        try {
            mId = jsonObject.optInt(NameParseUrl.ID, 0);
            mName = jsonObject.optString(NameParseUrl.NAME, null);
            mDate = jsonObject.optString(NameParseUrl.DATE, null);
            mPathImage = jsonObject.optString(NameParseUrl.PATH_IMAGE, null);
            mVote = jsonObject.optDouble(NameParseUrl.VOTE, 0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public @interface NameParseUrl {
        String RESULT = "results";
        String ID = "id";
        String NAME = "title";
        String DATE = "release_date";
        String PATH_IMAGE = "poster_path";
        String VOTE = "vote_average";
    }
}
