package com.example.hello;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CommentItemView extends LinearLayout{
    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    RatingBar ratingBar;
    ImageView imageView;

    public CommentItemView(Context context) {
        super(context);

        init(context);
    }

    public CommentItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.comment_item_view, this, true);

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        imageView = (ImageView) findViewById((R.id.imageView));
    }

    public void setId(String id){
        textView.setText(id);
    }

    public void setMinutes(int minutes){
        textView2.setText(String.valueOf(minutes));
    }

    public void setContents(String contents){
        textView3.setText(contents);
    }

    public void setLikes(int likes){
        textView4.setText(String.valueOf(likes));
    }

    public void setRatingBar(float rating){
        ratingBar.setRating(rating);
    }

    public void setImage(int resId){
        imageView.setImageResource(resId);
    }
}