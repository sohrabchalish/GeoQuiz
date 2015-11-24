package android.bignerdranch.com.geoquiz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends ActionBarActivity {
    private boolean mIsCheater ;
    public static final String SAVE_KEY = "index";

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data== null) {
        }
        else{
            mIsCheater =  data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN , false) ;
        }
    }
    void checkAnswer(boolean userPressedTrue) {
        boolean isTrueAnswer = mQuestionBank[mCurrentIndex].isTrueQuestion();
        int resIdM = 0;
        if (mIsCheater) resIdM = R.string.judgementToast;
        else {
            if (userPressedTrue == isTrueAnswer) {
                resIdM = R.string.correct_toast;
            } else {
                resIdM = R.string.wrong_toast;
            }

            Toast t1 = Toast.makeText(this, resIdM, Toast.LENGTH_LONG);
            t1.show();

        }
    }

    public void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);

    }

    private Button mtruebutton;
    private Button mfalsebutton;
    private Button mNextButton;
    private Button mPrevButton;
    private Button mCheatButton ;

    private TextView mQuestionTextView;
    final TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_esfahan, true),
            new TrueFalse(R.string.question_tehran, true),
            new TrueFalse(R.string.question_kermanshah, true),
            new TrueFalse(R.string.question_sari, false),
            new TrueFalse(R.string.question_mashhad, false),
    };

    int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(SAVE_KEY, 0);
        }
        setContentView(R.layout.activity_quiz);


        // ********************************************
       mNextButton = (Button) findViewById(R.id.nextbutton);
        mtruebutton = (Button) findViewById(R.id.true_button);
        mfalsebutton = (Button) findViewById(R.id.false_button);
        mCheatButton = (Button) findViewById(R.id.CheatButton) ;
        mtruebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // does nothing but will soon !
                checkAnswer(true);
            }
        });
        mfalsebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                int question = mQuestionBank[mCurrentIndex].getQuestion();

                mQuestionTextView.setText(question);
                mIsCheater = false ;
                updateQuestion();
            }


        });
        mPrevButton = (Button) findViewById(R.id.prevbutton);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuestionBank[mCurrentIndex].getQuestion() == R.string.question_esfahan) {
                    //do nothing
                } else {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                    int question = mQuestionBank[mCurrentIndex].getQuestion();
                    mQuestionTextView.setText(question);
                    updateQuestion();
                }

            }
        });


        updateQuestion();
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

          Intent cheatStarter = new Intent(QuizActivity.this , CheatActivity.class) ;
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion() ;
                cheatStarter.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE , answerIsTrue) ;
                startActivityForResult(cheatStarter , 0 );
            }
        });
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(SAVE_KEY, mCurrentIndex);
    }

}






