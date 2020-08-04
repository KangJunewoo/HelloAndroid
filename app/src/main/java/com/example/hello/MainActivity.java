package com.example.hello;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RatingBar ratingBar;
    TextView outputView;

    // 클래스 안에서 언제든 접근할 수 있도록 하려면 앞쪽에 선언.
    TextView likeCountView;
    TextView dislikeCountView;
    Button likeButton;
    Button dislikeButton;
    Button yemaeButton;
    Button facebookButton;
    Button kakaoButton;
    Button moduButton;
    Button jakseongButton;

    int likeCount = 34;
    int dislikeCount = 12;
    boolean likeState = false;
    boolean dislikeState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 인플레이션 과정, 이후 findViewById 사용 가능.

        ratingBar = (RatingBar) findViewById(R.id.ratingBar2);
        outputView = (TextView) findViewById(R.id.outputView);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showCommentWriteActivity();
            }
        });
        // 상관없다면 여기서 선언.
        // Button likeButton = (Button) findViewById(R.id.likeButton);

        likeButton = (Button) findViewById(R.id.likeButton);
        dislikeButton = (Button) findViewById(R.id.dislikeButton);

        yemaeButton = (Button) findViewById(R.id.yemaeButton);
        moduButton = (Button) findViewById(R.id.moduButton);
        jakseongButton = (Button) findViewById(R.id.jakseongButton);
        facebookButton = (Button) findViewById(R.id.facebookButton);
        kakaoButton = (Button) findViewById(R.id.kakaoButton);


        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // onclick시 4가지 경우
                // 좋o싫x : 좋x
                // 좋x싫o : 좋o싫x
                // 좋x싫x : 좋o
                if(likeState){
                    decrLikeCount();
                } else{
                    incrLikeCount();
                    if(dislikeState){
                        decrDislikeCount();
                        dislikeState = false;
                    }
                }

                likeState = !likeState;
            }
        });

        dislikeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(dislikeState){
                    decrDislikeCount();
                } else{
                    incrDislikeCount();
                    if(likeState){
                        decrLikeCount();
                        likeState = false;
                    }
                }

                dislikeState = !dislikeState;
            }
        });

        yemaeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onYemaeButtonClicked(v);
            }
        });

        moduButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onModuButtonClicked(v);
            }
        });

        jakseongButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onJakseongButtonClicked(v);
            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onFacebookButtonClicked(v);
            }
        });

        kakaoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onKakaoButtonClicked(v);
            }
        });

        likeCountView = (TextView) findViewById(R.id.likeCountView);
        dislikeCountView = (TextView) findViewById(R.id.dislikeCountView);

        ListView listView = (ListView) findViewById(R.id.listView);
        CommentAdapter adapter = new CommentAdapter();

        adapter.addItem(new CommentItem("brianjune", 20, (float) 8.9, "넘나재밌어요", 4, R.drawable.user1));
        adapter.addItem(new CommentItem("junewoo98", 10, (float) 2.3, "형편없어요 ㅜㅜ", 5, R.drawable.user1));

        listView.setAdapter(adapter);

    }

    public void showCommentWriteActivity(){
        float rating = ratingBar.getRating();

        Intent intent = new Intent(getApplicationContext(), CommentWriteActivity.class);
        intent.putExtra("rating", rating);
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 101){
            if(intent != null){
                String contents = intent.getStringExtra("contents");
                outputView.setText(contents);
            }
        }

    }

    class CommentAdapter extends BaseAdapter {
        ArrayList<CommentItem> items = new ArrayList<CommentItem>();

        @Override
        public int getCount(){
            return items.size();
        }

        public void addItem(CommentItem item){
            items.add(item);
        }

        @Override
        public Object getItem(int position){
            return items.get(position);
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup){
            CommentItemView view = new CommentItemView(getApplicationContext());
            CommentItem item = items.get(position);
            view.setId(item.getId());
            view.setImage(item.getResId());
            view.setRatingBar(item.getRating());
            view.setContents(item.getContents());
            view.setLikes(item.getLikes());
            view.setMinutes(item.getMinutes());

            return view;
        }
    }

    public void incrLikeCount(){
        likeCount += 1;
        likeCountView.setText(String.valueOf(likeCount));

        likeButton.setBackgroundResource(R.drawable.ic_thumb_up_selected);

    }

    public void decrLikeCount(){
        likeCount -= 1;
        likeCountView.setText(String.valueOf(likeCount));

        likeButton.setBackgroundResource(R.drawable.ic_thumb_up);
    }

    public void incrDislikeCount(){
        dislikeCount += 1;
        dislikeCountView.setText(String.valueOf(dislikeCount));

        dislikeButton.setBackgroundResource(R.drawable.ic_thumb_down_selected);
    }

    public void decrDislikeCount(){
        dislikeCount -= 1;
        dislikeCountView.setText(String.valueOf(dislikeCount));

        dislikeButton.setBackgroundResource(R.drawable.ic_thumb_down);
    }

    public void onYemaeButtonClicked(View v){
        Toast.makeText(getApplicationContext(), "예매버튼이 눌렸어요.", Toast.LENGTH_LONG).show();
    }

    public void onJakseongButtonClicked(View v){
        Toast.makeText(getApplicationContext(), "작성버튼이 눌렸어요.", Toast.LENGTH_LONG).show();
    }

    public void onModuButtonClicked(View v){
        Toast.makeText(getApplicationContext(), "모두보기버튼이 눌렸어요.", Toast.LENGTH_LONG).show();
    }

    public void onFacebookButtonClicked(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.facebook.com"));
        startActivity(intent);
    }

    public void onKakaoButtonClicked(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.kakao.com"));
        startActivity(intent);
    }
}

