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

public class CheatActivity extends ActionBarActivity {
    private boolean isAnswerTrue ;
    private TextView mTextView ;
    private Button mshowAnswerButton ;
    private boolean mIsCheater = false ;
    public static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.geoquiz.answer_is_true" ;
    public static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.geoquiz.answer_shown" ;



    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent() ;
        data.putExtra(EXTRA_ANSWER_IS_TRUE ,isAnswerShown ) ;
        setResult(RESULT_OK , data);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        setAnswerShownResult(false);

        mTextView = (TextView) findViewById(R.id.AnswerTextView);
        mshowAnswerButton = (Button) findViewById(R.id.ShowAnswerButton);
         final boolean manswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE , false);

        mshowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (manswerIsTrue) {
                    mTextView.setText(R.string.true_button);

                } else {
                    mTextView.setText(R.string.false_button);

                }
                setAnswerShownResult(true);
            }

        });
    }




}

