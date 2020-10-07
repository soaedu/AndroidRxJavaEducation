package edu.sostrovsky.androidrxjavaedu.code.create;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class ObservableCreateFromArray {
    public static void print() {
        printThreeItemsFromArray();
    }

    public static void printThreeItemsFromArray() {
        List<String> letters = new ArrayList<>();
        letters.add("S");
        letters.add("O");
        letters.add("S");

        Observable.fromArray(letters)
                .subscribe(System.out::println);
    }
}
