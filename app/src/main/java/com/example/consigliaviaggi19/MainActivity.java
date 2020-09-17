package com.example.consigliaviaggi19;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.consigliaviaggi19.fragment.SchermataHomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SchermataHomeFragment.newInstance(this))
                    .commitNow();
        }
    }
}