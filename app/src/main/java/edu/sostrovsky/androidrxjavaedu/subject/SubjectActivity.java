package edu.sostrovsky.androidrxjavaedu.subject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.sostrovsky.androidrxjavaedu.R;
import edu.sostrovsky.androidrxjavaedu.subject.publish_subject.PublishSubjectActivity;

public class SubjectActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        setList();
    }

    private void setList() {
        String[] rxSubjects = getResources().getStringArray(R.array.RxSubject);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1, rxSubjects);
        listView = (ListView) findViewById(R.id.lvSubjectList);
        listView.setAdapter(adapter);

        setClickListener();
    }

    private void setClickListener() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = null;
            Context context = SubjectActivity.this;

            switch (position) {
                case 0:
                    intent = new Intent(context, PublishSubjectActivity.class);
                    break;
            }

            if (intent != null) {
                startActivity(intent);
            }
        });
    }
}