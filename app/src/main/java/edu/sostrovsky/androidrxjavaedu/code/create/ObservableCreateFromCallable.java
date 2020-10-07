package edu.sostrovsky.androidrxjavaedu.code.create;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import io.reactivex.Observable;

public class ObservableCreateFromCallable {
    public static void print() {
        // printResultFromCallable();
        // printErrorFromCallable();
        retryInFromCallable();
    }

    public static void printResultFromCallable() {
        List<String> letters = new ArrayList<>();
        letters.add("S");
        letters.add("O");
        letters.add("S");

        Observable.fromCallable(() -> {
            /*Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your name:");
            String name = scanner.nextLine();
            return "Hello " +name;*/

            return letters;
        }).subscribe(
                System.out::println,
                System.out::println
        );
    }

    public static void printErrorFromCallable() {
        Observable.fromCallable(() -> {
            throw new Exception("Test error");
        }).subscribe(System.out::println, System.out::println);
    }

    @SuppressLint("CheckResult")
    public static void retryInFromCallable() {
        Observable.fromCallable(() -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter correct pass: ");
            String enteredValue = scanner.nextLine();
            if (enteredValue.equals("123")) {
                return "Password is good";
            } else {
                throw new Exception("Password is incorrect");
            }
        })
                .retry(3)
                .subscribe(System.out::println, System.out::println);
    }
}
