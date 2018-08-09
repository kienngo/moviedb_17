package com.example.admin.moviebd.data.model;

import org.json.JSONObject;

import java.io.Serializable;

public class MovieInformation implements Serializable {
    private int mId;
    private String mTitle, mOverview, mPathImage, mVote, mDate;

    public MovieInformation(int id, String title, String overview, String pathImage, String vote, String date) {
        mId = id;
        mTitle = title;
        mOverview = overview;
        mPathImage = pathImage;
        mVote = vote;
        mDate = date;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getPathImage() {
        return mPathImage;
    }

    public void setPathImage(String pathImage) {
        mPathImage = pathImage;
    }

    public String getVote() {
        return mVote;
    }

    public void setVote(String vote) {
        mVote = vote;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public MovieInformation(JSONObject jsonObject) {
        try {
            mId = jsonObject.optInt(NameParseUrl.ID, 0);
            mTitle = jsonObject.optString(NameParseUrl.TITLE, null);
            mOverview = jsonObject.optString(NameParseUrl.OVERVIEW, null);
            mPathImage = jsonObject.optString(NameParseUrl.PATH_IMAGE, null);
            mVote = jsonObject.optString(NameParseUrl.VOTE, null);
            mDate = jsonObject.optString(NameParseUrl.DATE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public @interface NameParseUrl {
        String ID = "id";
        String TITLE = "title";
        String OVERVIEW = "overview";
        String PATH_IMAGE = "poster_path";
        String VOTE = "vote_average";
        String DATE = "release_date";
    }
}
