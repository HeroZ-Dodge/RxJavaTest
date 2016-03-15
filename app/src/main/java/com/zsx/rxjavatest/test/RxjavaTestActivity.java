package com.zsx.rxjavatest.test;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zsx.rxjavatest.R;
import com.zsx.rxjavatest.test.bean.Book;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.MainThreadSubscription;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxjavaTestActivity extends AppCompatActivity {

    private Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBook();
//                mDialog.show();
            }
        });
        mDialog = new AlertDialog.Builder(this).setTitle("正在加载...").setMessage("15555").create();
    }

    private Observable<List<Book>> getObservable() {
        return Observable.just(getBooks());
    }

    private List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setName("Book Name = " + i);
            book.setPrice("Price = " + 100 + i);
            books.add(book);
        }
        return books;
    }


    private void getBook() {
        getObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<List<Book>, Observable<Book>>() {
                    @Override
                    public Observable<Book> call(List<Book> books) {
                        if (books.size() > 100) {
                            return Observable.error(new RuntimeException("too many books"));
                        } else {
                            Log.e("RxJava", "sleep");
                            return Observable.from(books);
                        }
                    }
                })
                .subscribe(new Subscriber<Book>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        Toast.makeText(RxjavaTestActivity.this, "start", Toast.LENGTH_SHORT).show();
                        Log.e("RxJava", "onStart");
                        mDialog.show();
                    }

                    @Override
                    public void onCompleted() {
                        Log.e("RxJava", "onCompleted");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mDialog.dismiss();
                            }
                        },3000);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("RxJava", "onError");
                        mDialog.dismiss();
                    }

                    @Override
                    public void onNext(Book book) {
                        Log.e("Book", book.getName());
                    }
                });
    }

}
