package edu.sostrovsky.androidrxjavaedu.subject.publish_subject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.sostrovsky.androidrxjavaedu.R;
import edu.sostrovsky.androidrxjavaedu.subject.publish_subject.example1.AlertDialogChainActivity;
import edu.sostrovsky.androidrxjavaedu.subject.publish_subject.example2.HandleSearchTextChangeActivity;

public class PublishSubjectActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_subject);

        setList();
    }

    private void setList() {
        String[] rxExamples = getResources().getStringArray(R.array.RxPublishSubject);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1, rxExamples);
        listView = (ListView) findViewById(R.id.lvPublishSubjectExampleList);
        listView.setAdapter(adapter);

        setClickListener();
    }

    private void setClickListener() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = null;
            Context context = PublishSubjectActivity.this;

            switch (position) {
                case 0:
                    intent = new Intent(context, AlertDialogChainActivity.class);
                    break;
                case 1:
                    intent = new Intent(context, HandleSearchTextChangeActivity.class);
                    break;
            }

            if (intent != null) {
                startActivity(intent);
            }
        });
    }
}