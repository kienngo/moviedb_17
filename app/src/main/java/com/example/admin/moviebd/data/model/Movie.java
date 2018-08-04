package com.example.admin.moviebd.data.model;

import org.json.JSONObject;

import java.io.Serializable;

public class Movie implements Serializable {
    private int mId;
    private int mVoteAverage;
    private String mTitleVideo;
    private String mPosterPath;
    private String mReleaseDate;

    public Movie(int id, int voteAverage, String titleVideo, String posterPath, String releaseDate) {
        mId = id;
        mVoteAverage = voteAverage;
        mTitleVideo = titleVideo;
        mPosterPath = posterPath;
        mReleaseDate = releaseDate;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(int voteAverage) {
        mVoteAverage = voteAverage;
    }

    public String getTitleVideo() {
        return mTitleVideo;
    }

    public void setTitleVideo(String titleVideo) {
        mTitleVideo = titleVideo;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public Movie(JSONObject jsonObject) {
        try {
            mId = jsonObject.optInt(NameParseUrl.ID, 0);
            mVoteAverage = jsonObject.optInt(NameParseUrl.VOTE_AVERAGE, 0);
            mTitleVideo = jsonObject.optString(NameParseUrl.TITLE, null);
            mPosterPath = jsonObject.optString(NameParseUrl.POSTER_PATH, null);
            mReleaseDate = jsonObject.optString(NameParseUrl.RELEASE_DATE, null);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public @interface NameParseUrl {
        String RESULTS = "results";
        String ID = "id";
        String VOTE_AVERAGE = "vote_average";
        String TITLE = "title";
        String POSTER_PATH = "poster_path";
        String RELEASE_DATE = "release_date";
    }
}
