package edu.sostrovsky.androidrxjavaedu.observable.operator.transforming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.sostrovsky.androidrxjavaedu.R;
import edu.sostrovsky.androidrxjavaedu.observable.operator.transforming.flatmap.FlatMapOperator;

public class ObservableTransformingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable_transforming);

        FlatMapOperator.print();
    }
}