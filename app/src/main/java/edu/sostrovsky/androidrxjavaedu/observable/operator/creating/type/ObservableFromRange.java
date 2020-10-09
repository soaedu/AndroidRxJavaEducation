package edu.sostrovsky.androidrxjavaedu.observable.operator.creating.type;

import io.reactivex.Observable;

public class ObservableFromRange {
    public static void print() {
        printThreeItemsFromRange();
    }

    public static void printThreeItemsFromRange() {
        Observable.range(1, 3)
                .subscribe(System.out::println);
    }
}
