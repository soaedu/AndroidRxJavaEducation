package edu.sostrovsky.androidrxjavaedu.observable.operator.creating;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.sostrovsky.androidrxjavaedu.R;

public class ObservableCreatingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable_creating);

        // ObservableFromJust.print();
        // ObservableFromArray.print();
        // ObservableFromIterable.print();
        // ObservableFromCallable.print();
        // ObservableFromRange.print();
        // ObservableFromEmitter.print();
    }
}