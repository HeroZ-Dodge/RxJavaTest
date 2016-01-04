package com.zsx.rxjavatest.data.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2015/12/29.
 */
public class PreferencesHelper {

    public final static String PREFERENCES_NAME = "com.zsx.rxjavatest";

    private static PreferencesHelper mInstance;
    private SharedPreferences mPreferences;


    private PreferencesHelper(Context context) {
        if (mPreferences == null) {
            mPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        }
    }

    public static void init(Context context) {
        if (mInstance == null) {
            synchronized (PreferencesHelper.class) {
                mInstance = new PreferencesHelper(context);
            }
        }
    }

    public static PreferencesHelper getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("PreferencesHelper doesn't init!");
        }
        return mInstance;
    }

    public static void putString(String key, String value) {
        getInstance().mPreferences.edit().putString(key, value).commit();
    }


    public static String getString(String key) {
        return getInstance().mPreferences.getString(key, null);
    }


    public static void putInt(String key, int value) {
        getInstance().mPreferences.edit().putInt(key, value).commit();
    }


    public static int getInt(String key) {
        return getInstance().mPreferences.getInt(key, -1);
    }


    public static void putBoolean(String key, boolean value) {
        getInstance().mPreferences.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(String key) {
        return getInstance().mPreferences.getBoolean(key, false);
    }

}
