package com.gist.nibinsalim.gistcomment.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gist.nibinsalim.gistcomment.adapter.GistAdapter;
import com.gist.nibinsalim.gistcomment.retrofit.ApiInterface;
import com.gist.nibinsalim.gistcomment.adapter.CommentsAdapter;
import com.gist.nibinsalim.gistcomment.retrofit.model.GetCommentsResponse;
import com.gist.nibinsalim.gistcomment.retrofit.model.GistData;
import com.gist.nibinsalim.gistcomment.retrofit.model.PostCommentRequest;
import com.gist.nibinsalim.gistcomment.R;
import com.gist.nibinsalim.gistcomment.retrofit.RetrofitClientConfig;
import com.gist.nibinsalim.gistcomment.preference.PreferenceManager;
import com.gist.nibinsalim.gistcomment.utils.GistConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * @screen GistActivity
 * @author Nibin Salim
 * @Description Displays the gist with content, comment and filename and n option to add new comment to gist
 */
public class GistActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView mGistRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mGistAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager mGistLayoutManager;
    private Activity mActivity;
    private List<GetCommentsResponse> mCommentsResponse;
    private ProgressBar mProgressBar;
    private EditText mCommentsText;
    private TextView mGistDescription;
    private NestedScrollView mScrollview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gist_comment);
        initViews();

        //checks the username aand passowrd already entered,
        // if yes, calls the api to get gist and commnet
        //if not, the dispaly the dialog for credentials
        if (checkCredentials()) {
            getComments(false);
            getGist();
        } else {

            credentialsDialog();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuItem edit_item = menu.add(getString(R.string.menu_gist_credentials));
        edit_item.setIcon(R.drawable.user);
        edit_item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return super.onCreateOptionsMenu(menu);
    }



    //methosd handles the views present in the acitivty
    private void initViews() {
        mActivity = this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mCommentsResponse = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_comment);
        mGistRecyclerView = (RecyclerView) findViewById(R.id.rv_gist);
        mGistRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setNestedScrollingEnabled(false);
        mGistDescription = (TextView) findViewById(R.id.tv_description);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_comments);
        mScrollview = (NestedScrollView) findViewById(R.id.sv_nested);
        mCommentsText = (EditText) findViewById(R.id.et_comment);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return false ;
        }
        if(item.getTitle().equals(getString(R.string.menu_gist_credentials))){
            credentialsDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    //check for credentials
    private boolean checkCredentials() {

        if (PreferenceManager.getInstance(mActivity).getUserEmail() == null || PreferenceManager.getInstance(mActivity).getUserPassword() == null) {
            return false;
        } else {
            return true;
        }
    }

    //method to call the get api for comments from the gist
    private void getComments(final boolean scrollDown) {

        if(!isOnline(mActivity)){
            Toast.makeText(this, getString(R.string.toast_msg_no_internet), Toast.LENGTH_SHORT).show();
            return;
        }

        mProgressBar.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                RetrofitClientConfig.getInstance(PreferenceManager.getInstance(mActivity).getUserEmail(), PreferenceManager.getInstance(mActivity).getUserPassword()).create(ApiInterface.class);

        Call<List<GetCommentsResponse>> call = apiService.getComments(getIntent().getStringExtra(GistConstants.GIST_ID));
        call.enqueue(new Callback<List<GetCommentsResponse>>() {
            @Override
            public void onResponse(Call<List<GetCommentsResponse>> call, Response<List<GetCommentsResponse>> response) {
                mProgressBar.setVisibility(View.GONE);
                // use a linear layout manager
                mLayoutManager = new LinearLayoutManager(mActivity);
                mRecyclerView.setLayoutManager(mLayoutManager);
                // specify an adapter (see also next example)
                mCommentsResponse = response.body();
                mAdapter = new CommentsAdapter(R.layout.item_comment, mCommentsResponse, mActivity);
                mRecyclerView.setAdapter(mAdapter);
                if (scrollDown) {

                    mScrollview.post(new Runnable() {
                        @Override
                        public void run() {
                            mScrollview.fullScroll(NestedScrollView.FOCUS_DOWN);
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<List<GetCommentsResponse>> call, Throwable t) {
                t.printStackTrace();
                mProgressBar.setVisibility(View.GONE);
            }
        });

    }

    //method displays the dialog that is needed for entering the credentials
    private void credentialsDialog() {

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(getString(R.string.dialog_title_gist_credentials));
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);


        //edittext for username
        final EditText userName = new EditText(this);
        userName.setHint(getString(R.string.dialog_et_email));
        layout.addView(userName); // Notice this is an add method

        //Edittext for password
        final EditText passwordBox = new EditText(this);
        passwordBox.setHint(getString(R.string.dialog_et_password));
        layout.addView(passwordBox); // Another add method
        dialog.setView(layout); //
        if (PreferenceManager.getInstance(mActivity).getUserEmail() != null && PreferenceManager.getInstance(mActivity).getUserPassword() != null) {
            passwordBox.setText(PreferenceManager.getInstance(mActivity).getUserPassword());
            userName.setText(PreferenceManager.getInstance(mActivity).getUserEmail());
        }
        dialog.setPositiveButton(getString(R.string.dialog_btn_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (userName.getText().toString().trim().length() != 0 && passwordBox.getText().toString().trim().length() != 0) {
                    PreferenceManager.getInstance(mActivity).updateUserEmail(userName.getText().toString().trim());
                    PreferenceManager.getInstance(mActivity).updateUserPassword(passwordBox.getText().toString().trim());
                    dialog.cancel();
                    getComments(false);
                    getGist();
                }
            }
        });
        dialog.setNegativeButton(getString(R.string.dialog_btn_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.show();

    }

    //handles the send comment button click
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_send:
                postComments();
                break;
        }
    }

    //method calls the api to post the comments to gist
    public void postComments() {

        if(!isOnline(mActivity)){
            Toast.makeText(this, getString(R.string.toast_msg_no_internet), Toast.LENGTH_SHORT).show();
            return;
        }

        if (mCommentsText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, getString(R.string.toast_msg_enter_comment), Toast.LENGTH_SHORT).show();
            return;
        }
        mProgressBar.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                RetrofitClientConfig.getInstance(PreferenceManager.getInstance(mActivity).getUserEmail(), PreferenceManager.getInstance(mActivity).getUserPassword()).create(ApiInterface.class);

        PostCommentRequest postComment = new PostCommentRequest();
        postComment.setBody(mCommentsText.getText().toString());
        Call<PostCommentRequest> call = apiService.postComment(getIntent().getStringExtra(GistConstants.GIST_ID), postComment);
        call.enqueue(new Callback<PostCommentRequest>() {
            @Override
            public void onResponse(Call<PostCommentRequest> call, Response<PostCommentRequest> response) {
                getComments(true);
            }

            @Override
            public void onFailure(Call<PostCommentRequest> call, Throwable t) {
                t.printStackTrace();
                mProgressBar.setVisibility(View.GONE);
            }
        });

    }


    //method calls the api to get the gist content and filte type from the gist
    private void getGist() {

        mProgressBar.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                RetrofitClientConfig.getInstance(PreferenceManager.getInstance(mActivity).getUserEmail(), PreferenceManager.getInstance(mActivity).getUserPassword()).create(ApiInterface.class);

        Call<ResponseBody> call = apiService.getTheGist(getIntent().getStringExtra(GistConstants.GIST_ID));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mProgressBar.setVisibility(View.GONE);
                try {
                    ResponseBody jsonString = response.body();
                    getJson(new JSONObject(jsonString.string()).getJSONObject(GistConstants.FILES));
//                    mGistDescription.setText(new JSONObject(jsonString.string()).get("description").toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }


    //method to convert the file name and content from the json with dynamic filename
    // as key in to a model class and update the UI
    public void getJson(JSONObject json) {

        List<GistData> dataList = new ArrayList<>();
        Iterator<String> iter = json.keys();
        while (iter.hasNext()) {
            String key = iter.next();
            try {
                GistData data = new GistData();
                Object value = json.get(key);
                String val = value.toString();
                JSONObject object = new JSONObject(val);
                data.setContent(object.get(GistConstants.CONTENT).toString());
                data.setFilename(object.get(GistConstants.FILE_NAME).toString());
                data.setLanguage(object.get(GistConstants.LANGUAGE).toString());
                data.setRawUrl(object.get(GistConstants.RAW_URL).toString());
                dataList.add(data);


            } catch (JSONException e) {
                // Something went wrong!
            }
        }

        //updates the gist data to recyclerview
        if (dataList.size() != 0) {
            mGistLayoutManager = new LinearLayoutManager(mActivity);
            mGistRecyclerView.setLayoutManager(mGistLayoutManager);
            mGistAdapter = new GistAdapter(R.layout.item_comment, dataList, mActivity);
            mGistRecyclerView.setAdapter(mGistAdapter);
        }
    }

    //method checks whether you are connected to internet or not
    public  boolean isOnline(Activity activity) {

        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) activity
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI")) {
                if (ni.isConnected()) {
                    haveConnectedWifi = true;
                }
            }
            if (ni.getTypeName().equalsIgnoreCase("MOBILE")) {
                if (ni.isConnected()) {
                    haveConnectedMobile = true;
                }
            }
        }
        return haveConnectedWifi || haveConnectedMobile;
    }



}





