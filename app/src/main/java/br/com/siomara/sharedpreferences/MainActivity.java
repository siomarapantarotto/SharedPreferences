package br.com.siomara.sharedpreferences;

import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    // Define objects
    private TextInputEditText edtName;
    private Button btnSave;
    private TextView txtMessage;

    // Define the XML file to store info as shared preferences
    private static final String SHARED_PREFERENCES_FILE = "SharedPreferencesFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link defined objects to the ones on MainActivity
        edtName = findViewById(R.id.edtName);
        btnSave = findViewById(R.id.btnSave);
        txtMessage = findViewById(R.id.txtMessage);

        // Execute when SAVE button is clicked
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // SharedPreferences creates a XML file to store few and short user info
                // Mode 0 stands for a private file, only the APP will write and read the file
                SharedPreferences preferences = getSharedPreferences(SHARED_PREFERENCES_FILE, 0);
                SharedPreferences.Editor editor = preferences.edit();

                // Checks id user informed mandatory field name to save on shared preferences
                if (edtName.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Please, inform your name.", Toast.LENGTH_LONG).show();
                } else {
                    // Stores user name on shared preferences and display message
                    String name = edtName.getText().toString();
                    editor.putString("name", name);
                    editor.commit();
                    txtMessage.setText("Hello, " + name);
                }
            }
        });

        // Retrieves info stored on shared preferences
        SharedPreferences preferences = getSharedPreferences(SHARED_PREFERENCES_FILE, 0);

        // Checks for info on shared preferences
        if (preferences.contains("name")) {
            String name = preferences.getString("name", "Sorry, user name not found.");
            txtMessage.setText("Hello, " + name);
        } else {
            txtMessage.setText("Sorry, no user stored on shared preferences.");
        }
    }
}
