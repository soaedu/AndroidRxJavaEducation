package edu.sostrovsky.androidrxjavaedu.code.create;

import io.reactivex.Observable;

public class ObservableCreateFromRange {
    public static void print() {
        printThreeItemsFromRange();
    }

    public static void printThreeItemsFromRange() {
        Observable.range(1, 3)
                .subscribe(System.out::println);
    }
}
