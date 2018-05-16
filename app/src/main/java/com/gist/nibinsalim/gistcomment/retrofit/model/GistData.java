package com.gist.nibinsalim.gistcomment.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


/*
 * @class GistData
 * @author Nibin Salim
 * @Description Model class where response for getting gist data is being stored
 */

public class GistData {

    @SerializedName("filename")
    @Expose
    private String filename;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("raw_url")
    @Expose
    private String rawUrl;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("truncated")
    @Expose
    private Boolean truncated;
    @SerializedName("content")
    @Expose
    private String content;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRawUrl() {
        return rawUrl;
    }

    public void setRawUrl(String rawUrl) {
        this.rawUrl = rawUrl;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getTruncated() {
        return truncated;
    }

    public void setTruncated(Boolean truncated) {
        this.truncated = truncated;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}


