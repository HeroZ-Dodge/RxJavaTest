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

    public static PreferencesHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (PreferencesHelper.class) {
                mInstance = new PreferencesHelper(context);
            }
        }
        return mInstance;
    }

    public static void putString(Context context, String key, String value) {
        getInstance(context).mPreferences.edit().putString(key, value).commit();
    }


    public static String getString(Context context, String key) {
        return getInstance(context).mPreferences.getString(key, null);
    }


    public static void putInt(Context context, String key, int value) {
        getInstance(context).mPreferences.edit().putInt(key, value).commit();
    }


    public static int getInt(Context context, String key) {
        return getInstance(context).mPreferences.getInt(key, -1);
    }


    public static void putBoolean(Context context, String key, boolean value) {
        getInstance(context).mPreferences.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key) {
        return getInstance(context).mPreferences.getBoolean(key, false);
    }

}
