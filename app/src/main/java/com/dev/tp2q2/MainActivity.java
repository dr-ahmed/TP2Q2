package com.dev.tp2q2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private Button button;
    TextView tmpTextView;
    private LinearLayout dynamicLayout;
    private ArrayList<String> textViewNames;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("textViewNames", textViewNames);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        button.setOnClickListener(this);
        textViewNames = new ArrayList<>();

        if (savedInstanceState != null)
            for (String currentString : savedInstanceState.getStringArrayList("textViewNames"))
                addTextViewToDynamicLayout(currentString);
    }

    private void initViews() {
        editText = findViewById(R.id.edittex);
        button = findViewById(R.id.button);
        dynamicLayout = findViewById(R.id.dynamicLayout);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            if (editText.getText().toString().trim().isEmpty()) {
                editText.requestFocus();
                editText.setError("Veuillez saisi un nom !");
                return;
            }

            addTextViewToDynamicLayout("Bienvenue " + editText.getText().toString());
            editText.setText("");
        }
    }

    private void addTextViewToDynamicLayout(String str) {
        tmpTextView = new TextView(this);
        tmpTextView.setTextColor(Color.BLACK);
        tmpTextView.setTextSize(16);
        tmpTextView.setText(str);
        textViewNames.add(tmpTextView.getText().toString());
        dynamicLayout.addView(tmpTextView);
    }
}
