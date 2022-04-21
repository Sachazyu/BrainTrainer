package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView hasilTextView,pointTextView, waktuTextView, sumTextView;
    Button mulaiButton, btnjawaban1, btnjawaban2, btnjawaban3,btnjawaban4, btnmulaiKembali;
    LinearLayout LayoutPermainan;

    ArrayList<Integer> jawaban = new ArrayList<Integer>();
    int lokasiJawabanBenar;
    int score = 0;
    int numberOfQuestions = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mulaiButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        btnjawaban1 = (Button) findViewById(R.id.Button1);
        btnjawaban2 = (Button) findViewById(R.id.Button2);
        btnjawaban3 = (Button) findViewById(R.id.Button3);
        btnjawaban4 = (Button) findViewById(R.id.Button4);
        hasilTextView = (TextView) findViewById(R.id.resultTextView);
        pointTextView = (TextView) findViewById(R.id.poinTextView);
        waktuTextView = (TextView) findViewById(R.id.timeTextView);
        btnmulaiKembali = (Button) findViewById(R.id.playAgainButton);
        LayoutPermainan =(LinearLayout) findViewById(R.id.linearLayoutGame);

    }

    public void generateQuestion(){

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        lokasiJawabanBenar = rand.nextInt(4);

        jawaban.clear();

        int incorrectAnswer;

        for (int i=0;i<4;i++){
            if (i == lokasiJawabanBenar){
                jawaban.add(a + b);
            }else{
                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == a + b){
                    incorrectAnswer = rand.nextInt(41);
                }

                jawaban.add(incorrectAnswer);
            }
        }

        btnjawaban1.setText(Integer.toString(jawaban.get(0)));
        btnjawaban2.setText(Integer.toString(jawaban.get(1)));
        btnjawaban3.setText(Integer.toString(jawaban.get(2)));
        btnjawaban4.setText(Integer.toString(jawaban.get(3)));

    }

    public void MulaiPermainan(View view) {
        // untuk menampilkan dan menyembunyikan layout
        mulaiButton.setVisibility(View.INVISIBLE);
        LayoutPermainan.setVisibility(LinearLayout.VISIBLE);
        mulaiKembali(findViewById(R.id.playAgainButton));
    }

    public void pilihjawaban(View view) {
        if (view.getTag().toString().equals(Integer.toString(lokasiJawabanBenar))){
            score++;
            hasilTextView.setText("Benar Bro!");
        }else{
            hasilTextView.setText("Salah Bro!");
        }

        numberOfQuestions++;
        pointTextView.setText(Integer.toString(score)+ "/" +Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public void mulaiKembali(View view) {
        score = 0;
        numberOfQuestions = 0;

        waktuTextView.setText("30s");
        pointTextView.setText("0/0");
        hasilTextView.setText("");
        btnmulaiKembali.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30010, 1000) {
            @Override
            public void onTick(long l) {
                waktuTextView.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                btnmulaiKembali.setVisibility(View.VISIBLE);
                waktuTextView.setText("0s");
                waktuTextView.setText(" Score " + Integer.toString(score)+ "/" +Integer.toString(numberOfQuestions));
            }

        }.start();

    }

}
