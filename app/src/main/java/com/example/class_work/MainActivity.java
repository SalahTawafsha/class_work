package com.example.class_work;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private EditText name, winner;
    private Button save;
    private boolean saved = false;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    public static final String nameField = "NAME";
    public static final String teamField = "TEAM";
    public static final boolean FLAG = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        winner = findViewById(R.id.winner);
        save = findViewById(R.id.save);
        save.setOnClickListener(e -> {
            if (!saved)
                saved = true;
        });

        pref = PreferenceManager.getDefaultSharedPreferences(this); // save data
        editor = pref.edit();   // read data

        checkDate();
    }

    private void checkDate() {
        boolean f = pref.getBoolean("FLAG", false);
        if (f) {
            String name = pref.getString(nameField, "");
            String team = pref.getString(teamField, "");
            this.name.setText(name);
            winner.setText(team);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!saved) {
            String name = String.valueOf(this.name.getText());
            String team = String.valueOf(this.winner.getText());
            if (!name.isEmpty() && !team.isEmpty()) {
                editor.putString(nameField, name);
                editor.putString(teamField, team);
                editor.putString("NAME", FLAG + "");
                editor.commit();
            }
        }
    }
}