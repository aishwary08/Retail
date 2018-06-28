package com.software.beacon;

public class ViewFeedbackItem {

    private String review;
    private String author;
    private int rating;


    public ViewFeedbackItem(String review, String author, int rating){
        this.review = review;
        this.author = author;
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
