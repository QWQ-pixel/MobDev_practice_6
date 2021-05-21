package com.mirea.kuznetsova.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private EditText filename, text;
    private String save_text, saves_files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        filename = (EditText) findViewById(R.id.name_file);

        text = (EditText) findViewById(R.id.text_to_save);

        preferences = this.getSharedPreferences("all_files", Context.MODE_PRIVATE);

    }

    @Override
    protected void onStop() {
        super.onStop();

        save_text = String.valueOf(text.getText());

        saves_files = String.valueOf(filename.getText());

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(saves_files, save_text);

        editor.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Map<String, ?> allPreferences = preferences.getAll();

        Set<String> setKeys = allPreferences.keySet();

        String string = preferences.getString((String) getLastElement(setKeys), "Empty");

        text.setText(string);

    }
    public Object getLastElement(final Collection c) {
        final Iterator itr = c.iterator();
        Object lastElement = itr.next();
        while(itr.hasNext()) {
            lastElement = itr.next();
        }
        return lastElement;
    }

}