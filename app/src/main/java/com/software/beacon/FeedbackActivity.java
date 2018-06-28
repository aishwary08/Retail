package com.software.beacon;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity implements ValidationResponse{

    RatingBar mRatingBar;
    TextView mRatingScale;
    EditText mFeedback;
    Button mSendFeedback;
    SessionManager session;
    private PopupWindow mPopup;
    int rating;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        mRatingScale = (TextView) findViewById(R.id.tvRatingScale);
        mFeedback = (EditText) findViewById(R.id.etFeedback);
        mSendFeedback = (Button) findViewById(R.id.btnSubmit);
        session = new SessionManager(this);

        getSupportActionBar().setHomeButtonEnabled(true);

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                rating = (int) ratingBar.getRating();
                switch (rating) {
                    case 1:
                        mRatingScale.setText("Very bad");
                        break;
                    case 2:
                        mRatingScale.setText("Need some improvement");
                        break;
                    case 3:
                        mRatingScale.setText("Good");
                        break;
                    case 4:
                        mRatingScale.setText("Great");
                        break;
                    case 5:
                        mRatingScale.setText("Awesome. I love it");
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
        });

        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFeedback.getText().toString().isEmpty()) {
                    Toast.makeText(FeedbackActivity.this, "Please fill in feedback text box", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(FeedbackActivity.this, "Thank you for sharing your feedback", Toast.LENGTH_SHORT).show();
                    Get_Result conn = new Get_Result(FeedbackActivity.this);
                    conn.delegate = FeedbackActivity.this;
                    String query = "insert into user_feedback (user_id, rating, comment) values('" +
                            session.getUserDetails().get(session.KEY_MOB) +
                            "'," + rating + ",'" + mFeedback.getText().toString() + ""  + "')";
                    Log.e("dopost", query);
                    showProgress(true);
                    conn.execute(URLS.Write_Feedback_URL, query);
                }
            }
        });
    }

    @Override
    public void response(boolean result, String s) {
        showProgress(false);
        counter++;
        if (result) {
            mFeedback.setText("");
            mRatingBar.setRating(0);

            if(counter==1){
                Get_Result conn = new Get_Result(FeedbackActivity.this);
                conn.delegate = FeedbackActivity.this;
                String query = "delete from user_cart where user_id = '" + session.getUserDetails().get(session.KEY_MOB) + "'";
                Log.e("dopost", query);
                conn.execute(URLS.Remove_From_Cart_URL, query);
            }

            if(counter==2) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Feedback");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(FeedbackActivity.this, Home.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
                builder.setCancelable(false);
                builder.setMessage("Thank for Feedback!");
                AlertDialog alert = builder.create();
                alert.show();
            }
        } else {
            Snackbar.make(findViewById(R.id.feedback), Html.fromHtml("<b> Error </b>"), Snackbar.LENGTH_INDEFINITE).show();
        }
    }

    public void showProgress(final boolean show) {
        View popupView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.my_progress, null);
        if (show) {
            Point size = new Point();
            this.getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;
            int height = size.y;
            mPopup = new PopupWindow(popupView, width, height);
            findViewById(R.id.feedback).post(new Runnable() {
                public void run() {
                    mPopup.showAtLocation(findViewById(R.id.feedback), Gravity.CENTER, 0, 0);
                }
            });
        } else
            mPopup.dismiss();
    }
}
