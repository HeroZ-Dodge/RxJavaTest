package com.zsx.rxjavatest.ui.activity.impl;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cgutech.obuhelper.ObuMainActivity;
import com.zsx.rxjavatest.R;
import com.zsx.rxjavatest.presenter.impl.TestOnePresenter;
import com.zsx.rxjavatest.ui.activity.ITestOneActivity;
import com.zsx.rxjavatest.ui.adapter.recycler.BaseQuickAdapter;
import com.zsx.rxjavatest.ui.adapter.recycler.ViewHolderHelper;
import com.zsx.rxjavatest.ui.layout.BaseActivity;

import java.io.FileNotFoundException;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class TestOneActivity extends BaseActivity<TestOnePresenter> implements ITestOneActivity {

    private DownloadManager mDownloadManager;
    private PtrClassicFrameLayout mPtrFrame;
    private RecyclerView mRecyclerView;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(action)) {
                startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));
            } else {
                long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                Uri uri = getDownloadManager().getUriForDownloadedFile(id);
                if (uri != null) {
                    Intent installIntent = new Intent(Intent.ACTION_VIEW);
                    installIntent.setDataAndType(uri, "application/vnd.android.package-archive");
                    installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(installIntent);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        intentFilter.addAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
        intentFilter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        registerReceiver(mBroadcastReceiver, intentFilter);
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }

    public void onButtonClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn1:
                Intent intent = new Intent(this, ObuMainActivity.class);
                startActivity(intent);
                break;

            case R.id.btn2:

//                downloadApp(false);
//                Toast.makeText(this, "download apk...false", Toast.LENGTH_SHORT).show();
                break;

            default:

                break;
        }
    }


    private DownloadManager getDownloadManager() {
        if (mDownloadManager == null) {
            mDownloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        }
        return mDownloadManager;
    }

    private void downloadApp(boolean isApk) {
        String url = "http://gdown.baidu.com/data/wisegame/12c7b45f15f68851/baiduyun_447.apk";
        Uri uri = Uri.parse(url);
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        getContext().startActivity(intent);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setVisibleInDownloadsUi(true);
        request.allowScanningByMediaScanner();
        if (isApk) {
            request.setMimeType("application/vnd.android.package-archive");
        }
        request.setDestinationInExternalFilesDir(this, null, "baiduyun_447.apk");
//        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);
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
