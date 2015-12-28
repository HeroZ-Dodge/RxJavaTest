package com.zsx.rxjavatest.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.zsx.rxjavatest.R;
import com.zsx.rxjavatest.presenter.main.MainPresenter;
import com.zsx.rxjavatest.ui.base.MainMvpActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainMvpActivity {

    private MainPresenter mMainPresenter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainPresenter = new MainPresenter();
        mMainPresenter.attachView(this);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.list_view);
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPresenter.loadData();
            }
        });
    }

    @Override
    public void showRecyclerView(List<String> data) {
        MyAdapter adapter = new MyAdapter(data);
        mListView.setAdapter(adapter);
    }

    @Override
    public void showErrorView() {

    }

    private class MyAdapter extends BaseAdapter {

        private List<String> mData;

        public MyAdapter(List<String> data) {
            mData = data;
        }

        @Override
        public int getCount() {
            return mData == null ? 0 : mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = new TextView(parent.getContext());
            }
            ((TextView) convertView).setText(mData.get(position));
            return convertView;
        }
    }

}
