package com.zsx.rxjavatest.ui.activity;

import com.zsx.rxjavatest.data.model.UserInfo;

/**
 * 用户信息相关页面
 */
public interface UserView {
    /**
     * 登录
     */
    void login();

    /**
     * 显示用户信息
     * @param userInfo 用户信息
     */
    void showUserInfo(UserInfo userInfo);

    /**
     * 退出登录
     */
    void logout();

    /**
     * 加载页面
     */
    void showProgressPage();

    /**
     * 错误、异常页面
     */
    void showErrorPage();

}
