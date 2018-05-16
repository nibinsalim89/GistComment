package com.gist.nibinsalim.gistcomment.retrofit.model;

/**
 * Created by nibinsalim on 13/05/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * @class PostCommentResponse
 * @author Nibin Salim
 * @Description Model class where response for post comment is stored
 */



public class PostCommentResponse implements Serializable{

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

        @SerializedName("gravatar_id")
        String gravatarId;
        @SerializedName("url")
        String url;

        @SerializedName("html_url")
        String htmlUrl;
        @SerializedName("followers_url")
        String followersUrl;

        @SerializedName("gists_url")
        String gistsUrl;

        @SerializedName("starred_url")
        String starredUrl;


        @SerializedName("subscriptions_url")
        String subscriptionsUrl;



        @SerializedName("organizations_url")
        String organizationsUrl;


        @SerializedName("repos_url")
        String reposUrl;


        @SerializedName("events_url")
        String eventsUrl;


        @SerializedName("received_events_url")
        String receivedEventsUrl;

        @SerializedName("type")
        String type;

        @SerializedName("site_admin")
        String siteAdmin;

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

        public String getGravatarId() {
            return gravatarId;
        }

        public void setGravatarId(String gravatarId) {
            this.gravatarId = gravatarId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHtmlUrl() {
            return htmlUrl;
        }

        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        public String getFollowersUrl() {
            return followersUrl;
        }

        public void setFollowersUrl(String followersUrl) {
            this.followersUrl = followersUrl;
        }

        public String getGistsUrl() {
            return gistsUrl;
        }

        public void setGistsUrl(String gistsUrl) {
            this.gistsUrl = gistsUrl;
        }

        public String getStarredUrl() {
            return starredUrl;
        }

        public void setStarredUrl(String starredUrl) {
            this.starredUrl = starredUrl;
        }

        public String getSubscriptionsUrl() {
            return subscriptionsUrl;
        }

        public void setSubscriptionsUrl(String subscriptionsUrl) {
            this.subscriptionsUrl = subscriptionsUrl;
        }

        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        public void setOrganizationsUrl(String organizationsUrl) {
            this.organizationsUrl = organizationsUrl;
        }

        public String getReposUrl() {
            return reposUrl;
        }

        public void setReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
        }

        public String getEventsUrl() {
            return eventsUrl;
        }

        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }

        public void setReceivedEventsUrl(String receivedEventsUrl) {
            this.receivedEventsUrl = receivedEventsUrl;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSiteAdmin() {
            return siteAdmin;
        }

        public void setSiteAdmin(String siteAdmin) {
            this.siteAdmin = siteAdmin;
        }
    }

}

