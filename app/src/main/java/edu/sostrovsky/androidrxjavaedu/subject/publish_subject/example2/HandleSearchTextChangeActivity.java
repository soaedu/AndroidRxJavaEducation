package edu.sostrovsky.androidrxjavaedu.subject.publish_subject.example2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.Arrays;

import edu.sostrovsky.androidrxjavaedu.R;
import io.reactivex.subjects.PublishSubject;

public class HandleSearchTextChangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_search_text_change);

        handleSearchTextChange();
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