package edu.sostrovsky.androidrxjavaedu.observable.operator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.sostrovsky.androidrxjavaedu.MainActivity;
import edu.sostrovsky.androidrxjavaedu.R;
import edu.sostrovsky.androidrxjavaedu.observable.operator.creating.ObservableCreatingActivity;
import edu.sostrovsky.androidrxjavaedu.observable.operator.transforming.ObservableTransformingActivity;

public class ObservableOperatorActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable_operator);

        setList();
    }

    private void setList() {
        String[] rxObservableOperators = getResources().getStringArray(R.array.ObservableOperator);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1, rxObservableOperators);
        listView = (ListView) findViewById(R.id.lvOperatorList);
        listView.setAdapter(adapter);

        setClickListener();
    }

    private void setClickListener() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = null;
            Context context = ObservableOperatorActivity.this;

            switch (position) {
                case 0:
                    intent = new Intent(context, ObservableCreatingActivity.class);
                    break;
                case 1:
                    intent = new Intent(context, ObservableTransformingActivity.class);
                    break;
            }

            if (intent != null) {
                startActivity(intent);
            }
        });
    }
}