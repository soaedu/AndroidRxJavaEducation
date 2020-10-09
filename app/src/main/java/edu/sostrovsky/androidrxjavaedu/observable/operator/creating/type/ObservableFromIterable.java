package edu.sostrovsky.androidrxjavaedu.observable.operator.creating.type;

import java.util.Set;
import java.util.TreeSet;

import io.reactivex.Observable;

public class ObservableFromIterable {
    public static void print() {
        printThreeItemsFromArray();
    }

    public static void printThreeItemsFromArray() {
        Set<Integer> numbers = new TreeSet<>();
        numbers.add(21);
        numbers.add(56);
        numbers.add(102);

        Observable.fromIterable(numbers)
                .subscribe(System.out::println);
    }
}