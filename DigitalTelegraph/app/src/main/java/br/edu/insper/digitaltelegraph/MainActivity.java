package br.edu.insper.digitaltelegraph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Instance of MorseCode to access conversion dictionaries
    MorseCode morsecode = new MorseCode();

    //List to store user inputs of dots and dashes
    ArrayList<String> userInput = new ArrayList<>();

    //Visual components by order of appearance
    TextView labelMessage;
    EditText userMessageField;
    TextView currentLetter;
    TextView labelEnterSignalHere;
    Button confirmLetter;
    Button backspace;
    Button confirmWord;
    EditText userInputField;
    Button sendMessage;
    EditText touchBlockerLeft;
    EditText touchBlockerRight;
    EditText touchBlocker;

    //Toast for user action feedback
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //Create screen elements
        labelMessage = (TextView) findViewById(R.id.labelMessage);
        userMessageField = (EditText) findViewById(R.id.userMessageField);
        currentLetter = (TextView) findViewById(R.id.currentLetter);
        labelEnterSignalHere = (TextView) findViewById(R.id.labelEnterSignalHere);
        confirmLetter = (Button) findViewById(R.id.confirmLetter);
        backspace = (Button) findViewById(R.id.backspace);
        confirmWord = (Button) findViewById(R.id.confirmWord);
        userInputField = (EditText) findViewById(R.id.userInputField);
        sendMessage = (Button) findViewById(R.id.sendMessage);
        touchBlockerLeft = (EditText) findViewById(R.id.touchBlockerLeft);
        touchBlockerRight = (EditText) findViewById(R.id.touchBlockerRight);
        touchBlocker = (EditText) findViewById(R.id.touchBlocker);
        toast = Toast.makeText(MainActivity.this, "DEFAULT", Toast.LENGTH_SHORT);

        //Config screen elements
        userMessageField.setEnabled(false); //Make user message read-only
        userMessageField.setText("");
        currentLetter.setText("Current Letter:   ");
        confirmLetter.setEnabled(false);
        confirmWord.setEnabled(false);
        sendMessage.setEnabled(false);
        touchBlockerLeft.setEnabled(false); //Make touchBlocker not accessible
        touchBlockerRight.setEnabled(false); //Make touchBlocker not accessible
        touchBlocker.setEnabled(false);  //Make touchBlocker not accessible
        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL,0,0);




        //Click listeners for the signal sending area
        userInputField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.confirmLetter.setEnabled(true);
                MainActivity.this.confirmWord.setEnabled(true);

                String dot = ".";
                userInput.add(dot);
                TextView cLetter = MainActivity.this.currentLetter;
                cLetter.append(dot);
                //String userInputJoined = TextUtils.join("", userInput);
                //userInput.clear();
                //String letter = morsecode.getLetterFromMorseCode(userInputJoined);
                //EditText msgField = SendNewActivity.this.message;
                //msgField.setText(msgField.getText()+letter);
            }
        });

        userInputField.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MainActivity.this.confirmLetter.setEnabled(true);
                MainActivity.this.confirmWord.setEnabled(true);

                String dash = "-";
                userInput.add(dash);
                TextView cLetter = MainActivity.this.currentLetter;
                cLetter.append(dash);
                return true;
            }
        });




        //Click listeners for the confirm buttons
        confirmLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.this.sendMessage.setEnabled(true);

                if(userInput.isEmpty()) {
                    Toast toast = MainActivity.this.toast;
                    toast.setText("Please insert a letter!");
                    toast.show();
                    return;
                }

                String userInputJoined = TextUtils.join("", userInput);
                userInput.clear();
                String letter = morsecode.getLetterFromMorseCode(userInputJoined);

                if(letter == null) {
                    Toast toast = MainActivity.this.toast;
                    toast.setText("This character does not exist!");
                    toast.show();
                    return;
                }

                EditText msgField = MainActivity.this.userMessageField;
                msgField.append(letter);
                TextView cLetter = MainActivity.this.currentLetter;
                cLetter.setText("Current Letter:   ");
            }
        });

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText msgField = MainActivity.this.userMessageField;
                userInput.clear();
                currentLetter.setText("Current Letter:   ");

                int length = msgField.getText().length();
                if (length > 0) {
                    msgField.getText().delete(length - 1, length);
                    Toast toast = MainActivity.this.toast;
                    toast.setText("Last character erased!");
                    toast.show();
                }

                if(msgField.getText().toString().trim().length() == 0) {
                    MainActivity.this.confirmLetter.setEnabled(false);
                    MainActivity.this.confirmWord.setEnabled(false);
                    MainActivity.this.sendMessage.setEnabled(false);
                }
            }
        });

        confirmWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText msgField = MainActivity.this.userMessageField;
                int length = msgField.getText().length();
                if (length == 0) {
                    return;
                }
                String spacing = " ";
                msgField.append(spacing);
            }
        });




    }
}
