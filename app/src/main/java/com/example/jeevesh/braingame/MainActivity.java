package com.example.jeevesh.braingame;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView, scoreTextView, quesTextView,resultTextView,resulttext;
    Button startButton, button1, button2, button3, button4, playAgain;
    ArrayList<Integer> answers = new ArrayList<>();
    int correctlocation;
    int wronganswer;
    int score;
    int questions;

    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        generateQuestion();
        gameVisible();
        new CountDownTimer(30000 + 500,1000){
            public void onTick(long millisecond){
                timerTextView.setText(Integer.toString((int) millisecond / 1000));
            }
            public void onFinish(){
                Toast.makeText(MainActivity.this, "Finished", Toast.LENGTH_SHORT).show();
                timerTextView.setVisibility(View.INVISIBLE);
                scoreTextView.setVisibility(View.INVISIBLE);
                quesTextView.setVisibility(View.INVISIBLE);
                resultTextView.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                button4.setVisibility(View.INVISIBLE);
                resulttext.setVisibility(View.VISIBLE);
                resulttext.setText("Your score is: " + Integer.toString(score) + "/" + Integer.toString(questions));
                playAgain.setVisibility(View.VISIBLE);
                score=0;
                questions=0;
            }
        }.start();
    }


    public void solve(View view){

        if (view.getTag().equals(Integer.toString(correctlocation))){
            resultTextView.setText("Correct!");
            score++;
        }
        else {
            resultTextView.setText("Incorrect!");
        }
        questions++;
        generateQuestion();
    }

    public void generateQuestion(){

        Random rand = new Random();
        int a = rand.nextInt(25);
        int b = rand.nextInt(25);

        quesTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        correctlocation = rand.nextInt(4);
        answers.clear();

        for (int i=0; i<4; i++){
            if (i==correctlocation){
                answers.add(a + b);
            }
            else {
                wronganswer = rand.nextInt(50);
                while (wronganswer == a+b){
                    wronganswer = rand.nextInt(50);
                }
                answers.add(wronganswer);
            }
        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));

        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(questions));
    }

    public void playAgain(View view){

        start(view);
        gameVisible();
        resulttext.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        generateQuestion();

    }
    public void gameVisible(){

        timerTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        quesTextView.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);

        timerTextView = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        quesTextView = findViewById(R.id.quesTextView);
        resultTextView = findViewById(R.id.resultTextView);
        resulttext = findViewById(R.id.resultext);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        playAgain = findViewById(R.id.againButton);



    }
}
