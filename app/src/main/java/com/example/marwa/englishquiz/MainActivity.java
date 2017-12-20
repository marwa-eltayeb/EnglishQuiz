package com.example.marwa.englishquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    /**
     * Keys is used for saving and restoring values
     */
    protected static final String NAME = "name";
    protected static final String RESULT = "result";

    /**
     * Tracks the percentage for each question.
     */
    int percentage = 0;

    /**
     * Gets the user name.
     */
    EditText userName;

    /**
     * Gets question one's result.
     */
    RadioGroup question1;

    /**
     * Gets question two's result.
     */
    RadioGroup question2;

    /**
     * Gets question three's result.
     */
    EditText question3;

    /**
     * Gets question four's result.
     */
    CheckBox question4_A;
    CheckBox question4_B;
    CheckBox question4_C;
    CheckBox question4_D;

    /**
     * Gets question five's result.
     */
    RadioGroup question5;

    /**
     * stores the user name in the variable called name.
     */
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gets references using findViewById() method
        userName = (EditText) findViewById(R.id.userName);
        question1 = (RadioGroup) findViewById(R.id.question1);
        question2 = (RadioGroup) findViewById(R.id.question2);
        question3 = (EditText) findViewById(R.id.question3);
        question4_A = (CheckBox) findViewById(R.id.question4_A);
        question4_B = (CheckBox) findViewById(R.id.question4_B);
        question4_C = (CheckBox) findViewById(R.id.question4_C);
        question4_D = (CheckBox) findViewById(R.id.question4_D);
        question5 = (RadioGroup) findViewById(R.id.question5);
    }

    /**
     * This method is used for calculating correct answers.
     */
    private int calculateCorrectAnswers() {
        // Find the user name
        name = userName.getText().toString();

        // Question number 1
        if (question1.getCheckedRadioButtonId() == R.id.question1_right_answer) {
            percentage = 20;
        }

        // Question number 2
        if (question2.getCheckedRadioButtonId() == R.id.question2_right_answer) {
            percentage += 20;
        }

        // Question number 3
        String answer = question3.getText().toString().trim().toLowerCase();
        String answer2 = getResources().getString(R.string.question3Answer);
        if (answer.equals(answer2)) {
            percentage += 20;
        }

        // Question number 4
        if (!question4_B.isChecked() && question4_A.isChecked() && question4_C.isChecked() && !question4_D.isChecked()) {
            percentage += 20;
        }

        // Question number 5
        if (question5.getCheckedRadioButtonId() == R.id.question_5_right_answer) {
            percentage += 20;
        }

        // returns the results
        return percentage;
    }

    /**
     * This method is called when the Submit bg_style is clicked.
     * Opens Results activity and passes good answers' percentage
     */
    public void submitAnswers(View view) {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent = intent.putExtra(RESULT, calculateCorrectAnswers());
        intent = intent.putExtra(NAME, name);
        unCheckAllResults();
        // Resets User's result to Zero after it is submitted.
        percentage = 0;
        startActivity(intent);
    }

    /**
     * This method is called to unCheck the user name and questions
     * after all the answers are submitted.  .
     */
    private  void unCheckAllResults(){
        userName.setText("");
        question1.clearCheck();
        question2.clearCheck();
        question3.setText("");
        question4_A.setChecked(false);
        question4_B.setChecked(false);
        question4_C.setChecked(false);
        question4_D.setChecked(false);
        question5.clearCheck();
    }

}
