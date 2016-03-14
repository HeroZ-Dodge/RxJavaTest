package com.zsx.rxjavatest.util;

import android.content.Context;
import android.net.Uri;


public class UriUtil {

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    /**
     * 简单的从资源ID转换成Uri
     *
     * @param context    上行文
     * @param resourceId 资源ID
     * @return Uri
     */
    private static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }

}
