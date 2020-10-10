package edu.sostrovsky.androidrxjavaedu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import edu.sostrovsky.androidrxjavaedu.subject.SubjectActivity;
import edu.sostrovsky.androidrxjavaedu.subscription.SubscriptionActivity;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setList();
    }

    private void setList() {
        String[] rxThemes = getResources().getStringArray(R.array.RxTheme);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1, rxThemes);
        listView = (ListView) findViewById(R.id.lvThemeList);
        listView.setAdapter(adapter);

        setClickListener();
    }

    private void setClickListener() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = null;
            Context context = MainActivity.this;

            switch (position) {
                case 0:
                    intent = new Intent(context, SubjectActivity.class);
                    break;
                case 1:
                    intent = new Intent(context, SubscriptionActivity.class);
                    break;
            }

            if (intent != null) {
                startActivity(intent);
            }
        });
    }
}