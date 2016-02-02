package com.zsx.rxjavatest.ui.activity.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zsx.rxjavatest.R;
import com.zsx.rxjavatest.presenter.impl.TestTwoPresenter;
import com.zsx.rxjavatest.ui.activity.ITestTwoActivity;
import com.zsx.rxjavatest.ui.layout.BaseActivity;

public class TestTwoActivity extends BaseActivity<TestTwoPresenter> implements ITestTwoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Button button = (Button) findViewById(R.id.btn1);
        button.setText("第二个Activity");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().getList();
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onTest() {
        Toast.makeText(this, "finish", Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Override
    protected TestTwoPresenter createPresenter() {
        TestTwoPresenter presenter = new TestTwoPresenter();
        presenter.attachView(this);
        return presenter;
    }
}
