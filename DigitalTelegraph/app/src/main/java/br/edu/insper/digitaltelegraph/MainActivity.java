package br.edu.insper.digitaltelegraph;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Instance of MorseCode to access conversion dictionaries
    MorseCode morsecode = new MorseCode();

    //Create BinaryTree
    public BinaryTree bt = new BinaryTree();
    public Node[] tree = bt.getBinaryTree();
    public Node userDesiredLetter = null;
    public Node currentNode = tree[0];

    //Counter to check how many times user clicked
    public int clickCounter = 0;

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


    //Timer to keep track of interval between signals
    public CountDownTimer countdown = new CountDownTimer(1500, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            //Do nothing because there is only one tick
        }

        @Override
        public void onFinish() {
            String letter = userDesiredLetter.getLetter();
            Log.d("PRINTED_LETTER", letter);
            clickCounter = 0;
            currentNode = tree[0];
            EditText msgField = userMessageField;
            msgField.setText(msgField.getText() + letter);
        }
    };

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
                if(clickCounter == 0) {
                    countdown.start();
                    clickCounter++;
                    currentNode = currentNode.getLeftChild();
                } else if(clickCounter >= 1){
                    countdown.cancel();
                    countdown.start();
                    if(currentNode.hasChildTree("left")) {
                        currentNode = currentNode.getLeftChild();
                    }
                }

                userDesiredLetter = currentNode;

                MainActivity.this.confirmLetter.setEnabled(true);
                MainActivity.this.confirmWord.setEnabled(true);
            }
        });
//
        userInputField.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(clickCounter == 0) {
                    countdown.start();
                    clickCounter++;
                    currentNode = currentNode.getRightChild();
                } else if(clickCounter >= 1){
                    countdown.cancel();
                    countdown.start();
                    if(currentNode.hasChildTree("right")) {
                        currentNode = currentNode.getRightChild();
                    }
                }

                userDesiredLetter = currentNode;

                MainActivity.this.confirmLetter.setEnabled(true);
                MainActivity.this.confirmWord.setEnabled(true);
                return true;
            }
        });

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText msgField = MainActivity.this.userMessageField;

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
    }
}
