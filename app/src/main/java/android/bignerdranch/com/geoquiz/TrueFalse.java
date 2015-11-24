package android.bignerdranch.com.geoquiz;

/**
 * Created by Sohrab on 06/10/2015.
 */
public class TrueFalse {
    private int mQuestion ;
    private boolean mTrueQuestion ;



    public TrueFalse( int Question , boolean TrueQuestion){
        mQuestion = Question  ;
        mTrueQuestion = TrueQuestion ;

    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }
    public boolean isTrueQuestion() {
        return mTrueQuestion;
    }

    public void setTrueQuestion(boolean trueQuestion) {
        mTrueQuestion = trueQuestion;
    }
}
