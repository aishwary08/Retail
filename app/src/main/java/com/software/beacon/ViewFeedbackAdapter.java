package com.software.beacon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;


public class ViewFeedbackAdapter extends RecyclerView.Adapter<ViewFeedbackAdapter.ViewHolder>{

    private List<ViewFeedbackItem> listItems;
    private Context context;

    public ViewFeedbackAdapter(List<ViewFeedbackItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewfeedback_list_item, parent, false) ;
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewFeedbackItem listItem = listItems.get(position);

        holder.myRating.setRating(listItem.getRating());
        holder.textViewAuthor.setText(listItem.getAuthor());
        holder.textViewReview.setText(listItem.getReview());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public RatingBar myRating;
        public TextView textViewAuthor, textViewReview;

        public ViewHolder(View itemView) {
            super(itemView);

            myRating = (RatingBar) itemView.findViewById(R.id.myRating);
            textViewAuthor = (TextView) itemView.findViewById(R.id.name);
            textViewReview = (TextView) itemView.findViewById(R.id.review);
        }
    }
}
