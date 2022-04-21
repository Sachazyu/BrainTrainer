package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView resultTextView, pointTextView, sumTextView, timerTextView, startText;
    Button startBtn, button0, button1, button2, button3, playAgainButton;
    LinearLayout gameLayout;

    ArrayList <Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = (Button) findViewById(R.id.startBtn);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        startText = (TextView) findViewById(R.id.startText);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointTextView = (TextView)findViewById(R.id.pointTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        gameLayout = (LinearLayout)findViewById(R.id.gameLayout);
    }

    public void generateQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();

        int incorrectAnswer;
        for (int i=0;i<4;i++){
            if (i == locationOfCorrectAnswer){
                answers.add(a + b);
            }else{
                incorrectAnswer = rand.nextInt(41);

            while (incorrectAnswer == a + b){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view){
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Benar Bro!!");
        }else{
            resultTextView.setText("Salah Bro!!"); }

        numberOfQuestions++;

        pointTextView.setText(Integer.toString(score)+ "/" +Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public void start(View view){
        startText.setVisibility(View.INVISIBLE);
        startBtn.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(LinearLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    public void playAgain(View view){
        score=0;
        numberOfQuestions=0;

        timerTextView.setText("30s");
        pointTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score " + Integer.toString(score)+ "/" +Integer.toString(numberOfQuestions));
            }
        }
        .start();
    }
}