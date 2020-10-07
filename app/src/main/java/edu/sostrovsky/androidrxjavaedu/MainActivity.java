package edu.sostrovsky.androidrxjavaedu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ObservableJust.print();
        // ObservableFromArray.print();
        // ObservableFromIterable.print();
        // ObservableCreateFromCallable.print();
        // ObservableFromRange.print();
        // ObservableCreateFromEmitter.print();

        // FlatMapOperation.print();

        // showChainOfDialogs();

        handleSearchTextChange();
    }

    private void showChainOfDialogs() {
        showDialog("First question", "Yes", "No")
                .flatMap(index -> showDialog("Second question", "Yes", "No"))
                .flatMap(index -> showDialog("Third question", "Yes", "No"))
                .subscribe();
    }

    Observable<Integer> showDialog(String title, String positive, String negative) {
        PublishSubject<Integer> publishSubject = PublishSubject.create();

        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setPositiveButton(positive, (view, index) -> publishSubject.onNext(index))
                .setNegativeButton(negative, (view, index) -> publishSubject.onNext(index));

        return publishSubject
                .hide() // nobody can put on it some data, that we do not need
                .doOnSubscribe(disp -> dialog.show());

    }

    @SuppressLint("CheckResult")
    private void handleSearchTextChange() {
        RecyclerView recyclerView = findViewById(R.id.rvSearchResult);
        PublishSubject<String> edtTextChangesSubject = PublishSubject.create();

        EditText edtSearch = findViewById(R.id.edtSearch);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edtTextChangesSubject.onNext(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        edtTextChangesSubject
                .map(string -> new MyAdapter(Arrays.asList(string.split(" "))))
                .subscribe(recyclerView::setAdapter);
    }
}