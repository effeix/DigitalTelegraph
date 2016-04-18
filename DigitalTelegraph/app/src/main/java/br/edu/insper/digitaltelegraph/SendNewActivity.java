package br.edu.insper.digitaltelegraph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SendNewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_new);

        Button addCharacter = (Button) findViewById(R.id.addChar);
        Button send = (Button) findViewById(R.id.send);

        TextView sendNewTitle = (TextView) findViewById(R.id.sendNewTitle);
        EditText message = (EditText) findViewById(R.id.message);
        message.setEnabled(false);

        addCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SendNewActivity.this, AddCharActivity.class);
                startActivity(intent);

            }
        });
    }
}
