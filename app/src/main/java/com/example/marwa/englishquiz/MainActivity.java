package com.example.marwa.englishquiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
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
    private final String QUESTION1 = "question1";
    private final String QUESTION2 = "question2";
    private final String QUESTION4_A = "question4_a";
    private final String QUESTION4_B = "question4_b";
    private final String QUESTION4_C = "question4_c";
    private final String QUESTION4_D = "question4_d";
    private final String QUESTION5 = "question5";

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


        /*
         * Gets the selections of the user.
         */
        if (savedInstanceState != null) {
            int q1 = savedInstanceState.getInt(QUESTION1);
            int q2 = savedInstanceState.getInt(QUESTION2);
            boolean q4_A = savedInstanceState.getBoolean(QUESTION4_A);
            boolean q4_B = savedInstanceState.getBoolean(QUESTION4_B);
            boolean q4_C = savedInstanceState.getBoolean(QUESTION4_C);
            boolean q4_D = savedInstanceState.getBoolean(QUESTION4_D);
            int q5 = savedInstanceState.getInt(QUESTION5);
            question1.check(q1);
            question2.check(q2);
            question4_A.setChecked(q4_A);
            question4_B.setChecked(q4_B);
            question4_C.setChecked(q4_C);
            question4_D.setChecked(q4_D);
            question5.check(q5);
        }

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
        // Saves the values of the name and result.
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
    private void unCheckAllResults() {
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

    /**
     * Saves the selections of the user.
     */
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(QUESTION1, question1.getCheckedRadioButtonId());
        outState.putInt(QUESTION2, question2.getCheckedRadioButtonId());
        outState.putBoolean(QUESTION4_A, question4_A.isChecked());
        outState.putBoolean(QUESTION4_B, question4_B.isChecked());
        outState.putBoolean(QUESTION4_C, question4_C.isChecked());
        outState.putBoolean(QUESTION4_D, question4_D.isChecked());
        outState.putInt(QUESTION5, question5.getCheckedRadioButtonId());
    }

}
