package com.example.mytranslator;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class TypeTextActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type_text);
        Toolbar toolbar = (Toolbar) findViewById(R.id.include);

        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }
//        textView = findViewById(R.id.textView);
    }

}
