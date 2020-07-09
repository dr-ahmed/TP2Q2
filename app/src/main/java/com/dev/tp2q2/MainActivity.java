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
    private TextView tmpTextView;
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
            if (!isEditTextEmpty()) {
                addTextViewToDynamicLayout("Bienvenue " + editText.getText().toString());
                editText.setText("");
            }
        }
    }

    private boolean isEditTextEmpty() {
        if (editText.getText().toString().trim().isEmpty()) {
            editText.requestFocus();
            editText.setError("Veuillez saisi un nom !");
            return true;
        }
        return false;
    }

    private void addTextViewToDynamicLayout(String str) {
        tmpTextView = new TextView(this);
        tmpTextView.setTextColor(Color.BLACK);
        tmpTextView.setTextSize(16);
        tmpTextView.setText(str);
        dynamicLayout.addView(tmpTextView);
        // Si on enlève cette instrcution de cette méthode et on l'ajoute après l'appel d'addTextViewToDynamicLayout dans la méthode OnClick,
        // lors de rotation, l'application pourra sauvegarder les données lors d'une seule rotation ! Pourqquoi ?
        // Parce qu'à chauque rotation, on récupère et on affiche dans le layout le contenu de textViewNames précédent mais on réinitialise l'ArrayList,
        // De ce fait, lors de la prochain rotation, l'ArrayList sera vide car elle vient d'etre réinitialisée !
        // Plus précisement, le contenu qui sera sauvegardé lors de la rotation est uniquement ce qui est ajouté au dynamicLayout après la rotation mais pas tout son contenu
        textViewNames.add(tmpTextView.getText().toString());
    }
}
