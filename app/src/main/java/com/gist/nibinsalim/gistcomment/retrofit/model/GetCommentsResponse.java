package com.gist.nibinsalim.gistcomment.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * @class GetCommentsResponse
 * @author Nibin Salim
 * @Description Model class where response for getting comment is being stored
 */
public class GetCommentsResponse implements Serializable

    {

    @SerializedName("url")
    String url;
        @SerializedName("id")
        String  id;
        @SerializedName("user")
        @Expose
        User user;
        @SerializedName("author_association")
        @Expose
        String authorAssociation;
        @SerializedName("created_at")
        @Expose
        String createdAt;
        @SerializedName("updated_at")
        @Expose
        String  updatedAt;
        @SerializedName("body")
        @Expose
        String body;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getAuthorAssociation() {
            return authorAssociation;
        }

        public void setAuthorAssociation(String authorAssociation) {
            this.authorAssociation = authorAssociation;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public class User {

            @SerializedName("login")
            String login;
            @SerializedName("id")
            String  id;
            @SerializedName("avatar_url")
            String avatarUrl;

            public String getLogin() {
                return login;
            }

            public void setLogin(String login) {
                this.login = login;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }
        }

    }
