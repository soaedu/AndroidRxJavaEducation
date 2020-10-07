package edu.sostrovsky.androidrxjavaedu.code.operation;

import io.reactivex.Observable;

public class FlatMapOperation {
    public static void print() {
        transformDataWithFlatMap();
    }

    public static void transformDataWithFlatMap() {
        Observable.just("I am sending the sms")
                .flatMap(string -> Observable.fromArray(string.split(" ")))
                .subscribe(System.out::println);
    }
}
