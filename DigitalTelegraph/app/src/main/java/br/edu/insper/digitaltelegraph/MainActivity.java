package br.edu.insper.digitaltelegraph;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //MorseCode instance to get conversion dictionaries
    MorseCode morsecode = new MorseCode();

    //Create BinaryTree
    public BinaryTree bt = new BinaryTree();
    public Node[] tree = bt.getBinaryTree();
    public Node userDesiredLetter = null;
    public Node currentNode = tree[0];

    //Counter to check how many times user clicked
    public int clickCounter = 0;

    //Visual components by order of appearance
    public ImageView appLogo;
    public TextView labelYourMessage;
    public Button dictionaryBtn;
    public ListView helperDictionary;
    public EditText userMessageField;
    public TextView currentLetter;
    public Button backspace;
    public Button contacts;
    public EditText userInputField;
    public Button sendMessage;
    public EditText touchBlockerLeft;
    public EditText touchBlockerRight;
    public EditText touchBlocker;





    //Timer to keep track of interval between signals
    public CountDownTimer countdownLetter = new CountDownTimer(1500, 1500) {
        @Override
        public void onTick(long millisUntilFinished) {
            //Do nothing because there is only one tick
        }

        @Override
        public void onFinish() {
            String letter = userDesiredLetter.getLetter();
            clickCounter = 0;
            currentNode = tree[0];
            userMessageField.append(letter);
            userMessageField.setSelection(userMessageField.getText().length());
            currentLetter.setText("Current Letter:   ");
        }
    };

    //Timer to keep track of interval between words
    public CountDownTimer countdownWord = new CountDownTimer(3000,3000) {
        @Override
        public void onTick(long millisUntilFinished) {
            //DO NOTHING
        }

        @Override
        public void onFinish() {
            userMessageField.append(" ");
            userMessageField.setSelection(userMessageField.getText().length());
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        bt.BFS();

        //Create screen elements
        appLogo = (ImageView) findViewById(R.id.appLogo);
        labelYourMessage = (TextView) findViewById(R.id.labelYourMessage);
        dictionaryBtn = (Button) findViewById(R.id.dictionary);
        userMessageField = (EditText) findViewById(R.id.userMessageField);
        currentLetter = (TextView) findViewById(R.id.currentLetter);
        backspace = (Button) findViewById(R.id.backspace);
        contacts = (Button) findViewById(R.id.contacts);
        userInputField = (EditText) findViewById(R.id.userInputField);
        touchBlockerLeft = (EditText) findViewById(R.id.touchBlockerLeft);
        touchBlockerRight = (EditText) findViewById(R.id.touchBlockerRight);
        sendMessage = (Button) findViewById(R.id.sendMessage);


        //Config screen elements

        //Disable any touch events on userMessageField (field that shows user typed message)
        //so keyboard is not going to show when clicked!
        userMessageField.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View w, MotionEvent m) {
                return true;
            }
        });

        currentLetter.setText("Current Letter:   ");
        touchBlockerLeft.setEnabled(false); //Make touchBlocker not accessible
        touchBlockerRight.setEnabled(false); //Make touchBlocker not accessible





        //Click listener for the dictionary
        dictionaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //Click listeners for the signal sending area
        userInputField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickCounter++;
                currentLetter.append(".");

                if(clickCounter == 1) {
                    countdownLetter.start();
                    countdownWord.start();
                    currentNode = currentNode.getLeftChild();
                } else if(clickCounter > 1 && clickCounter <= 5) {
                    countdownLetter.cancel();
                    countdownLetter.start();
                    countdownWord.cancel();
                    countdownWord.start();
                    if (currentNode.hasChildTree("left")) {
                        currentNode = currentNode.getLeftChild();
                    } else {
                        countdownWord.cancel();
                        countdownLetter.cancel();
                    }
                }

                userDesiredLetter = currentNode;
            }
        });

        userInputField.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                clickCounter++;
                currentLetter.append("-");

                if(clickCounter == 1) {
                    countdownLetter.start();
                    countdownWord.start();
                    currentNode = currentNode.getRightChild();
                } else if(clickCounter > 1 && clickCounter <= 5){
                    countdownLetter.cancel();
                    countdownLetter.start();
                    countdownWord.cancel();
                    countdownWord.start();
                    if(currentNode.hasChildTree("right")) {
                        currentNode = currentNode.getRightChild();
                    } else {
                        countdownWord.cancel();
                        countdownLetter.cancel();
                    }
                }

                userDesiredLetter = currentNode;
                return true;
            }
        });

        //Click listener for the backspace button
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int length = userMessageField.getText().length();
                if (length > 0) {
                    userMessageField.getText().delete(length - 1, length);
                }
            }
        });

        //Click listener for the send button
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userMessageField.getText().toString().trim().length() == 0) {
                    Toast toast = Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT);
                    toast.setText("Your message is empty!");
                    toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL,0,0);
                    toast.show();
                }
            }
        });
    }
}