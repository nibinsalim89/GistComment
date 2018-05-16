package com.gist.nibinsalim.gistcomment.retrofit;
import com.gist.nibinsalim.gistcomment.retrofit.model.GetCommentsResponse;
import com.gist.nibinsalim.gistcomment.retrofit.model.PostCommentRequest;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiInterface {

    @GET("{gist_id}/comments")
    Call<List<GetCommentsResponse>> getComments(@Path("gist_id") String gist_id);

    @GET("{gist_id}")
    Call<ResponseBody> getTheGist(@Path("gist_id") String gist_id);

    @POST("{gist_id}/comments")
    Call<PostCommentRequest> postComment(@Path("gist_id") String gist_id, @Body PostCommentRequest postCommentRequest);

}
