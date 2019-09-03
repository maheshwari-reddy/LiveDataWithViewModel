package com.livedatasample.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeneralResponse {

    @SerializedName("resultCount")
    private  String resultCount;
    @SerializedName("results")
    private List<Songs> results;

    public String getResultCount() {
        return resultCount;
    }

    public void setResultCount(String resultCount) {
        this.resultCount = resultCount;
    }

    public List<Songs> getResults() {
        return results;
    }

    public void setResults(List<Songs> results) {
        this.results = results;
    }
}
