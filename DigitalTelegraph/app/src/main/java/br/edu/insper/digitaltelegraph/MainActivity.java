package br.edu.insper.digitaltelegraph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView appTitle = (TextView) findViewById(R.id.appTitle);
        Button sendNew = (Button) findViewById(R.id.sendNew);

        sendNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SendNewActivity.class);
                startActivity(intent);

            }
        });
    }
}
