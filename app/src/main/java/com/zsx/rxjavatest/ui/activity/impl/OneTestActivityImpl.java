package com.zsx.rxjavatest.ui.activity.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zsx.rxjavatest.R;
import com.zsx.rxjavatest.presenter.impl.OneTestPresenter;
import com.zsx.rxjavatest.ui.activity.OneTestActivity;
import com.zsx.rxjavatest.ui.layout.BaseActivity;

public class OneTestActivityImpl extends BaseActivity<OneTestPresenter> implements OneTestActivity{

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

    }

    @Override
    public void onTest() {
        Toast.makeText(this, "finish", Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Override
    protected OneTestPresenter createPresenter() {
        OneTestPresenter presenter = new OneTestPresenter();
        presenter.attachView(this);
        return presenter;
    }
}
