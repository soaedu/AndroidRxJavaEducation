package edu.sostrovsky.androidrxjavaedu.subject.publish_subject.example1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.sostrovsky.androidrxjavaedu.R;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class AlertDialogChainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog_chain);

        showChainOfDialogs();
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
}