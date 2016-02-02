package com.zsx.rxjavatest.presenter.impl;

import com.zsx.rxjavatest.data.DataManager;
import com.zsx.rxjavatest.data.model.UserInfo;
import com.zsx.rxjavatest.ui.activity.UserView;

/**
 * 用户信息界面Presenter
 */
public class UserPresenter {

    private UserView mUserView;

    public UserPresenter(UserView userView) {
        mUserView = userView;
    }

    /* 登录操作 */
    public void login(String userName, String pw) {
        // 开启一个loading页面
        String accessToken = DataManager.login(userName, pw); // 调用登录接口，获取授权码
        UserInfo userInfo = DataManager.getUserInfo(accessToken); //  通过授权码，获取用户信息
        mUserView.showUserInfo(userInfo); // 更新UI
    }

    public void logout() {
        // 清除当前用户缓存
        // 。。。。
        mUserView.logout();
    }


}
