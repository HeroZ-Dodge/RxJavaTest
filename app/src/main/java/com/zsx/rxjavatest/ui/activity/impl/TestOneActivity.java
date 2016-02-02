package com.zsx.rxjavatest.ui.activity.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.zsx.rxjavatest.R;
import com.zsx.rxjavatest.presenter.impl.TestOnePresenter;
import com.zsx.rxjavatest.ui.activity.ITestOneActivity;
import com.zsx.rxjavatest.ui.adapter.recycler.BaseQuickAdapter;
import com.zsx.rxjavatest.ui.adapter.recycler.ViewHolderHelper;
import com.zsx.rxjavatest.ui.layout.BaseActivity;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class TestOneActivity extends BaseActivity<TestOnePresenter> implements ITestOneActivity {

    private PtrClassicFrameLayout mPtrFrame;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mPtrFrame = (PtrClassicFrameLayout) findViewById(R.id.ptr);
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(500);
        mPtrFrame.setDurationToCloseHeader(600);
        // default is false
        mPtrFrame.setPullToRefresh(false);
        // default is true
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view1) {
                RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
                if (layoutManager == null) {
                    return false;
                } else {
                    int count = layoutManager.getItemCount();
                    if (count > 0) {
                        if (layoutManager instanceof LinearLayoutManager) {
                            int firstPosition = ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
                            return firstPosition == 0;
                        }
                        return true;
                    } else {
                        return false;
                    }
                }
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                System.out.println("onRefreshBegin");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrame.refreshComplete();
                    }
                }, 1000);
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button button = (Button) findViewById(R.id.btn1);
        button.setText("第一个Activity");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    public void onButtonClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn1:
                Intent intent = new Intent(this, TestTwoActivity.class);
                startActivity(intent);
                break;

            case R.id.btn2:
                getPresenter().checkViewAttached();
                getPresenter().loadList();
                break;

            default:

                break;
        }
    }

    @Override
    public void onRetryClick(View v) {
        super.onRetryClick(v);
        getViewExpansionDelegate().dismissErrorPage();
    }

    @NonNull
    @Override
    protected TestOnePresenter createPresenter() {
        TestOnePresenter presenter = new TestOnePresenter();
        presenter.attachView(this);
        return presenter;
    }

    @Override
    public void showList(List<String> list) {
        SimpleStringAdapter adapter = new SimpleStringAdapter(this, list);
        mRecyclerView.setAdapter(adapter);
    }

    private class SimpleStringAdapter extends BaseQuickAdapter<String> {

        public SimpleStringAdapter(Context context, List<String> data) {
            super(context, data);
        }

        @Override
        public int getLayoutId() {
            return R.layout.list_item_text_view;
        }

        @Override
        public void onBindView(ViewHolderHelper holder, int position) {
            holder.setText(R.id.text_view, getData().get(position));
        }
    }


}