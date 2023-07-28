package com.example.quizeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class QuizeApp extends AppCompatActivity {

    private TextView optionA,optionB,optionC,optionD;
    private TextView questionnumber,question,score;
    private TextView checkout1,checkout2;
    int currentIndex=0;
    int mscore=0;
    int qn=1;

    int CurrentQuestion,CurrentOptionA,CurrentOptionB,CurrentOptionC,CurrentOptionD;
    private AnswerClass[] questionBank=new AnswerClass[]{
            new AnswerClass(R.string.question1,R.string.option_A,R.string.option_B,R.string.option_C,R.string.option_D,R.string.answer_1),
            new AnswerClass(R.string.question2,R.string.option_2A,R.string.option_2B,R.string.option_2C,R.string.option_2D,R.string.answer_2),
            new AnswerClass(R.string.question3,R.string.option_3A,R.string.option_3B,R.string.option_3C,R.string.option_3D,R.string.answer_3),
            new AnswerClass(R.string.question4,R.string.option_4A,R.string.option_4B,R.string.option_4C,R.string.option_4D,R.string.answer_4),
            new AnswerClass(R.string.question5,R.string.option_5A,R.string.option_5B,R.string.option_5C,R.string.option_5D,R.string.answer_5),
            new AnswerClass(R.string.question6,R.string.option_6A,R.string.option_6B,R.string.option_6C,R.string.option_6D,R.string.answer_6),
            new AnswerClass(R.string.question7,R.string.option_7A,R.string.option_7B,R.string.option_7C,R.string.option_7D,R.string.answer_7),
            new AnswerClass(R.string.question8,R.string.option_8A,R.string.option_8B,R.string.option_8C,R.string.option_8D,R.string.answer_8),

    };

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quize_app);
        optionA=findViewById(R.id.optionA);
        optionB=findViewById(R.id.optionB);
        optionC=findViewById(R.id.optionC);
        optionD=findViewById(R.id.optionD);

        question=findViewById(R.id.question);
        score=findViewById(R.id.score);
        questionnumber=findViewById(R.id.questionNumber);

        checkout1=findViewById(R.id.selectedOption);
        checkout2=findViewById(R.id.CorrectAnswer);



        CurrentQuestion=questionBank[currentIndex].getQuestionid();
        question.setText(CurrentQuestion);
        CurrentOptionA=questionBank[currentIndex].getOptionA();
        optionA.setText(CurrentOptionA);
        CurrentOptionB=questionBank[currentIndex].getOptionB();
        optionB.setText(CurrentOptionB);
        CurrentOptionC=questionBank[currentIndex].getOptionC();
        optionC.setText(CurrentOptionC);
        CurrentOptionD =questionBank[currentIndex].getOptionD();
        optionD.setText(CurrentOptionD);

        optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(CurrentOptionA);
                updateQuestion();

            }
        });
        optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(CurrentOptionB);
                updateQuestion();

            }
        });
        optionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(CurrentOptionC);
                updateQuestion();

            }
        });
        optionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(CurrentOptionD);
                updateQuestion();

            }
        });




    }


    private void checkAnswer(int userSelectoon) {

      int correctanswer=questionBank[currentIndex].getAnswerid();
      checkout1.setText(userSelectoon);
      checkout2.setText(correctanswer);

      String m=checkout1.getText().toString().trim();
      String n=checkout2.getText().toString().trim();

      if(m.equals(n)){
          Toast.makeText(getApplicationContext(),"right",Toast.LENGTH_SHORT).show();
          mscore=mscore+1;
      }else{
          Toast.makeText(getApplicationContext(),"Wrong",Toast.LENGTH_SHORT).show();
      }

    }
    private void updateQuestion() {

        currentIndex=(currentIndex+1)%questionBank.length;

        if(currentIndex==0){
            AlertDialog.Builder alter=new AlertDialog.Builder(this);
            alter.setTitle("Game Over");
            alter.setMessage("Your Score"+ mscore+"points");
            alter.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            alter.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mscore=0;
                     qn=1;
                    questionnumber.setText(qn+"/"+questionBank.length+"Question");
                    score.setText("score"+mscore+"/"+questionBank.length);
                }
            });
            alter.show();

        }

        CurrentQuestion=questionBank[currentIndex].getQuestionid();
        question.setText(CurrentQuestion);
        CurrentOptionA=questionBank[currentIndex].getOptionA();
        optionA.setText(CurrentOptionA);
        CurrentOptionB=questionBank[currentIndex].getOptionB();
        optionB.setText(CurrentOptionB);
        CurrentOptionC=questionBank[currentIndex].getOptionC();
        optionC.setText(CurrentOptionC);
        CurrentOptionD =questionBank[currentIndex].getOptionD();
        optionD.setText(CurrentOptionD);

        qn=qn+1;
        if(qn<=questionBank.length){
            questionnumber.setText(qn+"/"+questionBank.length+"Question");
        }
        score.setText("Score"+ mscore+"/"+questionBank.length);


    }

}