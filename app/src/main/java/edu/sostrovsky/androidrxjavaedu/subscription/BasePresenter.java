package edu.sostrovsky.androidrxjavaedu.subscription;

import java.util.List;

import rx.Observer;
import rx.functions.Action1;

public class BasePresenter {

    protected MainInterface.View view;

    public BasePresenter (MainInterface.View view) {
        this.view = view;
    }

    protected <T extends Object> Observer<T> getObserver() {
        return new Observer<T>() {
            @Override
            public void onCompleted() {
                showMsg("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                showMsg("onError: " + e);
            }

            @Override
            public void onNext(T s) {
                showMsg("onNext: " + s);
            }
        };
    }

    protected <T extends Object> Observer<T> getObserver(final int observerId) {
        return new Observer<T>() {
            @Override
            public void onCompleted() {
                showMsg("observer" +observerId+ " onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                showMsg("onError: " + e);
            }

            @Override
            public void onNext(T value) {
                showMsg("observer" +observerId+ " onNext value= " + value);
            }
        };
    }

    protected <T extends List<? extends Object>>  Observer<T> getListObserver() {
        return new Observer<T>() {
            @Override
            public void onCompleted() {
                showMsg("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                showMsg("onError: " + e);
            }

            @Override
            public void onNext(T s) {
                showMsg("onNext: " + s);
            }
        };
    }

    protected <T extends Object> Action1<T> getAction() {
        return new Action1<T>() {
            @Override
            public void call(T value) {
                showMsg("onNext " + value);
            }
        };
    }

    protected void showMsg(String msg) {
        if (view != null) {
            view.log(msg);
        }
    }

}
