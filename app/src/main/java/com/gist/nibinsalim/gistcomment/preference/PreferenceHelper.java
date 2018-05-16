package com.gist.nibinsalim.gistcomment.preference;

/**
 * Preference Helper Class
 *
 * @author nibinsalim
 */
public interface PreferenceHelper {
    // Shared Preferences file name
    String PREFERENCE_NAME   = "EXCHANGE_APP_PREFERENCE";
    // Shared Preferences mode
    int PRIVATE_MODE         = 0;
    String KEY_USER_PASSWORD = "USER_PASSWORD";
    String KEY_USER_EMAIL    = "USER_EMAIL";
    String RATING_FILTER_MAX = "RATING_FILTER_MAX";
    String RATING_FILTER_MIN = "RATING_FILTER_MIN";
    String VERIFIED_AMOUNT   = "VERIFIED_AMOUNT";
    String LOGGED_IN            = "LOGGED_IN";
    String EXCHANGE_RATE            = "EXCHANGE_RATE";
}
