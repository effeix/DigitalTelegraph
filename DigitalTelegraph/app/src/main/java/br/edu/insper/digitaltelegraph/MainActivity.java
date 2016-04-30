package br.edu.insper.digitaltelegraph;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    Button backspace;
    EditText userInputField;
    Button sendMessage;
    EditText touchBlockerLeft;
    EditText touchBlockerRight;
    EditText touchBlocker;

    //Toast for user action feedback
    Toast toast;


    //Timer to keep track of interval between signals
    public CountDownTimer countdownLetter = new CountDownTimer(1500, 1500) {
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
            userMessageField.setText(userMessageField.getText() + letter);
        }
    };

    public CountDownTimer countdownWord = new CountDownTimer(3000,3000) {
        @Override
        public void onTick(long millisUntilFinished) {
            //DO NOTHING
        }

        @Override
        public void onFinish() {
            userMessageField.setText(userMessageField.getText() + " ");
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
        backspace = (Button) findViewById(R.id.backspace);
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
                    countdownLetter.start();
                    countdownWord.start();
                    clickCounter++;
                    currentNode = currentNode.getLeftChild();
                } else if(clickCounter >= 1 && clickCounter <= 5){
                    countdownLetter.cancel();
                    countdownLetter.start();
                    countdownWord.cancel();
                    countdownWord.start();
                    if(currentNode.hasChildTree("left")) {
                        currentNode = currentNode.getLeftChild();
                    }
                } else {
                    toast.setText("This character does not exist!");
                    toast.show();
                }

                userDesiredLetter = currentNode;
            }
        });
//
        userInputField.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(clickCounter == 0) {
                    countdownLetter.start();
                    countdownWord.start();
                    clickCounter++;
                    currentNode = currentNode.getRightChild();
                } else if(clickCounter >= 1){
                    countdownLetter.cancel();
                    countdownLetter.start();
                    countdownWord.cancel();
                    countdownWord.start();
                    if(currentNode.hasChildTree("right")) {
                        currentNode = currentNode.getRightChild();
                    }
                }

                userDesiredLetter = currentNode;
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
                    MainActivity.this.sendMessage.setEnabled(false);
                }
            }
        });
    }
}
