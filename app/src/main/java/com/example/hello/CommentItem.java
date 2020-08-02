package com.example.hello;

public class CommentItem {
    String id;
    int minutes;
    float rating;
    String contents;
    int likes;
    int resId;

    public CommentItem(String id, int minutes, float rating, String contents, int likes, int resId){
        this.id = id;
        this.minutes = minutes;
        this.rating = rating;
        this.contents = contents;
        this.likes = likes;
        this.resId = resId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
