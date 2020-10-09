package edu.sostrovsky.androidrxjavaedu.observable.operator.transforming.flatmap;

import android.annotation.SuppressLint;

import io.reactivex.Observable;

public class FlatMapOperator {
    public static void print() {
        transformDataWithFlatMap();
    }

    @SuppressLint("CheckResult")
    public static void transformDataWithFlatMap() {
        Observable.just("I am sending the sms")
                .flatMap(string -> Observable.fromArray(string.split(" ")))
                .subscribe(System.out::println);
    }
}
