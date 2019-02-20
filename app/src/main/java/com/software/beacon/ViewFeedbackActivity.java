package com.software.beacon;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewFeedbackActivity extends AppCompatActivity implements ValidationResponse{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private PopupWindow mPopup;
    private List<ViewFeedbackItem> listItems;
    private RatingBar mRatingBar;
    private TextView textRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);

        Get_Result conn = new Get_Result(this);
        conn.delegate = ViewFeedbackActivity.this;
        String query = "select name,rating,comment from user_feedback,users where users.mobile=user_feedback.user_id;";
        conn.execute(URLS.Fetch_Product_URL, query);

        recyclerView = (RecyclerView) findViewById(R.id.viewfeedback_recycler_view);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mRatingBar = (RatingBar) findViewById(R.id.ratingBarMain);
        textRating = (TextView) findViewById(R.id.totalStar);

        listItems = new ArrayList<>();
        adapter = new ViewFeedbackAdapter(listItems, this);
        recyclerView.setAdapter(adapter);

        showProgress(true);
    }

    @Override
    public void response(boolean result, String s) {
        showProgress(false);
        int sumRating = 0;
        double totRating = 0.0;
        if (result) {
            try {
                JSONArray data = new JSONArray(s);
                for (int i = 0; i < data.length(); i++) {
                    JSONObject detail = data.getJSONObject(i);
                        ViewFeedbackItem listItem = new ViewFeedbackItem(detail.getString("comment"), detail.getString("name"), detail.getInt("rating"));
                        sumRating += detail.getInt("rating");
                        listItems.add(listItem);
                    Log.e("dopost","aish"+s);
                }
                totRating = (double) sumRating/data.length();
                textRating.setText(String.format("%.1f", totRating)+textRating.getText());
                mRatingBar.setRating((float)totRating);
                adapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
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
            //mPopup.showAtLocation(findViewById(R.id.deals_offers), Gravity.CENTER, 0, 0);
            findViewById(R.id.view_feedback).post(new Runnable() {
                public void run() {
                    mPopup.showAtLocation(findViewById(R.id.view_feedback), Gravity.CENTER, 0, 0);
                }
            });
        } else
            mPopup.dismiss();
    }
}
