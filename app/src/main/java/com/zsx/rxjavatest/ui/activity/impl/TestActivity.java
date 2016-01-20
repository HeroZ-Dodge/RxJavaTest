package com.zsx.rxjavatest.ui.activity.impl;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zsx.rxjavatest.R;
import com.zsx.rxjavatest.presenter.impl.TestPresenter;
import com.zsx.rxjavatest.ui.activity.ITestActivity;
import com.zsx.rxjavatest.ui.adapter.recycler.BaseQuickAdapter;
import com.zsx.rxjavatest.ui.adapter.recycler.ViewHolderHelper;
import com.zsx.rxjavatest.ui.layout.BaseActivity;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by Administrator on 2016/1/19.
 */
public class TestActivity extends BaseActivity implements ITestActivity {

    private PtrClassicFrameLayout mPtrFrame;
    private RecyclerView mRecyclerView;
    private TestPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new TestPresenter();
        mPresenter.attachView(this);
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
    }


    public void onButtonClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn1:
                getViewExpansionDelegate().showErrorPage();
                break;

            case R.id.btn2:
//                getViewExpansionDelegate().showProgressPage();
                mPresenter.checkViewAttached();
                mPresenter.loadList();
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
