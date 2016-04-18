package br.edu.insper.digitaltelegraph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SendNewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_new);

        Button send = (Button) findViewById(R.id.send);

        TextView sendNewTitle = (TextView) findViewById(R.id.sendNewTitle);
        TextView sendNewSignalTitle = (TextView) findViewById(R.id.sendNewSignalTitle);
        EditText message = (EditText) findViewById(R.id.message);
        EditText signalInput = (EditText) findViewById(R.id.signalInput);

        message.setEnabled(false);

        signalInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SHORT_CLICK", "Short click");
                Toast.makeText(SendNewActivity.this, "TESTE", Toast.LENGTH_SHORT).show();
            }
        });

        signalInput.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("LONG_CLICK", "Long click");
                Toast.makeText(SendNewActivity.this, "TESTE2", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
