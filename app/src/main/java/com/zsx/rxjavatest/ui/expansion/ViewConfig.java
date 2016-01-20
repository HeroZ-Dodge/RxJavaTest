package com.zsx.rxjavatest.ui.expansion;

import android.view.View;

import com.zsx.rxjavatest.R;

/**
 * 扩展视图 配置
 */
public class ViewConfig {
    String mProgressMsg = "正在加载...";
    int mProgressPageRes = R.layout.progress_page;
    View mProgressPage;
    int mErrorPageRes = R.layout.error_page;
    View mErrorPage;

}
