package edu.sostrovsky.androidrxjavaedu.observable.operator.creating.type;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class ObservableFromEmitter {
    public static void print() {
        printThreeItemsFromEmitter();
    }

    @SuppressLint("CheckResult")
    public static void printThreeItemsFromEmitter() {
        List<String> letters = new ArrayList<>();
        letters.add("S");
        letters.add("O");
        letters.add("S");

        Observable.create(emitter -> {
            emitter.onNext(letters);
            emitter.onComplete();
        }).subscribe(System.out::println);
    }
}
