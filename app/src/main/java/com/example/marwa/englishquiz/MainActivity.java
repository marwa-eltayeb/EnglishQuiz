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

    /** Tracks the percentage for each question. */
    int percentage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /** This method is used for counting good answers. */
    String name;
    private int countGoodAnswers(){
        // Find the user's name
        EditText nameField = (EditText) findViewById(R.id.name_field);
        name = nameField.getText().toString();
        nameField.setText("");


        //Count good answers percentage
        RadioGroup question1 = (RadioGroup) findViewById(R.id.radio_group1);
        if (question1.getCheckedRadioButtonId() == R.id.question1_right_answer) {
            percentage = 20;
        }
        // Question number 2
        RadioGroup question2 = (RadioGroup) findViewById(R.id.radio_group2);
        if (question2.getCheckedRadioButtonId() == R.id.question2_right_answer) {
            percentage += 20;
        }
        // Question number 3
        EditText editText = (EditText) findViewById(R.id.question3_TextField);
        String answer = editText.getText().toString();
        answer = answer.trim();
        answer = answer.toLowerCase();
        String answer2 = getResources().getString(R.string.question3Answer);
        if (answer.equals(answer2)) {
            percentage += 20;
        }
        editText.setText("");

        // Question number 4
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkbox1);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkbox2);
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkbox3);
        CheckBox checkBox4 = (CheckBox) findViewById(R.id.checkbox4);
        if (!checkBox2.isChecked() && checkBox1.isChecked() && checkBox3.isChecked() && !checkBox4.isChecked()) {
            percentage += 20;
        }
        // Question number 5
        RadioGroup question5 = (RadioGroup) findViewById(R.id.radio_group3);
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
        Intent intent = new Intent(MainActivity.this,ResultActivity.class);
        intent = intent.putExtra(RESULT, countGoodAnswers());
        intent = intent.putExtra(NAME ,name);
        startActivity(intent);
    }

}
