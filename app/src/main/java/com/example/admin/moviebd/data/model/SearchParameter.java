package com.example.admin.moviebd.data.model;

public class SearchParameter {
    private String mKeySearch;
    private String mValueSearch;
    private String mDescription;

    public SearchParameter(String keySearch, String valueSearch, String description) {
        mKeySearch = keySearch;
        mValueSearch = valueSearch;
        mDescription = description;
    }

    public String getKeySearch() {
        return mKeySearch;
    }

    public void setKeySearch(String keySearch) {
        mKeySearch = keySearch;
    }

    public String getValueSearch() {
        return mValueSearch;
    }

    public void setValueSearch(String valueSearch) {
        mValueSearch = valueSearch;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
