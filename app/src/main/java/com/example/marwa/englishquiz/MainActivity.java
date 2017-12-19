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

    EditText nameField;
    RadioGroup question1;
    RadioGroup question2;
    EditText question3;
    CheckBox question4_A;
    CheckBox question4_B;
    CheckBox question4_C;
    CheckBox question4_D;
    RadioGroup question5;

    String name;
    
    /**
     * This method is used for counting good answers.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = (EditText) findViewById(R.id.name_field);
        question1 = (RadioGroup) findViewById(R.id.radio_group1);
        question2 = (RadioGroup) findViewById(R.id.radio_group2);
        question3 = (EditText) findViewById(R.id.question3_TextField);
        question4_A = (CheckBox) findViewById(R.id.checkbox1);
        question4_B = (CheckBox) findViewById(R.id.checkbox2);
        question4_C = (CheckBox) findViewById(R.id.checkbox3);
        question4_D = (CheckBox) findViewById(R.id.checkbox4);
        question5 = (RadioGroup) findViewById(R.id.radio_group3);
    }

    private int countGoodAnswers() {

        // Find the user's name
        name = nameField.getText().toString();
        nameField.setText("");

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
        question3.setText("");

        // Question number 4
        if (!question4_B.isChecked() && question4_A.isChecked() && question4_C.isChecked() && !question4_D.isChecked()) {
            percentage += 20;
        }

        // Question number 5
        if (question5.getCheckedRadioButtonId() == R.id.question_5_right_answer) {
            percentage += 20;
        }

        // return the results
        return percentage;
    }

    /**
     * This method is called when the Submit button is clicked.
     * Open Results activity and pass good answers' percentage
     */
    public void submitAnswers(View view) {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent = intent.putExtra(RESULT, countGoodAnswers());
        intent = intent.putExtra(NAME, name);
        percentage = 0;
        startActivity(intent);
    }

}
