package com.gist.nibinsalim.gistcomment.preference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class PreferenceManager {
    // make private static instance of Sessionmanager class
    private static PreferenceManager sessionManager;
    // Shared Preferences
    public static SharedPreferences pref;
    // Editor for Shared preferences
    private Editor editor;
    // Context
    private Context mContext;
    //tag for debugging log
    String TAG=PreferenceManager.class.getSimpleName();

    // Constructor
    @SuppressLint("CommitPrefEdits")
    private PreferenceManager(Context context) {
        this.mContext = context;
        pref = mContext.getSharedPreferences(PreferenceHelper.PREFERENCE_NAME,
                PreferenceHelper.PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * getInstance method is used to initialize PreferenceManager singelton
     * instance
     *
     * @param context context instance
     * @return Singelton session manager instance
     */
    public static PreferenceManager getInstance(Context context) {
        if (sessionManager == null) {
            sessionManager = new PreferenceManager(context);
        }
        return sessionManager;
    }



    /**
     * Update Password session
     */
    public void updateUserPassword(String password) {
        editor.putString(PreferenceHelper.KEY_USER_PASSWORD, password);
        // commit changes
        editor.commit();
    }

    /**
     * Update userName session
     */
    public void updateUserEmail(String userName) {
        editor.putString(PreferenceHelper.KEY_USER_EMAIL, userName);
        // commit changes
        editor.commit();
    }

    /**
     * Get Login User name
     */
    public String getUserName() {
        return pref.getString(PreferenceHelper.KEY_USER_PASSWORD, null);
    }

    /**
     * Get Login User Email
     */
    public String getUserEmail() {
        return pref.getString(PreferenceHelper.KEY_USER_EMAIL, null);
    }

    /**
     * Get Login User Email
     */
    public String getUserPassword() {
        return pref.getString(PreferenceHelper.KEY_USER_PASSWORD, null);
    }


}
