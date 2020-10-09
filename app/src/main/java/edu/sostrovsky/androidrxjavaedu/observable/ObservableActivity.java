package edu.sostrovsky.androidrxjavaedu.observable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.sostrovsky.androidrxjavaedu.MainActivity;
import edu.sostrovsky.androidrxjavaedu.R;
import edu.sostrovsky.androidrxjavaedu.observable.operator.ObservableOperatorActivity;
import edu.sostrovsky.androidrxjavaedu.observable.type.ObservableTypeActivity;
import edu.sostrovsky.androidrxjavaedu.subject.SubjectActivity;

public class ObservableActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable);

        setList();
    }

    private void setList() {
        String[] rxObservables = getResources().getStringArray(R.array.RxObservable);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1, rxObservables);
        listView = (ListView) findViewById(R.id.lvObservableList);
        listView.setAdapter(adapter);

        setClickListener();
    }

    private void setClickListener() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = null;
            Context context = ObservableActivity.this;

            switch (position) {
                case 0:
                    intent = new Intent(context, ObservableOperatorActivity.class);
                    break;
                case 1:
                    intent = new Intent(context, ObservableTypeActivity.class);
                    break;
            }

            if (intent != null) {
                startActivity(intent);
            }
        });
    }
}