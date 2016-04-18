package br.edu.insper.digitaltelegraph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class AddCharActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_char);

        TextView addCharTitle = (TextView) findViewById(R.id.addCharTitle);
        TextView addCharDescription = (TextView) findViewById(R.id.addCharDesc);

        EditText signalInput = (EditText) findViewById(R.id.signalInput);
    }
}
