package br.edu.insper.digitaltelegraph;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final long LETTER_INTERVAL = 1500; //In milliseconds
    private static final long WORD_INTERVAL = 3000; //In milliseconds

    //MorseCode instance to get conversion dictionaries
    MorseCode morsecode = new MorseCode();

    //Create BinaryTree
    public BinaryTree bt = new BinaryTree();
    public Node[] tree = bt.getBinaryTree();
    public Node userDesiredLetter = null;
    public Node currentNode = tree[0];
    public ArrayList<String> bfs = bt.BFS();

    //Counter to check how many times user clicked
    public int clickCounter = 0;

    //Boolean to check if current selected field is phone number
    public boolean isOnPhoneNumberField = false;

    //Boolean to check if character exists before printing to screen
    boolean doesNotExist = false;

    //Define screen elements by order of appearance
    public ImageView appLogo;
    public TextView labelYourMessage;
    public ToggleButton dictionaryBtn;
    public ListView helperDictionary;
    public EditText userMessageField;
    public TextView currentLetter;
    public TextView labelPhoneNumber;
    public EditText chooseContactField;
    public Button backspace;
    public Button send;
    public Button cancel;
    public Button sendFinal;
    public EditText userInputField;
    public Button sendMessage;
    public EditText touchBlockerLeft;
    public EditText touchBlockerRight;
    public ArrayAdapter<String> adapter;
    public Toast toast;





    //Timer to keep track of interval between letters
    public CountDownTimer countdownLetter = new CountDownTimer(LETTER_INTERVAL, LETTER_INTERVAL) {
        @Override
        public void onTick(long millisUntilFinished) {
            //Do nothing because there is only one tick
        }

        @Override
        public void onFinish() {

            String letter = currentNode.getLetter();
            clickCounter = 0;
            currentNode = tree[0];
            currentLetter.setText("Current Letter:   ");

            if(doesNotExist) {
                doesNotExist = false;
                toast.setText("This character does not exist!");
                toast.show();
                return;
            }

            if(!isOnPhoneNumberField) {
                userMessageField.append(letter);
                userMessageField.setSelection(userMessageField.getText().length());
            } else {
                chooseContactField.append(letter);
                chooseContactField.setSelection(chooseContactField.getText().length());
            }
        }
    };

    //Timer to keep track of interval between words
    public CountDownTimer countdownWord = new CountDownTimer(WORD_INTERVAL, WORD_INTERVAL) {
        @Override
        public void onTick(long millisUntilFinished) {
            //DO NOTHING
        }

        @Override
        public void onFinish() {
            if(!isOnPhoneNumberField && !doesNotExist) {
                userMessageField.append(" ");
                userMessageField.setSelection(userMessageField.getText().length());
            }
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //Permission for sending sms
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if(permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 0);
        }

        //Create screen elements by order of appearance
        appLogo = (ImageView) findViewById(R.id.appLogo);
        labelYourMessage = (TextView) findViewById(R.id.labelYourMessage);
        dictionaryBtn = (ToggleButton) findViewById(R.id.dictionaryBtn);
        helperDictionary = (ListView) findViewById(R.id.helperDictionary);
        userMessageField = (EditText) findViewById(R.id.userMessageField);
        currentLetter = (TextView) findViewById(R.id.currentLetter);
        labelPhoneNumber = (TextView) findViewById(R.id.labelPhoneNumber);
        chooseContactField = (EditText) findViewById(R.id.chooseContactField);
        backspace = (Button) findViewById(R.id.backspace);
        send = (Button) findViewById(R.id.send);
        cancel = (Button) findViewById(R.id.cancel);
        sendFinal = (Button) findViewById(R.id.sendFinal);
        userInputField = (EditText) findViewById(R.id.userInputField);
        touchBlockerLeft = (EditText) findViewById(R.id.touchBlockerLeft);
        touchBlockerRight = (EditText) findViewById(R.id.touchBlockerRight);
        sendMessage = (Button) findViewById(R.id.sendMessage);
        toast = Toast.makeText(getApplicationContext(), "DEFAULT", Toast.LENGTH_SHORT);


        //Config screen elements

        //Disable any touch events on userMessageField (field that shows user typed message)
        //so keyboard is not going to show when clicked!
        userMessageField.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View w, MotionEvent m) {
                return true;
            }
        });

        chooseContactField.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View w, MotionEvent m) {
                return true;
            }
        });

        currentLetter.setText("Current Letter:   ");
        adapter = new ArrayAdapter<String>(this, R.layout.helper_dict_item, bfs);
        helperDictionary.setAdapter(adapter);
        helperDictionary.setVisibility(View.INVISIBLE);
        labelPhoneNumber.setVisibility(View.INVISIBLE);
        chooseContactField.setVisibility(View.INVISIBLE);
        touchBlockerLeft.setEnabled(false); //Make touchBlocker not accessible
        touchBlockerRight.setEnabled(false); //Make touchBlocker not accessible
        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL,0,0);



        //Click listener for the dictionary toggle button
        dictionaryBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    helperDictionary.setVisibility(View.VISIBLE);
                } else {
                    helperDictionary.setVisibility(View.INVISIBLE);
                }
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
                } else {
                    doesNotExist = true;
                }
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
                } else if(clickCounter > 1 && clickCounter <= 5) {
                    countdownLetter.cancel();
                    countdownLetter.start();
                    countdownWord.cancel();
                    countdownWord.start();
                    if (currentNode.hasChildTree("right")) {
                        currentNode = currentNode.getRightChild();
                    } else {
                        countdownWord.cancel();
                        countdownLetter.cancel();
                    }
                } else {
                    doesNotExist = true;
                }

                return true;
            }
        });

        //Click listener for the backspace button
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOnPhoneNumberField) {
                    int length = userMessageField.getText().length();
                    if (length > 0) {
                        userMessageField.getText().delete(length - 1, length);
                    }
                } else {
                    int length = chooseContactField.getText().length();
                    if (length > 0) {
                        chooseContactField.getText().delete(length - 1, length);
                    }
                }
            }
        });

        //Click listener for the send button
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout.LayoutParams backspaceLayoutParams = (LinearLayout.LayoutParams) backspace.getLayoutParams();

                if(userMessageField.getText().toString().trim().length() == 0) {
                    toast.setText("Your message is empty!");
                    toast.show();
                } else {
                    isOnPhoneNumberField = true;
                    labelPhoneNumber.setVisibility(View.VISIBLE);
                    chooseContactField.setVisibility(View.VISIBLE);
                    backspaceLayoutParams.weight = (float) 0.35;
                    send.setVisibility(View.GONE);
                    cancel.setVisibility(View.VISIBLE);
                    sendFinal.setVisibility(View.VISIBLE);
                    chooseContactField.requestFocus();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout.LayoutParams backspaceLayoutParams = (LinearLayout.LayoutParams) backspace.getLayoutParams();

                isOnPhoneNumberField = false;
                labelPhoneNumber.setVisibility(View.INVISIBLE);
                chooseContactField.setVisibility(View.INVISIBLE);
                backspace.setVisibility(View.VISIBLE);
                backspaceLayoutParams.weight = (float) 0.3;
                send.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.GONE);
                sendFinal.setVisibility(View.GONE);
                userMessageField.requestFocus();
            }
        });

        sendFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Send SMS
                SmsManager manager = SmsManager.getDefault();
                String phone = chooseContactField.getText().toString();
                if(phone.length() == 9 || phone.length() == 8) {
                    manager.sendTextMessage(phone, null, userMessageField.getText().toString(), null, null);
                    toast.setText("Message sent!");
                    toast.show();

                    LinearLayout.LayoutParams backspaceLayoutParams = (LinearLayout.LayoutParams) backspace.getLayoutParams();

                    isOnPhoneNumberField = false;
                    labelPhoneNumber.setVisibility(View.INVISIBLE);
                    chooseContactField.setVisibility(View.INVISIBLE);
                    backspace.setVisibility(View.VISIBLE);
                    backspaceLayoutParams.weight = (float) 0.3;
                    send.setVisibility(View.VISIBLE);
                    cancel.setVisibility(View.GONE);
                    sendFinal.setVisibility(View.GONE);
                    userMessageField.requestFocus();
                }
                else {
                    toast.setText("Invalid phone number!");
                    toast.show();
                }
            }
        });
    }
}