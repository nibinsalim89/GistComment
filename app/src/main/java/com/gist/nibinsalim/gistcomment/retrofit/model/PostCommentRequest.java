package com.gist.nibinsalim.gistcomment.retrofit.model;

import com.google.gson.annotations.SerializedName;

/*
 * @class PostCommentRequest
 * @author Nibin Salim
 * @Description Model class where comment to be send is set
 */

public class PostCommentRequest {

    @SerializedName("body")
    String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
