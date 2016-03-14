package com.zsx.rxjavatest.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.zsx.rxjavatest.R;
import com.zsx.rxjavatest.test.bean.Book;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.MainThreadSubscription;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.functions.Func1;

public class RxjavaTestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBook();
            }
        });
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
        getObservable().flatMap(new Func1<List<Book>, Observable<Book>>() {
            @Override
            public Observable<Book> call(List<Book> books) {
                if (books.size() > 100) {
                    return Observable.error(new RuntimeException("too many books"));
                } else {
                    return Observable.from(books);
                }
            }
        }).subscribe(new Subscriber<Book>() {
            @Override
            public void onCompleted() {
                Log.e("RxJava", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("RxJava", "onError");
            }

            @Override
            public void onNext(Book book) {
                Log.e("Book", book.getName());
            }
        });
    }

}
