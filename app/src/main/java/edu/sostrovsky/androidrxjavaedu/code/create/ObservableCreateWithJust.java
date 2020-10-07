package edu.sostrovsky.androidrxjavaedu.code.create;

import io.reactivex.Observable;

public class ObservableCreateWithJust {
    public static void print() {
        // ObservableJust.printOneString();
        // ObservableJust.printThreeStrings();
        // ObservableJust.printThreeStringsShowError();
        // ObservableJust.printThreeStringsShowComplete();
        // ObservableJust.printThreeStringsConcatenated();
    }

    // will produce one strings
    public static void printOneString() {
        Observable.just("Hello World").subscribe(System.out::println);
    }

    // will produce three strings
    public static void printThreeStrings() {
        // will produce three strings
        Observable.just("Hello", "World", "!!!")
                .subscribe(System.out::println);
    }

    // will produce three strings
    // pass two params
    public static void printThreeStringsShowError() {
        // will produce three strings
        Observable.just("Hello", "World", "!!!")
                .subscribe(System.out::println, System.out::println);
    }

    // will produce three strings
    // pass three params
    public static void printThreeStringsShowComplete() {
        // will produce three strings
        Observable.just("Hello", "World", "!!!")
                .subscribe(System.out::println, System.out::println,
                        () -> System.out.println("Completed!"));
    }

    // will produce three strings but concatenated to one
    public static void printThreeStringsConcatenated() {
        Observable.just("S", "O", "S")
                .reduce((a, b) -> (a + b))
                .subscribe(System.out::println);
    }
}
