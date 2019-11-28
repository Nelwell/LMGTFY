package com.example.lmgtfy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText searchText;
    private Button searchButton;
    private TextView searchConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchText = findViewById(R.id.enter_search_text);
        searchButton = findViewById(R.id.search_button);
        searchConfirmation = findViewById(R.id.show_search_text);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Read text, and use it to set the text in searchConfirmation
                String text = editable.length() == 0 ? "..." : editable.toString();
                searchConfirmation.setText(getString(R.string.search_display_text, text));

            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Read text in EditText
                String search = searchText.getText().toString();
                // Checks if two teams are entered
                if (search.isEmpty()) {
                    Toast enterTeam = Toast.makeText(MainActivity.this, "Enter some text please", Toast.LENGTH_LONG);
                    enterTeam.show();
                    return;
                }
                // Show toast
                Toast confirmation = Toast.makeText(MainActivity.this, "Searching for " + search, Toast.LENGTH_LONG);
                confirmation.show();

                // Launch web browser with Google search
                String uriString = "https://www.google.com/search?q=" + search;
                Uri uri = Uri.parse(uriString);

                Intent launchBrowserIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(launchBrowserIntent);
            }
        });

    }
}
