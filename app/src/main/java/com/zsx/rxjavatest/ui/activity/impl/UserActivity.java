package com.zsx.rxjavatest.ui.activity.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.EditText;

import com.zsx.rxjavatest.data.model.UserInfo;
import com.zsx.rxjavatest.presenter.BasePresenter;
import com.zsx.rxjavatest.presenter.impl.UserPresenter;
import com.zsx.rxjavatest.ui.activity.UserView;
import com.zsx.rxjavatest.ui.layout.BaseActivity;

/**
 * 用户信息Activity
 */
public class UserActivity extends BaseActivity implements UserView {

    private EditText mEtUserName;
    private EditText mEtPassword;

    private UserPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    public void login() {
        String userName = mEtUserName.getText().toString();
        String password = mEtPassword.getText().toString();
        mPresenter.login(userName, password);
        // View层不再关心业务如何处理，只需将必要的输入交给Presenter，
        // 让Presenter通过Model层对UI进行改变
    }

    @Override
    public void showUserInfo(UserInfo userInfo) {
        // 获取用户信息后，更新UI
    }

    @Override
    public void logout() {
        // 退出登录
    }

    @Override
    public void showProgressPage() {

    }

    @Override
    public void showErrorPage() {

    }


}
