package com.example.admin.moviebd.data.model;

import org.json.JSONObject;

public class SearchResult {
    private int mId;
    private String mNameTelevision;
    private String mTitleMovie;
    private String mMediaType;
    private double mVoteAverage;
    private String mPostPath;
    private String mFirstAirDate;
    private String mReleaseDate;

    public SearchResult(JSONObject jsonObject) {
        try {
            mId = jsonObject.optInt(NameParseUrl.ID, 0);
            mNameTelevision = jsonObject.optString(NameParseUrl.NAME_TELEVISION, null);
            mTitleMovie = jsonObject.optString(NameParseUrl.TITLE_MOVIE, null);
            mMediaType = jsonObject.optString(NameParseUrl.MEDIA_TYPE, null);
            mVoteAverage = jsonObject.optDouble(NameParseUrl.VOTE_AVERAGE, 0);
            mPostPath = jsonObject.optString(NameParseUrl.POST_PATH, null);
            mFirstAirDate = jsonObject.optString(NameParseUrl.FIRST_AIR_DATE, null);
            mReleaseDate = jsonObject.optString(NameParseUrl.RELEASE_DATE, null);
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

    public String getNameTelevision() {
        return mNameTelevision;
    }

    public void setNameTelevision(String nameTelevision) {
        mNameTelevision = nameTelevision;
    }

    public String getTitleMovie() {
        return mTitleMovie;
    }

    public void setTitleMovie(String titleMovie) {
        mTitleMovie = titleMovie;
    }

    public String getMediaType() {
        return mMediaType;
    }

    public void setMediaType(String mediaType) {
        mMediaType = mediaType;
    }

    public double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public String getPostPath() {
        return mPostPath;
    }

    public void setPostPath(String postPath) {
        mPostPath = postPath;
    }

    public String getFirstAirDate() {
        return mFirstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        mFirstAirDate = firstAirDate;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public @interface NameParseUrl {
        String RESULTS = "results";
        String ID = "id";
        String NAME_TELEVISION = "name";
        String TITLE_MOVIE = "title";
        String MEDIA_TYPE = "media_type";
        String VOTE_AVERAGE = "vote_average";
        String POST_PATH = "poster_path";
        String FIRST_AIR_DATE = "first_air_date";
        String RELEASE_DATE = "release_date";
    }
}
