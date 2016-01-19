package com.zsx.rxjavatest.ui.activity.impl;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zsx.rxjavatest.R;
import com.zsx.rxjavatest.ui.adapter.recycler.BaseQuickAdapter;
import com.zsx.rxjavatest.ui.adapter.recycler.ViewHolderHelper;
import com.zsx.rxjavatest.ui.layout.BaseActivity;
import com.zsx.rxjavatest.ui.view.MyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by Administrator on 2016/1/12.
 */
public class MyActivity extends BaseActivity {

    private MyRecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        final PtrClassicFrameLayout mPtrFrame = (PtrClassicFrameLayout) findViewById(R.id.ptr);

        mRecyclerView = (MyRecyclerView) findViewById(R.id.recycler_view);
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                System.out.println("onRefreshBegin");
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrame.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                int position = layoutManager.findFirstCompletelyVisibleItemPosition();
                return position == 0;
            }
        });
        // the following are default settings
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(500);
        mPtrFrame.setDurationToCloseHeader(600);
        // default is false
        mPtrFrame.setPullToRefresh(false);
        // default is true
        mPtrFrame.setKeepHeaderWhenRefresh(true);
//        mPtrFrame.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mPtrFrame.autoRefresh();
//            }
//        }, 100);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 55; i++) {
            String string = "index = " + i;
            stringList.add(string);
        }
        BaseQuickAdapter adapter = new SimpleStringAdapter(this, stringList);
        View footView = getLayoutInflater().inflate(R.layout.foot_view, null);
        footView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mRecyclerView.addFootView(footView);
        mRecyclerView.setAdapter(adapter);
    }

    public void onButtonClick(View view) {
        int id = view.getId();
        if (id == R.id.btn1) {
//            getViewExpansionDelegate().showProgressDialog("title");
            mRecyclerView.setLoadStatus(MyRecyclerView.LOADING_STATUS_DEFAULT);
        } else {
//            getViewExpansionDelegate().showErrorPage();
            mRecyclerView.setLoadStatus(MyRecyclerView.LOADING_STATUS_FINISH);
        }


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
